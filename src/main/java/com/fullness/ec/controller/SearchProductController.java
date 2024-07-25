package com.fullness.ec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullness.ec.entity.ProductCategory;


@Controller
@RequestMapping("product")
public class SearchProductController {
    @GetMapping("productlist")
    public String searchList(@PageableDefault(page=0,size=5)Pageable pageable, Model model) {
    Page<Product> products = products.selectItemsByPage(pageable);
    model.addAttribute("pageUrl", "/item/list?");
    model.addAttribute("products",products); 
    model.addAttribute("next",pageable.getPageNumber()+2);
    model.addAttribute("prev",pageable.getPageNumber());
    return "item/list";
    }
    
}
