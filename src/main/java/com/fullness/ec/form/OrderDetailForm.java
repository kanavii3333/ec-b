package com.fullness.ec.form;

import lombok.Data;

@Data
public class OrderDetailForm {
   private Integer orderDetailId;
   private Integer orderId;
   private Integer productId;
   private Integer count;
   private Integer customerId;
   private Integer stock;
}
