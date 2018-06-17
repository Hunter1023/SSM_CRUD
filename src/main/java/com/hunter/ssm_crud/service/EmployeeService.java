package com.hunter.ssm_crud.service;

import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;

public interface EmployeeService {

    PageInfo<Employee> getAll(Integer pageNum);

    void saveEmp(Employee employee);

    boolean checkUser(String empName);

    Employee getEmp(Integer id);

    void updateEmp(Employee employee);
}
