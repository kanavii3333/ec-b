package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.OrderStatus;

@SpringBootTest
public class OrderStatusRepositoryTest {
    @Autowired
    OrderStatusRepository repository;

    @Sql("/sql/data2.sql")
    @Test
    void selectAllTest(){
        assertNotNull(repository);
        List<OrderStatus> statusList = repository.selectAll();
        assertEquals(4, statusList.size());

    }
}
