package com.fullness.ec.helper;

import java.sql.Timestamp;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.form.CustomerForm;

public class CustomerConverter {
    public static Customer convertToEntity(CustomerForm customerForm){
        Customer customer = new Customer();
        customer.setCustomerName(customerForm.getCustomerName());
        customer.setCustomerNameKana(customerForm.getCustomerNameKana());
        customer.setAddress1(customerForm.getCustomerAddress1());
        customer.setAddress2(customerForm.getCustomerAddress2());
        customer.setPhone(customerForm.getCustomerPhoneNumber());
        customer.setMailAddress(customerForm.getCustomerMailAdress());
        customer.setUsername(customerForm.getCustomerUsername());
        customer.setPassword(customerForm.getCustomerPassword());
        customer.setRegisteredDate(Timestamp.from(customerForm.getCustomerRegisterDate().toInstant()));
        return customer;
    }
    
}
