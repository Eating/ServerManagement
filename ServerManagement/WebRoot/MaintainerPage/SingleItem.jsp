<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>单项商品销售统计</title>
</head>
<body>
您正在查看的商品：${requestScope.itemName}	
	<table>
		<tr><td>商店名称</td><td>销售量</td><td>销售总额</td></tr>
		<s:iterator value="#request.staItemList" id="curr">
			<tr><td>${curr.storeName}</td>
				<td>${curr.itemNum}</td>
				<td>${curr.totalPrice}</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>