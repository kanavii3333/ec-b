package com.fullness.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.repository.ProductCategoryRepository;
import com.fullness.ec.repository.ProductRepository;

@Service
public class ProductService implements SomeService{
    @Autowired ProductRepository productRepository;
    @Autowired ProductCategoryRepository productCategoryRepository;
    public List<Product> getProductList(){
        return null;
    }
    
	public List<ProductForm> getProductListByCategoryId(Integer categoryId){
        return null;
    }
    
	public Page<Product> selectProductByPage(Pageable pageable, Integer productCategoryId){
        return null;
    }

    public void addProduct(ProductForm productForm, ProductCategory productCategory, byte[] imageByte){
        Product product = ProductConverter.convertToEntity(productForm, imageByte);
        productRepository.insert(product);
    }

    public void updateProduct(ProductForm productForm, byte[] imageByte){
        Product product = ProductConverter.convertToEntity(productForm, imageByte);
        productRepository.update(product);
    }
}
