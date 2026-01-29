package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderModel,Long> {
    @Query(value = "SELECT * FROM t_order WHERE customer_id = :customerName", nativeQuery = true)
    List<OrderModel> existsByCustomer(@Param("customerName") String customerName);

}
