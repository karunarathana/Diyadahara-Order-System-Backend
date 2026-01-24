package com.diyadahara.orders_manage.service.impl;

import com.diyadahara.orders_manage.dto.ProductDto;
import com.diyadahara.orders_manage.dto.UpdateProductDto;
import com.diyadahara.orders_manage.model.CategoryModel;
import com.diyadahara.orders_manage.model.PotionModel;
import com.diyadahara.orders_manage.model.ProductModel;
import com.diyadahara.orders_manage.repo.CategoryRepo;
import com.diyadahara.orders_manage.repo.PotionRepo;
import com.diyadahara.orders_manage.repo.ProductRepo;
import com.diyadahara.orders_manage.response.BaseAllProductResponse;
import com.diyadahara.orders_manage.response.BaseProductResponse;
import com.diyadahara.orders_manage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final PotionRepo potionRepo;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, PotionRepo potionRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.potionRepo = potionRepo;
    }

    @Override
    public BaseProductResponse createProduct(ProductDto productDto) {
        logger.info("Method Execution Started IN createProduct |ProductDto={} |ProductName={}", productDto, productDto.getFoodName());

        //check existing foodname
        if (productRepo.existsByFoodName(productDto.getFoodName()) == null) {
            try {
                ProductModel productModel = generateProductModel(productDto);
                ProductModel saveData = productRepo.save(productModel);
                if (saveData != null) {
                    logger.info("Method Execution Completed IN createProduct |SaveData={}", saveData);
                    return createSuccessProductResponse("Save Product Successfully", HttpStatus.CREATED, saveData);
                }
                logger.info("Method Execution Completed IN createProduct |SaveData={}", saveData);
                return createErrorProductResponse("Failed to create product: " + productDto.getFoodName(),
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Error Create Product: {}", e.getMessage(), e);
                return createErrorProductResponse("Failed to create product: " + e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.info("Method Execution Completed IN createProduct |Response={}", productDto.getFoodName());
        return createErrorProductResponse("Already Product In System",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public BaseAllProductResponse viewAllProduct() {
        logger.info("Method Execution Started IN viewAllProduct");
        BaseAllProductResponse baseAllProductResponse = new BaseAllProductResponse();
        try {
            List<ProductModel> allProductResponse = productRepo.findAll();
            if (allProductResponse.isEmpty()) {
                logger.info("Method Execution Completed IN viewAllProduct |Data={}", allProductResponse.size());
                baseAllProductResponse.setStatusCode("200");
                baseAllProductResponse.setMsg("Empty product data In System");
                baseAllProductResponse.setData(allProductResponse);
            }
            logger.info("Method Execution Completed IN viewAllProduct ?Response={}", allProductResponse);
            baseAllProductResponse.setStatusCode("200");
            baseAllProductResponse.setMsg("Successfully fetch all product data");
            baseAllProductResponse.setData(allProductResponse);
            return baseAllProductResponse;
        } catch (Exception e) {
            logger.error("Error View All product: {}", e.getMessage(), e);
            return createErrorProductAllResponse("Failed to view product: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseProductResponse viewSingleProduct(int productId) {
        logger.info("Method Execution Started IN viewSingleProduct |ProductId={}", productId);
        Optional<ProductModel> productData = productRepo.findById((long) productId);
        if (productData.isPresent()) {
            logger.info("Method Execution Completed IN viewSingleProduct |ProductId={}", productId);
            return createSuccessProductResponse("Product Data Fetch Successfully", HttpStatus.OK, productData.get());
        }
        logger.info("Method Execution Completed IN viewSingleProduct |ProductId={} |Response={}", productId, "Please Check Product");
        return createErrorProductResponse("Please Check Product", HttpStatus.BAD_REQUEST);
    }


    //TODO : FIX This problem problem of potion data
    @Override
    public BaseProductResponse deleteSingleProduct(int productId) {
        logger.info("Method Execution Started IN deleteSingleProduct |ProductId={}", productId);
        Optional<ProductModel> productData = productRepo.findById((long) productId);
        if (productData.isPresent()) {
            if (productData.get().getPotionId().getPotionId() !=null) {
                potionRepo.deleteById(productData.get().getPotionId().getPotionId());
            }
            productRepo.deleteById((long) productId);
            logger.info("Method Execution Completed IN deleteSingleProduct |ProductId={}", productId);
            return createSuccessProductResponse("Product Delete Successfully", HttpStatus.OK, null);
        }
        logger.info("Method Execution Completed IN deleteSingleProduct |ProductId={} |Response={}", productId, "Please Check Product");
        return createErrorProductResponse("Please Check Product ProductId = "+productId, HttpStatus.BAD_REQUEST);
    }

    @Override
    public BaseAllProductResponse viewAllProductByCategory(int categoryId) {
        logger.info("Method Execution Started IN viewAllProductByCategory");
        BaseAllProductResponse baseAllProductResponse = new BaseAllProductResponse();
        try {
            List<ProductModel> allProductResponse = productRepo.allProductByCategory((long) categoryId);
            if (allProductResponse.isEmpty()) {
                logger.info("Method Execution Completed IN viewAllProductByCategory |Data={}", allProductResponse.size());
                baseAllProductResponse.setStatusCode("200");
                baseAllProductResponse.setMsg("Empty product data In System");
                baseAllProductResponse.setData(allProductResponse);
            }
            logger.info("Method Execution Completed IN viewAllProductByCategory ?Response={}", allProductResponse);
            baseAllProductResponse.setStatusCode("200");
            baseAllProductResponse.setMsg("Successfully fetch all product data");
            baseAllProductResponse.setData(allProductResponse);
            return baseAllProductResponse;
        } catch (Exception e) {
            logger.error("Error View All product category: {}", e.getMessage(), e);
            return createErrorProductAllResponse("Failed to view product category: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseProductResponse updateSingleProduct(UpdateProductDto productDto) {
        logger.info("Method Execution Started IN updateSingleProduct |ProductDto={} |ProductName={}", productDto, productDto.getFoodName());

        if (productRepo.existsByFoodName(productDto.getFoodName()) != null) {
            try {
                ProductModel productModel = generateUpdateProductModel(productDto);
                ProductModel saveData = productRepo.save(productModel);
                if (saveData != null) {
                    logger.info("Method Execution Completed IN updateSingleProduct |SaveData={}", saveData);
                    return createSuccessProductResponse("Upgrade Product Successfully", HttpStatus.CREATED, saveData);
                }
                logger.info("Method Execution Completed IN updateSingleProduct |SaveData={}", saveData);
                return createErrorProductResponse("Failed to create product: " + productDto.getFoodName(),
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Error Create Product: {}", e.getMessage(), e);
                return createErrorProductResponse("Failed to update product: " + e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.info("Method Execution Completed IN updateSingleProduct |Response={}", productDto.getFoodName());
        return createErrorProductResponse("Empty Product In System " + productDto.getFoodName(),
                HttpStatus.BAD_REQUEST);
    }

    private ProductModel generateProductModel(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setFoodName(productDto.getFoodName());
        CategoryModel category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        productModel.setCategoryId(category);
        productModel.setDiscountPercentage(productDto.getDiscountPercentage());
        productModel.setFoodPrice(productDto.getFoodPrice());
        productModel.setUpdatedBy(productDto.getUpdatedBy());
        productModel.setSize(productDto.getSize());

        //Check ProductPotion Have
        if (productDto.getSizeOfPotion() != null) {
            PotionModel savePotion = potionRepo.save(generatePotionModel(productDto));
            productModel.setPotionId(savePotion);
        }
        return productModel;
    }

    private ProductModel generateUpdateProductModel(UpdateProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setFoodID((long) productDto.getProductId());
        productModel.setFoodName(productDto.getFoodName());
        CategoryModel category = categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        productModel.setCategoryId(category);
        productModel.setDiscountPercentage(productDto.getDiscountPercentage());
        productModel.setFoodPrice(productDto.getFoodPrice());
        productModel.setUpdatedBy(productDto.getUpdatedBy());
        productModel.setSize(productDto.getSize());

        //Check ProductPotion Have
        if (productDto.getSizeOfPotion() != null) {
            PotionModel savePotion = potionRepo.save(generateUpdatePotionModel(productDto));
            productModel.setPotionId(savePotion);
        }
        return productModel;
    }

    private PotionModel generateUpdatePotionModel(UpdateProductDto productDto) {
        PotionModel potionModel = new PotionModel();
        potionModel.setPotionId(productDto.getPotionID());
        potionModel.setSmallPotion(productDto.getSizeOfPotion().getSmall());
        potionModel.setMediumPotion(productDto.getSizeOfPotion().getMedium());
        potionModel.setLargePotion(productDto.getSizeOfPotion().getLarge());
        potionModel.setSpecialPotion(productDto.getSizeOfPotion().getSpecial());
        return potionModel;
    }

    private PotionModel generatePotionModel(ProductDto productDto) {
        PotionModel potionModel = new PotionModel();
        potionModel.setSmallPotion(productDto.getSizeOfPotion().getSmall());
        potionModel.setMediumPotion(productDto.getSizeOfPotion().getMedium());
        potionModel.setLargePotion(productDto.getSizeOfPotion().getLarge());
        potionModel.setSpecialPotion(productDto.getSizeOfPotion().getSpecial());
        return potionModel;
    }

    private BaseProductResponse createSuccessProductResponse(String message, HttpStatus status, ProductModel productModel) {
        BaseProductResponse response = new BaseProductResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        response.setData(productModel);
        return response;
    }

    private BaseProductResponse createErrorProductResponse(String message, HttpStatus status) {
        BaseProductResponse response = new BaseProductResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        response.setData(null);
        return response;
    }

    private BaseAllProductResponse createErrorProductAllResponse(String message, HttpStatus status) {
        BaseAllProductResponse response = new BaseAllProductResponse();
        response.setStatusCode(String.valueOf(status.value()));
        response.setMsg(message);
        response.setData(null);
        return response;
    }
}
