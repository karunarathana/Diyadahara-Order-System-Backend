package com.diyadahara.orders_manage.service.impl;

import com.diyadahara.orders_manage.config.OrderStatus;
import com.diyadahara.orders_manage.dto.OrderDto;
import com.diyadahara.orders_manage.dto.OrderItemDto;
import com.diyadahara.orders_manage.model.CustomerModel;
import com.diyadahara.orders_manage.model.OrderItemModel;
import com.diyadahara.orders_manage.model.OrderModel;
import com.diyadahara.orders_manage.model.ProductModel;
import com.diyadahara.orders_manage.repo.CustomerRepo;
import com.diyadahara.orders_manage.repo.OrderItemRepo;
import com.diyadahara.orders_manage.repo.OrderRepo;
import com.diyadahara.orders_manage.repo.ProductRepo;
import com.diyadahara.orders_manage.response.BaseOrderResponse;
import com.diyadahara.orders_manage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepo orderRepo, OrderItemRepo orderItemRepo, CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    @Override
    public String createOrder(OrderDto orderDto) {
        logger.info("Method Execution Started IN createOrder |OrderDto={}", orderDto);
        try {
            List<OrderModel> orderModels = orderRepo.existsByCustomer(String.valueOf(orderDto.getCustomerId()));
            if(orderModels.isEmpty()){
                OrderModel saveOrder = orderRepo.save(genarateOrderModel(orderDto));
                orderItemRepo.saveAll(generateOrderItemModel(orderDto, saveOrder));
                if (saveOrder != null) {
                    return "Order Placed Successfully";
                }
                return "Oops some error";
            }
            orderItemRepo.saveAll(upgradeOrderItemModel(orderDto, orderModels.get(0)));
            return "Order Placed Successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseOrderResponse viewOrderByCustomerPhoneNumber(String customerPhoneNumber) {
        BaseOrderResponse baseOrderResponse = new BaseOrderResponse();
        List<OrderItemModel> allItem = new LinkedList<>();
        CustomerModel customerModel = customerRepo.existsByCustomer(customerPhoneNumber);
        try{
            if(customerModel==null){
                baseOrderResponse.setStatusCode("200");
                baseOrderResponse.setMsg("Customer Not Found");
                baseOrderResponse.setItemData(new LinkedList<>());
                baseOrderResponse.setOrderData(null);
                return baseOrderResponse;
            }
            List<OrderModel> orderModel = orderRepo.existsByCustomer(customerModel.getCustomerID().toString());
            if(orderModel == null){
                baseOrderResponse.setStatusCode("200");
                baseOrderResponse.setMsg("Customer Not have order");
                baseOrderResponse.setItemData(new LinkedList<>());
                baseOrderResponse.setOrderData(null);
                return baseOrderResponse;
            }
            baseOrderResponse.setOrderData(null);
            for (OrderModel orders : orderModel){
                allItem.addAll(orderItemRepo.getAllItemData(orders.getOrderId()));
            }
            baseOrderResponse.setItemData(allItem);
            return baseOrderResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String changeOrderStatus(int orderId) {
        logger.info("Method Execution Started IN changeOrderStatus |Status={}", orderId);
        try {
            int dbResponse = orderItemRepo.updateStatusToSold(String.valueOf(OrderStatus.SOLD), (long)orderId);
            System.out.println(dbResponse);
            logger.info("Method Execution Completed IN changeOrderStatus |Response={}", dbResponse);
            return "Update Successfully";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteOrder(int orderId) {
        logger.info("Method Execution Started IN deleteOrder |OrderId={}", orderId);
        Long dbOrderId = 0L;
        try {
            List<OrderItemModel> allItemData = orderItemRepo.getAllItemDataByOrderItem((long) orderId); //Fetch match data itemCode= 22;
            if(allItemData.isEmpty()){
                return "Item Data Not Found";
            }
            dbOrderId = allItemData.get(0).getOrderId().getOrderId();
            orderItemRepo.deleteOrderItems((long)orderId);
            List<OrderItemModel> allItemDataByOrderItem = orderItemRepo.getAllItemDataByOrderId(dbOrderId);
            if (allItemDataByOrderItem.isEmpty()){
                orderRepo.deleteById(dbOrderId);
                return "Order Delete Successfully";
            }
            return "Order Delete Successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderItemModel> generateOrderItemModel(OrderDto orderDto, OrderModel saveOrder) {
        List<OrderItemModel> saveAllOrderData = new LinkedList<>();
        for (OrderItemDto data : orderDto.getOrderItems()) {
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setOrderId(saveOrder);
            orderItemModel.setPrice(data.getPrice());
            orderItemModel.setQuantity(data.getQuantity());
            ProductModel productModel = productRepo.findById((long) data.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItemModel.setProductId(productModel);
            orderItemModel.setPotion(data.getPotion());
            saveAllOrderData.add(orderItemModel);
        }
        return saveAllOrderData;
    }
    private List<OrderItemModel> upgradeOrderItemModel(OrderDto orderDto, OrderModel saveOrder) {
        List<OrderItemModel> saveAllOrderData = new LinkedList<>();
        double calculateTotal = saveOrder.getTotalPrice()+orderDto.getPrice();
        saveOrder.setTotalPrice(calculateTotal);
        orderRepo.save(saveOrder);
        for (OrderItemDto data : orderDto.getOrderItems()) {
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setOrderId(saveOrder);
            orderItemModel.setPrice(data.getPrice());
            orderItemModel.setQuantity(data.getQuantity());
            ProductModel productModel = productRepo.findById((long) data.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItemModel.setProductId(productModel);
            orderItemModel.setPotion(data.getPotion());
            saveAllOrderData.add(orderItemModel);
        }
        return saveAllOrderData;
    }

    private OrderModel genarateOrderModel(OrderDto orderDto) {
        OrderModel orderModel = new OrderModel();
        CustomerModel customerModel = customerRepo.findById((long) orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        orderModel.setCustomerId(customerModel);
        orderModel.setCreateBy(orderDto.getCreateBy());
        orderModel.setTotalPrice(orderDto.getPrice());
        orderModel.setTime(orderDto.getTime());
        return orderModel;
    }
}
