<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加新店铺</title>
</head>
<body>	
<% 
		if(request.getAttribute("inputError") != null)
	   	{
	   		out.print("<script>alert('"+request.getAttribute("inputError")+"');</script>") ;
	   	}
   		request.getSession().removeAttribute("inputError") ;
%>

<form action="addStoreDetail">
	<select name="category_id">
		<option value=0>请选择商品类别</option>
	 	<s:iterator value="#request.categorylist" id="currOp">
	 		<option value="${currOp.id}">${currOp.name}</option>	
		</s:iterator>
	</select>
	<input type="submit" value="查看"/>
</form>
<form action="addStoreContent">
	店铺名称<input type="text" maxlength="45" name="addStoreName"/>
	店铺地址<input type="text" maxlength="100" name="addStoreAddr"/>
	<br/>
	<s:iterator value="#request.items" id="curr">
		${curr.name}<input type="checkbox" name="addContent" value="${curr.id}"/>
	</s:iterator>
	<br/>
	<input type="submit" value="添加"/>
</form>

</body>
</html>