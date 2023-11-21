package com.example.springbootmalljpa.dao;

import com.example.springbootmalljpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * ClassName: ProductDao
 * Package: com.example.springbootmalljpa.dao
 */
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT * FROM product WHERE product_id = :product_id",nativeQuery = true)
    public ProductEntity getProductById(@Param("product_id") Integer productId);

    @Query(value = "SELECT created_date FROM product WHERE product_id = :productId",nativeQuery =true)
    public Date getCreatedDateById(Integer productId);
}
