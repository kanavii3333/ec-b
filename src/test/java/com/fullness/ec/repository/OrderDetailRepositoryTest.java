package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.OrderDetail;
import com.fullness.ec.entity.Product;

@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired OrderDetailRepository repository;
    @Autowired OrderRepository orderRepository;
    @Sql("/sql/data2.sql")
    @Test
    void insertTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(null);
        orderDetail.setCount(100);
        orderDetail.setCustomerId(1);
        orderDetail.setOrderId(1);
        Product product = new Product();
        product.setProductId(1);
        orderDetail.setProduct(product);
        repository.insert(orderDetail);
        assertEquals(100, orderRepository.selectByPage(PageRequest.of(0, 3),null,1).get(0).getOrderDetailList().get(2).getCount());
    }
}
