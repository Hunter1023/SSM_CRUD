package com.hunter.ssm_crud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.bean.EmployeeExample;
import com.hunter.ssm_crud.bean.EmployeeExample.Criteria;
import com.hunter.ssm_crud.dao.EmployeeMapper;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> getAll(Integer pageNum) {
        //设置分页
        PageHelper.startPage(pageNum, 5);
        List<Employee> emps = employeeMapper.selectByExampleWithDept(null);

        return new PageInfo<Employee>(emps, 5);
    }

    /**
     * 按照员工id查询员工
     * @param id 员工id
     * @return 员工对象
     */
    @Override
    public Employee getEmp(Integer id) {

        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 员工保存
     *
     * @param employee 传入员工对象
     */
    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检验员工名是否可用
     *
     * @param empName 传入的员工名
     * @return true: 当前姓名可用；false：不可用
     */
    @Override
    public boolean checkUser(String empName) {
        //查询符合条件的记录数，如果没有 返回 0；如果有 返回对应的数量
        EmployeeExample example = new EmployeeExample();
        //Criteria(标准)是EmployeeExample中的静态类
        Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

}
