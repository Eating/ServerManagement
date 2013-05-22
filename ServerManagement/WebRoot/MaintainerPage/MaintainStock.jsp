<%@ page language="java" import="eating.user.UserInfo" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
	
	<div class="maintainStockRight">
	<form action="maintainStock">
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
	    </select>	
	   
	   	<input type="submit" value="查看"></input>
	</form>

	<table>
	<tr><td>店铺</td><td>名称</td><td>类别</td><td>数量</td><td>库存</td><td>进货</td><td>出库</td>
	</tr>
	</table>
	
	<s:actionmessage/>
	
	<table>
	  	<s:iterator value="#request.itemlist" id="curr">
		 <tr><td>${curr.store}</td>
		 <td>${curr.name}</td> 
		 <td>${curr.category}</td>
		 <td>${curr.number}</td>
		 <td>${curr.stock}</td>
		 <td><form action="alterStock" onsubmit="return if_alter();">
				<input type="text" name="stockInNum" maxlength="3" onkeypress="return isNum(event)" value="0"></input>
				<input type="text" name="stockOutNum" maxlength="3" onkeypress="return isNum(event)" value="0"></input>
				<input type="hidden" name="alterStockId" value="${curr.id}"></input>
				<input type="submit" value="进货/出库"/>
			 </form></td>
	</s:iterator>
	</table>
	</div>
</body>
</html>