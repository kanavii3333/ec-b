package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fullness.ec.service.EmployeeService;

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
}
