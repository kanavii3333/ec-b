package com.fullness.ec.controller;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.CustomerForm;
import com.fullness.ec.form.CustomerFormValidator;
import com.fullness.ec.form.GroupOrder;
import com.fullness.ec.form.OrderDetailFormValidator;
import com.fullness.ec.form.UpdateProductForm;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.CustomerServiceImpl;

@SessionAttributes("customerForm")
@Controller
@RequestMapping("/customer/registercustomer")
public class RegisterCustomerController {
    @ModelAttribute("customerForm")
    private CustomerForm setup(){
        return new CustomerForm(); 
    }

    @Autowired
    CustomerFormValidator validator;
    @InitBinder("customerForm")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired CustomerServiceImpl service;

    @GetMapping("input")
    public String input(){
        return "customer/register/input";
    }

    @PostMapping("confirm")
    public String confirm(@Validated @ModelAttribute("customerForm") CustomerForm customerForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customerForm",bindingResult);
            redirectAttributes.addFlashAttribute("customerForm", customerForm);
            return "redirect:/customer/registercustomer/input";
        }
        model.addAttribute("customerForm", customerForm);
        return "customer/register/confirm";
    }

    @GetMapping("confirm")
    public String confirmGet() {
                return "customer/register/confirm";
    }

    @GetMapping("execute")
    public String execute(@ModelAttribute("customerForm") CustomerForm customerForm){
        customerForm.setCustomerRegisterDate(ZonedDateTime.now());
        service.registerCustomer(customerForm);
        return "redirect:/customer/registercustomer/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("customerForm") CustomerForm customerForm, Model model,
            SessionStatus sessionStatus) {
        if (customerForm.getCustomerName() == null)
            return "redirect:/admin/menu";
        sessionStatus.setComplete();
        return "customer/register/complete";
    }
}
