package com.fullness.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired private AccountUserDetailsService service; 
    @Autowired private PasswordEncoder encoder;

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoderencoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(encoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public WebSecurityCustomizer webCustomizer(){
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        return (web)->web.httpFirewall(firewall).ignoring().requestMatchers("/public/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(login->login.loginProcessingUrl("/auth").loginPage("/login")
        .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/menu").failureUrl("/login").permitAll());

        http.logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
        .deleteCookies("JSESSONID").clearAuthentication(true).permitAll());

        http.authorizeHttpRequests(authz->authz.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .requestMatchers("/**").permitAll() //全てセキュリティオフ
        // .requestMatchers("/login").permitAll()
        // .requestMatchers("/menu","/logout","/session/**").authenticated()
        // .anyRequest().authenticated()
        );

        return http.build();
    }
}
