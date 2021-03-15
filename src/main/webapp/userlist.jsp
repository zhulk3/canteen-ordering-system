<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/10
  Time: 11:39 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<!-- 创建一个table -->
<table id="userListDg" class="easyui-datagrid"></table>

<!-- 创建工具栏 -->
<div id="userListTb" style="padding:2px 5px;"><a href="javascript:void(0)"
                                                 class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                                                 onclick="SetIsEnableUser(1);">启用客户</a> <a href="javascript:void(0)"
                                                                                           class="easyui-linkbutton"
                                                                                           iconCls="icon-remove"
                                                                                           onclick="SetIsEnableUser(0);"
                                                                                           plain="true">禁用客户</a>
</div>

<!-- 创建搜索栏 -->
<div id="searchUserListTb" style="padding:4px 3px;">
    <form id="searchUserListForm">
        <div style="padding:3px ">
            客户名称&nbsp;&nbsp;<input class="easyui-textbox" name="search_userName"
                                   id="search_userName" style="width:110px"/><a href="javascript:void(0)"
                                                                                class="easyui-linkbutton"
                                                                                iconCls="icon-search" plain="true"
                                                                                onclick="searchUserInfo();">查找</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        $('#userListDg').datagrid({
            singleSelect: false,
            url: 'userinfo/list',
            queryParams: {}, //查询条件
            pagination: true, //启用分页
            pageSize: 5, //设置初始每页记录数（页大小）
            pageList: [5, 10, 15], //设置可供选择的页大小
            rownumbers: true, //显示行号
            fit: true, //设置自适应
            toolbar: '#userListTb', //为datagrid添加工具栏
            header: '#searchUserListTb', //为datagrid标头添加搜索栏
            columns: [[{ //编辑datagrid的列
                title: '序号',
                field: 'id',
                align: 'center',
                checkbox: true
            }, {
                field: 'userName',
                title: '登录名',
                width: 100
            }, {
                field: 'realName',
                title: '真实姓名',
                width: 80
            }, {
                field: 'sex',
                title: '性别',
                width: 100
            }, {
                field: 'address',
                title: '住址',
                width: 200
            }, {
                field: 'email',
                title: '邮箱',
                width: 150
            }, {
                field: 'regDate',
                title: '注册日期',
                width: 100
            }, {
                field: 'status',
                title: '客户状态',
                width: 100,
                formatter: function (value, row, index) {
                    if (row.status == 1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            }]]
        });
    });

    var urls;
    var data;

    function searchUserInfo() {
        var userName = $('#search_userName').textbox("getValue");
        $('#userListDg').datagrid('load', {
            userName: userName
        });
    }


    // 设置启用或禁用客户
    function SetIsEnableUser(flag) {
        var rows = $("#userListDg").datagrid('getSelections');
        if (rows.length > 0) {
            $.messager.confirm('Confirm', '确认要设置么?', function (r) {
                if (r) {
                    var uids = "";
                    for (var i = 0; i < rows.length; i++) {
                        uids += rows[i].id + ",";
                    }
                    $.post('userinfo/setIsEnableUser', {
                        uids: uids,
                        flag: flag
                    }, function (result) {
                        if (result.success == 'true') {
                            $("#userListDg").datagrid('reload');
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
            $.messager.alert('提示', '请选择要启用或禁用的客户', 'info');
        }
    }

</script>
</body>
</html>
