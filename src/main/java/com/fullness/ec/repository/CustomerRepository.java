package com.fullness.ec.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.Customer;

@Mapper
public interface CustomerRepository {
    void insert(Customer customer);
    Customer selectByMailAddress(String mailAddress);
    List<Customer> selectAll();
}
