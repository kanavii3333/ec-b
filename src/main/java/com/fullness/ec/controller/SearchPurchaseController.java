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
import com.fullness.ec.entity.Customer;
import com.fullness.ec.service.CustomerService;
import com.fullness.ec.service.CustomerServiceImpl;
import com.fullness.ec.service.OrderServiceImpl;
import com.fullness.ec.service.ProductService;
import java.util.*;
import java.sql.Timestamp;

@SessionAttributes("orders")
@Controller
@RequestMapping
public class SearchPurchaseController {
    @Autowired OrderServiceImpl service;
    @Autowired CustomerServiceImpl customerService;
    @GetMapping("/admin/history")
    public String histry(@RequestParam(name="date", required = false)String dateString, @RequestParam(name="customerId", required=false)Integer customerId, @PageableDefault(page = 0, size = 5) Pageable pageable, Model model){
        Timestamp date;
        if(dateString!=null && !dateString.isEmpty()){
            date = Timestamp.valueOf(dateString);
        } else {
            date =null;
        }
        List<Customer> customers = customerService.getCustomerList();
        Page<Order> orders = service.getOrderListForAdmin(pageable,date,customerId);
        List<Timestamp> dates = new ArrayList<>();
        for(Order order:orders){
            if(dates.size()==0 || dates.get(dates.size() - 1).toLocalDateTime().getDayOfYear() != order.getOrderDate().toLocalDateTime().getDayOfYear()){
                dates.add(order.getOrderDate());
            }
        }
        model.addAttribute("pageUrl", "/admin/history?");
        model.addAttribute("orders", orders);
        model.addAttribute("dates", dates);
        model.addAttribute("date", date);
        model.addAttribute("customers", customers);
        model.addAttribute("customerId", customerId);
        model.addAttribute("next", pageable.getPageNumber() + 2);
        model.addAttribute("prev", pageable.getPageNumber());
        return "/order/history/backend";
    }

    
}
