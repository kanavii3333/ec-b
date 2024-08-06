package com.fullness.ec.form;

import lombok.Data;

@Data
public class OrderDetailForm {
   private Integer orderDetailId;
   private Integer orderId;
   private Integer productId;
   private String productName;
   private Integer price;
   private Integer count; //買いたい個数
   private Integer customerId;
   private Integer stock;
}
