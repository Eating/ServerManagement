<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
  request.setCharacterEncoding("GBK");
  String msg = "";//消息
  if(request.getAttribute("msg")!=null){
    //有消息
    response.setCharacterEncoding("GBK");
    msg = (String)request.getAttribute("msg");
    request.setAttribute("msg",null);//清空
  }
  if(!msg.equals("")){
    response.getWriter().println("<script>alert('"+msg+"！');</script>");
  }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>还原数据库</title>
    <link rel="stylesheet" href="../css/main.css" type="text/css" />
	<style type="text/css">
	<!--
	.STYLE2 {
		font-size: 14px;
		font-family: "黑体";
		margin-left:30px;
	}
	img{
	  border:0;
	  cursor:pointer;
	}
	-->
	</style>
  </head>
  
<body oncontextmenu="return false">
<br />
<span class="STYLE2">数据库管理&rarr;还原数据库</span>
<hr />
<%
 if(request.getAttribute("nodb")!=null){%>
   <br /><span style='color:#AAAAAA;font-size:16px;margin-left:15px;margin-top:40px;'>${nodb }</span>
 <%
	request.setAttribute("nodb",null);
	return;
  }
 %>
<form name="form1" action="../operateDb" method="post">
<div style="margin-left:0px; text-align:left;">
<table border="1" cellspacing="0" cellpadding="6px" width="730px" style="font-size: 12px;text-align:left;">
 <tr>
  <td>数据库名</td>
  <td>
	  <input type="text" value="${dbName }" name="txtDbName" readonly="readonly" />
  </td>
 </tr>
 <tr>
  <td>备份日期</td>
  <td>
      <select name="txtTime" style="width: 150px">
       <c:forEach var="db" items="${list}" >
		<option value="${db}">${db}</option>
		</c:forEach>
      </select>
  </td>
 </tr>
</table>
</div>
<div style="margin-left:0px; margin-top:8px; text-align:center;">
<input type="button" value="还原" onclick="checkSubmit()" />&nbsp;&nbsp;
<input type="button" value="备份" disabled="disabled" />
</div>
</form>
<script type="text/javascript">
//还原
function checkSubmit(){
  if(confirm("确定还原数据库？\n\r\n\r注意：还原后将覆盖现有数据，请做好备份！")){
		document.form1.action+= "/doRestore.do";
		document.form1.submit();	
	  }
 }
</script>					
</body>
</html>
