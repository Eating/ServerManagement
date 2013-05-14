<%@ page language="java" import="java.util.*" import="eating.user.UserInfo" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" media="screen" type="text/css"
	href="../css/LeftMenuPage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'LeftMenu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
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
   <div class="adminMenu">
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
	
	<!-- <jsp:include page="MaintainItemMenu.jsp" />   -->
	<div class="maintainItemsRight">
		<form action="searchItems">
			请输入店铺名称： <input type="text" name="search_store" /> 请输入商品名称： <input
				type="text" name="search_item" /> <input type="submit" value="搜索" />
		</form>

		<br />

		<s:actionerror />

		<!-- 各商店各类别商品列表 -->
		各店铺商品列表
		<form action="maintainItems">
			<select name="store_id">
				<option value=0>请选择店铺</option>
				<s:iterator value="#request.storelist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> <select name="category_id">
				<option value=0>请选择商品类别</option>
				<s:iterator value="#request.categorylist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> <input type="submit" value="查看"></input>
		</form>

		<table>
			<tr>
				<td>店铺</td>
				<td>名称</td>
				<td>类别</td>
				<td>价格</td>
				<td>数量</td>
				<td>库存</td>
				<td>折扣</td>
				<td>赠品</td>
				<td>赠品数</td>
			</tr>
		</table>
		<table>
			<s:iterator value="#request.itemlist" id="curr">
				<tr>
					<td>${curr.store}</td>
					<td>${curr.name}</td>
					<td>${curr.category}</td>
					<td>${curr.price}</td>
					<td>${curr.number}</td>
					<td>${curr.stock}</td>
					<td><form action="alterItemlist" onsubmit="return if_alter();">
							<input id="dis" type="text" name="alterListDis"
								value="${curr.discount}" /> <select name="alterListGift">
								<option value=${curr.gift_id}>${curr.giftName}</option>
								<option value=0>取消赠品</option>
								<s:iterator value="#request.items" id="currOp">
									<option value="${currOp.id}">${currOp.name}</option>
								</s:iterator>
							</select> <input type="text" maxlength="2"
								onkeypress="return isNum(event)" name="alterListGiftNum"
								value="${curr.giftNum}" /> <input type="hidden"
								name="alterListId" value="${curr.id}"></input> <input
								type="submit" value="修改" />
						</form>
					</td>
					<td><form action="removeItemlist" onsubmit="return if_rmv();">
							<input type="submit" value="删除该店此商品" /> <input type="hidden"
								name="rmvItemlistId" value="${curr.id}"></input>
						</form>
					</td>
				</tr>
			</s:iterator>
		</table>

		<form action="addItemlist">
			店铺 <select name="addListStore">
				<option value=0>请选择店铺</option>
				<s:iterator value="#request.storelist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> 物品 <select name="addListItem">
				<option value=0>请选择商品</option>
				<s:iterator value="#request.items" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> <input type="submit" value="添加商品" />
		</form>

		<s:actionmessage />

		<br />
	</div>
	
	
  </body>
</html>
