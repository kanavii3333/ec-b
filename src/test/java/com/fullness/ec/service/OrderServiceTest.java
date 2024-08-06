package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.PaymentMethod;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired ProductRepository productRepository;

    @Test
    @Sql("/sql/data2.sql")
    void getStockByProductIdTest(){
        ProductStock Productstock = orderService.getStockByProductId(1);
        assertEquals(10, Productstock.getQuantity());
    }

    @Test
    @Sql("/sql/data2.sql")
    void getPaymentMethodListTest(){
        List<PaymentMethod> payment=orderService.getPaymentMethodList();
        assertEquals(1, payment.size());
    }
    // @Test
    // @Sql("/sql/data2.sql")
    // void getOrderListTest(){
    //     Pageable pageable = PageRequest.of(1, 1);

    //     assertEquals(new PageImpl<>(productRepository.selectByPage(pageable, null),pageable,25), orderService.getOrderList(pageable));
    // }


}
