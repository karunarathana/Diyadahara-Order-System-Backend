package com.diyadahara.orders_manage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_order_item")
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private int quantity;
    private double price;
    private String potion;

    // Order relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_user_order_item"))
    private OrderModel orderId;

    // Food relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_user_order_item_product"))
    private ProductModel productId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderModel getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderModel orderId) {
        this.orderId = orderId;
    }

    public ProductModel getProductId() {
        return productId;
    }

    public void setProductId(ProductModel productId) {
        this.productId = productId;
    }

    public String getPotion() {
        return potion;
    }

    public void setPotion(String potion) {
        this.potion = potion;
    }
}
