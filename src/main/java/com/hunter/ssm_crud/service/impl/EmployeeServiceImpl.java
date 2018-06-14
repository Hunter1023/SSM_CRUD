package com.hunter.ssm_crud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.dao.EmployeeMapper;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> getAll(Integer pageNum) {
        //设置分页
        PageHelper.startPage(pageNum, 5);
        List<Employee> emps = employeeMapper.selectByExampleWithDept(null);

        return new PageInfo<Employee>(emps, 5);
    }
}
