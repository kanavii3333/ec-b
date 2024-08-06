package com.fullness.ec.entity;

import lombok.Data;

@Data
public class OrderDetail {
   private Integer orderDetailId; 
   private Integer count; 
   private Product product;
   private Integer customerId;
   private Integer orderId;
}
