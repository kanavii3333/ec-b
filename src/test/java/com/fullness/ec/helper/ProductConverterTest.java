package com.fullness.ec.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.UpdateProductForm;
import com.fullness.ec.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductConverterTest {
    @Autowired
    ProductRepository productRepository;
    @Sql("/sql/data.sql")
    @Test
    void convertToEntityTest(){
        ProductForm productForm = new ProductForm();
        productForm.setProductId(1);
        productForm.setProductName("黒ボールペン");
        productForm.setPrice(100);
        productForm.setFile(null);
        productForm.setCategoryId(1);
        productForm.setQuantity(100);
        productForm.setStockId(1);

         MockMultipartFile file = new MockMultipartFile(
            "file",          // ファイルフィールドの名前
            "test.txt",      // ファイル名
            "text/plain",    // コンテンツタイプ
            "Hello, World!".getBytes() // ファイルの内容
        );

        productForm.setFile(file);
        byte[] imageByte = new byte[1024]; // 例として1024バイトのダミーデータを使用

        Product product = ProductConverter.convertToEntity(productForm, imageByte);
        assertNotNull(product);
        assertEquals("黒ボールペン", product.getProductName());
        assertEquals(100, product.getPrice());
        assertEquals(1, product.getProductCategory().getProductCategoryId());
        assertEquals(100, product.getProductStock().getQuantity());
      
    }
    @Sql("/sql/data.sql")
    @Test
    void convertFormCategoryToEntityTest(){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setProductCategoryId(1);
        categoryForm.setProductCategoryName("文房具");

        ProductConverter.convertFormCategoryToEntity(categoryForm);

        assertEquals(1, categoryForm.getProductCategoryId());
        assertEquals("文房具", categoryForm.getProductCategoryName());
    }

    @Sql("/sql/data.sql")
    @Test
    void convertToFormTest(){
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("黒ボールペン");
        product.setPrice(100);
        product.setProductCategory(new ProductCategory());
        product.getProductCategory().setProductCategoryId(1);
        product.getProductCategory().setProductCategoryName("文房具");
        product.setProductStock(new ProductStock());
        product.getProductStock().setQuantity(100);
        product.getProductStock().setProductStockId(1);

        UpdateProductForm productForm = ProductConverter.convertToForm(product);

        assertNotNull(product);
        assertEquals(1, productForm.getProductId());
        assertEquals("黒ボールペン", productForm.getProductName());
        assertEquals(100, productForm.getPrice());
        assertEquals(1, productForm.getCategoryId());
        assertEquals(100, productForm.getQuantity());
        assertEquals(1, productForm.getStockId());

    }
}
