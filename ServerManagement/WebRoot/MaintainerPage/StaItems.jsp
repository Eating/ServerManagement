<%@ page language="java" contentType="text/html;" import="eating.user.UserInfo" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />	
	<link rel="stylesheet" media="screen" type="text/css"
	href="css/StaItemsPage.css" />
<html>
<head>
<base target="_blank" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品销售统计</title>
</head>
<body>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
	</script>
	<%
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"userInfo");
	%>
	<div class="topMenuDiv">
		<ul class="topMenuUl">
			<li class="topMenuLi"><img src="css/logo_topMenu.png"
				width="30px" height="30px" /></li>
			<li class="topMenuLi">您好,<%=userInfo.getUserName()%></li>
			<li class="topMenuLi"><a href="Login.jsp">退出</a></li>
		</ul>
	</div>
	<!-- 以上为导航条@@@@@ -->
	
	 <div class="leftMenu">
		<ul id="nav">
			<li>商品维护
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
			<ul>
					<li><a href="statistics.action">销售金额统计</a>
					</li>
					<li><a href="staItems.action">商品销售统计</a>
					</li>
					<li><a href="orders.action">历史订单</a>
					</li>
				</ul>		
			</li>
			<li><a href="MaintainerPage/testDatabaseOpe.jsp">数据备份及恢复</a>
			</li>
		</ul>
	</div>
	<div class="border">
	
	</div>








<div class="StaItemsRight">
<div class="chooseTimes">
<fieldset>
<form action="staItems" target="_self">
	请选择时间
		开始日期：<input type="text" id="d1" name="beginDateStr" value="${beginDefault}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d2\')||\'%y-%M-%d\'}'})"/>
		结束日期：<input type="text" id="d2" name="endDateStr" value="${endDefault}" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d1\')}', maxDate:'%y-%M-%d'})"/>
	请选择商店
		<select name="store_id">
			<option value="${requestScope.store_id}">${requestScope.storeName}</option>
			<option value=0>所有店铺</option>
			<s:iterator value="#request.storelist" id="currOp">
				<option value="${currOp.id}">${currOp.name}</option>
			</s:iterator>
		</select>
	<input type="submit" value="查看">
</form>
</fieldset>
</div>

<div class="staItems">
<fieldset>
<!-- 小意婷~这里${requestScope.storeName}是商店名称，需要显示的明显一点~~~ -->	
您正在查看<font color="#0000FF" >${requestScope.storeName}</font>的销售情况
<br/>
eating君~点击商品名称查看各店铺此商品的销售情况哦~^-^
	<table>
		<tr><td>商品名称</td><td>销售量</td><td>销售总额</td></tr>
		<s:iterator value="#request.staItemList" id="curr">
			<tr><td><a href="singleItem.action?id=${curr.itemName}" target="_blank">${curr.itemName}</a></td>
				<td>${curr.itemNum}</td>
				<td>${curr.totalPrice}</td>
			</tr>
		</s:iterator>
	</table>
	<s:actionmessage/>
	</fieldset>
	</div>
	
	</div>
</body>
</html>