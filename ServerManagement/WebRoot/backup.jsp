<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
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
    <title>备份数据库</title>
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
<span class="STYLE2">数据库管理&rarr;备份数据库</span>
<hr />
<form name="form1" action="../operateDb" method="post">
<div style="margin-left:0px; text-align:left;">
<table border="1" cellspacing="0" cellpadding="6px" width="730px" align="center" style="font-size: 12px; text-align:center;">
 <tr>
  <td>备份数据库</td>
 </tr>
 <tr>
  <td>数据库名:mydb&nbsp;&nbsp;&nbsp;备份日期：${date }</td>
 </tr>
 <%if(request.getAttribute("hasFile")!=null && (Integer)request.getAttribute("hasFile")==1){ %>
 <tr>
  <td style="color:red;">注：今日已经备份过数据库！</td>
 </tr>
 <%} %>
</table>
<input type="hidden" id="hidHasFile" value="${hasFile }" />
</div>
<div style="margin-left:0px; margin-top:8px; text-align:center;">
  <input type="button" value="还原" disabled="disabled" />&nbsp;&nbsp;
  <input type="button" value="备份" onclick="checkSubmit()"  />
</div>
</form>
				
</body>
</html>
