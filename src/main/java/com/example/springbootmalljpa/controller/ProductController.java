package com.example.springbootmalljpa.controller;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.dto.ProductQueryParams;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: ProductController
 * Package: com.example.springbootmalljpa.controller
 */
@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    //查詢商品
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Integer productId) {
        // producId 不存在 會拋出exception
        ProductEntity product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    //新增商品
    @PostMapping("/products")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        ProductEntity product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    //修改商品
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Integer productId,
                                                       @RequestBody @Valid ProductRequest productRequest) {
        // producId 不存在 會拋出exception
        ProductEntity product = productService.getProductById(productId);


        ProductEntity updateProduct = productService.updateProduct(productId, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    //刪除商品
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //查詢商品列表
    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getProducts(
            @RequestParam(required = false) ProductCategory productCategory,
            @RequestParam(required = false) String search
            ) {
        ProductQueryParams params = new ProductQueryParams();
        params.setProductCategory(productCategory);
        params.setSearch(search);
        List<ProductEntity> products = productService.getProducts(params);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
