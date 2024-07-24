package com.fullness.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullness.ec.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
    @Autowired EmployeeRepository employeeRepository;
    
}
