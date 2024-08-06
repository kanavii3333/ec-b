package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Customer;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository repository;

    @Sql("/sql/data2.sql")
    @Test
    void insertTest() {
        Customer customer = new Customer();
        customer.setCustomerId(null);
        customer.setCustomerName("後藤三郎");
        customer.setCustomerNameKana("ゴトウサブロウ");
        customer.setAddress1("さいたま新都心55-5-5");
        customer.setAddress2(null);
        customer.setPhone("11-1111-1111");
        customer.setMailAddress("goto@example.com");
        customer.setUsername("goto");
        customer.setPassword("passgoto");
        customer.setRegisteredDate(Timestamp.from(ZonedDateTime.now().toInstant()));

        repository.insert(customer);
        assertEquals("goto", repository.selectByMailAddress("goto@example.com").getUsername());
    }

    @Sql("/sql/data2.sql")
    @Test
    void selectAllTest() {
        List<Customer> customers = repository.selectAll();
        assertEquals(2, customers.size());

    }

    @Sql("/sql/data2.sql")
    @Test
    void selectByMailAddressTest() {
        Customer user1 = repository.selectByMailAddress("tanaka@email.jp");
        Customer user2 = repository.selectByMailAddress("");
        assertEquals("田中太郎", user1.getCustomerName());
        assertNull(user2);
    }

}