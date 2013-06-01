<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售金额统计</title>
</head>
<body>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<a href="statistics.action">销售金额统计</a>
<a href="staItems.action">商品销售统计</a>
<a href="orders.action">历史订单</a>

<form action="statistics">
	请选择时间
	<!-- 额。。。每逢','',/'/'总是让人头大！！！ -->
		开始日期：<input type="text" id="d1" name="beginDateStr" value="${beginDefault}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d2\')||\'%y-%M-%d\'}'})"/>
		结束日期：<input type="text" id="d2" name="endDateStr" value="${endDefault}" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d1\')}', maxDate:'%y-%M-%d'})"/>
	请选择商店
		<select name="store_id">
			<option value="${requestScope.store_id}">${requestScope.storeDefault}</option>
			<option value=0>所有店铺</option>
			<s:iterator value="#request.storelist" id="currOp">
				<option value="${currOp.id}">${currOp.name}</option>
			</s:iterator>
		</select>
	<input type="submit" value="查看">
</form>
	
	<table>
			<tr><td>商店名称</td><td>总利润</td><td>净利润</td></tr>
		<s:iterator value="#request.stalist" id="curr">
			<tr><td>${curr.storeName}</td>
				<td>${curr.gProfit}</td>
				<td>${curr.rProfit}</td>
			</tr>
		</s:iterator>
	</table>
	<s:actionmessage/>
	
</body>
</html>