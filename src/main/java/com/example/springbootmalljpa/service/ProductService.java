package com.example.springbootmalljpa.service;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.dto.ProductQueryParams;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductService
 * Package: com.example.springbootmalljpa.service
 */

public interface ProductService {
    public ProductEntity getProductById(Integer productId);

    public ProductEntity createProduct(ProductRequest productRequest);

    public ProductEntity updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

    public List<ProductEntity> getProducts(ProductQueryParams params);
}
