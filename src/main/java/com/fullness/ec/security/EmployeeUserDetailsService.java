package com.fullness.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.repository.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
    @Autowired EmployeeRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount employee = repository.selectByUsername(username);
        if(employee==null) throw new UsernameNotFoundException("ユーザーが存在しません");
        return new EmployeeUserDetails(employee, AuthorityUtils.createAuthorityList("ROLE_ADMIN")) {
        };
    }
    
}
