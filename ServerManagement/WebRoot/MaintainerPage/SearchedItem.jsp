<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" media="screen" type="text/css"
	href="css/MaintainItemsBPage.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

		<!-- 商品总列表 -->
		<fieldset>
			<div class="maintainItemsB">
				<table>
					<tr>
						<th>商品名称</th>
						<th>商品类别</th>
						<th>商品价格</th>
						<th>商品进价</th>
						<th>修改</th>
						<th>删除</th>
					</tr>

					<s:iterator value="#request.items" id="curr">
						<tr>
							<form action="alterItems" onsubmit="return if_alter();">
							
									<td><input type="text" name="alterItemsName" value="${curr.name}" /></td>
									<td>
									<select name="alterItemsCate">
										<option value="${curr.category.id}">${curr.category.name}</option>
										<s:iterator value="#request.categorylist" id="currOp">
											<option value="${currOp.id}">${currOp.name}</option>
										</s:iterator>	
									</select> 
									</td>
									<td>
									<input type="text" name="alterItemsPrice"
										onkeypress="return isPrice(event)" value="${curr.price}" /> </td>
									<td>	<input
										type="text" name="alterPurchasePrice"
										onkeypress="return isPrice(event)"
										value="${curr.purchasePrice}" /> </td>
									<td>	<input type="hidden"
										name="alterItemsId" value="${curr.id}"></input>
									 <input
										type="submit" style="width:40px;" value="修改" /> </td>
								</form>

							<form action="removeItems" onsubmit="return if_rmv();">
								<td>	<input type="submit" style="width:40px;" value="删除" />
								 <input type="hidden"
										name="rmvItemsId" value="${curr.id}"/></td>
								</form>
						</tr>
					</s:iterator>
					
				</table>
				<s:actionerror/>
				<s:actionmessage/>	
			</div>
		</fieldset>
		
	
</body>
</html>