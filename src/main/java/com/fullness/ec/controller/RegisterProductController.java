package com.fullness.ec.controller;

import java.io.IOException;
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

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryService;
import com.fullness.ec.service.ProductService;

@Controller
@RequestMapping("registerProduct")
@SessionAttributes({"productForm","imageByte","productCategory"})
public class RegisterProductController {
    @ModelAttribute("productForm")
    public ProductForm setupForm(){return new ProductForm();}
    @Autowired ProductCategoryService productCategoryService;
    @Autowired ProductService productService;
    @GetMapping("input")
    public String input(Model model){
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        model.addAttribute("categoryList", categoryList);
        return "product/register/input";
    }
    @PostMapping("confirm")
    public String confirm(@ModelAttribute("productForm") ProductForm productForm, Model model) throws IOException{
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        for(ProductCategory category:categoryList){
            if(category.getProductCategoryId()==productForm.getCategoryId()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(category.getProductCategoryId());
                productCategory.setProductCategoryName(category.getProductCategoryName());
                model.addAttribute("productCategory", productCategory);
                break;
            }
        }
        model.addAttribute("image", ImageUploadHelper.createBase64ImageString(productForm.getFile()));
        model.addAttribute("imageByte", productForm.getFile().getBytes());
        return "product/register/confirm";
    }
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("productForm") ProductForm productForm, 
        @ModelAttribute("productCategory") ProductCategory productCategory, 
        @ModelAttribute("imageByte") byte[] imageByte){
        productService.addProduct(productForm, productCategory, imageByte);
        return "redirect:complete";
    }
    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm, SessionStatus sessionStatus, Model model){
        model.addAttribute("productName", productForm.getProductName());
        sessionStatus.setComplete();
        return "product/register/complete";
    }
}
