<%@ page language="java" contentType="text/html;"
	import="eating.user.UserInfo" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/MaintainItemsBPage.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<style type="text/css">
input {
	width: 2.5cm;
}
</style>
</head>
<body>
	<%
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"userInfo");
	%>
	<script language="JavaScript">
	startList = function() {
			if (document.all && document.getElementById) {
				navRoot = document.getElementById("nav");
				for (i = 0; i < navRoot.childNodes.length; i++) {
					node = navRoot.childNodes[i];
					if (node.nodeName == "LI") {
						node.onmouseover = function() {
							this.className += " over";
						}
						node.onmouseout = function() {
							this.className = this.className
									.replace(" over", "");
						}
					}
				}
			}
		}
		window.onload = startList;
	
	
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

	<div class="topMenuDiv">
		<ul class="topMenuUl">
			<li class="topMenuLi"><img src="css/logo_topMenu.png"
				width="30px" height="30px" />
			</li>
			<li class="topMenuLi">您好,<%=userInfo.getUserName()%></li>
			<li class="topMenuLi"><a href="Login.jsp">退出</a>
			</li>
		</ul>
	</div>
	<div class="leftMenu">
		<ul id="nav">
			<li><a href="maintainItems.action">商品维护</a>
				<ul>
					<li><a href="maintainItems.action">各店铺商品维护</a></li>
					<li><a href="maintainItemsB.action">商品管理</a></li>
					<li><a href="maintainItemsC.action">商品类别管理</a></li>
				</ul>
			</li>
			<li><a href="maintainStores.action">商店管理</a></li>
			<li><a href="maintainStock.action">库存管理</a></li>
			<li><a href="statistics.action">销售统计</a></li>
			<li><a href="MaintainerPage/testDatabaseOpe.jsp">数据备份及恢复</a></li>
		</ul>
	</div>
	<div class="border"></div>




	<!--  <jsp:include page="MaintainItemMenu.jsp" /> -->
	<div class="maintainItemsBRight">
		<div class="searchItems">
			<fieldset>
				<form action="searchItem" target="_blank">
					请输入商品名称： <input type="text" name="search_item" /> <input
						type="submit" value="搜索" />
				</form>

				<br />

				<s:actionerror />
			</fieldset>
		</div>
		<!-- 商品总列表 -->

		<fieldset>
			<div class="maintainItemsB">
				商品列表
				<form action="maintainItemsB">
					<select name="category_id2">
						<option value=0>请选择商品类别</option>
						<s:iterator value="#request.categorylist" id="currOp">
							<option value="${currOp.id}">${currOp.name}</option>
						</s:iterator>
					</select> <input  type="submit" value="查看"></input>
				</form>

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
			</div>
		</fieldset>

        
        <div class="addItems">
        <fieldset>
		<form action="addItems">
			商品名称<input type="text" name="addItemsName" /> 商品类别 <select
				name="addItemsCate">
				<option value=0>请选择类别</option>
				<s:iterator value="#request.categorylist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> 商品价格<input type="text" onkeypress="return isPrice(event)"
				name="addItemsPrice" /> 商品进价<input type="text"
				onkeypress="return isPrice(event)" name="addPurchasePrice" /> <input
				type="submit" value="添加商品" />
		</form>
		</fieldset>
		</div>
		
		<br />
	</div>
</body>
</html>