<%@ page language="java" import="java.util.*" 
import="eating.user.ShowAllMembers" 
import="eating.user.GetAllStores"
import="eating.user.AllStoreInfo"
import="eating.user.AllMembersInfo" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <link rel="stylesheet" media="screen" type="text/css" href="css/ModificationPage.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Modification.jsp' starting page</title>

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
ShowAllMembers sh = new ShowAllMembers(0);
sh.setUserId(userId);
sh.setOneStaffById();
GetAllStores getAS = new GetAllStores();
getAS.setStore();
request.setAttribute("userId", userId);
request.setAttribute("userName",sh.getOneStaff().getUserName());
request.setAttribute("userEmail", sh.getOneStaff().getEmail());
 %>

 <div class="modificationInfo">
    
  <fieldset>
				<s:form action="modification">
					<s:actionerror />
					<s:textfield name="userId" value="%{#request.userId}" label="用户id" readOnly="true" style="width:200px;height:30px;font-size:20px;"></s:textfield>
					<s:textfield name="userName" value="%{#request.userName}" label="用户名" readOnly="true" style="width:200px;height:30px;font-size:20px;"></s:textfield>
					<br/>
					<s:textfield name="userEmail" value="%{#request.userEmail}" label="用户邮箱" style="width:270px;height:30px;font-size:20px;"></s:textfield>
					<h3>用户所在商店: <%=sh.getOneStaff().getStoreName() %></h3>
					<select name="store">
					
    			<%
    			if(sh.getOneStaff().getStoreName().equals("superDepartment")){
    			%>
    		 <option value="<%=sh.getOneStaff().getStoreId() %>" selected="selected">superDepartment</option>	
    			<% }else if(sh.getOneStaff().getStoreName().equals("superDepartment")){
    			%>
    			<option value=<%=sh.getOneStaff().getStoreId() %>" selected="selected">server</option>	
    			<% } else{
    				for(int i=0; i < getAS.getStore().size(); i++)
    				{
    				if(!getAS.getStore().get(i).getName().equals("superDepartment") && !getAS.getStore().get(i).getName().equals("server")){
    				
    					
    			%> 
    					<option value=<%=getAS.getStore().get(i).getId() %>><%=getAS.getStore().get(i).getName()%></option>	
    			<%
    			 }
    			}
    			}%>
    		</select>					
					<s:submit value="修改" />
					<s:reset value="重置" />
				</s:form>
			</fieldset>
    </div>
 
 
 
</body>
</html>
