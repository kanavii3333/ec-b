package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.OrderDetail;

@Mapper
public interface OrderDetailRepository {
   void insert(OrderDetail orderDetail);
}
