<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/21
  Time: 7:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<div id="adminLoginDlg" class="easyui-dialog"
     style="left: 550px; top: 200px;width: 300px;height: 200px"
     data-options="title:'修改密码',buttons:'#bb',modal:true">
    <form id="adminInfoForm" method="post">
        <table style="margin:20px;font-size: 13px;">
            <tr>
                <th>用户名</th>
                <td><input class="easyui-textbox" type="text" id="name"
                           name="name" data-options="required:true" value="firstcanteen"></input></td>
            </tr>
            <tr>
                <th>旧密码</th>
                <td><input class="easyui-textbox" type="text" id="pwd"
                           name="pwd" data-options="required:true" value=""></input></td>
            </tr>
            <tr>
                <th>新密码</th>
                <td><input class="easyui-textbox" type="text" id="newpwd"
                           name="newpwd" data-options="required:true" value=""></input></td>
            </tr>
        </table>
    </form>
</div>
<div id="bb">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       onclick="modifyPassword()">确认修改</a>
    <a href="javascript:void(0)"
       class="easyui-linkbutton" onclick="clearForm();">取消</a>
</div>
</body>
</html>
