<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <link rel="stylesheet" media="screen" type="text/css" href="css/ShowAllMembersPage.css" />
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
  <script language="JavaScript">
  function validatorChangePassword(e){
  var originalPsw = document.getElementById("originalPsw").value;
  var newPsw = document.getElementById("newPsw").value;
  var newPswAgain = document.getElementById("newPswAgain").value;
  if(originalPsw.length == 0 || newPsw.length == 0 || newPswAgain.length == 0){
  alert("信息不能为空");
  e.preventDefault();
  }
  else if(originalPsw.length < 4 || originalPsw.length > 20 || newPsw.length < 4 || newPsw.length > 20 || newPswAgain.length < 4 || newPswAgain.length > 20){
  alert("密码的长度应介于4--20之间");
  e.preventDefault();}
  else if( newPsw!= newPswAgain){
  alert("两次新密码不一致");
  e.preventDefault();
  }
  }
  
  </script>
  <%
  int userId = Integer.valueOf(request.getParameter("userId"));
  request.setAttribute("id", userId);
   %>
   <div class="title">
       <img src="css/change.png" width="30"height="30" /><h1>修改密码</h1>
     </div>
      <div>
      <fieldset>
  	<s:actionerror/>
    <s:form action="changePassword">
    <s:hidden name="userId" value="%{#request.id}"/>
    		<s:password id="originalPsw" name="originalPsw" label="原密码"/>
    		<s:password id="newPsw" name="newPsw" label="新密码"/>
    		<s:password id="newPswAgain" name="newPswAgain" label="再次输入新密码"/>
			<s:submit value="确定" onclick="validatorChangePassword(event);"/>
			<s:reset value="重置"/>
	</s:form>
	</fieldset>
     </div>
  </body>
</html>
