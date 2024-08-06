package com.fullness.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.Employee;

import com.fullness.ec.form.EmployeeForm;
import com.fullness.ec.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Sql("/sql/data.sql")
    void addAccountTest(){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(3);
        employeeForm.setEmpAccountName("satojiro");
        employeeForm.setEmpAccountPassword("11111");

        employeeService.addAccount(employeeForm);
        assertEquals("satojiro",employeeRepository.selectByUsername("satojiro").getName());
    }

    @Test
    @Sql("/sql/data.sql")
    void getEmployeeListTest(){
        List<Employee> EmployeeList = employeeService.getEmployeeList();
        assertEquals(3,EmployeeList.size() );
    }

    @Test
    @Sql("/sql/data.sql")
    void isAccountExistTest(){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(1);
        Boolean boolean1 = employeeService.isAccountExist(employeeForm);
        assertEquals(true, boolean1);
    }
    @Test
    @Sql("/sql/data.sql")
    void isAccountExistTest2(){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(2);
        Boolean boolean1 = employeeService.isAccountExist(employeeForm);
        assertEquals(false, boolean1);
    }
}

