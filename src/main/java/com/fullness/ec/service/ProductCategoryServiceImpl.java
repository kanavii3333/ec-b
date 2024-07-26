package com.fullness.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> selectAll() {
        return productCategoryRepository.selectAll();
    }
}
