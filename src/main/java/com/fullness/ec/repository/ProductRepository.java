package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.fullness.ec.entity.Product;
import com.fullness.ec.form.ProductForm;

@Mapper
public interface ProductRepository {
    public void insert(Product product);
	public void	updateDeleteFlag(Integer productId);
    public void update(Product product);
    public Product selectByProductId(@Param("productId")Integer productId);
    public Product selectByName(@Param("productName")String productName);
    public List<Product> selectAll();  
    public List<Product> selectByPage(@Param("pageable")Pageable pageable, @Param("productCategoryId")Integer productCategoryId);
}
