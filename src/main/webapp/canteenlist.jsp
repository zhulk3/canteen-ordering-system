<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/12
  Time: 2:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>食堂列表</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<table id="canteenDg" class="easyui-datagrid"></table>
<div id="canteenTb" style="padding:2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" onclick="addCanteen();">添加</a>
    <a
            href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true" onclick="editCanteen();">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"
       onclick="deleteCanteen();" plain="true">删除食堂</a>
</div>

<div id="canteenDlg" class="easyui-dialog" title="New Type" closed="true"
     style="width:500px;">
    <div style="padding:10px 60px 20px 60px">
        <form id="canteenForm" method="POST" action="">
            <table cellpadding="5">
                <tr>
                    <td>食堂名称:</td>
                    <td><input class="easyui-textbox" type="text" id="name"
                               name="name" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>地址:</td>
                    <td><input class="easyui-textbox" type="text" id="address"
                               name="address" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>是否营业:</td>
                    <td><input class="easyui-textbox" type="text" id="isServe"
                               name="isServe" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="saveCanteen();">保存</a> <a href="javascript:void(0)"
                                                  class="easyui-linkbutton" onclick="clearForm();">清空</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#canteenDg').datagrid({
            singleSelect: false, //设置datagrid为单选
            url: 'canteen/getCanteen', //为datagrid设置数据源
            rownumbers: true, //显示行号
            fit: true, //设置自适应
            toolbar: '#canteenTb', //为datagrid添加工具栏
            columns: [[{ //编辑datagrid的列
                title: '序号',
                field: 'id',
                align: 'center',
                checkbox: true
            }, {
                field: 'name',
                title: '食堂名称',
                width: 200
            }, {
                field: 'address',
                title: '地址',
                width: 300
            }, {
                field: 'isServe',
                title: '是否营业',
                width: 200,
                formatter: function (value, row, index) {
                    if (row.isServe == 1) {
                        return "营业";
                    } else {
                        return "不营业";
                    }
                }
            }]]
        });
    });

    var urls;
    var data;


    function addCanteen() {
        $('#canteenDlg').dialog('open').dialog('setTitle', '添加食堂');
        $('#canteenDlg').form('clear');
        urls = 'canteen/addCanteen';
    }

    function saveCanteen() {
        $("#canteenForm").form("submit", {
            url: urls, //使用参数
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.success == 'true') {
                    $("#typeDg").datagrid("reload");
                }
                $("#typeDlg").dialog("close");
                $.messager.show({
                    title: "提示信息",
                    msg: result.message
                });
            }
        });
    }

    function clearForm() {
        $('#canteenForm').form('clear');
    }

    function editCanteen() {
        var row = $("#canteenDg").datagrid("getSelected");
        if (row) {
            $("#canteenDlg").dialog("open").dialog('setTitle', '修改食堂信息');
            $("#canteenForm").form("load", {
                "name": row.name,
                "address": row.address,
                "isServe": row.isServe
            });
            urls = "canteen/updateCanteen?id=" + row.id;
        }
    }

    function deleteCanteen() {
        // 获取选中的订单记录行
        var rows = $("#canteenDg").datagrid('getSelections');
        if (rows.length > 0) {
            $.messager.confirm('Confirm', '确认要删除么?', function (r) {
                if (r) {
                    var ids = "";
                    // 获取选中订单记录的订单id, 保存到ids中
                    for (var i = 0; i < rows.length; i++) {
                        ids += rows[i].id + ",";
                    }
                    // 发送请求
                    $.post('canteen/deleteCanteen', {
                        cids: ids
                    }, function (result) {
                        if (result.success == 'true') {
                            $("#canteenDg").datagrid('reload');
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

</script>
</body>
</html>
