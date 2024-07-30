package com.fullness.ec.service;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.form.EmployeeForm;
import java.util.List;

public interface EmployeeService {
    void addAccount(EmployeeForm employeeForm);

    List<Employee> getEmployeeList();

    boolean isAccountExist(EmployeeForm employeeForm);

    boolean isAccountNameExist(EmployeeForm employeeForm);
}