package com.fullness.ec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.entity.EmployeeAccount;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Sql("/sql/data.sql")


    @Test
    void insertTest(){
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmpAccountId(0);
        employeeAccount.setName("佐藤次郎");
        employeeAccount.setPassword("11111");

        employeeRepository.insert(employeeAccount);
        //目視確認
    }

    @Test
    void selectAllEmployeeTest(){
        List<Employee> EmployeeList = employeeRepository.selectAllEmployee();
        assertEquals(3,EmployeeList.size() );
    }

    @Test
    void selectByUsernameTest(){
        EmployeeAccount employeeAccount = employeeRepository.selectByUsername("admin");
        assertEquals(1, employeeAccount.getEmpAccountId());
        assertEquals("admin", employeeAccount.getName());
        //assertEquals("adminの暗号化されたやつ", employeeAccount.getPassword());

    }
}
