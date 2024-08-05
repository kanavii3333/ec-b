package com.fullness.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.repository.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired CustomerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String address) throws UsernameNotFoundException {
        Customer customer = repository.selectByMailAddress(address);
        if(customer==null) throw new UsernameNotFoundException("ユーザーが存在しません");
        return new CustomerUserDetails(customer, AuthorityUtils.createAuthorityList("CUSTOMER")) {
        };
    }
    
}
