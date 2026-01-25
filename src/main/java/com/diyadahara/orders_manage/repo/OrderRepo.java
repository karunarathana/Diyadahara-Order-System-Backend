package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.config.OrderStatus;
import com.diyadahara.orders_manage.model.OrderModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends JpaRepository<OrderModel,Long> {
    @Query(value = "SELECT * FROM t_order WHERE customer_id = :customerName", nativeQuery = true)
    OrderModel existsByCustomer(@Param("customerName") String customerName);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE t_order SET status = :status WHERE order_id = :customerId", // 'o.' ඉවත් කරන්න
            nativeQuery = true
    )
    int updateStatusNative(
            @Param("status") String status,
            @Param("customerId") Long customerId
    );

}
