package com.diyadahara.orders_manage.model;

import com.diyadahara.orders_manage.config.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodID;
    @Column(columnDefinition = "TEXT")
    private String foodName;

    @Column(columnDefinition = "TEXT")
    private String size;

    @Builder.Default
    private double foodPrice = 00.00;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;

    private int discountPercentage;

    // Category relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
    private CategoryModel categoryId;

    // Potion relationship
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "potion_id", foreignKey = @ForeignKey(name = "fk_product_potion"))
    private PotionModel potionId;

    // Audit fields
    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @LastModifiedDate
    @Column(name = "updated_by")
    private String updatedBy;

    public Long getFoodID() {
        return foodID;
    }

    public void setFoodID(Long foodID) {
        this.foodID = foodID;
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public CategoryModel getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryModel categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public PotionModel getPotionId() {
        return potionId;
    }

    public void setPotionId(PotionModel potionId) {
        this.potionId = potionId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
