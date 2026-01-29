package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.OrderItemModel;
import com.diyadahara.orders_manage.model.OrderModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItemModel,Long> {
    @Query(value = "SELECT * FROM t_order_item WHERE order_id = :orderId", nativeQuery = true)
    List<OrderItemModel> getAllItemData(@Param("orderId") Long orderId);

    @Query(value = "SELECT * FROM t_order_item WHERE order_item_id = :orderId", nativeQuery = true)
    List<OrderItemModel> getAllItemDataByOrderItem(@Param("orderId") Long orderId);

    @Query(value = "SELECT * FROM t_order_item WHERE order_id = :orderId", nativeQuery = true)
    List<OrderItemModel> getAllItemDataByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM t_order_item WHERE order_item_id = :orderId",
            nativeQuery = true
    )
    int deleteOrderItems(@Param("orderId") Long orderId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE t_order_item SET status = :status WHERE order_item_id = :orderItemID",
            nativeQuery = true
    )
    int updateStatusToSold(
            @Param("status") String status,
            @Param("orderItemID") Long orderItemID
    );

}
