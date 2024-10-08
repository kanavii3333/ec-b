package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.ProductStock;

@Mapper
public interface StockRepository {
    public void insert(ProductStock productStock);

    public void update(ProductStock productStock);

    public ProductStock selectByProductId(Integer productId);
}
