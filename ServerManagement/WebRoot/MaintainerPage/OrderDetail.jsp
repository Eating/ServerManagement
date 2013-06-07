<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查看订单详情</title>
</head>
<body>
<a href="statistics.action">销售金额统计</a>
<a href="staItems.action">商品销售统计</a>
<a href="orders.action">历史订单</a>

<br/>
订单序列号：${requestScope.orderId}

<table>
	<tr><td>商品名称</td><td>商品数量</td><td>单价</td><td>总价</td><td>折扣</td></tr>
	<s:iterator value="#request.detaillist" id="curr">
		<tr><td>${curr.itemName}</td>
			<td>${curr.number}</td>
			<td>${curr.singlePrice}</td>
			<td>${curr.totalPrice}</td>
			<td>${curr.discount}</td>
		</tr>
	</s:iterator>
</table>

</body>
</html>