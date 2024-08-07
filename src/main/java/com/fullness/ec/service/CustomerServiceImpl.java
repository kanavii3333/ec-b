package com.fullness.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.CustomerForm;
import com.fullness.ec.helper.CustomerConverter;
import com.fullness.ec.helper.EmployeeConverter;
import com.fullness.ec.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired 
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> selectAll(){
        return customerRepository.selectAll();
    };

    @Override
    public Customer getCustomerByMailAddress(String address){
        Customer customer = customerRepository.selectByMailAddress(address);
        return customer;

    };

    @Override
    public void registerCustomer(CustomerForm customerForm){
           Customer customer = CustomerConverter.convertToEntity(customerForm);
           customer.setPassword(passwordEncoder.encode(customer.getPassword()));
           customerRepository.insert(customer);
    }

    public List<Customer> getCustomerList() {
        return customerRepository.selectAll();
    };
    
}
