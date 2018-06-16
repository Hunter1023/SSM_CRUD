//校验表单数据方法
function validate_add_form() {
    // 校验用户名
    var empName = $("#empName_add_input").val();
    var regName = /^([a-zA-Z0-9_-]{6,16})|([\u4e00-\u9fa5]{2,5})$/;
    if (!regName.test(empName)) {
        show_validate_msg("#empName_add_input", "error", "姓名必须为6-16位数字和字母的组合 或 2-5位中文");
        return false;
    } else {
        show_validate_msg("#empName_add_input", "success", "");
    }
    //校验邮箱
    var email = $("#email_add_input").val();
    var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;
    if (!regEmail.test(email)) {
        show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
        return false;
    } else {
        show_validate_msg("#email_add_input", "success", "");
    }
    return true;
}

//显示校验结果的提示信息
function show_validate_msg(ele, status, msg) {
    //清除当前元素的校验状态
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");

    if (status == "success") {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if (status == "error") {
        //渲染输入框,清空这个元素之前的样式
        $(ele).parent().addClass("has-error");
        //显示错误提示
        $(ele).next("span").text(msg);
    }
}

// 校验员工名
function validate_empName(ele) {
    //发送ajax请求校验用户名是否可用
    var empName = $(ele).val();
    $.ajax({
        url: "checkUser",
        data: "empName=" + empName,
        type: "POST",
        success: function (result) {
            if (result.message == "success"){
                show_validate_msg(ele, "success", result.extend.va_msg);
                $("#emp_save_btn").attr("ajax-va", "success");
            }else if(result.message == "error"){
                show_validate_msg(ele, "error", result.extend.va_msg);
                $("#emp_save_btn").attr("ajax-va", "error");
            }
        }
    });

}
