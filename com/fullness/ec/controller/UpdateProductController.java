package com.fullness.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.form.ProductForm;
import org.springframework.web.bind.annotation.RequestParam;

@SessionAttributes({"productForm","categoryList"})
@RequestMapping("admin/updateproduct")
@Controller
public class UpdateProductController {

    //@PostMapping("input")

    @PostMapping("confirm")
	public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm Model model){
        model.addAttribute("productForm",productForm);
        return "confirm";
    }
    @PostMapping("execute")
    public String execute(@ModelAttribute("productForm") ProductForm productForm,RedirectAttributes redirectAttributes){
       redirectAttributes.addFlashAttribute("productForm",productForm);
       return "redirect:comlete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm,SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "complete";
    }
	

}
