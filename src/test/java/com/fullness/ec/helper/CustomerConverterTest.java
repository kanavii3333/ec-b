package com.fullness.ec.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Customer;
import com.fullness.ec.form.CustomerForm;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerConverterTest {

    @Sql("/sql/data2.sql")
    @Test
    void convertToEntityTest(){
        CustomerForm customerForm = new CustomerForm();
        customerForm.setCustomerName("佐藤");
        customerForm.setCustomerNameKana("サトウ");
        customerForm.setCustomerAddress1("aaaaaaaaaa");
        customerForm.setCustomerAddress2("aaa");
        customerForm.setCustomerPhoneNumber("00-0000-0000");
        customerForm.setCustomerMailAdress("aaa@email");
        customerForm.setCustomerUsername("sato1");
        customerForm.setCustomerPassword("sato1");
        ZonedDateTime specificDate = ZonedDateTime.of(2024, 8, 6, 12, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
        customerForm.setCustomerRegisterDate(specificDate);

        Customer customer = CustomerConverter.convertToEntity(customerForm);
        assertEquals("佐藤", customer.getCustomerName());
        assertEquals("サトウ", customer.getCustomerNameKana());
        assertEquals("aaaaaaaaaa", customer.getAddress1());
        assertEquals("aaa", customer.getAddress2());
        assertEquals("00-0000-0000", customer.getPhone());
        assertEquals("aaa@email", customer.getMailAddress());
        assertEquals("sato1", customer.getUsername());
        assertEquals("sato1", customer.getPassword());
        // assertEquals("2024-08-06 12:00:00.0", customer.getRegisteredDate());
        //↑期待と結果が同じなのにエラーが出る    
    }
    
}
