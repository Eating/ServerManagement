<%@ page language="java" import="eating.user.UserInfo" contentType="text/html;1"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />	
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
	</script>

<div class="topMenuDiv">
		<ul class="topMenuUl">
			<li class="topMenuLi"><img src="css/logo_topMenu.png"
				width="30px" height="30px" /></li>
			<li class="topMenuLi">您好,<%=userInfo.getUserName() %></li>
			<li class="topMenuLi"><a href="Login.jsp">退出</a></li>
		</ul>
	</div>
   <div class="leftMenu">
		<ul id="nav">
			<li><a href="maintainItems.action">商品维护</a>
				<ul>
					<li><a href="maintainItems.action">各店铺商品维护</a>
					</li>
					<li><a href="maintainItemsB.action">商品管理</a>
					</li>
					<li><a href="maintainItemsC.action">商品类别管理</a>
					</li>
				</ul></li>
			<li><a href="maintainStores.action">商店管理</a>
			</li>
			<li><a href="maintainStock.action">库存管理</a>
			</li>
			<li><a href="statistics.action">销售统计</a>
			</li>
			<li><a href="backup.action">数据备份及恢复</a>
			</li>
		</ul>
	</div>
	<div class="border">
	
	</div>
	
	<div>
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
	</div>
</body>
</html>