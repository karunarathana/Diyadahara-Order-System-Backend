package com.diyadahara.orders_manage.controller;

import com.diyadahara.orders_manage.constant.APIConst;
import com.diyadahara.orders_manage.dto.CustomerDto;
import com.diyadahara.orders_manage.dto.UpdateCustomerDto;
import com.diyadahara.orders_manage.model.CustomerModel;
import com.diyadahara.orders_manage.response.BaseCustomerResponse;
import com.diyadahara.orders_manage.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(APIConst.API_ROOT)
public class CustomerController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = APIConst.CREATE_CUSTOMER, method = RequestMethod.POST)
    public ResponseEntity<BaseCustomerResponse> createProduct(@Valid @RequestBody CustomerDto customerDto) {
        logger.info("Request Started IN createProduct |Request={} |CustomerName={}", customerDto, customerDto.getCustomerName());
        BaseCustomerResponse response = customerService.creteCustomer(customerDto);
        logger.info("Request Completed IN createProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConst.VIEW_SINGLE_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<BaseCustomerResponse> viewSingleCustomer(
            @RequestParam("customerId") String phoneNumber) {
        logger.info("Request Started IN viewSingleCustomer |Request={} ", phoneNumber);
        BaseCustomerResponse response = customerService.viewSingleCustomer(phoneNumber);
        logger.info("Request Completed IN viewSingleCustomer |Response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = APIConst.UPDATE_CUSTOMER, method = RequestMethod.POST)
    public ResponseEntity<BaseCustomerResponse> updateCustomer(
            @Valid @RequestBody UpdateCustomerDto customerDto) {
        logger.info("Request Started IN updateCustomer |Request={} ", customerDto);
        BaseCustomerResponse response =
                customerService.updateSingleCustomer(customerDto);
        logger.info("Request Completed IN updateCustomer |Response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = APIConst.DELETE_CUSTOMER, method = RequestMethod.DELETE)
    public ResponseEntity<BaseCustomerResponse> deleteCustomer(
            @RequestParam("customerId") int customerId) {
        logger.info("Request Started IN deleteCustomer |Request={} ", customerId);
        BaseCustomerResponse response =
                customerService.deleteSingleCustomer(customerId);
        logger.info("Request Completed IN deleteCustomer |Response={} ", response);
        return ResponseEntity.ok(response);
    }
    @RequestMapping(value = APIConst.VIEW_ALL_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> viewAllCustomer() {
        logger.info("Request Started IN viewAllCustomer");
        List<CustomerModel> response = customerService.viewAllCustomer();
        logger.info("Request Completed IN viewAllCustomer |Response={} ", response);
        return ResponseEntity.ok(response);
    }
}
