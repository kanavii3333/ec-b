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

    public EmployeeAccount selectByEmpId(Integer empId); // 追加

    public EmployeeAccount selectByEmpAccountName(String empAccountName); // 追加
}
