package com.fullness.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private EmployeeUserDetailsService service;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service).passwordEncoder(encoder);
    }

//     @Bean
//     public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
//             PasswordEncoder passwordEncoderencoder) {
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(service);
//         authenticationProvider.setPasswordEncoder(encoder);
//         return new ProviderManager(authenticationProvider);
//     }

    @Bean
    public WebSecurityCustomizer webCustomizer() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        return (web) -> web.httpFirewall(firewall).ignoring().requestMatchers("/public/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.formLogin(login->login.loginProcessingUrl("/auth_customer").loginPage("/loginCustomer")
        // .usernameParameter("mailAddress").passwordParameter("password").defaultSuccessUrl("/menu").failureUrl("/login").permitAll());

        http.formLogin(login -> login
                .loginProcessingUrl("/auth_employee")
                .loginPage("/loginEmployee")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/menu", true)// ここにtrueを付けました。
                .failureUrl("/loginEmployee?error=true")// ここに?error=trueを付けました
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/logoutEmployee")
                .logoutSuccessUrl("/menu")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSONID")
                .clearAuthentication(true)
                .permitAll());

        http.authorizeHttpRequests(authz -> authz
                .dispatcherTypeMatchers(DispatcherType.ERROR)
                .permitAll()
                .requestMatchers(PathRequest.toStaticResources()
                .atCommonLocations())
                .permitAll()
                // .requestMatchers("/**").permitAll() //全てセキュリティオフ
                .requestMatchers("/login", "/menu", "/img/**").permitAll()
                .requestMatchers("/logout", "/registerproduct/**", "/deleteproduct/**", "/updateproduct/**",
                        "/productlist", "/registeraccount/**", "/registerproductcategory/**")
                .authenticated()
                .anyRequest().authenticated());

        return http.build();
    }
}
