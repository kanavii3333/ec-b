package com.fullness.ec.service;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.EmployeeForm;
import com.fullness.ec.helper.EmployeeConverter;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public void addCategory(CategoryForm categoryForm) {

        ProductCategory productCategory = ProductConverter.convertFormCategoryToEntity(categoryForm);
        productCategoryRepository.insert(productCategory);
    }

    @Override
    public List<ProductCategory> selectAll() {
        return productCategoryRepository.selectAll();
    }

    @Override
    public boolean isCategoryNameDuplicate(CategoryForm categoryForm) {
        // カテゴリ名の重複チェック
        return productCategoryRepository.countByName(categoryForm.getProductCategoryName()) > 0;
    }
}
