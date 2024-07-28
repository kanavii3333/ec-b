// package com.fullness.ec.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.SessionAttributes;
// import org.springframework.web.bind.support.SessionStatus;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.fullness.ec.form.CategoryForm;
// import com.fullness.ec.service.ProductCategoryServiceImpl;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// @SessionAttributes
// @RequestMapping("categoryForm")
// @Controller
// public class RegisterCategoryProductController {
// @ModelAttribute("categoryForm")
// public CategoryForm setupForm() {
// return new CategoryForm();
// }

// @Autowired
// ProductCategoryServiceImpl productCategoryServiceImpl;

// @GetMapping("input")
// public String input() {
// return "input";
// }

// @PostMapping("confirm")
// public String confirm(@ModelAttribute("categoryForm") CategoryForm
// categoryForm, BindingResult bindingResult,
// Model model) {
// if (bindingResult.hasErrors())
// return "input";
// model.addAttribute("categoryForm", categoryForm);
// return "confirm";
// }

// @PostMapping("execute")
// public String execute(@ModelAttribute("categoryForm") CategoryForm
// categoryForm,
// RedirectAttributes redirectAttributes) {

// return "redirect:/complete";
// }

// @GetMapping("complete")
// public String complete(@ModelAttribute("categoryForm") CategoryForm
// categoryForm, SessionStatus sessionStatus) {
// sessionStatus.setComplete();
// return "complete";
// }

// }
