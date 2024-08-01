package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@Controller
@RequestMapping("admin/deleteproduct")
@SessionAttributes({ "product" })
public class DeleteProductController {
    @ModelAttribute("product")
    public Product setupForm() {
        return new Product();
    }

    @Autowired
    ProductCategoryServiceImpl productCategoryService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("confirm")
	public String confirm(@ModelAttribute("productId")Integer productId, BindingResult bindingResult, Model model){
        Product product = productService.getProductByProductId(productId);
        model.addAttribute("product",product);
        return "product/delete/confirm";
    }
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("product") Product product, 
        RedirectAttributes redirectAttributes
        ){
            productService.deleteProduct(product.getProductId());
            redirectAttributes.addFlashAttribute("product",product);
            return "redirect:complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("product") Product product,SessionStatus sessionStatus,Model model){
        if(product.getProductId()==null) return "redirect:/menu";
        sessionStatus.setComplete();
        return "product/delete/complete";
    }
}