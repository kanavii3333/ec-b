package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class OrderFormValidator implements Validator{
    // @Autowired
    // OrderServiceImpl OrderService;
    @Override
    public boolean supports(Class<?> clazz) {
    return CategoryForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        OrderForm orderForm = (OrderForm) target;
        // if (productCategoryService.isCategoryNameDuplicate(categoryForm)) {
        //     errors.reject("com.fullness.ec.OrderForm.message");
        // } 
    }
}
