package com.fullness.ec.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class Order {
       private Integer orderId;
       private Timestamp orderDate;
       private Integer amountTotal;
       private List<OrderDetail> orderDetailList;
       private OrderStatus orderStatus;
       private PaymentMethod payMethod;
       private Integer customerId;
       private String customerName;
       private String username;
}
