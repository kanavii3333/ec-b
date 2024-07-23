package com.fullness.ec.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullness.ec.entity.Account;
import com.fullness.ec.repository.AccountRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired AccountRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.selectByUsername(username);
        if(account==null) throw new UsernameNotFoundException("ユーザーが存在しません");
        return new AccountUserDetails(account, this.getAuthorities(account.getRole().getName())) {
        };
    }
    // private Collection<GrantedAuthority> getAuthorities(String role) {
    //     switch(role){
    //         case "admin":
    //             return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER","ROLE_GUEST");
    //         case "user":
    //             return AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_GUEST");
    //         case "guest":
    //             return AuthorityUtils.createAuthorityList("ROLE_GUEST");
    //         default:
    //             return null;
    //     }
    // }
    
}
