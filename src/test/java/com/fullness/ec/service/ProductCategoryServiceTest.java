package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.byteThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.repository.ProductCategoryRepository;

@SpringBootTest
public class ProductCategoryServiceTest {
    @Autowired ProductCategoryServiceImpl categoryService;
    @Autowired ProductCategoryRepository productCategoryRepository;

    @Test
    @Sql("/sql/data.sql")
    void addCategoryTest(){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setProductCategoryName("日用品");
        categoryService.addCategory(categoryForm);
        assertEquals(4, productCategoryRepository.selectAll().size());
    }

    @Test
    @Sql("/sql/data.sql")
    void selectAllTest(){
        List<ProductCategory> ProductCategories = categoryService.selectAll();
        assertEquals(3, ProductCategories.size());

    }

    @Test
    @Sql("/sql/data.sql")
    void isCategoryNameDuplicate(){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setProductCategoryName("雑貨");
        Boolean boolean1 = categoryService.isCategoryNameDuplicate(categoryForm);
        assertEquals(true, boolean1);
    }

    @Test
    @Sql("/sql/data.sql")
    void isCategoryNameDuplicate1(){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setProductCategoryName("日用品");
        Boolean boolean1 = categoryService.isCategoryNameDuplicate(categoryForm);
        assertEquals(false, boolean1);
    }
}
