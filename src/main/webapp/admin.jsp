<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/9
  Time: 6:36 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<style type="text/css">
    .horizontal {
        display: inline-block;
    }
</style>

<body class="easyui-layout">
<div data-options="region:'north',border:false"
     style="height: 120px; background: #055a26; padding: 10px;justify-content: space-between">
    <div class="horizontal" style="float: left">
        <img src="img/logo.jpeg">
    </div>
    <div class="horizontal"
         style="font-family: Microsoft YaHei; font-size: 28px;font-weight: bold;margin-left: 400px;margin-top: 40px">
        中山大学食堂订餐后台管理系统
    </div>

    <div class="horizontal" style="float: right;margin-top: 43px">
        <div style="font-family: Microsoft YaHei; font-size: 16px;font-weight: bold">
            欢迎您，${sessionScope.admin.name}</div>

    </div>
</div>
<div data-options="region:'west',split:true,title:'功能菜单'"
     style="width: 280px">
    <ul id="tt"></ul>
</div>
<div data-options="region:'south',border:false"
     style="height: 50px; background: #A9FACD; padding: 10px; text-align: center">
    <div class="horizontal" style="text-align: center">
        通讯地址:广州市新港西路135号 邮编:510275 电话:18011849685 powered by zhulongkai
    </div>
    <div class="horizontal" style="float: right">
        <a href="javascript:void(0)" class="easyui-linkbutton" style="font-size: medium;font-family: Microsoft YaHei"
           iconCls="icon-clear" plain="true" onclick="logout()">退出登录</a>
    </div>
    <div class="horizontal" style="float: right">
        <a href="javascript:void(0)" class="easyui-linkbutton" style="font-size: medium;font-family: Microsoft YaHei"
           iconCls="icon-edit" plain="true" onclick="editPassword()">修改密码</a>
    </div>

</div>
<div data-options="region:'center'">
    <div id="tabs" data-options="fit:true" class="easyui-tabs"
         style="width: 500px; height: 250px;"></div>
</div>
<script type="text/javascript">
    // 为tree指定数据
    $('#tt').tree({
        url: 'admininfo/getTree?adminid=${sessionScope.admin.id}'
    });
    $('#tt').tree({
        onClick: function (node) {
            if ("菜品列表" == node.text) {
                if ($('#tabs').tabs('exists', '菜品列表')) {
                    $('#tabs').tabs('select', '菜品列表');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'productlist.jsp',
                        closable: true
                    });
                }
            } else if ("菜品类型列表" == node.text) {
                if ($('#tabs').tabs('exists', '菜品类型列表')) {
                    $('#tabs').tabs('select', '菜品类型列表');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'typelist.jsp',
                        closable: true
                    });
                }
            } else if ("查询订单" == node.text) {
                if ($('#tabs').tabs('exists', '查询订单')) {
                    $('#tabs').tabs('select', '查询订单');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'searchorder.jsp',
                        closable: true
                    });
                }
            } else if ("创建订单" == node.text) {
                if ($('#tabs').tabs('exists', '创建订单')) {
                    $('#tabs').tabs('select', '创建订单');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'createorder.jsp',
                        closable: true
                    });
                }
            } else if ("客户列表" == node.text) {
                if ($('#tabs').tabs('exists', '客户列表')) {
                    $('#tabs').tabs('select', '客户列表');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'userlist.jsp',
                        closable: true
                    });
                }
            }  else if ("食堂管理" == node.text) {
                if ($('#tabs').tabs('exists', '食堂管理')) {
                    $('#tabs').tabs('select', '食堂管理');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'canteenlist.jsp',
                        closable: true
                    });
                }
            } else if ("管理员信息" == node.text) {
                if ($('#tabs').tabs('exists', '管理员信息')) {
                    $('#tabs').tabs('select', '管理员信息');
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        href: 'admininfo.jsp',
                        closable: true
                    });
                }
            }
        }
    });

    function editPassword() {
        $.ajax({
            url: 'admininfo/logout',
            success: function (data) {
                window.location.href = "password.jsp";
            }
        })

    }

    function logout() {
        $('#typeDlg').dialog('open').dialog('setTitle', '确认退出？');
        $('#typeDlg').form('clear');
        $.ajax({
            url: 'admininfo/logout',
            success: function (data) {
                window.location.href = "admin_login.jsp";
            }
        })
    }
</script>
</body>
</html>
