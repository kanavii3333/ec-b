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
public String detail(Integer productId, Model model) {
Product product = productService.getProductByProductId(productId);
Integer quantity = product.getProductStock().getQuantity();
int sum = 0;
List<Integer> quantities = new ArrayList<>();
for (int i = 0; i <= quantity; i++) {
sum += i;
quantities.add(sum);

}
model.addAttribute("product", product);
model.addAttribute("quantities", quantities);
model.addAttribute("quantity", quantity);
return "productDetails";

}

}
