package com.example.springbootmalljpa.service.Impl;

import com.example.springbootmalljpa.dao.ProductDao;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
