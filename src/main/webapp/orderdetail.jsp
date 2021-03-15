<%--
  Created by IntelliJ IDEA.
  User: zhulongkai
  Date: 2021/3/10
  Time: 11:26 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单明细</title>
    <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="EasyUI/demo/demo.css" rel="stylesheet" type="text/css"/>
    <script src="EasyUI/jquery.min.js" type="text/javascript"></script>
    <script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="EasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<table id="editodbox"></table>

<div id="editordertb" style="padding: 2px 5px;">
    <div id="editdivOrderInfo">
        <div style="padding: 3px">
            客户名称&nbsp;<input style="width: 115px;" id="edit_uid"
                             class="easyui-textbox" name="edit_uid" readonly="readonly"
                             value="${requestScope.oi.ui.userName }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            订单金额&nbsp;<input type="text" name="edit_orderprice"
                             id="edit_orderprice" value="${requestScope.oi.orderprice }"
                             class="easyui-textbox" readonly="readonly" style="width: 115px" />
            &nbsp;&nbsp;
        </div>
        <div style="padding: 3px">
            订单日期&nbsp;<input type="text" name="edit_ordertime" readonly="readonly"
                             id="edit_ordertime" value="${requestScope.oi.ordertime }"
                             class="easyui-datebox" style="width: 115px" /> &nbsp;&nbsp;&nbsp;&nbsp; 订单状态&nbsp;<input
                id="edit_status" class="easyui-textbox" name="edit_status"
                style="width: 115px;" readonly="readonly" value="${requestScope.oi.status }">
        </div>
    </div>
</div>
<script type="text/javascript">
    var $editodbox = $('#editodbox');
    $(function() {
        $editodbox.datagrid({
            url : 'orderinfo/getOrderDetails?oid=${requestScope.oi.id }',
            rownumbers : true,
            singleSelect : false,
            fit : true,
            toolbar : '#editordertb',
            columns : [ [ {
                field : 'pid',
                title : '商品名称',
                width : 300,
                formatter : function(value, row, index) {
                    if (row.pi) {
                        return row.pi.name;
                    } else {
                        return value;
                    }
                }
            }, {
                field : 'price',
                title : '单价',
                width : 80
            }, {
                field : 'num',
                title : '数量',
                width : 50
            }, {
                field : 'totalprice',
                title : '小计',
                width : 100
            } ] ]
        });
    });
</script>



</body>
</html>
