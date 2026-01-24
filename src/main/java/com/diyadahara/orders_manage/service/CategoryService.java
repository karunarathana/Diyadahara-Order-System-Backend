package com.diyadahara.orders_manage.service;

import com.diyadahara.orders_manage.response.BaseCategoryResponse;

public interface CategoryService {
    BaseCategoryResponse createCategory(String categoryName);
    BaseCategoryResponse deleteCategory(int categoryId);
    BaseCategoryResponse viewAllCategory();
    BaseCategoryResponse updateCategory(int categoryId,String categoryName);
}
