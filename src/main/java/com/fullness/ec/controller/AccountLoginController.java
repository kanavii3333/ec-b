package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullness.ec.form.FormEmployeeLogin;
import com.fullness.ec.form.FormEmployeeLoginValidator;
import com.fullness.ec.service.EmployeeService;

@RestController
@RequestMapping
public class AccountLoginController {
    @Autowired FormEmployeeLoginValidator validator;
    @InitBinder("formEmployeeLogin")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validator);
    }
    @Autowired EmployeeService employeeService;
    @GetMapping("login")
    public String form(){
        return "login";
    }

    @PostMapping("menu")
    public String login(@Validated @ModelAttribute("formEmployeeLogin")FormEmployeeLogin formEmployeeLogin, BindingResult bindingResult, Model model){
        if(employeeService.employeeLogin(formEmployeeLogin)){
            return "menu";
        } else {
            return "login";
        }
    }
}
