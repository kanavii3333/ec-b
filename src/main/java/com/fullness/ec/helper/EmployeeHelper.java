package com.fullness.ec.helper;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;

public class EmployeeHelper {
    public ProductCategory convertToEntity(CategoryForm categoryForm){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(categoryForm.getProductCategoryId());
        productCategory.setProductCategoryName(categoryForm.getProductCategoryName());
        return productCategory;
    }    
    public CategoryForm convertEntityToCategoryForm(ProductCategory productCategory){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setProductCategoryId(categoryForm.getProductCategoryId());
        categoryForm.setProductCategoryName(categoryForm.getProductCategoryName());
        return categoryForm;
    }  
}
