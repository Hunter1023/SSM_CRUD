//显示员工数据
function build_emps_table(result) {
    //先清空table表格
    $("#emps_table tbody").empty();
    var emps = result.extend.pageInfo.list;
    $.each(emps, function (index, item) {
        var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
        var empIdTd = $("<td></td>").append(item.empId);
        var empNameTd = $("<td></td>").append(item.empName);
        var genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
        var emailTd = $("<td></td>").append(item.email);
        var deptNameTd = $("<td></td>").append(item.department.deptName);
        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
        // 为编辑按钮添加自定义的属性，来表示当前员工的id
        editBtn.attr("edit-id", item.empId);

        var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        delBtn.attr("del-id", item.empId);

        var btnTd = $("<td></td>").append(editBtn).append(delBtn);
        $("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd)
            .append(genderTd).append(emailTd).append(deptNameTd)
            .append(btnTd).appendTo("#emps_table tbody");
    });
}


