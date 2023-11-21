package com.example.springbootmalljpa.service.Impl;

import com.example.springbootmalljpa.dao.ProductDao;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ClassName: ProductServiceImpl
 * Package: com.example.springbootmalljpa.service.Impl
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public ProductEntity getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setImageUrl(productRequest.getImageUrl());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());

        Date now = new Date();
        productEntity.setCreatedDate(now);
        productEntity.setLastModifiedDate(now);

        ProductEntity product = productDao.save(productEntity);
        return product.getProductId();
    }
}
