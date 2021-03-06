<%@ page language="java" import="java.util.*" import="eating.user.UserInfo" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />	
	<link rel="stylesheet" media="screen" type="text/css"
	href="css/MaintainItemsPage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>各店铺商品维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<% 
		if(request.getAttribute("inputError") != null)
	   	{
	   		out.print("<script>alert('"+request.getAttribute("inputError")+"');</script>") ;
	   	}
   		request.getSession().removeAttribute("inputError") ;
%>  
  
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
		
	    function isNum(e) {
           var k = window.event ? e.keyCode : e.which;
           if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
           } else {
               if (window.event) {
                   window.event.returnValue = false;
               }
               else {
                   e.preventDefault(); //for firefox 
               }
           }
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
			<li><a href="MaintainerPage/testDatabaseOpe.jsp">数据备份及恢复</a>
			</li>
		</ul>
	</div>
	<div class="border">
	
	</div>
	
	<div class="maintainItemsRight">
	<div class="searchItems">
	<fieldset>
		<form action="searchItemlist" target="_blank">
			请输入店铺名称： <input type="text" name="search_store" maxlength="45"/> 
			请输入商品名称： <input type="text" name="search_item" maxlength="45"/> 
			<input type="submit" value="搜索" />
		</form>

		<br />
	</fieldset>
    </div>
     <fieldset>
    <div class="maintainItems" style="height:330px;overflow:auto;">

		<!-- 各商店各类别商品列表 -->
		各店铺商品列表
		<form action="maintainItems">
			<select name="store_id">
				<option value="${requestScope.store_id}">${requestScope.storeDefault}</option>
				<option value=0>所有店铺</option>
				<s:iterator value="#request.storelist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> 
			<select name="category_id">
				<option value="${requestScope.category_id}">${requestScope.cateDefault}</option>
				<option value=0>所有类别</option>
				<s:iterator value="#request.categorylist" id="currOp">
					<option value="${currOp.id}">${currOp.name}</option>
				</s:iterator>
			</select> <input  type="submit" value="查看"></input>
		</form>

		<table>
			<tr>
				<th>店铺</th>
				<th>名称</th>
				<th>类别</th>
				<th>价格</th>
				<th>数量</th>
				<th>库存</th>
				<th>折扣</th>
				<th>赠品</th>
				<th>赠品数</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			<s:iterator value="#request.itemlist" id="curr">
				<tr>
					<td>${curr.store}</td>
					<td>${curr.name}</td>
					<td>${curr.category}</td>
					<td>${curr.price}</td>
					<td>${curr.number}</td>
					<td>${curr.stock}</td>
					<form action="alterItemlist" onsubmit="return if_alter();">
					<td>
							<input style="width:40px;" maxlength="4" type="text" name="alterListDis"
								onkeypress="return isPrice(event)" value="${curr.discount}" /> 
					</td>
						<td>
						<select name="alterListGift">
							<option value=${curr.gift_id}>${curr.giftName}</option>
							<option value=0>无赠品</option>
							<s:iterator value="#request.items" id="currOp">
								<option value="${currOp.id}">${currOp.name}</option>
							</s:iterator>
						</select>
						</td>
						<td>
						 <input type="text" maxlength="2" style="width:40px;"
							onkeypress="return isNum(event)" name="alterListGiftNum"
							value="${curr.giftNum}" /> 
							<input type="hidden" name="alterListId" value="${curr.id}"></input> 
						</td>	
						
						<td>	
							<input type="submit" value="修改" />
							</td>
						</form>
					
					<form action="removeItemlist" onsubmit="return if_rmv();">
						<td>
							<input type="submit" value="删除" /> <input type="hidden"
								name="rmvItemlistId" value="${curr.id}"></input>
						</td>
					</form>
					
				</tr>
			</s:iterator>
		</table>
		<s:actionmessage />
		<s:actionerror />
      </div>
      </fieldset>
      
      <div class="addItem" >
      <fieldset>
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

		<br />
		</fieldset>
		</div>
		
	</div>
	
	
  </body>
</html>
