package com.hunter.ssm_crud.controller;

import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.bean.Message;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum 页码
     * @return 利用jackson依赖将返回的对象转换为JSON字符串
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        PageInfo<Employee> pageInfo = employeeService.getAll(pageNum);


        return Message.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    // 由于表单传输的标签内容，标签的name都和Employee的属性名相同，因此参数可以直接传对象
    @ResponseBody
    public Message saveEmp(Employee employee) {
        employeeService.saveEmp(employee);
        return Message.success();
    }
}
