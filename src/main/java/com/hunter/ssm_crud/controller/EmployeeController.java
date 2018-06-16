package com.hunter.ssm_crud.controller;

import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.bean.Message;
import com.hunter.ssm_crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
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
     *
     * @param pageNum 页码
     * @return 利用jackson依赖将返回的对象转换为JSON字符串
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        PageInfo<Employee> pageInfo = employeeService.getAll(pageNum);


        return Message.success().add("pageInfo", pageInfo);
    }

    /**
     * 添加员工
     *
     * @param employee 传入的员工数据
     * @return 新增员工的结果
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    // 由于表单传输的标签内容，标签的name都和Employee的属性名相同，因此参数可以直接传对象
    @ResponseBody
    public Message saveEmp(Employee employee) {
        employeeService.saveEmp(employee);
        return Message.success();
    }

    /**
     * 后端表单校验
     *
     * @param empName 传入的员工名
     * @return 返回添加操作的结果
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Message checkUser(@RequestParam("empName") String empName) {
        //判断用户名是否合法
        String regx = "^([a-zA-Z0-9_-]{6,16})|(^[\u4e00-\u9fa5]{2,5})$";
        if(!empName.matches(regx)){
            return Message.fail().add("va_msg", "姓名必须为6-16位数字和字母的组合 或 2-5位中文");
        }

        //数据库用户名重复校验
        boolean usable = employeeService.checkUser(empName);
        if (usable) {
            return Message.success().add("va_msg", "该姓名可用");
        }
        return Message.fail().add("va_msg", "该姓名已被使用");
    }
}
