package com.fullness.ec.form;

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
    @Min(5)
    @Max(20)
    private String empAccountName;

    @NotBlank
    @Min(5)
    @Max(20)
    private String empAccountPassword;

}
