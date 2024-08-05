package com.fullness.ec.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.entity.Order;
import com.fullness.ec.entity.OrderDetail;
import com.fullness.ec.entity.OrderStatus;
import com.fullness.ec.entity.PaymentMethod;
import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderForm;
import com.fullness.ec.helper.OrderConverter;
import com.fullness.ec.repository.OrderRepository;
import com.fullness.ec.repository.OrderStatusRepository;
import com.fullness.ec.repository.StockRepository;
import com.fullness.ec.security.CustomerUserDetails;
import java.util.Collections;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    OrderConverter orderConverter;

    @Override
    public ProductStock getStockByProductId(Integer productId) {
        ProductStock productStock = stockRepository.selectByProductId(productId);
        return productStock;
    }

    @Override
    public void getStockByOrderDetailForm(OrderDetailForm orderDetailForm) {
        stockRepository.selectByProductId(orderDetailForm.getProductId());
    }

    @Override
    public List<PaymentMethod> getPaymentMethodList() {
        return orderRepository.selectAllPaymentMethod();

    }

    @Override
    public void registerOrder(List<OrderDetailForm> orderDetailForm, OrderForm orderForm) {
        // Order order = orderConverter.convertToEntity(orderForm);

        // orderRepository.insert(order);

        // for (OrderDetailForm orderDetailFormList : orderDetailForm) {
        // OrderDetail orderDetail =
        // orderConverter.convertToEntity(orderDetailFormList);
        // orderRepository.insertOrderDetail(orderDetail);
        // }

        // orderRepository.insertOrderStatus(String status);

    }

    @Override
    public Page<Order> getOrderList(Pageable pageable) {
        Integer userId = ((CustomerUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getCustomerId();
        Integer total = orderRepository.countOrder(userId);
        List<Order> orders;
        if (total > 0) {
            orders = orderRepository.selectByPage(pageable,null,userId);
        } else {
            orders = Collections.emptyList();
        }
        return new PageImpl<>(orders, pageable, total);
    }

    // @Override
    // public Page<Order> selectPurchaseHistoryByPage(Pageable pageable,
    // LocalDateTime date, Integer customerId) {
    // // Integer total = orderRepository.se
    // // List<Order> orders = orderRepository.selectByPage(null, date, customerId);
    // }

    // @Override
    // public List<OrderStatus> getOrderStatusList() {
    // // return orderStatusRepository.selectAll();
    // }


    @Override
    public void updateStatus(OrderForm orderForm) {
        // orderConverter.convertToEntity(OrderForm orderForm);
        // orderRepository.updateStatus(Order order);

    }

    @Override
    public void deleteOrder() {

    }

}
