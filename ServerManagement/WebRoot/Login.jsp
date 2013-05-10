<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" media="screen" type="text/css" href="css/LoginPage.css"/>
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
		<div class="logo">
		
		</div>
		<div class="login">
			<fieldset>
				<s:form action="login_check">
					<s:actionerror />
					
					<s:textfield name="loginName" label="用户" style="width:200px;height:30px;font-size:20px"></s:textfield>
					<br/>
					<br/>
					<s:password name="password" label="密码" style="width:200px;height:30px;font-size:20px;margin-top:20px"></s:password>
					<s:submit  class="submit" value="登录" style="height:30px;width:55px;margin-top:10px"/>
					<s:reset value="重置" style="height:30px;width:55px;margin-top:5px"/>
				</s:form>
			</fieldset>
			</div>
		</div>





	</center>



</body>
</html>
