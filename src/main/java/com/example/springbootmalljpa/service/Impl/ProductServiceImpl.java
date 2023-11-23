package com.example.springbootmalljpa.service.Impl;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.dao.ProductDao;
import com.example.springbootmalljpa.dto.ProductQueryParams;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<ProductEntity> getProducts(ProductQueryParams params) {
        ProductCategory productCategory = params.getProductCategory();
        String search = params.getSearch();
        String orderBy = params.getOrderBy();
        String sort = params.getSort();
        StringBuffer searchLike = new StringBuffer("%" + search + "%");
        Sort jpaSort = Sort.by(Sort.Direction.valueOf(sort), orderBy);
        if (productCategory == null && search == null) {
            return productDao.findAll(jpaSort);
        }
        if (productCategory == null) {
            return productDao.findByProductNameLike(searchLike.toString(), jpaSort);
        }
        if (search == null) {
            return productDao.findByCategory(productCategory, jpaSort);
        }
        return productDao.findByCategoryAndProductNameLike(productCategory, searchLike.toString(), jpaSort);
    }


}
