package com.example.springbootmalljpa.service.Impl;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.dao.ProductDao;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * ClassName: ProductServiceImpl
 * Package: com.example.springbootmalljpa.service.Impl
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductEntity getProductById(Integer productId) throws NoSuchElementException {
        Optional<ProductEntity> product = productDao.findById(productId);
        if (!product.isPresent()) {
            throw new NoSuchElementException(" productId not found ");
        }
        return product.get();
    }

    @Override
    public ProductEntity createProduct(ProductRequest productRequest) {
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
        return product;
    }

    @Override
    public ProductEntity updateProduct(Integer productId, ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setImageUrl(productRequest.getImageUrl());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setCreatedDate(productDao.getCreatedDateById(productId));
        productEntity.setLastModifiedDate(new Date());
        return productDao.save(productEntity);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteById(productId);
    }

    @Override
    public List<ProductEntity> getProducts(ProductCategory productCategory, String search) {
        StringBuffer searchLike = new StringBuffer("%" + search + "%");
        if (productCategory == null && search == null) {
            return productDao.findAll();
        }
        if (productCategory == null) {
            return productDao.findByProductNameLike(searchLike.toString());
        }
        if (search == null) {
            return productDao.findByCategory(productCategory);
        }
        return productDao.findByCategoryAndProductNameLike(productCategory, searchLike.toString());
    }


}
