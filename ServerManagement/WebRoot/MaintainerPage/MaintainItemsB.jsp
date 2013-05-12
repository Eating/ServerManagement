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

        function isPrice(e) {
           var k = window.event ? e.keyCode : e.which;
           if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || k == 46) {
           } else {
               if (window.event) {
                   window.event.returnValue = false;
               }
               else {
                   e.preventDefault(); //for firefox 
               }
           }
        } 
	</script>

<jsp:include page="MaintainItemMenu.jsp" />

	<form action="searchItems">
		请输入商品名称：
		<input type="text" name="search_item" />
		<input type="submit" value="搜索" />
	</form>
	
	<br/>
	
	<s:actionerror/>
	
	<!-- 商品总列表 -->
	商品列表
	<form action="maintainItemsB">
		<select name="category_id2">
			<option value=0>请选择商品类别</option>
		   	<s:iterator value="#request.categorylist" id="currOp">
		   		<option value="${currOp.id}">${currOp.name}</option>	
		   	</s:iterator>
		</select>	
		
		<input type="submit" value="查看"></input>
	</form>
	    
	<table>
		<tr><td>商品名称</td><td>商品类别</td><td>商品价格</td><td>商品进价</td></tr>
	</table>
	<table>
		<s:iterator value="#request.items" id="curr">
			<tr>
			<td><form action="alterItems" onsubmit="return if_alter();">
				<input type="text" name="alterItemsName" value="${curr.name}" />
				<select name="alterItemsCate">
					<option value="${curr.category.id}">${curr.category.name}</option>
		   			<s:iterator value="#request.categorylist" id="currOp">
		   				<option value="${currOp.id}">${currOp.name}</option>	
		   			</s:iterator>
		    	</select>
				<input type="text" name="alterItemsPrice" onkeypress="return isPrice(event)" value="${curr.price}" /> 
				<input type="text" name="alterPurchasePrice" onkeypress="return isPrice(event)" value="${curr.purchasePrice}" />
				<input type="hidden" name="alterItemsId" value="${curr.id}"></input>
				<input type="submit" value="修改"/></form></td>
				
			<td><form action="removeItems" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此商品"/>
				<input type="hidden" name="rmvItemsId" value="${curr.id}"></input>
		 	</form></td></tr>
		 </s:iterator>
	</table>
	
	<form action="addItems">
		商品名称<input type="text" name="addItemsName" />
		商品类别
			<select name="addItemsCate">
				<option value=0>请选择类别</option>
	   			<s:iterator value="#request.categorylist" id="currOp">
	   				<option value="${currOp.id}">${currOp.name}</option>	
	   			</s:iterator>
	    	</select>
	        商品价格<input type="text" onkeypress="return isPrice(event)" name="addItemsPrice" />
	        商品进价<input type="text" onkeypress="return isPrice(event)" name="addPurchasePrice" />    
		<input type="submit" value="添加商品"/>
	</form>
	<br/>

</body>
</html>