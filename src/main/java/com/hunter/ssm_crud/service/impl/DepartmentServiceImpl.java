package com.hunter.ssm_crud.service.impl;

import com.hunter.ssm_crud.bean.Department;
import com.hunter.ssm_crud.dao.DepartmentMapper;
import com.hunter.ssm_crud.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepts() {
        //查出的所有部门信息
        return departmentMapper.selectByExample(null);
    }
}
