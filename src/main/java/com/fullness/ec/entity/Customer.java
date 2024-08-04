package com.fullness.ec.entity;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Customer {
    
   private Integer customerId; 
   private Integer customerName; 
   private String  customerNameKana; 
   private String  customerAddress1; 
   private String  customerAddress2; 
   private String  customerPhoneNumber; 
   private String  customerMailAdress;
   private String  customerUserName; 
   private String  customerPassword; 
   private ZonedDateTime customerRegisterDate; 
}
