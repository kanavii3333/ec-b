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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.ProductFormValidator;
import com.fullness.ec.form.UpdateProductFormValidator;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@SessionAttributes({"productForm","imageByte","filename"})
@RequestMapping("updateproduct")
@Controller
public class UpdateProductController {
    @ModelAttribute("productForm")
    public ProductForm setupForm() {
        return new ProductForm();
    }
    @Autowired ProductServiceImpl productServiceImpl; 
    @Autowired ProductCategoryServiceImpl productCategoryServiceImpl;

    @Autowired UpdateProductFormValidator validator;
    @InitBinder("productForm")
    public void InitBinder(WebDataBinder binder){
        binder.addValidators(validator);
    }
    @GetMapping("input")
    public String input(@RequestParam(name = "productId", required = false)Integer productId, Model model){
            Object error = model.getAttribute("org.springframework.validation.BindingResult.productForm");
            if(error == null){
                ProductForm productForm = ProductConverter.convertToForm(productServiceImpl.getProductByProductId(productId));
                model.addAttribute("productForm", productForm);
                return "product/update/input";
            }
           
            model.addAttribute("categoryList",productCategoryServiceImpl.selectAll());
            return "product/update/input";
        
    }

    @PostMapping("confirm")
	public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model) throws IOException{
        List<ProductCategory> categoryList = productCategoryServiceImpl.selectAll();
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productForm", bindingResult);
            return "redirect:/updateproduct/input";
        }
        for (ProductCategory category : categoryList) {
            if (category.getProductCategoryId() == productForm.getCategoryId()) {
                productForm.setCategoryName(category.getProductCategoryName());
                break;
            }
        }
        model.addAttribute("image", ImageUploadHelper.createBase64ImageString(productForm.getFile()));
        model.addAttribute("imageByte", productForm.getFile().getBytes());
        model.addAttribute("filename",productForm.getFile().getOriginalFilename());
        if(!productForm.getFile().isEmpty()){
            productForm.setImageUrl(null);
        } else {
            model.addAttribute("image", null);
        }
        model.addAttribute("productForm", productForm);
        return "product/update/confirm";
    }
    
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("productForm") ProductForm productForm,  
        @ModelAttribute("imageByte") byte[] imageByte,
        @ModelAttribute("filename") String filename,
        RedirectAttributes redirectAttributes
        ){
            if(productForm.getImageUrl()==null){
                productForm.setImageUrl(ImageUploadHelper.uploadFile(filename, imageByte));
            }
            Product product = new Product();
            product.setProductId(productForm.getProductId());
            product.setProductName(productForm.getProductName());
            product.setPrice(productForm.getPrice());
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productForm.getCategoryId());
            productCategory.setProductCategoryName(productForm.getCategoryName());
            product.setProductCategory(productCategory);
            ProductStock productStock = new ProductStock();
            productStock.setProductId(productForm.getProductId());
            productStock.setProductStockId(productForm.getStockId());
            productStock.setQuantity(productForm.getQuantity());
            product.setProductStock(productStock);
            product.setImageUrl(productForm.getImageUrl());
            productServiceImpl.updateProduct(product);
            return "redirect:/updateproduct/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("productForm") ProductForm productForm,SessionStatus sessionStatus,Model model){
        if(productForm.getProductId()==null) return "redirect:/menu";
        model.addAttribute("name", productForm.getProductName());    
        sessionStatus.setComplete();
        return "product/update/complete";
    }
}
