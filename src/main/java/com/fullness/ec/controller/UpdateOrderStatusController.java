package com.fullness.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderStatus;
import com.fullness.ec.service.OrderServiceImpl;
import java.util.List;

@SessionAttributes({ "order", "statusList" })
@Controller
@RequestMapping("/admin/updatestatus")
public class UpdateOrderStatusController {
    @Autowired
    OrderServiceImpl service;

    @PostMapping("input")
    public String input(@RequestParam("orderId") Integer orderId, Model model) {
        Order order = service.getOrderById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("statusList", service.getAllStatus());
        return "/order/updatestatus/input";
    }

    @GetMapping("input")
    public String inputGet() {

        return "/order/updatestatus/input";
    }

    @PostMapping("confirm")
    public String confirm(@ModelAttribute("statusList") List<OrderStatus> statusList,
            @RequestParam("statusId") Integer statusId, Model model) {
        for (OrderStatus status : statusList) {
            if (status.getOrderStatusId() == statusId)
                model.addAttribute("status", status);
        }
        return "/order/updatestatus/confirm";
    }

    @PostMapping("execute")
    public String execute(@ModelAttribute("statusList") List<OrderStatus> statusList,
            @ModelAttribute("order") Order order, @RequestParam("statusId") Integer statusId) {
        order.getOrderStatus().setOrderStatusId(statusId);
        for (OrderStatus status : statusList) {
            if (status.getOrderStatusId() == statusId)
                order.getOrderStatus().setOrderStatusName(status.getOrderStatusName());
            ;
        }
        service.updateStatus(order);
        return "redirect:/admin/updatestatus/complete";
    }

    @GetMapping("complete")
    public String complete(@ModelAttribute("order") Order order, SessionStatus sessionStatus, Model model) {
        model.addAttribute("order", order);
        sessionStatus.setComplete();
        return "/order/updatestatus/complete";
    }
}
