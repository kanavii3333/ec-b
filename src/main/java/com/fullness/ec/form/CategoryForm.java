package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {
    
    private Integer productCategoryId;
    @NotBlank
    @Length(min=2,max=20)
    private String productCategoryName; 
}
