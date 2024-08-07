package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fullness.ec.form.CustomerForm;
import com.fullness.ec.service.CustomerServiceImpl;

@SessionAttributes("customerForm")
@Controller
@RequestMapping("/customer/registercustomer")
public class RegisterCustomerController {
    @Autowired CustomerServiceImpl service;

    @ModelAttribute("customerForm")
    private CustomerForm setup(){
        return new CustomerForm(); 
    }

    @GetMapping("input")
    public String input(){
        return "/customer/register/input";
    }

    @PostMapping("confirm")
    public String confirm(){
        return "/customer/register/confirm";
    }

    @GetMapping("execute")
    public String execute(){
        return "redirect:/customer/registercustomer/complete";
    }

    @GetMapping("complete")
    public String complete(){
        return "/customer/register/input";
    }
}
