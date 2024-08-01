package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.fullness.ec.entity.Product;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.UpdateProductForm;
import com.fullness.ec.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Sql("/sql/data.sql")
    @Test
    void addProductTest() throws IOException{
        ProductForm productForm = new ProductForm();
        productForm.setProductId(null);
        productForm.setProductName("えんぴつ");
        productForm.setPrice(200);
        
        byte[] pngData = {
            (byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47, (byte)0x0D, (byte)0x0A, (byte)0x1A, (byte)0x0A, // PNGヘッダー
        };

        // MockMultipartFileの作成
        MultipartFile file = new MockMultipartFile(
            "file",                        // フィールド名
            "example.png",                 // 元のファイル名
            "image/png",                   // Content-Type
            pngData                        // PNGファイルのバイトデータ
        );

        productForm.setFile(file);
        productForm.setCategoryId(2);
        productForm.setStockId(1);
        productForm.setQuantity(30);
        productService.addProduct(productForm, file.getBytes());

        assertEquals(26,productRepository.selectAll().size());
        assertEquals("えんぴつ", productRepository.selectByProductId(26).getProductName());
        assertEquals(200, productRepository.selectByProductId(26).getPrice());
        assertEquals(2, productRepository.selectByProductId(26).getProductCategory().getProductCategoryId());
        assertEquals(30, productRepository.selectByProductId(26).getProductStock().getQuantity());
    }

    @Sql("/sql/data.sql")
    @Test
    void updateProductTest(){
        Product product = productRepository.selectByProductId(1);
        product.setProductName("水性ボールペン(黄)");
        product.setPrice(150);
        product.getProductCategory().setProductCategoryId(2);
        product.getProductStock().setQuantity(20);
        productService.updateProduct(product);

        assertEquals("水性ボールペン(黄)", productRepository.selectByProductId(1).getProductName());
        assertEquals(150, productRepository.selectByProductId(1).getPrice());
        assertEquals(2, productRepository.selectByProductId(1).getProductCategory().getProductCategoryId());
        assertEquals(20, productRepository.selectByProductId(1).getProductStock().getQuantity());
    }

    @Sql("/sql/data.sql")
    @Test
    void deleteProductTest(){
        productService.deleteProduct(1);
        assertEquals(24,productRepository.selectAll().size());
        assertEquals(null,productRepository.selectByProductId(1));
    }

    @Sql("/sql/data.sql")
    @Test
    void getProductByProductIdTest(){
        productService.getProductByProductId(1);
        assertEquals(productRepository.selectByProductId(1),productService.getProductByProductId(1));
    }
    
    @Sql("/sql/data.sql")
    @Test
    public void selectProductByPageWithProductsTest() {
        // Arrange
        Pageable pageable = PageRequest.of(1, 5);

        assertEquals(new PageImpl<>(productRepository.selectByPage(pageable, null),pageable,25), productService.selectProductByPage(pageable, null));

    }
    @Sql("/sql/data.sql")
    @Test
    public void isProductExistTest() {
        ProductForm productForm = new ProductForm();
        productForm.setProductName("水性ボールペン(黒)");
        assertEquals(true, productService.isProductExist(productForm));
    }
    @Sql("/sql/data.sql")
    @Test
    public void isUpdateProductExistTest() {
        UpdateProductForm productForm = new UpdateProductForm();
        productForm.setProductName("水性ボールペン(黒)");
        productForm.setProductId(1);
        assertEquals(false, productService.isUpdateProductExist(productForm));
    }
}
