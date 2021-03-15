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
    <title>商品类型</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<table id="typeDg" class="easyui-datagrid"></table>
<div id="typeTb" style="padding:2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" onclick="addType();">添加</a> <a
        href="javascript:void(0)" class="easyui-linkbutton"
        iconCls="icon-edit" plain="true" onclick="editType();">修改</a>
</div>

<div id="typeDlg" class="easyui-dialog" title="New Type" closed="true"
     style="width:500px;">
    <div style="padding:10px 60px 20px 60px">
        <form id="typeForm" method="POST" action="">
            <table cellpadding="5">
                <tr>
                    <td>产品名称:</td>
                    <td><input class="easyui-textbox" type="text" id="name"
                               name="name" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="saveType();">保存</a> <a href="javascript:void(0)"
                                               class="easyui-linkbutton" onclick="clearForm();">清空</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $('#typeDg').datagrid({
            singleSelect : false, //设置datagrid为单选
            url : 'type/getType/0', //为datagrid设置数据源
            rownumbers : true, //显示行号
            fit : true, //设置自适应
            toolbar : '#typeTb', //为datagrid添加工具栏
            columns : [ [ { //编辑datagrid的列
                title : '序号',
                field : 'id',
                align : 'center',
                checkbox : true
            }, {
                field : 'name',
                title : '商品类型',
                width : 200
            }] ]
        });
    });

    var urls;
    var data;


    function addType() {
        $('#typeDlg').dialog('open').dialog('setTitle', '新增商品类型');
        $('#typeDlg').form('clear');
        urls = 'type/addType';
    }

    function saveType() {
        $("#typeForm").form("submit", {
            url : urls, //使用参数
            success : function(result) {
                var result = eval('(' + result + ')');
                if (result.success == 'true') {
                    $("#typeDg").datagrid("reload");
                }
                $("#typeDlg").dialog("close");
                $.messager.show({
                    title : "提示信息",
                    msg : result.message
                });
            }
        });
    }
    function clearForm() {
        $('#typeForm').form('clear');
    }

    function editType() {
        var row = $("#typeDg").datagrid("getSelected");
        if (row) {
            $("#typeDlg").dialog("open").dialog('setTitle', '修改产品信息');
            $("#typeForm").form("load", {
                "name" : row.name
            });
            urls = "type/updateType?id=" + row.id;
        }
    }

</script>
</body>
</html>
