<%@ page language="java" contentType="text/html;1"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" media="screen" type="text/css" href="css/admin.css" />
	
	<style type="text/css">
  		input
  		{
  			width:2.5cm;
  		}
  	</style>
</head>
<body>
	<script language="JavaScript">
		function if_rmv()
		{
			var r=confirm("确认删除？");
			return r ;
		}
		
		function if_alter()
		{
			var r=confirm("确认修改？");
			return r ;
		}
	</script>

<jsp:include page="MaintainItemMenu.jsp" />
	<s:actionerror/>
	
	<!-- 商品类别列表 -->
	商品类别：
	<table>
		<s:iterator value="#request.categorylist" id="curr">
			<tr><td><form action="alterCategory" onsubmit="return if_alter();">
				<input type="text" name="categoryName" value="${curr.name}" />
				<input type="hidden" name="alterCategoryId" value="${curr.id}"></input>
				<input type="submit" value="修改"/>
				</form></td>
			<td><form action="removeCategory" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此类别"/>
				<input type="hidden" name="rmvCategoryId" value="${curr.id}"></input>
		 	</form></td></tr>
		</s:iterator>
	</table>
	<form action="addCategory">
		类别名称<input type="text" name="addCateName" />
		<input type="submit" value="添加商品类"/>
	</form>
	<br/>
</body>
</html>