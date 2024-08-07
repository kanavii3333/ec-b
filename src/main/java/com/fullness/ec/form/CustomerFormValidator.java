package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.CustomerServiceImpl;

@Component
public class CustomerFormValidator implements Validator{
    @Autowired
    CustomerServiceImpl customerService;
    @Override
    public boolean supports(Class<?> clazz) {
    return CustomerForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm customerForm = (CustomerForm) target;
        if (customerService.getCustomerByMailAddress(customerForm.getCustomerMailAdress()) != null) {
            errors.reject("com.fullness.ec.CustomerForm.message");
        } 
    }
}
