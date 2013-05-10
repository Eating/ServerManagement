<%@ page language="java" import="java.util.*"
	import="eating.user.GetAllStores" import="src.com.server.hiber.Store" pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>'AddMaintainer.jsp' starting page</title>

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
	   GetAllStores getAS = new GetAllStores();
       getAS.setStore();
       List<Store> list = getAS.getStore();
       int size = list.size();
       
     %>
	<div>
		<fieldset>
			<legend>添加信息</legend>
			<s:actionerror />
			<s:form action="addMaintainer">
				<s:textfield name="userName" label="用户名" />
				<s:textfield name="userEmail" label="用户邮箱" />
				<s:password name="password" label="设置密码" />
				<s:password name="rePassword" label="确认密码" />
            管理员类型
    <select name="setStore">
					<% for(int i=0; i<size; i++){
					if(!list.get(i).getName().equals("superDepartment") && !list.get(i).getName().equals("server")){
					 %>
					<option value=<%=list.get(i).getId() %>><%=list.get(i).getName() %></option>
					<%}
					}
					%>
				</select>
				<s:submit value="注册" />
				<s:reset value="重置" />
			</s:form>
		</fieldset>
	</div>

</body>
</html>
