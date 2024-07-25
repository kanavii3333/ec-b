package com.fullness.ec.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;

@Mapper
public interface ProductCategoryRepository {
    public void insert(ProductCategory productCategory);
    public List<ProductCategory> selectAll();
}
