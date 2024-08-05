package com.fullness.ec.service;

import java.util.List;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.form.CustomerForm;

public interface CustomerService {
    List<Customer> selectAll();

    Customer getCustomerByMailAddress(String address);
    
    void registerCustomer(CustomerForm customer);
    
}
