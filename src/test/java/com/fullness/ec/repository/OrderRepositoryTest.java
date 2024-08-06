package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderDetail;
import com.fullness.ec.entity.OrderStatus;
import com.fullness.ec.entity.PaymentMethod;

import java.time.ZonedDateTime;
import java.sql.Timestamp;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository repository;

    @Sql("/sql/data2.sql")
    @Test
    void selectByPageTest() {
        assertEquals(2, repository.selectByPage(PageRequest.of(0, 100), null, null).size());
        // assertEquals(2,repository.selectByPage(PageRequest.of(0,100), null,
        // null).get(0));
    }

    @Sql("/sql/data2.sql")
    @Test
    void selectAllPaymentMethodTest() {
        assertEquals(1, repository.selectAllPaymentMethod().size());
    }

    @Sql("/sql/data2.sql")
    @Test
    void insertTest() {
        Order order = new Order();
        order.setOrderId(null);
        order.setOrderDate(Timestamp.from(ZonedDateTime.now().toInstant()));
        order.setAmountTotal(1000);
        order.setCustomerId(2);
        OrderStatus status = new OrderStatus();
        status.setOrderStatusId(1);
        order.setOrderStatus(status);
        PaymentMethod method = new PaymentMethod();
        method.setPayMethodId(1);
        order.setPayMethod(method);
        repository.insert(order);
        assertEquals(2, repository.selectByPage(PageRequest.of(0, 100), null, null).size());
    }

    @Sql("/sql/data2.sql")
    @Test
    void updateOrderStatusTest() {
        repository.updateOrderStatus(2, 1);
        assertEquals(2,
                repository.selectByPage(PageRequest.of(0, 100), null, null).get(0).getOrderStatus().getOrderStatusId());
    }

    // Integer countOrder(Integer userId);

    @Sql("/sql/data2.sql")
    @Test
    void countOrderTest() {
        Integer countall = repository.countOrder(1);
        assertEquals(1, countall);
    }

}
