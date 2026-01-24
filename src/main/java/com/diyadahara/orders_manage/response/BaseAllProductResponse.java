package com.diyadahara.orders_manage.response;

import com.diyadahara.orders_manage.model.ProductModel;

import java.util.List;

public class BaseAllProductResponse {
    private String statusCode;
    private String msg;
    private List<ProductModel> data;

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

    public List<ProductModel> getData() {
        return data;
    }

    public void setData(List<ProductModel> data) {
        this.data = data;
    }
}
