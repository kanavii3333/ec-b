package com.fullness.ec.entity;

import lombok.Data;

@Data
public class EmpAccount extends Account{
    private Integer empId;
    private Integer empAccountId;
    private String empAccountName;
    private String password;
}
