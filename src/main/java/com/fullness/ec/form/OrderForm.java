package com.fullness.ec.form;

import lombok.Data;

@Data
public class OrderForm {
   private Integer orderId; 
   private String orderDate; 
   private Integer amountTotal; 
   private Integer orderStatusId; 
   private Integer paymentMethodId; 
   private Integer customerId; 
}
