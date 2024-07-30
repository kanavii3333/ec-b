package com.fullness.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class AccountLoginController {

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
