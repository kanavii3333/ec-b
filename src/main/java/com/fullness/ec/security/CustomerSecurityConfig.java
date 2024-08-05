// package com.fullness.ec.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.firewall.DefaultHttpFirewall;

// import jakarta.servlet.DispatcherType;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class CustomerSecurityConfig {
//     @Autowired
//     private CustomerUserDetailsService service;
//     @Autowired
//     private PasswordEncoder encoder;

//     @Autowired
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth.userDetailsService(service).passwordEncoder(encoder);
//     }


//     @Bean
//     @Order(2)
//     public WebSecurityCustomizer webCustomizer() {
//         DefaultHttpFirewall firewall = new DefaultHttpFirewall();
//         return (web) -> web.httpFirewall(firewall).ignoring().requestMatchers("/public/**");
//     }

//     @Bean
//     @Order(2)
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .securityMatcher("/customer/**")
//             .authorizeHttpRequests(authz -> authz
//             .dispatcherTypeMatchers(DispatcherType.ERROR)
//             .permitAll()
//             .requestMatchers(PathRequest.toStaticResources()
//             .atCommonLocations())
//             .permitAll()
//             // .requestMatchers("/**").permitAll() //全てセキュリティオフ
//             .requestMatchers("/customer/login", "/customer/top", "/customer/auth").permitAll()
//             .requestMatchers("/customer/logout").hasAnyAuthority("CUSTOMER")
//             .anyRequest().authenticated());;

//         http.formLogin(login->login
//                     .loginProcessingUrl("/customer/auth")
//                     .loginPage("/customer/login")
//                     .usernameParameter("mailAddress")
//                     .passwordParameter("password")
//                     .defaultSuccessUrl("/customer/top", true)
//                     .failureUrl("/customer/login?=error=true")
//                     .permitAll());


//         http.logout(logout -> logout
//                 .logoutUrl("/customer/logout")
//                 .logoutSuccessUrl("/customer/menu")
//                 .invalidateHttpSession(true)
//                 .deleteCookies("JSESSONID")
//                 .clearAuthentication(true)
//                 .permitAll());

//         // http.authorizeHttpRequests(authz -> authz
//         //         .dispatcherTypeMatchers(DispatcherType.ERROR)
//         //         .permitAll()
//         //         .requestMatchers(PathRequest.toStaticResources()
//         //         .atCommonLocations())
//         //         .permitAll()
//         //         // .requestMatchers("/**").permitAll() //全てセキュリティオフ
//         //         .requestMatchers("/customer/login", "/customer/top", "/customer/auth").permitAll()
//         //         .requestMatchers("/customer/logout").hasAnyAuthority("CUSTOMER")
//         //         .anyRequest().authenticated());

//         return http.build();
//     }
// }
