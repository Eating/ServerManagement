<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'AddManagers.jsp' starting page</title>

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
  	int id = Integer.valueOf(request.getParameter("manType"));
  	if(id == 3){
   %>
	<h3>对不起，您没有权限添加管理员</h3>
	<%} else
    %>
	<div>
		<fieldset>
			<legend>添加信息</legend>
			<s:actionerror />
			<s:form action="addManager">
				<s:textfield name="userName" label="用户名" />
				<s:textfield name="userEmail" label="用户邮箱" />
				<s:password name="password" label="设置密码"/>
			<s:password name="rePassword" label="确认密码"/>
            管理员类型
    <select name="setType">
					<%if(id == 1){  %>
					<option value=2>二级管理员</option>
					<option value=3>三级管理员</option>
					<%} else if(id == 2){ %>
					<option value=3>三级管理员</option><%} %>
				</select>
				<s:submit value="注册" />
				<s:reset value="重置" />
			</s:form>
		</fieldset>
	</div>
</body>
</html>
