package com.hunter.ssm_crud.bean;

import javax.validation.constraints.Pattern;

public class Employee {
    private Integer empId;

    @Pattern(regexp = "^([a-zA-Z0-9_-]{6,16})|(^[\u4e00-\u9fa5]{2,5})$",
            message = "姓名必须为6-16位数字和字母的组合 或 2-5位中文")
    private String empName;

    private String gender;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$",
    message = "邮箱格式不正确")
    private String email;

    private Integer dId;

    //部门信息
    private Department department;

    public Employee() {
        super();
    }

    public Employee(Integer empId, String empName, String gender,
                    String email, Integer dId, Department department) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
        this.department = department;
    }

    public Employee(Integer empId, String empName, String gender,
                    String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}