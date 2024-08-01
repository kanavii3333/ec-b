package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProductForm {
    
    private Integer productId;

    @NotBlank
    @Length(min=5,max=20, message = "{length.productName}")
    @Pattern(regexp = "^[a-zA-Z0-9()\u4E00-\u9FAF\u3040-\u309F\u30A0-\u30FF]+$", message = "{pattern.name}")
    private String productName;

    @NotNull
    @Range(min = 0, max = 999999999)
    private Integer price;

    private MultipartFile file;

    private Integer categoryId;
    private String categoryName;

    @NotNull
    @Min(0)
    @Max(1000000)
    private Integer quantity; 

    private Integer stockId;
    
    private String imageUrl;
    private String image;
}
