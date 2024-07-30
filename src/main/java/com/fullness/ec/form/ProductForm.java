package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductForm {
    
    private Integer productId;

    @NotBlank
    @Length(min=2,max=20)
    private String productName;

    @NotNull
    @Min(0)
    private Integer price;

    private MultipartFile file;

    private Integer categoryId;

    @NotNull
    @Min(0)
    @Max(1000000)
    private Integer quantity; 

    private Integer stockId;
}
