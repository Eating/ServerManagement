<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_blank" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品销售统计</title>
</head>
<body>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<a href="statistics.action" target="_self">销售金额统计</a>
<a href="staItems.action" target="_self">商品销售统计</a>
<a href="orders.action" target="_self">历史订单</a>

<form action="staItems" target="_self">
	请选择时间
		开始日期：<input type="text" id="d1" name="beginDateStr" value="${beginDefault}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d2\')||\'%y-%M-%d\'}'})"/>
		结束日期：<input type="text" id="d2" name="endDateStr" value="${endDefault}" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d1\')}', maxDate:'%y-%M-%d'})"/>
	请选择商店
		<select name="store_id">
			<option value="${requestScope.store_id}">${requestScope.storeName}</option>
			<option value=0>所有店铺</option>
			<s:iterator value="#request.storelist" id="currOp">
				<option value="${currOp.id}">${currOp.name}</option>
			</s:iterator>
		</select>
	<input type="submit" value="查看">
</form>

<!-- 小意婷~这里${requestScope.storeName}是商店名称，需要显示的明显一点~~~ -->	
您正在查看${requestScope.storeName}的销售情况	
<br/>
eating君~点击商品名称查看各店铺此商品的销售情况哦~^-^
	<table>
		<tr><td>商品名称</td><td>销售量</td><td>销售总额</td></tr>
		<s:iterator value="#request.staItemList" id="curr">
			<tr><td><a href="singleItem.action?id=${curr.itemName}" target="_blank">${curr.itemName}</a></td>
				<td>${curr.itemNum}</td>
				<td>${curr.totalPrice}</td>
			</tr>
		</s:iterator>
	</table>
	<s:actionmessage/>
</body>
</html>