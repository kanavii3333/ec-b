package com.fullness.ec.helper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderDetail;
import com.fullness.ec.entity.PaymentMethod;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderForm;

public class OrderConverter {
    public static Order convertToEntity(OrderForm orderForm,List<OrderDetailForm> orderDetailFormList){
        Order order = new Order();
            order.setOrderId(orderForm.getOrderId());
            order.setOrderDate(orderForm.getOrderDate());
            order.setAmountTotal(orderForm.getAmountTotal());
            order.setOrderDetailFormList(orderDetailFormList);
            order.setPayMethod(new PaymentMethod());
            order.getPayMethod().setPayMethodId(orderForm.getPaymentMethodId());
            order.setCustomerId(orderForm.getCustomerId());
        return order;
    }
}
