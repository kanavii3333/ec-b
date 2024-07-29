package com.fullness.ec.service;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;
import java.util.List;

public interface ProductCategoryService {
    void addCategory(CategoryForm categoryForm);

    List<ProductCategory> selectAll();
}