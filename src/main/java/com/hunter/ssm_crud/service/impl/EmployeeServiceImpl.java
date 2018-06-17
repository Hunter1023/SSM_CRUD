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
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> getAll(Integer pageNum) {
        //设置分页
        PageHelper.startPage(pageNum, 5);
        EmployeeExample example = new EmployeeExample();
        //设置按员工id排序，否则会默认先 按照 关联的部门id排序；再按照员工id排序
        example.setOrderByClause("emp_id");
        List<Employee> emps = employeeMapper.selectByExampleWithDept(example);

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

    /**
     * 员工更新
     */
    @Override
    public void updateEmp(Employee employee) {
        //根据主键有选择地更新
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除单个员工
     */
    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除员工
     */
    @Override
    public void deleteBatch(String ids) {
        List<Integer> del_ids = new ArrayList<Integer>();
        String[] str_ids = ids.split("-");
        //组装id的集合
        for (String string : str_ids){
            del_ids.add(Integer.parseInt(string));
        }

        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id in(...)
        criteria.andEmpIdIn(del_ids);
        employeeMapper.deleteByExample(example);
    }
}
