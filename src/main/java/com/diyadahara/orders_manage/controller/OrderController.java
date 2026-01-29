package com.diyadahara.orders_manage.controller;

import com.diyadahara.orders_manage.constant.APIConst;
import com.diyadahara.orders_manage.dto.OrderDto;
import com.diyadahara.orders_manage.response.BaseOrderResponse;
import com.diyadahara.orders_manage.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(APIConst.API_ROOT)
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = APIConst.CREATE_ORDER, method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto) {
        logger.info("Request Started IN createOrder |Request={}", orderDto);
        String response = orderService.createOrder(orderDto);
        logger.info("Request Completed IN createOrder |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConst.VIEW_ALL_ORDER_SINGLE_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<BaseOrderResponse> viewSingleOrder(@Valid @RequestParam("CusPhoneNumber") String phoneNumber) {
        logger.info("Request Started IN viewSingleOrder |Request={}", phoneNumber);
        BaseOrderResponse response = orderService.viewOrderByCustomerPhoneNumber(phoneNumber);
        logger.info("Request Completed IN viewSingleOrder |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConst.CHANGE_SINGLE_ORDER_STATUS, method = RequestMethod.POST)
    public ResponseEntity<?> changeOrderStatus(@Valid @RequestParam("orderItemId") int orderItemId) {
        logger.info("Request Started IN changeOrderStatus |Request={}", orderItemId);
        String response = orderService.changeOrderStatus(orderItemId);
        logger.info("Request Completed IN changeOrderStatus |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Delete Order (First One Delete OrderIt An Order)
    @RequestMapping(value = APIConst.DELETE_SINGLE_ORDER, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@Valid @RequestParam("orderId") int orderId) {
        logger.info("Request Started IN deleteOrder |Request={}", orderId);
        String response = orderService.deleteOrder(orderId);
        logger.info("Request Completed IN deleteOrder |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
