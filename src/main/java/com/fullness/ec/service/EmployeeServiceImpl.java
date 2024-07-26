package com.fullness.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.entity.EmployeeAccount;

import com.fullness.ec.form.EmployeeForm;
import com.fullness.ec.helper.EmployeeConverter;

import com.fullness.ec.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addAccount(EmployeeForm employeeForm) {

        EmployeeAccount employeeAccount = EmployeeConverter.converterFormEmpAccountToEmployee(employeeForm);
        employeeRepository.insert(employeeAccount);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.selectAllEmployee();
    }
}
