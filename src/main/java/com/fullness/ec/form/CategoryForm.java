package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryForm {
    
    private Integer productCategoryId;
    @NotBlank
    @Length(min=2,max=20, message = "{length.productCategoryName}")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4E00-\\u9FAF\\u3040-\\u309F\\u30A0-\\u30FF]+$", message = "{pattern.productCategoryName}")
    private String productCategoryName; 
}
