package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.fullness.ec.entity.Product;

@Mapper
 public interface ProductRepository {
     void insert(Product product);
	 void	updateDeleteFlag(Integer productId);
     void update(Product product);
     Product selectByProductId(@Param("productId")Integer productId);
     Integer selectProductIdByName(@Param("productName")String productName);
     List<Product> selectAll();
     Integer countAll(@Param("productCategoryId")Integer productCategoryId);  
     List<Product> selectByPage(Pageable pageable, Integer productCategoryId);
}
