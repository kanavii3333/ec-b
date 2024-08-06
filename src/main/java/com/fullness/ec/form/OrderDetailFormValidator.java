package com.fullness.ec.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.service.OrderServiceImpl;


@Component
public class OrderDetailFormValidator implements Validator{
    @Autowired
    OrderServiceImpl OrderService;
    @Override
    public boolean supports(Class<?> clazz) {
    return OrderDetailForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        List<OrderDetailForm> orderDetailFormList = (List<OrderDetailForm>) target;
        for(OrderDetailForm orderDetails : orderDetailFormList){
        if (OrderService.getStockByProductId(orderDetails.getProductId()).getQuantity() < orderDetails.getCount()) {
            errors.reject("com.fullness.ec.OrderDetailForm.message");
        } 
    }
    }
}
