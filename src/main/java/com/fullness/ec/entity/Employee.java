package com.fullness.ec.entity;

import lombok.Data;

@Data
public class Employee {
    private Integer empId;
    private String empName;
    private String empNameKana;
    private Department department;
    private EmployeeAccount empAccount;
}
