package com.fullness.ec.form;

import org.springframework.web.multipart.MultipartFile;

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
    @NotBlank
    @Min(0)
    private Integer price;
    private MultipartFile file;
    private Integer categoryId;
    @NotBlank
    @Min(0)
    @Max(1000000)
    private Integer quantity; 
}
