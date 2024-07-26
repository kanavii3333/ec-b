package com.fullness.ec.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.EmployeeForm;

public class EmployeeConverter {
    @Autowired
    PasswordEncoder passwordEncoder;

    // public EmployeeConverter(PasswordEncoder passwordEncoder) {
    // this.passwordEncoder = passwordEncoder;
    // }

    public static EmployeeAccount convertFormEmpAccountToEmployee(EmployeeForm employeeForm,
            PasswordEncoder passwordEncoder) {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmpId(employeeForm.getId());
        employeeAccount.setName(employeeForm.getEmpAccountName());
        employeeAccount.setPassword(passwordEncoder.encode(employeeForm.getEmpAccountPassword()));
        return employeeAccount;

    }
}
