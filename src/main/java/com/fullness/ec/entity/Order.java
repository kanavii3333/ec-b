package com.fullness.ec.entity;

import java.time.ZonedDateTime;
import java.util.List;

import com.fullness.ec.form.OrderDetailForm;

import lombok.Data;

@Data
public class Order {
       private Integer orderId; 
       private ZonedDateTime orderDate; 
       private Integer amountTotal; 
       private List<OrderDetailForm> orderDetailFormList; 
       private OrderStatus orderStatus; 
       private PaymentMethod payMethod; 
       private Integer customerId; 
}
