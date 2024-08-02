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
import com.fullness.ec.form.UpdateProductForm;
import com.fullness.ec.form.UpdateProductFormValidator;
import com.fullness.ec.helper.ImageUploadHelper;
import com.fullness.ec.helper.ProductConverter;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

@SessionAttributes({ "updateProductForm", "imageByte", "filename" })
@RequestMapping("admin/updateproduct")
@Controller
public class UpdateProductController {
    @ModelAttribute("updateProductForm")
    public UpdateProductForm setupForm() {
        return new UpdateProductForm();
    }

    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    ProductCategoryServiceImpl productCategoryServiceImpl;

    @Autowired
    UpdateProductFormValidator validator;

    @InitBinder("updateProductForm")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String input(@RequestParam(name = "productId", required = false) Integer productId, Model model) {
        Object error = model.getAttribute("org.springframework.validation.BindingResult.updateProductForm");
        if (error == null) {
            UpdateProductForm updateProductForm = ProductConverter
                    .convertToForm(productServiceImpl.getProductByProductId(productId));
            model.addAttribute("updateProductForm", updateProductForm);
        }

        model.addAttribute("categoryList", productCategoryServiceImpl.selectAll());
        return "product/update/input";

    }

    @PostMapping("confirm")
    public String confirm(@Validated @ModelAttribute("updateProductForm") UpdateProductForm updateProductForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {
        List<ProductCategory> categoryList = productCategoryServiceImpl.selectAll();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateProductForm",
                    bindingResult);
            updateProductForm.setFile(null);
            return "redirect:/admin/updateproduct/input?productId=" + updateProductForm.getProductId();
        }
        for (ProductCategory category : categoryList) {
            if (category.getProductCategoryId() == updateProductForm.getCategoryId()) {
                updateProductForm.setCategoryName(category.getProductCategoryName());
                break;
            }
        }
        updateProductForm.setImage(ImageUploadHelper.createBase64ImageString(updateProductForm.getFile()));
        model.addAttribute("imageByte", updateProductForm.getFile().getBytes());
        model.addAttribute("filename", updateProductForm.getFile().getOriginalFilename());
        if (!updateProductForm.getFile().isEmpty()) {
            updateProductForm.setImageUrl(null);
        } else {
            updateProductForm.setImage(null);
        }
        model.addAttribute("updateProductForm", updateProductForm);
        return "product/update/confirm";
    }

    @GetMapping("confirm")
    public String confirmGet(@ModelAttribute("updateProductForm") UpdateProductForm productForm, Model model)
            throws IOException {

        return "product/update/confirm";
    }

    @PostMapping("execute")
    public String execute(
            @ModelAttribute("updateProductForm") UpdateProductForm updateProductForm,
            @ModelAttribute("imageByte") byte[] imageByte,
            @ModelAttribute("filename") String filename,
            RedirectAttributes redirectAttributes) {
        if (updateProductForm.getImageUrl() == null) {
            updateProductForm.setImageUrl(ImageUploadHelper.uploadFile(filename, imageByte));
        }
        Product product = new Product();
        product.setProductId(updateProductForm.getProductId());
        product.setProductName(updateProductForm.getProductName());
        product.setPrice(updateProductForm.getPrice());
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(updateProductForm.getCategoryId());
        productCategory.setProductCategoryName(updateProductForm.getCategoryName());
        product.setProductCategory(productCategory);
        ProductStock productStock = new ProductStock();
        productStock.setProductId(updateProductForm.getProductId());
        productStock.setProductStockId(updateProductForm.getStockId());
        productStock.setQuantity(updateProductForm.getQuantity());
        product.setProductStock(productStock);
        product.setImageUrl(updateProductForm.getImageUrl());
        productServiceImpl.updateProduct(product);
        return "redirect:/admin/updateproduct/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("updateProductForm") UpdateProductForm updateProductForm,
            SessionStatus sessionStatus, Model model) {
        if (updateProductForm.getProductId() == null)
            return "redirect:/admin/menu";
        model.addAttribute("name", updateProductForm.getProductName());
        sessionStatus.setComplete();
        return "product/update/complete";
    }
}
