package com.diyadahara.orders_manage.response;

import com.diyadahara.orders_manage.model.ProductModel;

public class BaseProductResponse {
    private String statusCode;
    private String msg;
    private ProductModel data;

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

    public ProductModel getData() {
        return data;
    }

    public void setData(ProductModel data) {
        this.data = data;
    }
}
