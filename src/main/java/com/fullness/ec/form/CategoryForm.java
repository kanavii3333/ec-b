package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryForm {
    
    private Integer productCategoryId;
    @NotBlank(groups = ValidGroup1.class)
    @Length(min=2,max=20, message = "{length.productCategoryName}", groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4E00-\\u9FAF\\u3040-\\u309F\\u30A0-\\u30FF]+$", message = "{pattern.productCategoryName}", groups = ValidGroup2.class)
    private String productCategoryName; 
}
