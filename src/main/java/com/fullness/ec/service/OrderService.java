package com.fullness.ec.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderStatus;
import com.fullness.ec.entity.PaymentMethod;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderForm;
import com.fullness.ec.repository.OrderStatusRepository;

public interface OrderService {

    void registerOrder(List<OrderDetailForm> orderDetailForm, OrderForm orderForm);

    void deleteOrder();

    void updateStatus(OrderForm orderForm);

    // List<OrderStatusRepository> getOrderStatusList();

    Page<Order> getOrderList(Pageable pageable);

    ProductStock getStockByProductId(Integer productId);

    // ProductStock OrderDetailForm(OrderDetailForm orderDetailForm);

    List<PaymentMethod> getPaymentMethodList();

    // List<OrderStatus> getOrderStatusList();

    // Page<Order> selectPurchaseHistoryByPage(Pageable pageable, LocalDateTime
    // date, Integer customerId);

    void getStockByOrderDetailForm(OrderDetailForm orderDetailForm);

}
