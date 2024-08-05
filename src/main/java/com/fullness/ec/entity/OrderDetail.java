package com.fullness.ec.entity;

import lombok.Data;

@Data
public class OrderDetail {
   private Integer orderDetailId; 
   private Integer count; 
   private Integer productId; 
}
