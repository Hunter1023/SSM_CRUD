//显示员工数据
function build_emps_table(result) {
    //先清空table表格
    $("#emps_table tbody").empty();
    var emps = result.extend.pageInfo.list;
    $.each(emps, function (index, item) {
        var empIdTd = $("<td></td>").append(item.empId);
        var empNameTd = $("<td></td>").append(item.empName);
        var genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
        var emailTd = $("<td></td>").append(item.email);
        var deptNameTd = $("<td></td>").append(item.department.deptName);
        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
        var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        var btnTd = $("<td></td>").append(editBtn).append(delBtn);
        $("<tr></tr>").append(empIdTd).append(empNameTd)
            .append(genderTd).append(emailTd).append(deptNameTd)
            .append(btnTd).appendTo("#emps_table tbody");
    });
}


