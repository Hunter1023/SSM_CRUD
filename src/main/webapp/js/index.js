//创建总记录数
var totalRecord;

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
            //2.解析并显示分页信息（并将总记录数赋给totalRecord）
            build_page_info(result);
            //3.解析并显示分页条数据
            build_page_nav(result);
        }
    });
}

// 添加新增按钮的鼠标监听
$("#emp_add_modal_btn").click(function () {
    //重置表单的数据、样式
    reset_form("#empAddModal form");


    //发出ajax请求，查出部门信息，显示在下拉列表中
    getDepts();
    //弹出模态框
    $("#empAddModal").modal();
});

//重置表单
function reset_form(ele) {
    //清空表单数据 jQuery没有reset方法，DOM对象才有，因此取出DOM对象[0]
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}


// 查出所有部门信息并显示在下拉列表中
function getDepts() {
    $.ajax({
        url: "depts",
        type: "GET",
        success: function (result) {
            //在下拉列表中显示部门信息
            $.each(result.extend.depts, function () {
                var optionEle = $("<option></option>")
                    .append(this.deptName).attr("value", this.deptId);
                optionEle.appendTo("#dept_add_select");
            })
        }
    });
}

//添加姓名输入的 键盘监听
$("#empName_add_input").keyup(function () {
    validate_empName("#empName_add_input");
});

$("#emp_save_btn").click(function () {
    //判断“用户名是否已被使用”的校验是否成功
    if ($(this).attr("ajax-va") == "error") {
        return false;
    }

    //校验输入的数据
    if (!validate_add_form()) {
        return false;
    }

    //模态框中填写的表单数据提交给服务器进行保存
    $.ajax({
        url: "emp",
        type: "POST",
        data: $("#empAddModal form").serialize(),
        success: function (result) {
            // alert(result.message);
            //员工保存成功后，关闭模态框
            $('#empAddModal').modal('hide');
            // 将总记录数作为请求页码（mybatisConfig.xml中配置了相关属性，能保证获取最后一页信息）
            toPage(totalRecord);
        }
    });
});