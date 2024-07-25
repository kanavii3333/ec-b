package com.fullness.ec.form;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductForm {
    
    private Integer productId;
    @NotBlank
    @Min(2)
    @Max(20)
    private String productName;
    private Integer price;
    private String imageUrl;
    private String categoryName;
    private Integer quantity; 
}
