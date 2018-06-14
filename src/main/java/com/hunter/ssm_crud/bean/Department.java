package com.hunter.ssm_crud.bean;

public class Department {
    private Integer deptId;

    private String deptName;

    //一旦生成有参数的构造函数，一定要显式声明无参构造函数
    public Department() {
        super();
    }

    public Department(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}