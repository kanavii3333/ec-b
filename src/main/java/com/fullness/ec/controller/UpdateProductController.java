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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@SessionAttributes({"product","imageByte","filename"})
@RequestMapping("updateproduct")
@Controller
public class UpdateProductController {
    
    @Autowired ProductServiceImpl productServiceImpl; 
    @Autowired ProductCategoryServiceImpl productCategoryServiceImpl;
    @GetMapping("input")
    public String input(@RequestParam("productId")Integer productId,Model model){
        Product product = productServiceImpl.getProductByProductId(productId);
        model.addAttribute("product", product);
        model.addAttribute("categoryList",productCategoryServiceImpl.selectAll());
        return "product/update/input";
    }

    @PostMapping("confirm")
	public String confirm(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file, Model model) throws IOException{
        List<ProductCategory> categoryList = productCategoryServiceImpl.selectAll();
        for (ProductCategory category : categoryList) {
            if (category.getProductCategoryId() == product.getProductCategory().getProductCategoryId()) {
                product.getProductCategory().setProductCategoryName(category.getProductCategoryName());
                break;
            }
        }
        if(!file.isEmpty()){
            model.addAttribute("image", ImageUploadHelper.createBase64ImageString(file));
            model.addAttribute("imageByte", file.getBytes());
            model.addAttribute("filename",file.getOriginalFilename());
            product.setImageUrl(null);
        } else {
            model.addAttribute("image", null);
            model.addAttribute("imageByte", null);
            model.addAttribute("filename",null);
        }
        model.addAttribute("product", product);
        return "product/update/confirm";
    }
    
    @PostMapping("execute")
    public String execute(
        @ModelAttribute("product") Product product, 
        @ModelAttribute("productCategory") ProductCategory productCategory, 
        @ModelAttribute("imageByte") byte[] imageByte,
        @ModelAttribute("filename") String filename,
        RedirectAttributes redirectAttributes
        ){
            if(product.getImageUrl()==null){
                product.setImageUrl(ImageUploadHelper.uploadFile(filename, imageByte));
            }
            productServiceImpl.updateProduct(product);
            return "redirect:comlete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("product") Product product,SessionStatus sessionStatus,Model model){
        model.addAttribute("name", product.getProductName());    
        sessionStatus.setComplete();
        return "complete";
    }
}
