## SSM_CRUD

本项目是对 网易云课堂中，由尚硅谷教育提供的[SSM高级整合视频教程](http://study.163.com/course/courseMain.htm?courseId=1003862031)的实现。

***
## 运行环境

- Java 1.6
- Maven（maven的tomcat7插件）
- SSM
- MySQL
- Bootstrap
- jQuery

所用IDE： IDEA

***

## 版本更新

### 2018/6/14
初步实现查询功能

### 2018/6/15
将视图的展示由**返回jsp文件名**转变为**返回JSON数据**

### 2018/6/16

- ajax校验员工姓名、添加员工，和相应的页面跳转
- JSR303后端校验员工姓名、用户名是否重复的后端校验
- 前端解耦

### 2018/6/17

- 消除模态框的下拉列表内容展示重复展示的bug
- 利用`web.xml`中配置的`HiddenHttpMethodFilter`，ajax通过在data中设置实际的请求，将页面普通的`POST`请求转为指定的`DELETE`或者`PUT`请求
- 实现员工信息修改和相应页面跳转
- 实现员工信息的删除， 将表单更新的方法、删除员工的方法 解耦