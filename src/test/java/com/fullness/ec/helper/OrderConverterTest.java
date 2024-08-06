package com.fullness.ec.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Order;
import com.fullness.ec.form.OrderDetailForm;
import com.fullness.ec.form.OrderForm;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderConverterTest {
    @Sql("/sql/data.sql")
    @Test
    void convertToEntity(){
    OrderForm orderForm = new OrderForm();
    orderForm.setOrderId(1);
    ZonedDateTime specificDate = ZonedDateTime.of(2024, 8, 6, 12, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
    orderForm.setOrderDate(specificDate);
    orderForm.setOrderStatusId(2);
    orderForm.setPaymentMethodId(1);
    orderForm.setCustomerId(1);
    orderForm.setAmountTotal(5);

    List<OrderDetailForm> orderDetailFormList = new ArrayList<OrderDetailForm>();
    OrderDetailForm orderDetailForm = new OrderDetailForm();
    orderDetailForm.setOrderDetailId(1);
    orderDetailForm.setOrderId(1);
    orderDetailForm.setProductId(1);
    orderDetailForm.setProductName("水性ボールペン(黒)");
    orderDetailForm.setPrice(100);
    orderDetailForm.setCount(5);
    orderDetailForm.setCustomerId(1);
    orderDetailForm.setStock(5);
    orderDetailFormList.add(orderDetailForm);
    Order order = OrderConverter.convertToEntity(orderForm, orderDetailFormList);
     
    assertEquals(1, order.getOrderId());
    //assertEquals("2024-08-06 12:00:00.0", order.getOrderDate());
    assertEquals(5,order.getAmountTotal());
    assertEquals(1, order.getCustomerId());
    for(OrderDetailForm orderDetailForm2 : orderDetailFormList){
      assertEquals(1,orderDetailForm2.getOrderId());
      assertEquals(5, orderDetailForm2.getCount());
      assertEquals(1, orderDetailForm2.getProductId());
    }
    assertEquals(1, order.getPayMethod().getPayMethodId());
    assertEquals(1, order.getCustomerId());
    }
}
