package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.ProductStock;

@SpringBootTest
public class StockRepositoryTest {
    @Autowired StockRepository repository;

    @Sql("/sql/data2.sql")
    @Test
    void insertTest(){
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(10);
        productStock.setProductId(25);
        repository.insert(productStock);
        assertEquals(10, repository.selectByProductId(25).getQuantity());
    }

    @Sql("/sql/data2.sql")
    @Test
    void updateTest(){
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(33);
        productStock.setProductId(1);
        repository.update(productStock);
        assertEquals(33, repository.selectByProductId(1).getQuantity());
    }

    @Sql("/sql/data2.sql")
    @Test
    void selectByProductIdTest(){
        assertEquals(10, repository.selectByProductId(1).getQuantity());
    }
}
