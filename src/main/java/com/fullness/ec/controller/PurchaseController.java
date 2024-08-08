package com.fullness.ec.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.CategoryFormValidator;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderDetailFormValidator;
import com.fullness.ec.form.OrderForm;
import com.fullness.ec.security.CustomerUserDetails;
import com.fullness.ec.service.OrderServiceImpl;
import com.fullness.ec.service.ProductCategoryServiceImpl;
import com.fullness.ec.service.ProductServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@SessionAttributes("orderDetailFormList")
@RequestMapping("customer/purchaseproduct")
@Controller
public class PurchaseController {
    @ModelAttribute("orderDetailFormList")
    public List<OrderDetailForm> createOrderDetailFormList() {
        return new ArrayList<>();
    }

    @Autowired
    OrderServiceImpl orderService; 

    @Autowired
    ProductServiceImpl productService;
    
    @Autowired
    OrderDetailFormValidator validator;

    @InitBinder("orderDetailForm")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String input(@ModelAttribute("orderDetailForm")OrderDetailForm orderDetailForm,
    @ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList,Model model) {
        if(!orderDetailFormList.isEmpty()){
            for(OrderDetailForm od : orderDetailFormList){
                ProductStock productStock = orderService.getStockByProductId(od.getProductId());
                od.setStock(productStock.getQuantity());
            }
        }
        for(int i = 0; i < orderDetailFormList.size(); i++){
            if(orderDetailForm.getProductId() == orderDetailFormList.get(i).getProductId()){
                orderDetailFormList.remove(i);
            }
        }
        if(orderDetailForm.getProductId() != null){
            
        ProductStock productStock = orderService.getStockByProductId(orderDetailForm.getProductId());
        orderDetailForm.setStock(productStock.getQuantity());
        orderDetailFormList.add(orderDetailForm);
        }
        model.addAttribute("orderDetailFormList", orderDetailFormList);
        return "purchase/input";
    }

    @PostMapping("confirm")
    public String confirm(@Validated @RequestParam("countBuy")List<Integer> countList,
            @ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList,BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        for(int i = 0; i < orderDetailFormList.size(); i++){
                orderDetailFormList.get(i).setCount(countList.get(i));
            }
    
        Integer sum = 0;
        for(OrderDetailForm od : orderDetailFormList){
            sum = sum + (od.getPrice() * od.getCount());
        }
        model.addAttribute("paymentMethods", orderService.getPaymentMethodList());
        model.addAttribute("orderDetailFormList", orderDetailFormList);
        model.addAttribute("sum", sum);
        // if (bindingResult.hasErrors()) {
        //     redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDetailForm",
        //             bindingResult);
        //     return "redirect:/customer/purchaseproduct/input";
        // }
        return "purchase/confirm";
    }

    @GetMapping("confirm")
    public String confirmGet(@ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList,
            Model model) {
                if (orderDetailFormList.isEmpty()){                
                    return "redirect:/customer/top";
                }else{
        return "redirect:/customer/purchaseproduct/input";
                }
    }

    @PostMapping("execute")
    public String execute(@RequestParam("paymentMethod")Integer paymentMethod,@AuthenticationPrincipal CustomerUserDetails userDetails,
        @ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList,HttpSession session) {
        OrderForm orderForm = new OrderForm();
        Integer sum = 0;
        for(OrderDetailForm od : orderDetailFormList){
            sum = sum + (od.getPrice() * od.getCount());
        }
        
        orderForm.setAmountTotal(sum);
        orderForm.setCustomerId(userDetails.getCustomerId());
        orderForm.setOrderDate(ZonedDateTime.now());
        orderForm.setOrderStatusId(1);
        orderForm.setPaymentMethodId(paymentMethod);

        orderService.registerOrder(orderDetailFormList, orderForm);
        session.removeAttribute("orderDetailFormList");
        return "redirect:/customer/purchaseproduct/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList, Model model,
            SessionStatus sessionStatus) {
        if (orderDetailFormList.isEmpty())
            return "redirect:/customer/top";
        sessionStatus.setComplete();
        return "purchase/complete";
    }

    @GetMapping("delete")
    public String delete(@ModelAttribute("orderDetailFormList")List<OrderDetailForm> orderDetailFormList,
    @RequestParam("productId")Integer productId) {
        for(int i = 0; i < orderDetailFormList.size(); i++){
            if(productId == orderDetailFormList.get(i).getProductId()){
                orderDetailFormList.remove(i);
            }
        }
        return "redirect:/customer/purchaseproduct/input";
    }
    

}
