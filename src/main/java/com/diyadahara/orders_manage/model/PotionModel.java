package com.diyadahara.orders_manage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "t_potion")
public class PotionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long potionId;

    private double smallPotion;
    private double mediumPotion;
    private double largePotion;
    private double specialPotion;


    public Long getPotionId() {
        return potionId;
    }

    public void setPotionId(Long potionId) {
        this.potionId = potionId;
    }

    public double getSmallPotion() {
        return smallPotion;
    }

    public void setSmallPotion(double smallPotion) {
        this.smallPotion = smallPotion;
    }

    public double getMediumPotion() {
        return mediumPotion;
    }

    public void setMediumPotion(double mediumPotion) {
        this.mediumPotion = mediumPotion;
    }

    public double getLargePotion() {
        return largePotion;
    }

    public void setLargePotion(double largePotion) {
        this.largePotion = largePotion;
    }

    public double getSpecialPotion() {
        return specialPotion;
    }

    public void setSpecialPotion(double specialPotion) {
        this.specialPotion = specialPotion;
    }
}
