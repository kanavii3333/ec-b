package com.fullness.ec.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.entity.Employee;

@Mapper
public interface EmployeeRepository {
    // つかってない
    public Employee selectByNameAndPassward(String name, String password);

    public EmployeeAccount selectByUsername(String username);

    public void insert(EmployeeAccount employeeAccount);

    public List<Employee> selectAllEmployee();

    EmployeeAccount selectByEmpId(Integer empId); // 追加
}
