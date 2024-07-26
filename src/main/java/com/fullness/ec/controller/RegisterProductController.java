package com.fullness.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.service.ProductCategoryService;

@Controller
@RequestMapping("registerProduct")
@SessionAttributes("productForm")
public class RegisterProductController {
    @ModelAttribute("productForm")
    public ProductForm setupForm(){return new ProductForm();}
    @Autowired ProductCategoryService productCategoryService;
    @GetMapping("input")
    public String input(Model model){
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        model.addAttribute("categoryList", categoryList);
        return "product/register/input";
    }
    @PostMapping("confirm")
    public String confirm(@ModelAttribute("productForm") ProductForm productForm, Model model){
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        for(ProductCategory category:categoryList){
            if(category.getProductCategoryId()==productForm.getCategoryId()) {
                model.addAttribute("productCategoryName", category.getProductCategoryName());
                break;
            }
        }
        model.addAttribute("image", ImageUploadHelper.createBase64ImageString(productForm.getFile()));
        return "product/register/confirm";
    }
    @PostMapping("execute")
    public String execute(){
        return "redirect:complete";
    }
    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm, SessionStatus sessionStatus, Model model){
        model.addAttribute("productName", productForm.getProductName());
        sessionStatus.isComplete();
        return "product/register/complete";
    }
}
