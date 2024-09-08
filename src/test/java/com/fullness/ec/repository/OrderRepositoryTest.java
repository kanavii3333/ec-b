package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import java.util.List;
import java.sql.Timestamp;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository repository;

    @Sql("/sql/data2.sql")
    @Test
    void selectByPageTest() {
        assertEquals(2, repository.selectByPage(PageRequest.of(0, 100), null, null).size());
        assertEquals(1, repository.selectByPage(PageRequest.of(0, 100), null, 1).size());
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
        assertEquals(2, repository.countOrder(2));
    }

    // @Sql("/sql/data2.sql")
    // @Test
    // void updateOrderStatusTest() {
    // repository.updateOrderStatus(2, 1);
    // assertEquals(2,
    // repository.selectByPage(PageRequest.of(0, 100), null,
    // null).get(0).getOrderStatus().getOrderStatusId());
    // }

    // Integer countOrder(Integer userId);

    @Sql("/sql/data2.sql")
    @Test
    void countOrderTest() {
        Integer countall = repository.countOrder(1);
        assertEquals(1, countall);
    }

    @Sql("/sql/data2.sql")
    @Test
    void selectByIdTest() {
        Order order = repository.selectById(1);
        assertEquals(1, order.getOrderId());
        // assertEquals("2024-07-08 21:05:08.0", order.getOrderDate());
        assertEquals(10000, order.getAmountTotal());
        assertEquals(1, order.getCustomerId());

        assertEquals("田中太郎", order.getCustomerName());
        assertEquals("tarotanaka", order.getUsername());
        assertEquals(1, order.getOrderStatus().getOrderStatusId());
        assertEquals("注文済み", order.getOrderStatus().getOrderStatusName());
        assertEquals(1, order.getPayMethod().getPayMethodId());
        assertEquals("現金", order.getPayMethod().getPayMethodName());
        assertEquals("注文済み", order.getOrderStatus().getOrderStatusName());

        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        OrderDetail orderDetail1 = orderDetailList.get(0);// リストに商品が二つ入っている

        assertEquals(1, orderDetail1.getOrderDetailId());
        assertEquals(5, orderDetail1.getCount());
        assertEquals(1, orderDetail1.getProduct().getProductId());
        assertEquals("水性ボールペン(黒)", orderDetail1.getProduct().getProductName());
        assertEquals(120, orderDetail1.getProduct().getPrice());

        OrderDetail orderDetail2 = orderDetailList.get(1);

        assertEquals(2, orderDetail2.getOrderDetailId());
        assertEquals(10, orderDetail2.getCount());
        assertEquals(2, orderDetail2.getProduct().getProductId());
        assertEquals("水性ボールペン(赤)", orderDetail2.getProduct().getProductName());
        assertEquals(120, orderDetail2.getProduct().getPrice());

    }

}
