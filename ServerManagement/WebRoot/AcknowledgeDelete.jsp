<%@ page language="java" import="java.util.*" 
import="eating.user.AllMembersInfo" import="eating.user.ShowAllMembers" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <link rel="stylesheet" media="screen" type="text/css" href="css/AcknowledgeDeletePage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AcknowledgeDelete.jsp' starting page</title>
    
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
  String param = request.getParameter("param");
 ShowAllMembers sh  = new ShowAllMembers(0);
 sh.setUserName(param);
 sh.setOneStaff();
 AllMembersInfo info = sh.getOneStaff();
   %>
    <div class="acknowledgeInfo">
    <table>
    <tr>
   <th colspan="2"><h3>再次确认要删除的管理员信息</h3></th>
   </tr>
   <tr>
   <td>人员登陆名</td>
   <td><%=info.getUserName()%></td>
   </tr>
   <tr>
   <td>人员邮箱</td>
   <td><%=info.getEmail() %></td>
   </tr>
   <tr>
   <td>人员所属商店</td>
   <td><%=info.getStoreName() %></td>
   </tr>
    <tr>
   <td>人员类型</td>
   <td><%=info.getUserType() %></td>
   </tr>
   <tr clospan="2">
   <td>
   <form action="acknowledgeDelete">
     <input type="hidden" name="userId" value=<%=info.getUserId()%>></input>
     <input type="submit" value="删除此管理员"></input>
     </form>
   </td>
   </tr>
   
   
    </table>
    </div>
  </body>
</html>
