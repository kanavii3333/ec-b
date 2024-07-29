package com.fullness.ec.controller;

import java.io.IOException;
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

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@Controller
@RequestMapping("deleteproduct")
@SessionAttributes({ "product", "imageByte", "productCategory" })
public class DeleteProductController {
    @ModelAttribute("productForm")
    public ProductForm setupForm() {
        return new ProductForm();
    }

    @Autowired
    ProductCategoryServiceImpl productCategoryService;
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("confirm")
	public String confirm(@ModelAttribute("productId")Integer productId, BindingResult bindingResult, Model model){
        Product product = productService.getProductByProductId(productId);
        model.addAttribute("product",product);
        return "product/delete/confirm";
    }
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("product") ProductForm product, 
        @ModelAttribute("productCategory") ProductCategory productCategory, 
        @ModelAttribute("imageByte") byte[] imageByte,
        RedirectAttributes redirectAttributes
        ){
            productService.deleteProduct(product.getProductId());
            redirectAttributes.addFlashAttribute("product",product);
            return "redirect:complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("product") Product product,SessionStatus sessionStatus,Model model){
        sessionStatus.setComplete();
        return "complete";
    }
}