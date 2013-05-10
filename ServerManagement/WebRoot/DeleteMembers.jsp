<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
    <%
    String param = "a";
    if(request.getParameter("param")!=null)
    param = request.getParameter("param"); 
    int manType = Integer.valueOf(request.getParameter("manType"));
    System.out.println("delete members" + manType);
    request.setAttribute("type", manType);
     %>
     <div>
      <fieldset>
    <legend>添加信息</legend>
  	<s:actionerror/>
    <s:form action="deleteMembersB">
    <s:hidden name="manType" value="%{#request.type}"/>
    		<s:textfield name="userName" label="输入你要删除的人员登录名"/>
			<s:submit value="删除"/>
			<s:reset value="重置"/>
	</s:form>
	</fieldset>
     </div>
     <div>
     <%if(!param.equals("a")){ %>
     <jsp:include page="AcknowledgeDelete.jsp"><jsp:param name="param" value="<%=param%>"/></jsp:include>
     <%
     } %>
     </div>
  </body>
</html>
