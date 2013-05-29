<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <link rel="stylesheet" media="screen" type="text/css" href="css/ModificationPage.css" />
  <link rel="stylesheet" media="screen" type="text/css" href="css/ShowAllMembersPage.css" />
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DeleteMembers.jsp' starting page</title>
    
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
  function validator4(e){
  var userName = document.getElementById("userName").value;
  if(userName.length == 0){
  alert("用户名不能为空");
  e.preventDefault();
  }
  else if(userName.length > 15){
  alert("用户名长度控制在15字符以内");
  e.preventDefault();
  }
  else{
  var reg = new RegExp(/^[A-Za-z0-9]+$/);
  if(!reg.test(userName)){
  alert("用户名含有非法字符，只能由数字和字母组成");
  e.preventDefault();
  }
  
  }
  </script>
  <div class="title">
       <img src="css/delete.png" width="30"height="30" /><h1>输入删除人员信息</h1>
       </div>
    <%
    String param = "a";
    if(request.getParameter("param")!=null)
    param = request.getParameter("param"); 
    int manType = Integer.valueOf(request.getParameter("manType"));
    System.out.println("delete members" + manType);
    request.setAttribute("type", manType);
     %>
     <div class="deleteMembers">
      <fieldset>
  	<s:actionerror/>
    <s:form action="deleteMembersB">
    <s:hidden name="manType" value="%{#request.type}"/>
    		<s:textfield id="userName" name="userName" label="输入你要删除的人员登录名"/>
			<s:submit value="删除" onclick="validator4(event);" />
			<s:reset value="重置"/>
	</s:form>
	</fieldset>
     </div>
     <div >
     <%if(!param.equals("a")){ %>
     <jsp:include page="AcknowledgeDelete.jsp"><jsp:param name="param" value="<%=param%>"/></jsp:include>
     <%
     } %>
     </div>
  </body>
</html>
