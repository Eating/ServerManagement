<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	30天内销售统计<br/>
	<table>
			<tr><td>商店名称</td><td>总利润</td><td>净利润</td></tr>
		<s:iterator value="#request.stalist" id="curr">
			<tr><td>${curr.storeName}</td>
				<td>${curr.gProfit}</td>
				<td>${curr.rProfit}</td>
			</tr>
		</s:iterator>
	</table>
	
</body>
</html>