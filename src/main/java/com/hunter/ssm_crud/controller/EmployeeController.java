package com.hunter.ssm_crud.controller;

import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.bean.Message;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     *
     * @param pageNum
     * @return 利用jackson依赖将返回的对象转换为JSON字符串
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        PageInfo<Employee> pageInfo = employeeService.getAll(pageNum);


        return Message.success().add("pageInfo", pageInfo);
    }


    /**
     * 查询员工数据（分页查询）
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          Model model) {

        PageInfo<Employee> pageInfo = employeeService.getAll(pageNum);
        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }
}
