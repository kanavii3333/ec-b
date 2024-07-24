package com.fullness.ec.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fullness.ec.entity.EmpAccount;

public class EmployeeUserDetails implements UserDetails {
    private final EmpAccount employee;
    private final Collection<GrantedAuthority> authorities;
    public EmployeeUserDetails(EmpAccount employee, Collection<GrantedAuthority> authorities){
        this.employee=employee;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getName();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
}
