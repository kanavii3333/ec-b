package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.data.domain.Pageable;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Sql("/sql/data.sql")
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
        
        product.setProductId(25);
        product.setProductName("万年筆");
        product.setPrice(1000);
        product.setImageUrl("nnn.jpg");
        product.setProductCategory(prodCategory);
        product.setProductStock(prodStock);
        productRepository.insert(product);

        assertEquals(25,productRepository.selectAll().size());
    }

    @Test
    void updateDeleteFlagTest(){
        productRepository.updateDeleteFlag(1);
    }

    @Test
    void update(){
        Product product = new Product();

        ProductCategory prodCategory = new ProductCategory();
        prodCategory.setProductCategoryId(1);

        ProductStock prodStock = new ProductStock();
        prodStock.setQuantity(0);
        
        product.setProductId(25);
        product.setProductName("新万年筆");
        product.setPrice(1000);
        product.setImageUrl("nnn.jpg");
        product.setProductCategory(prodCategory);
        product.setProductStock(prodStock);
        productRepository.insert(product);
    }

    @Test
    void selectByProductIdTest(){
       Product product = productRepository.selectByProductId(1);
       assertEquals("水性ボールペン(黒)", product.getProductName());
       assertEquals(120, product.getPrice());
       assertEquals(10, product.getProductStock().getQuantity());
       assertEquals("文房具", product.getProductCategory().getProductCategoryName());
       assertEquals("black_pen_w.jpg", product.getImageUrl());
    };

    @Test
    void selectProductIdByNameTest(){
        Integer productId = productRepository.selectProductIdByName("水性ボールペン(黒)");
        assertEquals(1, productId);

    }

    @Test
    void selectAll(){
        List<Product>  products = productRepository.selectAll();
        assertEquals(24, products.size());
    }

    @Test
    void countAll(){
        Integer countall = productRepository.countAll(1);
        assertEquals(14, countall);
    }

    @Test
    void selectByPage(){
        Pageable pageable = PageRequest.of(1, 3);
        List<Product> products= productRepository.selectByPage(pageable, 1);
        assertEquals(3, products.size());
    }
    
}
