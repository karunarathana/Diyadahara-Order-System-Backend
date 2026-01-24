package com.diyadahara.orders_manage.controller;

import com.diyadahara.orders_manage.constant.APIConst;
import com.diyadahara.orders_manage.response.BaseCategoryResponse;
import com.diyadahara.orders_manage.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(APIConst.API_ROOT)
public class CategoryController {
    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @RequestMapping(value = APIConst.CREATE_CATEGORY, method = RequestMethod.POST)
    public ResponseEntity<BaseCategoryResponse> createCategory(@Valid @RequestParam("categoryName") String categoryName) {
        logger.info("Request Started IN createProduct |Request={}",categoryName);
        BaseCategoryResponse response = categoryService.createCategory(categoryName);
        logger.info("Request Completed IN createProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConst.VIEW_ALL_CATEGORY, method = RequestMethod.GET)
    public ResponseEntity<BaseCategoryResponse> viewAllCategory() {
        logger.info("Request Started IN viewAllCategory");
        BaseCategoryResponse response = categoryService.viewAllCategory();
        logger.info("Request Completed IN viewAllCategory |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = APIConst.DELETE_SINGLE_CATEGORY, method = RequestMethod.DELETE)
    public ResponseEntity<BaseCategoryResponse> deleteCategory(@RequestParam("categoryId") int categoryId) {
        logger.info("Request Started IN deleteCategory");
        BaseCategoryResponse response = categoryService.deleteCategory(categoryId);
        logger.info("Request Completed IN deleteCategory |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConst.UPDATE_SINGLE_CATEGORY, method = RequestMethod.POST)
    public ResponseEntity<BaseCategoryResponse> updateCategory(@RequestParam("categoryId") int categoryId,
    @RequestParam("categoryName") String categoryName
    ) {
        logger.info("Request Started IN updateCategory");
        BaseCategoryResponse response = categoryService.updateCategory(categoryId,categoryName);
        logger.info("Request Completed IN updateCategory |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
