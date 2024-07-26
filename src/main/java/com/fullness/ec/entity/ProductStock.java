package com.fullness.ec.entity;
import lombok.Data;

@Data
public class ProductStock {
    private Integer productStockId;
    private Integer quantity;
    private Integer productId;
}
