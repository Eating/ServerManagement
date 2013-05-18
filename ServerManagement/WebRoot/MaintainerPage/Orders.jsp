<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_blank" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<a href="statistics.action" target="_self">销售金额统计</a>
<a href="staItems.action" target="_self">商品销售统计</a>
<a href="orders.action" target="_self">历史订单</a>

<form action="orders" target="_self">
	请选择时间
		开始日期：<input type="text" id="d1" name="beginDateStr" value="${beginDefault}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d2\')||\'%y-%M-%d\'}'})"/>
		结束日期：<input type="text" id="d2" name="endDateStr" value="${endDefault}" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d1\')}', maxDate:'%y-%M-%d'})"/>
	请选择商店
		<select name="store_id">
			<option value=0>请选择店铺</option>
			<s:iterator value="#request.storelist" id="currOp">
				<option value="${currOp.id}">${currOp.name}</option>
			</s:iterator>
		</select>
	<input type="submit" value="查看">
	<br/>
	~~亲~点击订单号查看订单详情呦~
	<table>
		<tr><td>订单号</td><td>商店名称</td><td>总价</td><td>生成时间</td><td>员工</td></tr>
		<s:iterator value="#request.orderlist" id="curr">
			<tr><td><a href="orderDetail.action?id=${curr.id}" target="_blank">${curr.id}</a></td>
				<td>${curr.storeName}</td>
				<td>${curr.totalPrice}</td>
				<td>${curr.time}</td>
				<td>${curr.staffName}</td>
			</tr>
		</s:iterator>
	</table>
	<s:actionmessage/>
</form>
</body>
</html>