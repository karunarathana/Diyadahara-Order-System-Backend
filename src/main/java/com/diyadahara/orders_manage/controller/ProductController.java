package com.diyadahara.orders_manage.controller;

import com.diyadahara.orders_manage.constant.APIConst;
import com.diyadahara.orders_manage.dto.ProductDto;
import com.diyadahara.orders_manage.dto.UpdateProductDto;
import com.diyadahara.orders_manage.response.BaseAllProductResponse;
import com.diyadahara.orders_manage.response.BaseProductResponse;
import com.diyadahara.orders_manage.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(APIConst.API_ROOT)
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = APIConst.CREATE_PRODUCT, method = RequestMethod.POST)
    public ResponseEntity<BaseProductResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
        logger.info("Request Started IN createProduct |Request={} |FoodName={}", productDto,productDto.getFoodName());
        BaseProductResponse response = productService.createProduct(productDto);
        logger.info("Request Completed IN createProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value = APIConst.VIEW_ALL_PRODUCT, method = RequestMethod.GET)
    public ResponseEntity<BaseAllProductResponse> viewAllProduct() {
        logger.info("Request Started IN viewAllProduct");
        BaseAllProductResponse response = productService.viewAllProduct();
        logger.info("Request Completed IN viewAllProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConst.VIEW_PRODUCT_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<BaseProductResponse> viewSingleProduct(@Valid @RequestParam("ProductId") int productID) {
        logger.info("Request Started IN viewSingleProduct |ProductID={}",productID);
        BaseProductResponse response = productService.viewSingleProduct(productID);
        logger.info("Request Completed IN viewSingleProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConst.DELETE_PRODUCT_BY_ID, method = RequestMethod.DELETE)
    public ResponseEntity<BaseProductResponse> deleteSingleProduct(@Valid @RequestParam("ProductId") int productID) {
        logger.info("Request Started IN deleteSingleProduct |ProductID={}",productID);
        BaseProductResponse response = productService.deleteSingleProduct(productID);
        logger.info("Request Completed IN deleteSingleProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConst.GET_ALL_PRODUCT_BY_CATEGORY, method = RequestMethod.GET)
    public ResponseEntity<BaseAllProductResponse> viewAllProductByCategory(@Valid @RequestParam("CategoryId") int categoryId) {
        logger.info("Request Started IN viewAllProductByCategory |CategoryId={}",categoryId);
        BaseAllProductResponse response = productService.viewAllProductByCategory(categoryId);
        logger.info("Request Completed IN viewAllProductByCategory |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConst.Update_PRODUCT_BY_ID, method = RequestMethod.POST)
    public ResponseEntity<BaseProductResponse> updateSingleProduct(@Valid @RequestBody UpdateProductDto productDto) {
        logger.info("Request Started IN updateSingleProduct |Request={} |FoodName={}", productDto,productDto.getFoodName());
        BaseProductResponse response = productService.updateSingleProduct(productDto);
        logger.info("Request Completed IN updateSingleProduct |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
