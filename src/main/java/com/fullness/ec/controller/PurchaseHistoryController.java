package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fullness.ec.entity.Order;
import com.fullness.ec.repository.OrderRepository;
import com.fullness.ec.service.OrderServiceImpl;
import com.fullness.ec.service.ProductService;

import java.util.List;

@SessionAttributes("orders")
@Controller
@RequestMapping("/customer/history")
public class PurchaseHistoryController {
    @Autowired OrderServiceImpl service;
    @Autowired ProductService productService;
    @GetMapping("/list")
    public String histry(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model){
        Page<Order> orders = service.getOrderList(pageable);
        model.addAttribute("pageUrl", "/customer/history/list?");
        model.addAttribute("orders", orders);
        model.addAttribute("next", pageable.getPageNumber() + 2);
        model.addAttribute("prev", pageable.getPageNumber());
        return "/order/history/purchaselist";
    }

    @PostMapping("/detail")
    public String detail(@ModelAttribute("orders")Page<Order> orders ,@RequestParam("orderId")Integer orderId, Model model){
        for(Order order:orders){
            if(orderId==order.getOrderId()) model.addAttribute("order", order);
        }
        return "/order/history/detail";
    }
}
