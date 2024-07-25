package com.fullness.ec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullness.ec.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class AccountLoginController {

    @Autowired EmployeeService employeeService;
    @GetMapping("loginEmployee")
    public String form(){
        return "login";
    }

    @GetMapping("menu")
    public String login(){
        return "menu";
    }

    @GetMapping("logoutEmployee")
    public String logout() {
        return "redirect:menu";
    }
}
