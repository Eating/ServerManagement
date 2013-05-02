<%@ page language="java" import="java.util.*"
	import="eating.user.ShowAllMembers" import="eating.user.AllMembersInfo"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ShowAllMembers.jsp' starting page</title>

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
	 <div >
	 <table>
		<tr>
			<!--  <th><h3>人员ID</h3></th>-->
			<th><h3>人员名称</h3></th>
			<th><h3>邮件</h3></th>
			<th><h3>所属商店</h3></th>
			<th><h3>人员类型</h3></th>
			<th><h3>操作</h3></th>
		</tr>
		<%
			int type = Integer.valueOf(request.getParameter("manType"));
			ShowAllMembers showAll = new ShowAllMembers(type);
			showAll.setStaff();
		    List<AllMembersInfo> list = showAll.getStaff();
			int size = list.size();
			for (int i = 0; i < size; i++) {
		%>
		<tr>
			<!--  <td><%=list.get(i).getUserId()%></td> -->
			<td><%=list.get(i).getUserName()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getStoreName()%></td>
			<td><%=list.get(i).getUserType()%></td>
			<td>
			<form action="deleteMembers">
			<input type="hidden" name="id" value=<%=list.get(i).getUserId() %>></input>
			<input type="hidden" name="userType" value=<%=list.get(i).getUserTypeInt() %>></input>	
			<input type="hidden" name="managerType" value=<%=type %>></input> 
			<input type="submit" value="删除此人员"></input>
			</form>
			</td>
		</tr>
		<%} 
		%>

</table>
	</div>
</body>
</html>
