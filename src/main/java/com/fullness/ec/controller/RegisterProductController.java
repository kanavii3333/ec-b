package com.fullness.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.service.ProductCategoryService;

@Controller
@RequestMapping("registerProduct")
public class RegisterProductController {
    @Autowired ProductCategoryService productCategoryService;
    @GetMapping("input")
    public String input(Model model){
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        model.addAttribute("categoryList", categoryList);
        return "input";
    }
    @PostMapping("confirm")
    public String confirm(){
        return "confirm";
    }
    @PostMapping("execute")
    public String execute(){
        return "execute";
    }
    @GetMapping("complete")
    public String complete(){
        return "complete";
    }
}
