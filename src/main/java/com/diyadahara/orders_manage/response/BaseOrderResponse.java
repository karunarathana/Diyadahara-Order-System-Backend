package com.diyadahara.orders_manage.response;

import com.diyadahara.orders_manage.model.OrderItemModel;
import com.diyadahara.orders_manage.model.OrderModel;

import java.util.List;

public class BaseOrderResponse {
    private String statusCode;
    private String msg;
    private OrderModel orderData;
    private List<OrderItemModel>itemData;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OrderModel getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderModel orderData) {
        this.orderData = orderData;
    }

    public List<OrderItemModel> getItemData() {
        return itemData;
    }

    public void setItemData(List<OrderItemModel> itemData) {
        this.itemData = itemData;
    }
}
