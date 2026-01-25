package com.diyadahara.orders_manage.service;

import com.diyadahara.orders_manage.config.OrderStatus;
import com.diyadahara.orders_manage.dto.OrderDto;
import com.diyadahara.orders_manage.response.BaseOrderResponse;

public interface OrderService {
    String createOrder(OrderDto orderDto);
    BaseOrderResponse viewOrderByCustomerPhoneNumber(String customerPhoneNumber);
    String changeOrderStatus(int orderId, String status);
}
