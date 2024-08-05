package com.fullness.ec.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderDetail;
import com.fullness.ec.entity.PaymentMethod;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderForm;

public class OrderConverter {
    public static Order convertToEntity(OrderForm orderForm,List<OrderDetailForm> orderDetailFormList){
        Order order = new Order();
            order.setOrderId(orderForm.getOrderId());
            order.setOrderDate(Timestamp.from(orderForm.getOrderDate().toInstant()));
            order.setAmountTotal(orderForm.getAmountTotal());
            List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
                for(OrderDetailForm orderDetailForm : orderDetailFormList){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(orderDetailForm.getOrderDetailId());
                    orderDetail.setCount(orderDetailForm.getCount());
                    orderDetail.setProductId(orderDetailForm.getProductId());
                    orderDetailList.add(orderDetail);
                }
            order.setOrderDetailList(orderDetailList);
            order.setPayMethod(new PaymentMethod());
            order.getPayMethod().setPayMethodId(orderForm.getPaymentMethodId());
            order.setCustomerId(orderForm.getCustomerId());
        return order;
    }
}
