package com.fullness.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.form.ProductForm;

@SessionAttributes({"productForm","categoryList"})
@RequestMapping("admin/updateproduct")
@Controller
public class UpdateProductController {
    
    // @ModelAttribute("productForm")
    // public productForm setupForm(){
    //     return new ProductForm();
    // }
    // @Autowired ProductFormValidator validator;
    // @InitBinder("productForm")
    // public void initBinder(WebDataBinder binder){
    //     binder.addValidators(validator);
    // }
    // @Autowired ProductService productService; 
    // @Autowired ProductCategoryService productCategoryService;
    // @PostMapping("input")
    // public String input(Model model){
    //     model.addattridute("product",productService.getProductById());//←のメソッドに選択した商品のIdを入れる
    //     model.addattridute("productCategory",productCategoryService.selectAll());
    //     return "input";
    // }
    
    @PostMapping("confirm")
	public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return"input";
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
