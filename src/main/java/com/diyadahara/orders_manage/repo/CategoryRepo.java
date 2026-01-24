package com.diyadahara.orders_manage.repo;

import com.diyadahara.orders_manage.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel,Long> {
    @Query(value = "SELECT * FROM t_categories WHERE name = :categoryName", nativeQuery = true)
    CategoryModel existsByCategoryName(@Param("categoryName") String categoryName);
}
