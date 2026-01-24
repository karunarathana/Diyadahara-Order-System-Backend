package com.diyadahara.orders_manage.service;

import com.diyadahara.orders_manage.dto.ProductDto;
import com.diyadahara.orders_manage.dto.UpdateProductDto;
import com.diyadahara.orders_manage.response.BaseAllProductResponse;
import com.diyadahara.orders_manage.response.BaseProductResponse;

public interface ProductService {
    BaseProductResponse createProduct(ProductDto productDto);
    BaseAllProductResponse viewAllProduct();
    BaseProductResponse viewSingleProduct(int productId);
    BaseProductResponse deleteSingleProduct(int productId);
    BaseAllProductResponse viewAllProductByCategory(int categoryId);
    BaseProductResponse updateSingleProduct(UpdateProductDto productDto);
}
