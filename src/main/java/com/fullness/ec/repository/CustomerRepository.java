package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.Customer;

@Mapper
public interface CustomerRepository {
    public Customer selectByMailAddress(String MailAddress);
}
