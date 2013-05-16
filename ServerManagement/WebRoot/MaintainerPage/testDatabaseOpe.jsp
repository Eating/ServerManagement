<%@ page language="java" import="java.util.*" 
import="eating.user.UserInfo" import="eating.backup.GetBackupVersion" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" media="screen" type="text/css"
	href="../css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="../css/SuperManagerPage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testDatabaseOpe.jsp' starting page</title>
    
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
  <%
  int ifExist = 0;
  if(request.getParameter("ifExist") != null)
  ifExist = Integer.valueOf(request.getParameter("ifExist"));
  GetBackupVersion get = new GetBackupVersion();
  List<String> listOfVersion = get.getVersionList();
  request.setAttribute("path", "C:/Canjie/");
  UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"userInfo");
   %>
   <div class="topMenuDiv">
		<ul class="topMenuUl">
			<li class="topMenuLi"><img src="css/logo_topMenu.png"
				width="30px" height="30px" /></li>
			<li class="topMenuLi">您好,<%=userInfo.getUserName() %></li>
			<li class="topMenuLi"><a href="Login.jsp">退出</a></li>
		</ul>
	</div>
<!--以上为导航条  导航条完成 -->




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
			</li>
			<li><a href="MaintainerPage/testDatabaseOpe.jsp">数据备份及恢复</a>
			</li>
		</ul>
	</div>
	<div class="border">
	
	</div>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  <s:form action="toBackup">
  <s:textfield name="path" value="%{#request.path}" readonly="true"> </s:textfield>
  <s:submit value="备份"></s:submit>
  </s:form>
   <%if(listOfVersion == null || listOfVersion.size() == 0){ %>
  <h4>当前没有备份版本</h4>
  <%}else { %>
  <s:form action="toRecover">
  <s:actionerror />
  <s:hidden name="path" value="%{#request.path}"></s:hidden>
  <s:submit value="恢复"></s:submit>
 
  <h4>选择要恢复的版本</h4>
  <select name="versionToRecover">
  <% for(int i = 0; i < listOfVersion.size(); i ++){ %>
  <option value=<%=listOfVersion.get(i)%>><%=listOfVersion.get(i) %></option>
  <%} %>
  </select>
  </s:form>
  <%} %>
   
  </body>
</html>
