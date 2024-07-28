package com.fullness.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@SessionAttributes({"productForm","categoryList"})
@RequestMapping("updateproduct")
@Controller
public class UpdateProductController {
    
    @ModelAttribute("productForm")
    public ProductForm setupForm(){
        return new ProductForm();
    }

    @Autowired ProductServiceImpl productServiceImpl; 
    @Autowired ProductCategoryServiceImpl productCategoryServiceImpl;
    @GetMapping("input")
    public String input(@RequestParam("productId")Integer productId,Model model){
        model.addAttribute("product",productServiceImpl.getProductByProductId(productId));
        model.addAttribute("productCategory",productCategoryServiceImpl.selectAll());
        return "update/input";
    }
    
    @PostMapping("confirm")
	public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult, Model model){
         List<ProductCategory> categoryList = productCategoryServiceImpl.selectAll();
        for(ProductCategory category:categoryList){
            if(category.getProductCategoryId()==productForm.getCategoryId()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(category.getProductCategoryId());
                productCategory.setProductCategoryName(category.getProductCategoryName());
                model.addAttribute("productCategoryName", category.getProductCategoryName());
                break;
            }
        }
        model.addAttribute("productForm",productForm);
        model.addAttribute("image", ImageUploadHelper.createBase64ImageString(productForm.getFile()));
        return "confirm";
    }
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("productForm") ProductForm productForm, 
        @ModelAttribute("productCategory") ProductCategory productCategory, 
        @ModelAttribute("imageByte") byte[] imageByte,
        RedirectAttributes redirectAttributes
        ){
            productServiceImpl.addProduct(productForm, imageByte);
            redirectAttributes.addFlashAttribute("productForm",productForm);
            return "redirect:comlete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm,SessionStatus sessionStatus,Model model){
        model.addAttribute("image", ImageUploadHelper.createBase64ImageString(productForm.getFile()));
        sessionStatus.setComplete();
        return "complete";
    }
	

}
