<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/19
  Time: 8:19 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>

<body>
<!-- 创建一个table -->
<table id="adminListDg" class="easyui-datagrid"></table>
<div id="tb_admin" style="padding: 2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" onclick="addAdmin();">添加</a> <a
        href="javascript:void(0)" class="easyui-linkbutton"
        iconCls="icon-edit" plain="true" onclick="editAdminInfo();">修改</a> <a
        href="javascript:void(0)" class="easyui-linkbutton"
        iconCls="icon-remove" onclick="removeAdmin();" plain="true">删除</a>
</div>
<!-- 添加管理员信息对话框 -->
<div id="dlg_admin" class="easyui-dialog" title="添加管理员"
     closed="true" style="width: 500px;">
    <div style="padding: 10px 60px 20px 60px">
        <form id="ff_admin" method="POST" action=""
              enctype="multipart/form-data">
            <table cellpadding="5">
                <tr>
                    <td>姓名:</td>
                    <td>
                        <input class="easyui-textbox" type="text" id="name"
                               name="name" data-options="required:true"></input></td>
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input class="easyui-textbox" type="text" id="pwd"
                               name="pwd" data-options="required:true"></input></td>
                    </td>
                </tr>
                <tr>
                    <td>办公室:</td>
                    <td><input class="easyui-textbox" type="text" id="office"
                               name="office" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>上班时间:</td>
                    <td><input class="easyui-textbox" type="text" id="beginTime"
                               name="beginTime" data-options="required:true"></input></td>
                </tr>
                <tr>
                <tr>
                    <td>下班时间:</td>
                    <td><input class="easyui-textbox" type="text" id="endTime"
                               name="endTime" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td><input class="easyui-textbox" type="text" id="telephone"
                               name="telephone" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input class="easyui-textbox" type="text" id="email"
                               name="email" data-options="required:true"></input></td>
                </tr>

            </table>
        </form>
        <div style="text-align: center; padding: 5px">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="saveAdmin();">保存</a> <a href="javascript:void(0)"
                                                  class="easyui-linkbutton" onclick="clearForm();">清空</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#adminListDg').datagrid({
            singleSelect: false,
            url: 'admininfo/admininfoList',
            queryParams: {}, //查询条件
            pagination: true, //启用分页
            pageSize: 5, //设置初始每页记录数（页大小）
            pageList: [5, 10, 15], //设置可供选择的页大小
            rownumbers: true, //显示行号
            fit: true, //设置自适应
            toolbar: '#tb_admin', //为datagrid添加工具栏
            columns: [[{ //编辑datagrid的列
                title: '序号',
                field: 'id',
                align: 'center',
                checkbox: true,
                width: 40,
            }, {
                field: 'name',
                title: '姓名',
                width: 120
            }, {
                field: 'office',
                title: '办公室',
                width: 200
            }, {
                field: 'beginTime',
                title: '上班时间',
                width: 150
            }, {
                field: 'endTime',
                title: '下班时间',
                width: 150
            }, {
                field: 'telephone',
                title: '联系电话',
                width: 200
            }, {
                field: 'email',
                title: '邮箱',
                width: 200
            }]]
        });
    });

    var urls;
    var data;

    //删除管理员
    function removeAdmin() {
        var rows = $("#adminListDg").datagrid('getSelections');
        if (rows.length > 0) {
            $.messager.confirm('Confirm', '确认要删除么?', function (r) {
                if (r) {
                    var aids = "";
                    for (var i = 0; i < rows.length; i++) {
                        aids += rows[i].id + ",";
                    }
                    $.post('admininfo/deleteAdminInfo', {
                        aids: aids,
                    }, function (result) {
                        if (result.success == 'true') {
                            $("#adminListDg").datagrid('reload');
                            $.messager.show({
                                title: '提示信息',
                                msg: result.message
                            });
                        } else {
                            $.messager.show({
                                title: '提示信息',
                                msg: result.message
                            });
                        }
                    }, 'json');
                }
            });
        } else {
            $.messager.alert('提示', '请选择要删除的行', 'info');
        }
    }

    // 增加管理员
    function addAdmin() {
        $('#dlg_admin').dialog('open').dialog('setTitle', '新增管理员');
        $('#dlg_admin').form('clear');
        urls = 'admininfo/addAdmin';
    }

    // 保存管理员信息信息
    function saveAdmin() {
        $("#ff_admin").form("submit", {
            url: urls, //使用参数
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.success == 'true') {
                    $("#adminListDg").datagrid("reload");

                }
                $("#dlg_admin").dialog("close");
                $.messager.show({
                    title: "提示信息",
                    msg: result.message
                });
            }
        });
    }

    function clearForm() {
        $('#ff_admin').form('clear');
    }

    // 修改信息
    function editAdminInfo() {
        var rows = $("#adminListDg").datagrid('getSelections');
        if (rows.length > 0) {
            var row = $("#adminListDg").datagrid("getSelected");
            if (row) {
                $("#dlg_admin").dialog("open").dialog('setTitle',
                    '修改管理员信息');
                $("#ff_admin").form("load", {
                    "id": row.id,
                    "name": row.name,
                    "pwd": row.pwd,
                    "office": row.office,
                    "beginTime": row.beginTime,
                    "endTime": row.endTime,
                    "telephone": row.telephone,
                    "email": row.email,
                });
                urls = "admininfo/updateAdminInfo?id=" + row.id;
            }
        } else {
            $.messager.alert('提示', '请选择要修改的行', 'info');
        }
    }
</script>
</body>
</html>