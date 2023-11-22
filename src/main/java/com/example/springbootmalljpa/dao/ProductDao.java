package com.example.springbootmalljpa.dao;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: ProductDao
 * Package: com.example.springbootmalljpa.dao
 */
@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT * FROM product WHERE product_id = :product_id", nativeQuery = true)
    public ProductEntity getProductById(@Param("product_id") Integer productId);

    @Query(value = "SELECT created_date FROM product WHERE product_id = :product_id", nativeQuery = true)
    public Date getCreatedDateById(@Param("product_id") Integer productId);

}
