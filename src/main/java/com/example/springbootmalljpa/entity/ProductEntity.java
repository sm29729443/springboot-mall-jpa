package com.example.springbootmalljpa.entity;

import com.example.springbootmalljpa.constants.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * ClassName: ProductEntity
 * Package: com.example.springbootmalljpa.entity
 */
@Getter
@Setter
@Entity
@Table(name = "product", schema = "mall")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "category")
    // JPA 若是使用 enum name 須加上 @Enumerated
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "stock")
    private int stock;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "created_date")
    private Date createdDate;
    @Basic
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
