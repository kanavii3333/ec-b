package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
@SessionAttributes({ "categoryForm", "products" })

public class FrontSerchProductController {

    @ModelAttribute("categoryForm")
    public CategoryForm setUpForm() {
        return new CategoryForm();
    }

    @Autowired
    ProductServiceImpl service;
    @Autowired
    ProductCategoryServiceImpl categoryService;

    @GetMapping("searchproduct")
    public String input(@ModelAttribute("categoryForm") CategoryForm form,
            @PageableDefault(page = 0, size = 12) Pageable pageable,
            Model model, HttpSession session) {
        // 商品一覧遷移したときにセッションを破棄する
        session.removeAttribute("employeeForm");
        session.removeAttribute("employeeList");
        session.removeAttribute("product");
        session.removeAttribute("categoryForm");
        session.removeAttribute("productForm");
        session.removeAttribute("imageByte");
        session.removeAttribute("productCategory");
        session.removeAttribute("products");
        session.removeAttribute("filename");

        Page<Product> products = service.selectProductByPage(pageable,
                form.getProductCategoryId());
        model.addAttribute("pageUrl", "/customer/searchproduct?");
        model.addAttribute("products", products);
        model.addAttribute("next", pageable.getPageNumber() + 2);
        model.addAttribute("prev", pageable.getPageNumber());
        model.addAttribute("categoryList", categoryService.selectAll());
        return "frontproductlist";
    }

}