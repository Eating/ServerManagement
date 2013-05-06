<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ChangePassword.jsp' starting page</title>
    
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
  int userId = Integer.valueOf(request.getParameter("userId"));
  request.setAttribute("id", userId);
   %>
      <div>
      <fieldset>
    <legend>修改密码</legend>
  	<s:actionerror/>
    <s:form action="changePassword">
    <s:hidden name="userId" value="%{#request.id}"/>
    		<s:password name="originalPsw" label="原密码"/>
    		<s:password name="newPsw" label="新密码"/>
    		<s:password name="newPswAgain" label="再次输入新密码"/>
			<s:submit value="删除"/>
			<s:reset value="重置"/>
	</s:form>
	</fieldset>
     </div>
  </body>
</html>
