<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="jsp/include/head.jsp" %>
</head>
<body>
<%--搭建显示页面--%>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <%@include file="jsp/include/paging.jsp" %>
    </div>
</div>
</body>
<script type="text/javascript" src="${APP_PATH}/js/emps.js"></script>
<script type="text/javascript" src="${APP_PATH}/js/paging.js"></script>
<script type="text/javascript">
    // 1.页面加载完成以后，直接去发送一个AJAX请求，要到分页数据
    $(function () {
        toPage(1);
    });

    function toPage(pageNum) {
        $.ajax({
            url: "emps",
            data: "pageNum=" + pageNum,
            type: "GET",
            success: function (result) {
                //1.解析并显示员工数据
                build_emps_table(result);
                //2.解析并显示分页信息
                build_page_info(result);
                //3.解析并显示分页条数据
                build_page_nav(result);
            }
        });
    }
</script>
</html>
