package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.entity.EmployeeAccount;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Sql("/sql/data.sql")

    @Test
    void insertTest() {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmpAccountId(0);
        employeeAccount.setName("佐藤次郎");
        employeeAccount.setPassword("11111");

        employeeRepository.insert(employeeAccount);
        // 目視確認
    }

    @Sql("/sql/data.sql")
    @Test
    void selectAllEmployeeTest() {
        List<Employee> EmployeeList = employeeRepository.selectAllEmployee();
        assertEquals(3, EmployeeList.size());
    }

    @Sql("/sql/data.sql")
    @Test
    void selectByUsernameTest() {
        EmployeeAccount employeeAccount = employeeRepository.selectByUsername("admin");

        assertEquals(1, employeeAccount.getEmpAccountId());
        assertEquals("admin", employeeAccount.getName());
        // プレーンテキストのパスワードと暗号化されたパスワードの一致を確認
        assertTrue(passwordEncoder.matches("admin", employeeAccount.getPassword()));
        // assertEquals("adminの暗号化されたやつ", employeeAccount.getPassword());
        // $2a$10$yznxyLWUhuyVtMthOTqG8.NQy3yQZgNfMLxdgUkeSw95LRANregK2
    }

    @Sql("/sql/data.sql")
    @Test
    void selectByEmpIdTest() {
        EmployeeAccount employeeAccount = employeeRepository.selectByEmpId(1);
        assertEquals(1, employeeAccount.getEmpId());
        assertEquals("admin", employeeAccount.getName());
        // assertEquals("○○", employeeAccount.getPassword());
        assertTrue(passwordEncoder.matches("admin", employeeAccount.getPassword()));
        assertEquals(1, employeeAccount.getEmpAccountId());
    }

}
