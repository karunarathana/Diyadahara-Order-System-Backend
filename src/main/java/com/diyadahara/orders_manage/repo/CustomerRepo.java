package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<CustomerModel,Long> {
    @Query(value = "SELECT * FROM t_customer WHERE phone_number = :customerName", nativeQuery = true)
    CustomerModel existsByCustomer(@Param("customerName") String customerName);
}
