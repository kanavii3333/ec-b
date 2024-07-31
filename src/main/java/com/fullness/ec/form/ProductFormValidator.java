package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.ProductServiceImpl;

@Component
public class ProductFormValidator implements Validator{
    @Autowired
    ProductServiceImpl productService;
    @Override
    public boolean supports(Class<?> clazz) {
    return ProductForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;
        if (productForm.getPrice() == null || productForm.getProductName() == null) return;

        if(productService.isProductExist(productForm)){
            errors.reject("com.fullness.ec.ProductForm.message4");
        }
        if (productForm.getCategoryId() == 1){
            if(productForm.getPrice() > 5000 || productForm.getPrice() < 30) {
            errors.reject("com.fullness.ec.ProductForm.message1");
            }  
        }
        else if (productForm.getCategoryId() == 2){
            if(productForm.getPrice() > 10000 || productForm.getPrice() < 100) {
            errors.reject("com.fullness.ec.ProductForm.message2");
            }  
        }
        else if (productForm.getCategoryId() == 3){
            if(productForm.getPrice() > 30000 || productForm.getPrice() < 300) {
            errors.reject("com.fullness.ec.ProductForm.message3");
            }  
        }
    }
}
