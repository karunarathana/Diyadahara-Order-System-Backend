package com.diyadahara.orders_manage.dto;

public class OrderItemDto {
    private int productId;
    private double price;
    private int quantity;
    private String potion;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPotion() {
        return potion;
    }

    public void setPotion(String potion) {
        this.potion = potion;
    }
}
