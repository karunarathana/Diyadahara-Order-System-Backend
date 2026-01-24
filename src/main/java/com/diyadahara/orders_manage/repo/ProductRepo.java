package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel,Long> {
    @Query(value = "SELECT * FROM t_product WHERE food_name = :name", nativeQuery = true)
    ProductModel existsByFoodName(@Param("name") String foodName);

    @Query(value = "SELECT * FROM t_product WHERE category_id = :categoryId", nativeQuery = true)
    List<ProductModel> allProductByCategory(@Param("categoryId") Long categoryId);
}
