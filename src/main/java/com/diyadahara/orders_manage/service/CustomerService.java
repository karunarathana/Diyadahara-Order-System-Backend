package com.diyadahara.orders_manage.service;

import com.diyadahara.orders_manage.dto.CustomerDto;
import com.diyadahara.orders_manage.dto.UpdateCustomerDto;
import com.diyadahara.orders_manage.model.CustomerModel;
import com.diyadahara.orders_manage.response.BaseCustomerResponse;

import java.util.List;

public interface CustomerService {
    BaseCustomerResponse creteCustomer(CustomerDto customerDto);
    BaseCustomerResponse viewSingleCustomer(String phoneNumber);
    BaseCustomerResponse deleteSingleCustomer(int customerId);
    BaseCustomerResponse updateSingleCustomer(UpdateCustomerDto customerDto);
    List<CustomerModel> viewAllCustomer();
}
