package com.fullness.ec.entity;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private Integer price;
    private String imageUrl;
    private ProductCategory productCategory;
    private ProductStock productStock;
}
