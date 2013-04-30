<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmls="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

<title>登陆</title>

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
	<center>
		<div>
			<h1>欢迎来到</h1>
			<h2>服务器管理端</h2>
			<h1>登陆界面</h1>
		</div>
		<div>
			<fieldset>
				<legend>
					<h1>
						<font color="#9933FF">欢迎登陆</font>
					</h1>
				</legend>
				<s:form action="login_check">
					<s:actionerror />
					<s:textfield name="loginName" label="用户"></s:textfield>
					<br />
					<s:password name="password" label="密码"></s:password>
					<s:submit value="登录" />
					<s:reset value="重置" />
				</s:form>
				<a href="index.jsp">返回</a>
			</fieldset>
		</div>





	</center>



</body>
</html>
