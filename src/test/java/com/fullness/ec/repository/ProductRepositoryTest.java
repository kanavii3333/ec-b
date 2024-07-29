package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Sql("db/data.sql")
    @Test
    void insertTest(){
        Product product = new Product();

        ProductCategory prodCategory = new ProductCategory();
        prodCategory.setProductCategoryId(1);
        //prodCategory.setProductCategoryName("文房具");

        ProductStock prodStock = new ProductStock();
        //prodStock.setProductId(25);
        prodStock.setQuantity(0);
        //prodStock.setProductStockId(25);

        product.setProductName("万年筆");
        product.setPrice(1000);
        product.setImageUrl("nnn.jpg");
        product.setProductCategory(prodCategory);
        product.setProductStock(prodStock);
        productRepository.insert(product);

        assertEquals(25,productRepository.selectAll());
    }
    
}
