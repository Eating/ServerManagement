<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.hibernate.Session" import="org.hibernate.Criteria"
	import="org.hibernate.criterion.Restrictions" import="java.util.*"
	import="eating.user.UserInfo"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>SuperManager page</title>

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
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"userInfo");
		String userName = userInfo.getUserName();
		int type = userInfo.getType();
		String typeS = String.valueOf(type);
		int id = 1;
		String param = "a";
		if (request.getParameter("param") != null)
			param = request.getParameter("param");
		if (request.getParameter("id") != null)
			id = Integer.valueOf(request.getParameter("id"));
		int check = 0;
		String checkS = "";
		if (request.getParameter("check") != null) {
			check = Integer.valueOf(request.getParameter("check"));
			checkS = request.getParameter("check");
		}
		System.out.println("superManager" + check);
	%>

	<div class="topMenuDiv">
		<ul class="topMenuUl">
			<li class="topMenuLi"><img src="css/logo_topMenu.png"
				width="30px" height="30px" />
			</li>
			<li class="topMenuLi">您好,<%=userName%></li>
			<li class="topMenuLi"><a href="Login.jsp">退出</a>
			</li>
		</ul>
	</div>

	<div class="adminMenu">
		<ul>
			<li class="check"><a href="SuperManager.jsp?id=1">所有人员信息</a>
			</li>
			<li class="add"><a href="SuperManager.jsp?id=2">增加维护员</a>
			</li>
			<li class="add"><a href="SuperManager.jsp?id=3">增加管理人员</a>
			</li>
			<li class="delete"><a href="SuperManager.jsp?id=4">删除人员</a>
			</li>
			<li class="change"><a href="SuperManager.jsp?id=5">修改密码</a>
			</li>
		</ul>
	</div>
	<div class="includeMenu">
		<%
			if (!param.equals("a")) {
		%>
		<jsp:include page="DeleteMembers.jsp">
			<jsp:param name="manType" value="<%=type%>"></jsp:param>
			<jsp:param name="param" value="<%=param%>"></jsp:param>
		</jsp:include>
		<%
			} else if (check != 0) {
		%>
		<jsp:include page="ShowAllMembers.jsp">
			<jsp:param name="manType" value="<%=type%>"></jsp:param>
			<jsp:param name="check" value="<%=checkS%>"></jsp:param>
		</jsp:include>
		<%
			} else if (id == 1) {
		%>
		<jsp:include page="ShowAllMembers.jsp"><jsp:param
				name="manType" value="<%=type%>"></jsp:param></jsp:include>
		<%
			} else if (id == 2) {
		%>
		<jsp:include page="AddMaintainer.jsp"><jsp:param
				name="manType" value="<%=type%>"></jsp:param></jsp:include>
		<%
			} else if (id == 3) {
		%>
		<jsp:include page="AddManagers.jsp"><jsp:param
				name="manType" value="<%=type%>"></jsp:param></jsp:include>
		<%
			} else if (id == 4) {
		%>
		<jsp:include page="DeleteMembers.jsp"><jsp:param
				name="manType" value="<%=type%>"></jsp:param></jsp:include>
		<%
			} else if (id == 5) {
		%>
		<jsp:include page="ChangePassword.jsp"><jsp:param
				name="userId" value="<%=userInfo.getId() %>"></jsp:param></jsp:include>
		<%} %>

	</div>
</body>
</html>
