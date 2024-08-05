package com.fullness.ec.entity;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Customer {
    private Integer customerId;
    private String customerName;
    private String customerNameKana;
    private String address1;
    private String address2;
    private String phone;
    private String mailAddress;
    private String username;
    private String password;
    private ZonedDateTime registeredDate;
}
