package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.CategoryFormValidator;
import com.fullness.ec.form.ProductFormValidator;
import com.fullness.ec.service.ProductCategoryServiceImpl;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@SessionAttributes("categoryForm")
@RequestMapping("registerproductcategory")
@Controller
public class RegisterProductCategoryController {
    @ModelAttribute("categoryForm")
    public CategoryForm setupForm() {
        return new CategoryForm();
    }

    @Autowired
    ProductCategoryServiceImpl productCategoryServiceImpl;

    @Autowired
    CategoryFormValidator validator;

    @InitBinder("categoryForm")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String input() {
        return "product/categoryregister/input";
    }

    @PostMapping("confirm")
    public String confirm(@Validated @ModelAttribute("categoryForm") CategoryForm categoryForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryForm", categoryForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryForm",
                    bindingResult);
            return "redirect:/registerproductcategory/input";
        }
        model.addAttribute("categoryForm", categoryForm);
        return "product/categoryregister/confirm";
    }

    @GetMapping("confirm")
    public String confirmGet(Model model) {
        model.addAttribute("error", "不正な操作です");
        return "product/categoryregister/confirm";
    }

    @PostMapping("execute")
    public String execute(@ModelAttribute("categoryForm") CategoryForm categoryForm,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("categoryForm", categoryForm);

        productCategoryServiceImpl.addCategory(categoryForm);
        return "redirect:/registerproductcategory/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("categoryForm") CategoryForm categoryForm, Model model,
            SessionStatus sessionStatus) {
        model.addAttribute("categoryName", categoryForm.getProductCategoryName());
        sessionStatus.setComplete();
        return "product/categoryregister/complete";
    }

}
