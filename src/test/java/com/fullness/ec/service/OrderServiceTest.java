package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.time.ZonedDateTime;
import java.sql.Timestamp;
import org.assertj.core.api.ZonedDateTimeAssert;
import org.checkerframework.checker.units.qual.Time;
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
import com.fullness.ec.repository.OrderRepository;
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
    void getStockByOrderDetailFormTest(){
        
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
    //     orderService.getOrderList(pageable);
    // }

    // @Test
    // @Sql("/sql/data2.sql")
    // void getOrderListForAdminTest(){
    //     Pageable pageable = PageRequest.of(1, 25);
    //     // Timestamp date=
    //     assertEquals(new PageImpl<>(OrderRepository.countOrder(1), orderService.getOrderListForAdmin(pageable,Timestamp.from(ZonedDateTime.now().toInstant()),1));
    // }

}
