package com.diyadahara.orders_manage.dto;

public class UpdateProductDto {
    private int productId;
    private String foodName;
    private double foodPrice;
    private int discountPercentage;
    private Long categoryId;
    private Long potionID;
    private String updatedBy;
    private String size;
    private SizeOfProduct sizeOfPotion;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public SizeOfProduct getSizeOfPotion() {
        return sizeOfPotion;
    }

    public void setSizeOfPotion(SizeOfProduct sizeOfPotion) {
        this.sizeOfPotion = sizeOfPotion;
    }

    public Long getPotionID() {
        return potionID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPotionID(Long potionID) {
        this.potionID = potionID;
    }
}
