package com.diyadahara.orders_manage.service.impl;

import com.diyadahara.orders_manage.model.CategoryModel;
import com.diyadahara.orders_manage.repo.CategoryRepo;
import com.diyadahara.orders_manage.response.BaseCategoryResponse;
import com.diyadahara.orders_manage.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public BaseCategoryResponse createCategory(String categoryName) {
        logger.info("Method Execution Started In createCategory |CategoryName={}",categoryName);
        try {
            CategoryModel categoryModel = categoryRepo.existsByCategoryName(categoryName);
            if(categoryModel != null){
                logger.info("Method Execution Completed In createCategory |Response={}","Oops Already Existing CategoryName "+categoryName);
                return createErrorResponse("Oops Already Existing CategoryName",HttpStatus.BAD_REQUEST);
            }
            CategoryModel saveobject = new CategoryModel();
            saveobject.setName(categoryName);
            categoryRepo.save(saveobject);

            logger.info("Method Execution Completed In createCategory |Response={}","Successfully create new category "+categoryName);
            return createSuccessResponse("Successfully create new category",HttpStatus.CREATED,null);
        } catch (Exception e) {
            logger.error("Error create category: {}", e.getMessage(), e);
            return createErrorResponse("Failed to create category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCategoryResponse deleteCategory(int categoryId) {
        logger.info("Method Execution Started In deleteCategory |CategoryId={}",categoryId);
        try {
            categoryRepo.deleteById((long)categoryId);
            logger.info("Method Execution Completed In deleteCategory |Response={}","Category Deleted Successfully");
            return createSuccessResponse("Category Deleted Successfully",HttpStatus.CREATED,null);
        } catch (Exception e) {
            logger.error("Error delete category: {}", e.getMessage(), e);
            return createErrorResponse("Failed to delete category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCategoryResponse viewAllCategory() {
        logger.info("Method Execution Started In viewAllCategory");
        try {
            List<CategoryModel> allCategoryData = categoryRepo.findAll();
            if(allCategoryData.isEmpty()){
                logger.info("Method Execution Completed In viewAllCategory |Response={}","Oops no category data");
                return createErrorResponse("Oops no category data",HttpStatus.BAD_REQUEST);
            }
            logger.info("Method Execution Completed In viewAllCategory |Response={}",allCategoryData);
            return createSuccessResponse("Operation Successfully",HttpStatus.OK,allCategoryData);
        } catch (Exception e) {
            logger.error("Error view category: {}", e.getMessage(), e);
            return createErrorResponse("Failed to view category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseCategoryResponse updateCategory(int categoryId, String categoryName) {
        logger.info("Method Execution Started In updateCategory |CategoryName={}",categoryName);
        CategoryModel updateModel = new CategoryModel();
        updateModel.setCategoryId((long)categoryId);
        updateModel.setName(categoryName);
        try {
            categoryRepo.save(updateModel);
            return createSuccessResponse("Update Successfully",HttpStatus.OK,null);
        } catch (Exception e) {
            logger.error("Error update category: {}", e.getMessage(), e);
            return createErrorResponse("Failed to update category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private BaseCategoryResponse createErrorResponse(String message, HttpStatus status) {
        BaseCategoryResponse response = new BaseCategoryResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        return response;
    }
    private BaseCategoryResponse createSuccessResponse(String message, HttpStatus status,List<CategoryModel> data) {
        BaseCategoryResponse response = new BaseCategoryResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setData(data);
        response.setMsg(message);
        return response;
    }
}
