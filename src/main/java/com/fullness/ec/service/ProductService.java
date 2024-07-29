package com.fullness.ec.service;

import com.fullness.ec.form.ProductForm;
import com.fullness.ec.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    void addProduct(ProductForm productForm, byte[] imageByte) ;

    List<Product> getProductList();

    List<ProductForm> getProductListByCategoryId(Integer categoryId);

    Product getProductByProductId(Integer productId);

    void updateProduct(Product product);

    void deleteProduct(Integer productId);

    Page<Product> selectProductByPage(Pageable pageable, Integer productCategoryId);
}
