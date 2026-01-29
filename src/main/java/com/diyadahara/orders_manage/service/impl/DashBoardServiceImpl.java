package com.diyadahara.orders_manage.service.impl;

import com.diyadahara.orders_manage.config.CustomerStatus;
import com.diyadahara.orders_manage.config.OrderStatus;
import com.diyadahara.orders_manage.model.CustomerModel;
import com.diyadahara.orders_manage.model.OrderItemModel;
import com.diyadahara.orders_manage.model.OrderModel;
import com.diyadahara.orders_manage.model.ProductModel;
import com.diyadahara.orders_manage.repo.CustomerRepo;
import com.diyadahara.orders_manage.repo.OrderItemRepo;
import com.diyadahara.orders_manage.repo.OrderRepo;
import com.diyadahara.orders_manage.repo.ProductRepo;
import com.diyadahara.orders_manage.response.BaseDashBoardResponse;
import com.diyadahara.orders_manage.service.DashBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService {
    private static final Logger logger = LoggerFactory.getLogger(DashBoardServiceImpl.class);
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final OrderItemRepo orderItemRepo;
    private final OrderRepo orderRepo;

    public DashBoardServiceImpl(CustomerRepo customerRepo, ProductRepo productRepo, OrderItemRepo orderItemRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public BaseDashBoardResponse sendAllDashBoardDetails() {
        logger.info("Method Execution Start In sendAllDashBoardDetails");
        BaseDashBoardResponse baseDashBoardResponse = new BaseDashBoardResponse();
        try{
            List<CustomerModel> allCustomers = customerRepo.findAll();
            List<ProductModel> allProduct = productRepo.findAll();
            List<OrderItemModel> allOrdersItems = orderItemRepo.findAll();
            List<OrderModel> allOrders = orderRepo.findAll();

            baseDashBoardResponse.setActiveCustomers(allCustomers.size());
            baseDashBoardResponse.setTodayOrders(allOrdersItems.size());
            baseDashBoardResponse.setAllProduct(allProduct.size());
            baseDashBoardResponse.setTotalRevenue(calculateTodayRevenue(allOrders));
            baseDashBoardResponse.setCompletedOrders(calculateSuccessOrders(allOrders));
            baseDashBoardResponse.setPendingOrders(calculatePendingOrders(allOrders));
            logger.info("Method Execution Completed In sendAllDashBoardDetails |Response={}",baseDashBoardResponse);
            return baseDashBoardResponse;
        }catch (Exception e){
            logger.info("Method Execution Completed In sendAllDashBoardDetails |Error={}",e.toString());
            baseDashBoardResponse.setMsg(e.toString());
            return baseDashBoardResponse;
        }
    }
    private double calculateTodayRevenue(List<OrderModel> customerOrders){
        double totalOrderRevenue = 0.0;
        for(OrderModel items:customerOrders){
            totalOrderRevenue+=items.getTotalPrice();
        }
        LocalDateTime dateTime = LocalDateTime.parse(customerOrders.get(0).getCreatedAt().toString());
        LocalDate date = dateTime.toLocalDate();
        System.out.println(date);
        if (date.equals(LocalDate.now())) {
            System.out.println("✅ Same date");
        }
        return totalOrderRevenue;
    }
    private int calculatePendingOrders(List<OrderModel> customerOrders){
        int totalPendingOrder = 0;
        for(OrderModel items:customerOrders){
            if(items.getStatus().equals(OrderStatus.PENDING)){
                totalPendingOrder+=1;
            }
        }
        return totalPendingOrder;
    }
    private int calculateCanselOrders(List<OrderModel> customerOrders){
        int totalCanselOrder = 0;
        for(OrderModel items:customerOrders){
            if(items.getStatus().equals(OrderStatus.SUSPENDED)){
                totalCanselOrder+=1;
            }
        }
        return totalCanselOrder;
    }
    private int calculateSuccessOrders(List<OrderModel> customerOrders){
        int totalSuccessOrder = 0;
        for(OrderModel items:customerOrders){
            if(items.getStatus().equals(OrderStatus.SOLD)){
                totalSuccessOrder+=1;
            }
        }
        return totalSuccessOrder;
    }
}
