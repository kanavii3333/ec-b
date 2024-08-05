package com.fullness.ec.form;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class CustomerForm {
   private String customerName; 
   private String customerNameKana; 
   private String customerAddress1; 
   private String customerAddress2; 
   private String customerPhoneNumber; 
   private String customerMailAdress;
   private String customerUserName; 
   private String customerPassword; 
   private ZonedDateTime customerRegisterDate; 
}
