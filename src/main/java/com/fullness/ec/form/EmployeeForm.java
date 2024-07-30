package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeForm {
    @NotNull
    private Integer id;
    
    private String empName;

    @NotBlank
    @Length(min=5,max=20)
    private String empAccountName;

    @NotBlank
    @Length(min=5,max=20)
    private String empAccountPassword;

}
