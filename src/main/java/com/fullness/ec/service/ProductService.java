package com.fullness.ec.service;

import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.UpdateProductForm;
import com.fullness.ec.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    void addProduct(ProductForm productForm, byte[] imageByte) ;

    Product getProductByProductId(Integer productId);

    void updateProduct(Product product);

    void deleteProduct(Integer productId);

    boolean isProductExist(ProductForm productForm);
    
    boolean isUpdateProductExist(UpdateProductForm updateProductForm);

    Page<Product> selectProductByPage(Pageable pageable, Integer productCategoryId);
}
