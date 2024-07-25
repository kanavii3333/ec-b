package com.fullness.ec.entity;

import lombok.Data;

@Data
public class EmployeeAccount {
    private Integer empId;
    private Integer empAccountId;
    private String name;
    private String password;
}
