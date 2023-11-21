package com.example.springbootmalljpa.controller;

import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ClassName: ProductController
 * Package: com.example.springbootmalljpa.controller
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Integer productId) {
        ProductEntity product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        ProductEntity product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
