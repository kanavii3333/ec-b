package com.fullness.ec.entity;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Order {
       private Integer orderId; 
       private ZonedDateTime orderDate; 
       private Integer amountTotal; 
       private OrderDetail orderDetail; 
       private OrderStatus orderStatus; 
       private PaymentMethod payMethod; 
       private Integer customerId; 
}
