//引入展示员工列表的方法
document.write("<script type='text/javascript' src='js/include/emps.js'></script>");
// 引入展示分页的方法
document.write("<script type='text/javascript' src='js/include/paging.js'></script>");
//引入校验表单数据的方法
document.write("<script type='text/javascript' src='js/include/validate_form.js'></script>");

//创建总记录数，当前页码
var totalRecord, currentPage;


// 1.页面加载完成以后，直接去发送一个AJAX请求，要到分页数据
$(function () {
    to_page(1);
});

// 添加主页面新增按钮的鼠标监听
$("#emp_add_modal_btn").click(function () {
    //重置表单的数据、样式
    reset_form("#empAddModal form");
    //发出ajax请求，查出部门信息，显示在下拉列表中
    getDepts("#dept_add_select");
    //弹出模态框
    $("#empAddModal").modal();
});

//给 全部删除按钮 添加鼠标监听
$("#emp_delete_all_btn").click(function () {

    var empNames = "";
    var del_idstr = "";
    $.each($(".check_item:checked"), function () {
        //组装员工姓名字符串
        empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
        //组装员工id字符串
        del_idstr += $(this).parents("tr").find("td:eq(1)").text() + "-";
    });
    //去除empNames多余的“,”
    empNames = empNames.substring(0, empNames.length - 1);
    //去除del_idstr多余的“-”
    del_idstr = del_idstr.substring(0, del_idstr.length - 1);

    if (confirm("确认删除【" + empNames + "】？")) {
        delete_emps(del_idstr);
    }
});


//添加主页面 全选 勾选框的鼠标监听
$("#check_all").click(function () {
    // DOM原生的属性值用prop获取和修改；自定义属性值用attr获取
    $(this).prop("checked");
    $(".check_item").prop("checked", $(this).prop("checked"));
});

//添加所有 单独的勾选框的鼠标监听（当全部勾选时，全选的勾选框会自动勾选）
$(document).on("click", ".check_item", function () {
    //判断当前选中的元素，是否为 全部勾选框个数
    var flag = $(".check_item:checked").length == $(".check_item").length;
    $("#check_all").prop("checked", flag);
});


//添加主页面 所有编辑按钮 的鼠标监听, 因为在按钮创建之前就绑定了click，所以绑定不上
// .live(): 可以为以后添加进来的元素绑定方法（新版jQuery没有live方法，用on替代）
$(document).on("click", ".edit_btn", function () {
    //加载所有的部门选项
    getDepts("#dept_update_select");
    //根据id加载员工信息
    getEmp($(this).attr("edit-id"));

    //将员工id传递给模态框的更新按钮
    $("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));

    // 弹出模态框
    $("#empUpdateModal").modal();
});

//添加主页面 所有 单独删除按钮 的鼠标监听,
$(document).on("click", ".delete_btn", function () {
    //弹出 确认 是否删除 对话框
    var empName = $(this).parents("tr").find("td:eq(2)").text();
    var empId = $(this).attr("del-id");
    if (confirm("确认删除【" + empName + "】？")) {
        delete_emp(empId);
    }
});


//添加模态框 姓名输入 的键盘监听
$("#empName_add_input").keyup(function () {
    validate_empName("#empName_add_input");
});

//添加模态框 保存按钮 的鼠标监听
$("#emp_save_btn").click(function () {
    // 判断“用户名是否已被使用”的校验是否成功
    if ($("#emp_save_btn").attr("ajax-va") == "error") {
        return false;
    }
    // 前端校验输入的数据
    if (!validate_add_form()) {
        return false;
    }
    //上传表单内容
    post_emp_form();
});


//添加模态框 更新按钮 的鼠标监听
$("#emp_update_btn").click(function () {

    //校验邮箱
    var email = $("#email_update_input").val();
    var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;
    if (!regEmail.test(email)) {
        show_validate_msg("#email_update_input", "error", "邮箱格式不正确");
        return false;
    } else {
        show_validate_msg("#email_update_input", "success", "");
    }

    //更新表单数据
    update_emp_form();

});