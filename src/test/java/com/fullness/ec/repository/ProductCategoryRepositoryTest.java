package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.ProductCategory;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductCategoryRepositoryTest {
     @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Sql("/sql/data.sql")
    @Test
    void insertTest(){
        ProductCategory prodCategory =  new ProductCategory();
        prodCategory.setProductCategoryId(4);
        prodCategory.setProductCategoryName("日用品");
        productCategoryRepository.insert(prodCategory);
        assertEquals(4, productCategoryRepository.selectAll());
    }

    @Test
    void selectAllTest(){
        List<ProductCategory> ProductCategories = productCategoryRepository.selectAll();
        assertEquals(3, ProductCategories.size());

    }



    
}
