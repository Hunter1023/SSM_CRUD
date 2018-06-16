package com.hunter.ssm_crud.controller;

import com.hunter.ssm_crud.bean.Department;
import com.hunter.ssm_crud.utils.Message;
import com.hunter.ssm_crud.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * 处理和部门有关的请求
 */
@Controller
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 返回所有的部门信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Message getDepts(){
        List<Department> list = departmentService.getDepts();

        return Message.success().add("depts", list);
    }
}
