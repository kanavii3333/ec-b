package com.fullness.ec.repository;

import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.PaymentMethod;

@Mapper
public interface OrderRepository {
    void insert(Order order);

    List<Order> selectByPage(Pageable pageable, @Param("date") Timestamp date, @Param("id") Integer customerId);

    List<PaymentMethod> selectAllPaymentMethod();

    void updateOrderStatus(Integer orderStatusId, Integer orderId);

    Integer countOrder(Integer userId);

    Order selectById(Integer orderId);

}
