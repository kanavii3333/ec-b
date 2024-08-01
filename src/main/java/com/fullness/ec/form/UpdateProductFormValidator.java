package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.ProductServiceImpl;

@Component
public class UpdateProductFormValidator implements Validator{
    @Autowired
    ProductServiceImpl productService;
    @Override
    public boolean supports(Class<?> clazz) {
    return UpdateProductForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        UpdateProductForm updateProductForm = (UpdateProductForm) target;
        if (updateProductForm.getPrice() == null || updateProductForm.getProductName() == null) return;

        if(productService.isUpdateProductExist(updateProductForm)){
            errors.reject("com.fullness.ec.ProductForm.message4");
        }

        if(!updateProductForm.getFile().getContentType().equals("image/jpg")
        && !updateProductForm.getFile().getContentType().equals("image/jpeg")
        && !updateProductForm.getFile().getContentType().equals("image/png")
        && !updateProductForm.getFile().isEmpty()
        ){
            errors.reject("com.fullness.ec.ProductForm.message7");
        }

        if (updateProductForm.getCategoryId() == 1){
            if(updateProductForm.getPrice() > 5000 || updateProductForm.getPrice() < 30) {
            errors.reject("com.fullness.ec.ProductForm.message1");
            }  
        }
        else if (updateProductForm.getCategoryId() == 2){
            if(updateProductForm.getPrice() > 10000 || updateProductForm.getPrice() < 100) {
            errors.reject("com.fullness.ec.ProductForm.message2");
            }  
        }
        else if (updateProductForm.getCategoryId() == 3){
            if(updateProductForm.getPrice() > 30000 || updateProductForm.getPrice() < 300) {
            errors.reject("com.fullness.ec.ProductForm.message3");
            }  
        }
        else if (updateProductForm.getCategoryId() > 3){
            if(updateProductForm.getPrice() > 999999999) {
                errors.reject("com.fullness.ec.ProductForm.message5");
                }  
        }
    }
}
