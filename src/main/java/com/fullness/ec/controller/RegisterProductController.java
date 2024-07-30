package com.fullness.ec.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.ProductFormValidator;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@Controller
@RequestMapping("registerproduct")
@SessionAttributes({ "productForm", "imageByte", "productCategory" })
public class RegisterProductController {
    @ModelAttribute("productForm")
    public ProductForm setupForm() {
        return new ProductForm();
    }

    @Autowired
    ProductCategoryServiceImpl productCategoryService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired ProductFormValidator validator;
    @InitBinder("productForm")
    public void InitBinder(WebDataBinder binder){
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String input(Model model) {
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        model.addAttribute("categoryList", categoryList);
        return "product/register/input";
    }

    @PostMapping("confirm")
    public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model) throws IOException {
        List<ProductCategory> categoryList = productCategoryService.selectAll();
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productForm",productForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productForm", bindingResult);
            return "redirect:/registerproduct/input";
        }
        for (ProductCategory category : categoryList) {
            if (category.getProductCategoryId() == productForm.getCategoryId()) {
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
            @ModelAttribute("imageByte") byte[] imageByte) {
        productService.addProduct(productForm,imageByte);
        return "redirect:complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm, SessionStatus sessionStatus,
            Model model) {
        model.addAttribute("productName", productForm.getProductName());
        sessionStatus.setComplete();
        return "product/register/complete";
    }
}
