package com.fullness.ec.helper;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.EmployeeForm;

public class EmployeeConverter {

    // public EmployeeConverter(PasswordEncoder passwordEncoder) {
    // this.passwordEncoder = passwordEncoder;
    // }

    public static EmployeeAccount converterFormEmpAccountToEmployee(EmployeeForm employeeForm) {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmpId(employeeForm.getId());
        employeeAccount.setName(employeeForm.getEmpAccountName());
        employeeAccount.setPassword(employeeForm.getEmpAccountPassword());
        return employeeAccount;

    }
}
