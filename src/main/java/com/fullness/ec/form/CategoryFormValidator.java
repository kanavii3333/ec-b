package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.ProductCategoryServiceImpl;

@Component
public class CategoryFormValidator implements Validator{
    @Autowired
    ProductCategoryServiceImpl productCategoryService;
    @Override
    public boolean supports(Class<?> clazz) {
    return CategoryForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        CategoryForm categoryForm = (CategoryForm) target;
        if (productCategoryService.isCategoryNameDuplicate(categoryForm)) {
            errors.reject("com.fullness.ec.CategoryForm.message");
        } 
    }
}
