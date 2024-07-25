package com.fullness.ec.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {
    
    private Integer productCategoryId;
    @NotBlank
    @Min(2)
    @Max(20)
    private String productCategoryName; 
}
