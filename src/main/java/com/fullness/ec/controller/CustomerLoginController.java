package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.fullness.ec.entity.Product;
import com.fullness.ec.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class CustomerLoginController {
    @Autowired
    ProductService service;

    @GetMapping("login")
    public String form() {
        return "loginCustomer";
    }

    @GetMapping("top")
    public String login(HttpSession session, SessionStatus sessionStatus,
            @PageableDefault(page = 0, size = 12) Pageable pageable, Model model) {
        // メニュー遷移したときにセッションを破棄する
        session.removeAttribute("employeeForm");
        session.removeAttribute("employeeList");
        session.removeAttribute("product");
        session.removeAttribute("categoryForm");
        session.removeAttribute("productForm");
        session.removeAttribute("imageByte");
        session.removeAttribute("productCategory");
        session.removeAttribute("products");
        session.removeAttribute("filename");

        Page<Product> products = service.selectProductByPage(pageable, null);
        model.addAttribute("pageUrl", "/customer/searchproduct?");
        model.addAttribute("products", products);
        model.addAttribute("next", pageable.getPageNumber() + 2);
        model.addAttribute("prev", pageable.getPageNumber());
        // sessionStatus.setComplete();
        return "top";
    }

    @GetMapping("logout")
    public String logout() {
        return "redirect:/customer/login";
    }
}
