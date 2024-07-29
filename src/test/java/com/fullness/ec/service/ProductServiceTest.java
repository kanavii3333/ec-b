// package com.fullness.ec.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.web.multipart.MultipartFile;

// import com.fullness.ec.entity.Product;
// import com.fullness.ec.entity.ProductCategory;
// import com.fullness.ec.entity.ProductStock;
// import com.fullness.ec.form.ProductForm;
// import com.fullness.ec.helper.ProductConverter;
// import com.fullness.ec.repository.ProductRepository;
// import com.fullness.ec.repository.StockRepository;

// @MybatisTest
// public class ProductServiceTest {
//     @Autowired
//     private ProductRepository productRepository;
//     @Autowired
//     private StockRepository stockRepository;
//     @Sql("/db/data.sql")
//     @Test
//     void addProduct(){
//         ProductCategory productCategory = new ProductCategory();
//            productCategory.setProductCategoryId(2);
//            productCategory.setProductCategoryName("雑貨");
//         ProductStock productStock = new ProductStock();
//            productStock.setProductId(25);
//            productStock.setQuantity(20);
//            productStock.setProductStockId(25);
//         Product product = new Product();
//            product.setProductId(25);
//            product.setProductName("テスト商品");
//            product.setPrice(2000);
//            product.setImageUrl("umbrella.jpg");
//            product.setProductCategory(productCategory);
//            product.setProductStock(productStock);
//         Product productTest = productRepository.insert(product);
//         assertEquals(25,productForm.getProductId());
//         assertEquals(25,productForm.getProductName());
//         assertEquals(25,productForm.getPrice());
//         assertEquals(25,productForm.getFile());
//         assertEquals(25,productForm.getCategoryId());
//         assertEquals(25,productForm.get);


//     }

// }
