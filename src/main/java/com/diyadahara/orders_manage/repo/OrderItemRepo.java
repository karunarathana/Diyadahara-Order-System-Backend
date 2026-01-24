package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.OrderItemModel;
import com.diyadahara.orders_manage.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItemModel,Long> {
    @Query(value = "SELECT * FROM t_order_item WHERE order_id = :customerName", nativeQuery = true)
    List<OrderItemModel> getAllItemData(@Param("customerName") String customerName);
}
