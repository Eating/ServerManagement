<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
  request.setCharacterEncoding("GBK");
  String msg = "";//��Ϣ
  if(request.getAttribute("msg")!=null){
    //����Ϣ
    response.setCharacterEncoding("GBK");
    msg = (String)request.getAttribute("msg");
    request.setAttribute("msg",null);//���
  }
  if(!msg.equals("")){
    response.getWriter().println("<script>alert('"+msg+"��');</script>");
  }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�������ݿ�</title>
    <link rel="stylesheet" href="../css/main.css" type="text/css" />
	<style type="text/css">
	<!--
	.STYLE2 {
		font-size: 14px;
		font-family: "����";
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
<span class="STYLE2">���ݿ����&rarr;�������ݿ�</span>
<hr />
<form name="form1" action="../operateDb" method="post">
<div style="margin-left:0px; text-align:left;">
<table border="1" cellspacing="0" cellpadding="6px" width="730px" align="center" style="font-size: 12px; text-align:center;">
 <tr>
  <td>�������ݿ�</td>
 </tr>
 <tr>
  <td>���ݿ���:mydb&nbsp;&nbsp;&nbsp;�������ڣ�${date }</td>
 </tr>
 <%if(request.getAttribute("hasFile")!=null && (Integer)request.getAttribute("hasFile")==1){ %>
 <tr>
  <td style="color:red;">ע�������Ѿ����ݹ����ݿ⣡</td>
 </tr>
 <%} %>
</table>
<input type="hidden" id="hidHasFile" value="${hasFile }" />
</div>
<div style="margin-left:0px; margin-top:8px; text-align:center;">
  <input type="button" value="��ԭ" disabled="disabled" />&nbsp;&nbsp;
  <input type="button" value="����" onclick="checkSubmit()"  />
</div>
</form>
				
</body>
</html>
