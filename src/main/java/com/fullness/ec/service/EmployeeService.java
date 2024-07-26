package com.fullness.ec.service;

import com.fullness.ec.form.EmployeeForm;
import java.util.List;

public interface EmployeeService {
    void addAccount(EmployeeForm EmployeeForm);

    boolean employeeLogin(String name, String password);

    List<EmployeeForm> getEmployeeList();
}