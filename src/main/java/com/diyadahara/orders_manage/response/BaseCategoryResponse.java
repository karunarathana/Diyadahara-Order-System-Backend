package com.diyadahara.orders_manage.response;

import com.diyadahara.orders_manage.model.CategoryModel;

import java.util.List;

public class BaseCategoryResponse {
    private String statusCode;
    private String msg;
    private List<CategoryModel> data;

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

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }
}
