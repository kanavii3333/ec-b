package com.fullness.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.form.FormEmployeeLogin;
import com.fullness.ec.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
    @Autowired EmployeeRepository employeeRepository;
    public boolean employeeLogin(FormEmployeeLogin formEmployeeLogin) {
        Employee employee = employeeRepository.selectByNameAndPassward(formEmployeeLogin.getEmpName(),formEmployeeLogin.getPassword());
        return employee!=null;
    }
    
}
