package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Customer;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;


    @Test
    @Sql("/sql/data2.sql")
    void selectAllTest(){
        List<Customer> Customers =customerService.selectAll();
        assertEquals(2, Customers.size());
    }

    @Test
    @Sql("/sql/data2.sql")
    void getCustomerByMailAddressTest(){
        Customer customer = customerService.getCustomerByMailAddress("tanaka@email.jp");
        assertEquals("田中太郎", customer.getCustomerName());
        assertEquals("タナカタロウ", customer.getCustomerNameKana());
        assertEquals("千葉県市川市伊勢宿1234", customer.getAddress1());
        assertEquals("00-1111-2222", customer.getPhone());
        assertEquals("tanaka@email.jp", customer.getMailAddress());
        assertEquals("tarotanaka", customer.getUsername());
        //assertEquals("passtanaka", customer.getPassword());
    }

    @Test
    @Sql("/sql/data2.sql")
    void registerCustomer(){

    }

}
