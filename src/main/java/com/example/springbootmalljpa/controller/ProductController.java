package com.example.springbootmalljpa.controller;

import com.example.springbootmalljpa.constants.ProductCategory;
import com.example.springbootmalljpa.dto.ProductQueryParams;
import com.example.springbootmalljpa.dto.ProductRequest;
import com.example.springbootmalljpa.entity.ProductEntity;
import com.example.springbootmalljpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Slf4j
@Validated
@CrossOrigin
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
    public ResponseEntity<Page<ProductEntity>> getProducts(
            // 查詢條件 Filtering
            // 商品種類查詢
            @RequestParam(required = false) ProductCategory productCategory,
            // 商品名稱模糊查詢
            @RequestParam(required = false) String search,

            // 排序條件 orderBy
            // 根據什麼排序
            @RequestParam(defaultValue = "createdDate") String orderBy,
            // 要降序還是升序
            @RequestParam(defaultValue = "ASC") String sort,

            // 分頁 Pagination
            // offset，決定資料從第幾筆開始
            @RequestParam(defaultValue = "0") @Max(1000) @Min(0) Integer page,
            // 每頁資料數
            @RequestParam(defaultValue = "5") @Min(1) Integer size
    ) {

        ProductQueryParams params = new ProductQueryParams();
        params.setProductCategory(productCategory);
        params.setSearch(search);
        params.setOrderBy(orderBy);
        params.setSort(sort);
        params.setPage(page);
        params.setSize(size);
        Page<ProductEntity> products = productService.getProducts(params);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
