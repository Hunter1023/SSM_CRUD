package com.hunter.ssm_crud.controller;

import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     * 查询员工数据（分页查询）
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                          Model model){

        PageInfo<Employee> pageInfo = employeeService.getAll(pageNum);
        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }
}
