package com.example.springbootmalljpa.dao;

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

    @Transactional
    @Modifying
    @Query(value = "UPDATE product\n" +
            "SET product_name = :product_name, category = :category, image_url = :image_url," +
            " price = :price, stock = :stock, description = :description," +
            " last_modified_date = :last_modified_date\n" +
            " WHERE product_id = :product_id",
            nativeQuery = true)
    public void updataProduct(@Param("product_id") Integer productId,
                              @Param("product_name") String productName,
                              @Param("category") String category,
                              @Param("image_url") String imageUrl,
                              @Param("price") Integer price,
                              @Param("stock") Integer stock,
                              @Param("description") String description,
                              @Param("last_modified_date") Date lastModifiedDate);



}
