package com.fullness.ec.repository;

import org.apache.ibatis.annotations.Mapper;

import com.fullness.ec.entity.EmpAccount;
import com.fullness.ec.entity.Employee;

@Mapper
public interface EmployeeRepository{
    public Employee selectByNameAndPassward(String name,String password);
    public EmpAccount selectByUsername(String username);
}
