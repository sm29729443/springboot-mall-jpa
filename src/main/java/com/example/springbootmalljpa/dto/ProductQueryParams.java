package com.example.springbootmalljpa.dto;


import com.example.springbootmalljpa.constants.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryParams {
    private ProductCategory productCategory;
    private String search;
    private String orderBy;
    private String sort;
}
