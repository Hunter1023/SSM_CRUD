//引入展示员工列表的方法
document.write("<script type='text/javascript' src='js/include/emps.js'></script>");
// 引入展示分页的方法
document.write("<script type='text/javascript' src='js/include/paging.js'></script>");
//引入校验表单数据的方法
document.write("<script type='text/javascript' src='js/include/validate_form.js'></script>");

//创建总记录数
var totalRecord;

// 1.页面加载完成以后，直接去发送一个AJAX请求，要到分页数据
$(function () {
    toPage(1);
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

//添加主页面 所有编辑按钮 的鼠标监听, 因为在按钮创建之前就绑定了click，所以绑定不上
// .live(): 可以为以后添加进来的元素绑定方法（新版jQuery没有live方法，用on替代）
$(document).on("click", ".edit_btn", function () {
    //加载所有的部门选项
    getDepts("#dept_update_select");
    //根据id加载员工信息
    getEmp($(this).attr("edit-id"));

    //弹出模态框
    $("#empUpdateModal").modal();
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

    //发送ajax请求，保存更新的员工数据

});
