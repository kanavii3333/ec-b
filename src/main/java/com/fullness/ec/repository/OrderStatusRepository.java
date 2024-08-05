package com.fullness.ec.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.OrderStatus;

@Mapper
public interface OrderStatusRepository {
    List<OrderStatus> selectAll();
    
}
