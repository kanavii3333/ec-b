package com.fullness.ec.form;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class OrderForm {
   private Integer orderId; 
   private ZonedDateTime orderDate; 
   private Integer amountTotal; 
   private Integer orderStatusId; 
   private Integer paymentMethodId; 
   private Integer customerId; 
}
