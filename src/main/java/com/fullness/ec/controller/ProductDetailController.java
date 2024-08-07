package com.fullness.ec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fullness.ec.entity.Product;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.service.ProductServiceImpl;

@Controller
@RequestMapping("customer")
@SessionAttributes({ "product" })
public class ProductDetailController {
    @ModelAttribute("product")
    public Product setupForm() {
        return new Product();
    }

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("productDetails")
    public String detail(@ModelAttribute("productId") Integer productId, Model model) {
        Product product = productService.getProductByProductId(productId);
        Integer quantity = product.getProductStock().getQuantity();
        List<Integer> quantities = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            quantities.add(i);

        }
        OrderDetailForm orderDetailForm = new OrderDetailForm();
        orderDetailForm.setPrice(product.getPrice());
        orderDetailForm.setProductId(product.getProductId());
        orderDetailForm.setProductName(product.getProductName());
        orderDetailForm.setCount(0);

        model.addAttribute("orderDetailForm", orderDetailForm);
        model.addAttribute("product", product);
        model.addAttribute("quantities", quantities);
        model.addAttribute("quantity", quantity);
        return "productDetails";

    }

}
