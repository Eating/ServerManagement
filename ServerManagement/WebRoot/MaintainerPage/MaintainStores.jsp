<%@ page language="java" import="eating.user.UserInfo" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />	
<html>
<head>
<title>Insert title here</title>
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
		
		function addAll(){
			var iHeight = 400 ;
			var iWidth = 600 ;
			var iTop = (window.screen.availHeight-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-iWidth)/2; //获得窗口的水平位置;
	     	window.open("addStoreDetail.action","添加新店铺",
			"height="+iHeight+",width="+iWidth+",top="+iTop+", left="+iLeft+",status=yes,toolbar=no,menubar=no,location=no");
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



<div class="maintainStoreRight">
	<form action="searchItems">
		请输入店铺名称：
		<input type="text" name="search_store" />
		<input type="submit" value="搜索" />
	</form>
	
	<br/>
	
	<table>
	<tr><td>店铺名称</td><td>店铺地址</td></tr>
	</table>
	<table>
		<s:iterator value="#request.storelist" id="curr">
			<tr><td><form action="alterStore" onsubmit="return if_alter();">
				<input type="text" name="storeName" value="${curr.name}" />
				<input type="text" name="storeAddr" value="${curr.address}" />
				<input type="hidden" name="alterStoreId" value="${curr.id}"></input>
				<input type="submit" value="修改"/>
				</form></td>
			<td><form action="removeStore" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此店铺"/>
				<input type="hidden" name="rmvStoreId" value="${curr.id}"></input>
		 	</form></td></tr>
		</s:iterator>
	</table>
	
	<form action="addStore">
		店铺名称<input type="text" name="addStoreName"/>
		店铺地址<input type="text" name="addStoreAddr"/>
		<input type="submit" value="添加店铺"/>
	</form>
	
	<a href="javascript:;" onclick="addAll()">点此添加新店铺及其商品</a>
	</div>
</body>
</html>