package com.fullness.ec.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.Product;
import com.fullness.ec.form.ProductForm;

@Service
public class ProductService implements SomeService{
    
    public List<Product> getProductList(){
        return null;
    }
    
	public List<ProductForm> getProductListByCategoryId(Integer categoryId){
        return null;
    }
    
	public Page<Product> selectProductByPage(Pageable pageable){
        return null;
    }
}
