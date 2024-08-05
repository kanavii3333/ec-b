package com.fullness.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("admin")
public class AccountLoginController {

    @GetMapping("login")
    public String form(){
        return "login";
    }

    @GetMapping("menu")
    public String login(HttpSession session,SessionStatus sessionStatus){
        //メニュー遷移したときにセッションを破棄する
        session.removeAttribute("employeeForm");
        session.removeAttribute("employeeList");
        session.removeAttribute("product");
        session.removeAttribute("categoryForm");
        session.removeAttribute("productForm");
        session.removeAttribute("imageByte");
        session.removeAttribute("productCategory");
        session.removeAttribute("products");
        session.removeAttribute("filename");
         //sessionStatus.setComplete();
        return "menu";
    }

    @GetMapping("logout")
    public String logout() {
        return "redirect:menu";
    }
}
