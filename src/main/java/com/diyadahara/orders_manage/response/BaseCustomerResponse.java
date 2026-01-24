package com.diyadahara.orders_manage.response;

import com.diyadahara.orders_manage.model.CustomerModel;

public class BaseCustomerResponse {
    private String statusCode;
    private String msg;
    private CustomerModel data;

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

    public CustomerModel getData() {
        return data;
    }

    public void setData(CustomerModel data) {
        this.data = data;
    }
}
