package com.diyadahara.orders_manage.constant;

public class APIConst {
    public static final String API_ROOT = "api/com-diyadahara";

    public static final String CREATE_PRODUCT = "/create-product";
    public static final String VIEW_ALL_PRODUCT = "/view-all-product";
    public static final String DELETE_PRODUCT_BY_ID = "/delete-single-product";
    public static final String VIEW_PRODUCT_BY_ID = "/view-single-product";
    public static final String Update_PRODUCT_BY_ID = "/update-single-product";
    public static final String GET_ALL_PRODUCT_BY_CATEGORY = "/get-all-product-by-category";

    public static final String CREATE_CUSTOMER = "/create-customer";
    public static final String VIEW_SINGLE_CUSTOMER = "/view-single-customer";
    public static final String DELETE_CUSTOMER = "/delete-customer";
    public static final String UPDATE_CUSTOMER = "/update-customer";
    public static final String VIEW_ALL_CUSTOMER = "/view-all-customer";

    public static final String CREATE_ORDER = "/create-order";
    public static final String VIEW_ALL_ORDER_SINGLE_CUSTOMER = "/view-order-relevant-customer";
    public static final String DELETE_SINGLE_ORDER = "/delete-customer";
    public static final String UPDATE_SINGLE_ORDER = "/update-customer";
    public static final String CHANGE_SINGLE_ORDER_STATUS = "/change-order-status";

    public static final String CREATE_CATEGORY = "/create-category";
    public static final String VIEW_ALL_CATEGORY = "/view-all-category";
    public static final String DELETE_SINGLE_CATEGORY = "/delete-category-by-id";
    public static final String UPDATE_SINGLE_CATEGORY = "/update-category-by-id";

    public static final String GET_DASHBOARD_DETAILS = "/get-dashboard-details";
}
