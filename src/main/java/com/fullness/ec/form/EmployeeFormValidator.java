package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.EmployeeServiceImpl;

@Component
public class EmployeeFormValidator implements Validator{
    @Autowired
    EmployeeServiceImpl employeeService;
    @Override
    public boolean supports(Class<?> clazz) {
    return EmployeeForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        EmployeeForm employeeForm = (EmployeeForm) target;
        if (employeeService.isAccountExist(employeeForm) && employeeService.isAccountNameExist(employeeForm)) {
            errors.reject("com.fullness.ec.EmployeeForm.message2");
            errors.reject("com.fullness.ec.EmployeeForm.message1");
            
        }
        else if (employeeService.isAccountExist(employeeForm)) {
            errors.reject("com.fullness.ec.EmployeeForm.message2");
            
        }
        else if (employeeService.isAccountNameExist(employeeForm)) {
            errors.reject("com.fullness.ec.EmployeeForm.message1");
            
        }
    }
}
