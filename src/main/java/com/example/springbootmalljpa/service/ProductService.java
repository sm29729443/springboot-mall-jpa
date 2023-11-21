package com.example.springbootmalljpa.service;

import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProductService
 * Package: com.example.springbootmalljpa.service
 */

public interface ProductService {
    public ProductEntity getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);
}
