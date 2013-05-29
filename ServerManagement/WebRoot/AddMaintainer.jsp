<%@ page language="java" import="java.util.*"
	import="eating.user.GetAllStores" import="src.com.server.hiber.Store" pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <link rel="stylesheet" media="screen" type="text/css" href="css/ModificationPage.css" />
  <link rel="stylesheet" media="screen" type="text/css" href="css/ShowAllMembersPage.css" />
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
	<script language="JavaScript">
function validator2(e){
var userName = document.getElementById("userName").value;
var userEmail = document.getElementById("userEmail").value;
var password = document.getElementById("password").value;
var rePassword = document.getElementById("rePassword").value;

if(userName.length == 0 || userEmail.length == 0 || password.length == 0 || rePassword.length == 0){
alert("信息不能为空");
e.preventDefault();
}
else if(userName.length > 15 || password.length < 4 || password.length > 20){
alert("用户名控制在15字符以内，密码长度在4--20之间");
e.preventDefault();
}
else if(password != rePassword){
alert("两次密码不一致");
e.preventDefault();
}
else{
var reg2 = new RegExp(/^[A-Za-z0-9]+$/);
 if(!reg2.test(userName)){
alert("用户名含有非法字符");
e.preventDefault();
}
else{
var reg3 = new RegExp(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/);
if(!reg3.test(userEmail))
{
alert("邮箱格式不合法");
e.preventDefault();
}
}

}

}
</script>
	<% 
	   GetAllStores getAS = new GetAllStores();
       getAS.setStore();
       List<Store> list = getAS.getStore();
       int size = list.size();
       
     %>
     <div class="title">
       <img src="css/add.png" width="30"height="30" /><h1>增加销售人员</h1>
     </div>
	<div>
		<fieldset>
			<s:actionerror />
			<s:form action="addMaintainer">
				<s:textfield id="userName" name="userName" label="用户名" />
				<s:textfield id="userEmail" name="userEmail" label="用户邮箱" />
				<s:password id="password" name="password" label="设置密码" />
				<s:password id="rePassword" name="rePassword" label="确认密码" />
            所在门店
    <select name="setStore">
					<% for(int i=0; i<size; i++){
					if(!list.get(i).getName().equals("superDepartment") && !list.get(i).getName().equals("server")){
					 %>
					<option value=<%=list.get(i).getId() %>><%=list.get(i).getName() %></option>
					<%}
					}
					%>
				</select>
				<s:submit value="注册" onclick="validator2(event)" />
				<s:reset value="重置" />
			</s:form>
		</fieldset>
	</div>

</body>
</html>
