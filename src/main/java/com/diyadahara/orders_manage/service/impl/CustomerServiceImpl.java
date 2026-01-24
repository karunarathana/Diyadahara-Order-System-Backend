package com.diyadahara.orders_manage.service.impl;

import com.diyadahara.orders_manage.dto.CustomerDto;
import com.diyadahara.orders_manage.dto.UpdateCustomerDto;
import com.diyadahara.orders_manage.model.CustomerModel;
import com.diyadahara.orders_manage.repo.CustomerRepo;
import com.diyadahara.orders_manage.response.BaseCustomerResponse;
import com.diyadahara.orders_manage.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public BaseCustomerResponse creteCustomer(CustomerDto customerDto) {
        logger.info("Method Execution Started IN creteCustomer |CustomerDto={} |CustomerName={}", customerDto, customerDto.getCustomerName());
        CustomerModel existsByCustomer = customerRepo.existsByCustomer(customerDto.getPhoneNumber());
        try {
            if (existsByCustomer != null) {
                logger.info("Method Execution Completed IN creteCustomer |Response={}", "Already user have account " + customerDto.getCustomerName());
                return createErrorCustomerResponse("Already user have account", HttpStatus.BAD_REQUEST);
            }
            CustomerModel save = customerRepo.save(generateCustomerModel(customerDto));
            logger.info("Method Execution Completed IN creteCustomer |Response={}", save);
            return createSuccessCustomerResponse("Customer Save Successfully", HttpStatus.CREATED, save);
        } catch (Exception e) {
            logger.error("Error create customer: {}", e.getMessage(), e);
            return createErrorCustomerResponse("Failed to create customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCustomerResponse viewSingleCustomer(String phoneNumber) {
        logger.info("Method Execution Started IN viewSingleCustomer |PhoneNumber={}",phoneNumber);
        CustomerModel customerData = customerRepo.existsByCustomer(phoneNumber);
        try {
            if(customerData != null){
                logger.info("Method Execution Completed IN viewSingleCustomer |Response={}",customerData);
                return createSuccessCustomerResponse("Customer Already Existing In System",HttpStatus.OK,customerData);
            }
            logger.info("Method Execution Completed IN viewSingleCustomer |Response={}",customerData);
            return createSuccessCustomerResponse("Customer Not Existing In System",HttpStatus.OK,customerData);
        } catch (Exception e) {
            logger.error("Error fetch customer: {}", e.getMessage(), e);
            return createErrorCustomerResponse("Failed to fetch single customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCustomerResponse deleteSingleCustomer(int customerId) {
        logger.info("Method Execution Started IN deleteSingleCustomer |CustomerId={}",customerId);
        try{
            customerRepo.deleteById((long)customerId);
            logger.info("Method Execution Completed IN deleteSingleCustomer Delete Successful |Response={}",customerId);
            return createSuccessCustomerResponse("Customer Delete Successful In System",HttpStatus.OK,null);
        } catch (Exception e) {
            logger.error("Error delete customer: {}", e.getMessage(), e);
            return createErrorCustomerResponse("Failed to delete single customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCustomerResponse updateSingleCustomer(UpdateCustomerDto customerDto) {
        logger.info("Method Execution Started IN updateSingleCustomer |CustomerDto={} |CustomerName={}", customerDto, customerDto.getCustomerName());
        CustomerModel existsByCustomer = customerRepo.existsByCustomer(customerDto.getPhoneNumber());
        try {
//            if (existsByCustomer != null) {
//                logger.info("Method Execution Completed IN updateSingleCustomer |Response={}", "Already user have account " + customerDto.getCustomerName());
//                return createErrorCustomerResponse("Already user have account", HttpStatus.BAD_REQUEST);
//            }
            CustomerModel save = customerRepo.save(generateUpdateCustomerModel(customerDto));
            logger.info("Method Execution Completed IN updateSingleCustomer |Response={}", save);
            return createSuccessCustomerResponse("Customer Save Successfully", HttpStatus.CREATED, save);
        } catch (Exception e) {
            logger.error("Error update customer: {}", e.getMessage(), e);
            return createErrorCustomerResponse("Failed to update customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerModel> viewAllCustomer() {
        List<CustomerModel> allResponse = customerRepo.findAll();
        return allResponse;
    }

    private CustomerModel generateCustomerModel(CustomerDto customerDto) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerName(customerDto.getCustomerName());
        customerModel.setCustomerEmail(customerDto.getCustomerEmail());
        customerModel.setPhoneNumber(customerDto.getPhoneNumber());
        return customerModel;
    }

    private CustomerModel generateUpdateCustomerModel(UpdateCustomerDto customerDto) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerID((long)customerDto.getCustomerId());
        customerModel.setCustomerName(customerDto.getCustomerName());
        customerModel.setCustomerEmail(customerDto.getCustomerEmail());
        customerModel.setPhoneNumber(customerDto.getPhoneNumber());
        return customerModel;
    }

    private BaseCustomerResponse createSuccessCustomerResponse(String message, HttpStatus status, CustomerModel customerModel) {
        BaseCustomerResponse response = new BaseCustomerResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        response.setData(customerModel);
        return response;
    }

    private BaseCustomerResponse createErrorCustomerResponse(String message, HttpStatus status) {
        BaseCustomerResponse response = new BaseCustomerResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        response.setData(null);
        return response;
    }
}
