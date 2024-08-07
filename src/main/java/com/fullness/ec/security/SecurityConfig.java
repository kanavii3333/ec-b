package com.fullness.ec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Configuration
  @Order(2)
  public static class BackendConfigrationAdapter {

    @Autowired
    private EmployeeUserDetailsService backendUserDetailsService;
    @Autowired
    private PasswordEncoder encoder;

    // @Autowired
    // public void authenticationManager(AuthenticationManagerBuilder builder,
    // PasswordEncoder encoder) throws Exception {
    // builder.userDetailsService(backendUserDetailsService).passwordEncoder(encoder);
    // }

    @Bean
    public SecurityFilterChain backendSecurityFilterChain(HttpSecurity http) throws Exception {
      http.securityMatcher("/admin/**")
          .authorizeHttpRequests(authz -> authz
              .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() // エラー画面は認証対象外
              .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静的リソースは認証対象外
              .requestMatchers("/admin/login", "/admin/menu").permitAll() // ログイン画面は認証対象外
              .requestMatchers("/admin/**").hasRole("ADMIN") // 認証対象
              .anyRequest()
              .authenticated() // その他は認証対象
          );
      http.formLogin(login -> login
          .loginProcessingUrl("/admin/auth") // 認証処理を起動するURL
          .loginPage("/admin/login") // ログイン認証画面のURL
          .usernameParameter("username") // 認証リクエストのユーザパラメータのキー名の指定
          .passwordParameter("password") // 認証リクエストのパスワードパスワードのキー名の指定
          .defaultSuccessUrl("/admin/menu") // ログイン成功時のURL
          .failureUrl("/admin/login") // ログイン失敗時のURL
          .permitAll() // ログイン画面は認証対象外
      );
      http.logout(logout -> logout
          .logoutUrl("/admin/logout") // ログアウト処理をするURL
          .logoutSuccessUrl("/admin/menu") // ログアウト成功時のURL
          .invalidateHttpSession(true) // ログアウト時はセッションを破棄する
          .deleteCookies("JSESSIONID") // ログアウト時はクッキーを削除する
          .clearAuthentication(true) // ログアウト時は認証情報をクリアする
          .permitAll());

      AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
      builder.userDetailsService(backendUserDetailsService).passwordEncoder(encoder);
      http.authenticationManager(builder.build());

      return http.build();
    }

  }

  @Configuration
  @Order(1)
  public static class FrontendConfigrationAdapter {

    @Autowired
    private CustomerUserDetailsService frontendUserDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    // @Autowired
    // public void authenticationManager(AuthenticationManagerBuilder builder,
    // PasswordEncoder encoder) throws Exception {
    // builder.userDetailsService(frontendUserDetailsService).passwordEncoder(encoder);
    // }

    @Bean
    public SecurityFilterChain frontendSecurityFilterChain(HttpSecurity http) throws Exception {
      http.securityMatcher("/customer/**")
          .authorizeHttpRequests(authz -> authz
              .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() // エラー画面は認証対象外
              .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静的リソースは認証対象外
              .requestMatchers("/customer/login", "/customer/top", "/customer/productDetails",
                  "/customer/searchproduct","/customer/registercustomer/**")
              .permitAll() // ログイン画面は認証対象外
              .requestMatchers("/customer/**").hasRole("CUSTOMER")
              .anyRequest()
              .authenticated() // その他は認証対象
          );
      http.formLogin(login -> login
          .loginProcessingUrl("/customer/auth") // 認証処理を起動するURL
          .loginPage("/customer/login") // ログイン認証画面のURL
          .usernameParameter("mailAddress") // 認証リクエストのユーザパラメータのキー名の指定
          .passwordParameter("password") // 認証リクエストのパスワードパスワードのキー名の指定
          .defaultSuccessUrl("/customer/top") // ログイン成功時のURL
          .failureUrl("/customer/login") // ログイン失敗時のURL
          .permitAll() // ログイン画面は認証対象外
      );
      http.logout(logout -> logout
          .logoutUrl("/customer/logout") // ログアウト処理をするURL
          .logoutSuccessUrl("/customer/login") // ログアウト成功時のURL
          .invalidateHttpSession(true) // ログアウト時はセッションを破棄する
          .deleteCookies("JSESSIONID") // ログアウト時はクッキーを削除する
          .clearAuthentication(true) // ログアウト時は認証情報をクリアする
          .permitAll());

      AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
      builder.userDetailsService(frontendUserDetailsService).passwordEncoder(encoder);
      http.authenticationManager(builder.build());
      return http.build();
    }
  }
}
