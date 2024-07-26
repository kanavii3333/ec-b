package com.fullness.ec.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.repository.ProductCategoryRepository;
import com.fullness.ec.repository.ProductRepository;
import com.fullness.ec.repository.StockRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Product> getProductList() {
        return null;
    }
    @Override
    public List<ProductForm> getProductListByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public Page<Product> selectProductByPage(Pageable pageable, Integer productCategoryId) {
        Integer total = productRepository.countAll();
      List<Product> products;
      if (total > 0){
        products = productRepository.selectByPage(pageable, productCategoryId);
      }else{
        products = Collections.emptyList(); 
      }
      return new PageImpl<>(products , pageable , total);
    }

    @Override
    @Transactional
    public void addProduct(ProductForm productForm, byte[] imageByte) {
        Product product = ProductConverter.convertToEntity(productForm, imageByte);
        productRepository.insert(product);
        product.getProductStock().setProductId(productRepository.selectByName(product.getProductName()).getProductId());
        //stockRepository.insert(product.getProductStock());
    }

    @Override
    public void updateProduct(ProductForm productForm, byte[] imageByte) {
        Product product = ProductConverter.convertToEntity(productForm, imageByte);
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(Integer productId){
        
    }

    @Override
    public Product getProductByProductId(Integer productId){
        return null;
    }
}
