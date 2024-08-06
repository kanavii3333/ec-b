package com.fullness.ec.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.EmployeeForm;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeConverterTest {

    @Sql("/sql/data.sql")
    @Test
    void converterFormEmpAccountToEmployee() {
        EmployeeForm employeeform = new EmployeeForm();

        employeeform.setId(1);
        employeeform.setEmpAccountName("takahashi");
        employeeform.setEmpAccountPassword("takahashi");

        EmployeeAccount employeeAccountTest = EmployeeConverter.converterFormEmpAccountToEmployee(employeeform);

        assertEquals(1, employeeAccountTest.getEmpId());
        assertEquals("takahashi", employeeAccountTest.getName());
        assertEquals("takahashi", employeeAccountTest.getPassword());
    }
}
