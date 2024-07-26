package com.fullness.ec.controller;

import java.util.List;
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
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.service.ProductCategoryService;
import com.fullness.ec.service.ProductService;


@Controller
@RequestMapping
@SessionAttributes("categoryList")
public class SearchProductController {
    @ModelAttribute("categoryForm")
    public CategoryForm setUpForm(){return new CategoryForm();}
    @Autowired ProductService service;
    @Autowired ProductCategoryService categoryService;

    @GetMapping("productlist")
    public String searchList(@PageableDefault(page=0,size=5)Pageable pageable, Model model) {
        Page<Product> products = service.selectProductByPage(pageable);
        model.addAttribute("pageUrl", "/product/productlist?");
        model.addAttribute("products",products); 
        model.addAttribute("next",pageable.getPageNumber()+2);
        model.addAttribute("prev",pageable.getPageNumber());
        model.addAttribute("categoryList", categoryService.selectAll());
        return "product/productlist";
    }
}
