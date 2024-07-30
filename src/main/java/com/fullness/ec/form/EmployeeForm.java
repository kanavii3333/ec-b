package com.fullness.ec.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeForm {

    private Integer id;

    @NotBlank
    private String empName;

    @NotBlank
    @Length(min=5,max=20)
    private String empAccountName;

    @NotBlank
    @Length(min=5,max=20)
    private String empAccountPassword;

}
