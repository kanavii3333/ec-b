package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.fullness.ec.entity.Product;
import com.fullness.ec.form.ProductForm;

@Mapper
public interface ProductRepository {
    public void insert(Product product);
	public void	updateDeleteFlag(Integer productId);
    public Product selectByProductId(Integer productId);
    public Product selectByCategoryId(Integer categoryId);
    public List<ProductForm> selectAll();  
}
