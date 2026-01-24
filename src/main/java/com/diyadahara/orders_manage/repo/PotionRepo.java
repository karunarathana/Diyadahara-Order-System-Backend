package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.PotionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotionRepo extends JpaRepository<PotionModel,Long> {
}
