<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
    <title>��ԭ���ݿ�</title>
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
<span class="STYLE2">���ݿ����&rarr;��ԭ���ݿ�</span>
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
  <td>���ݿ���</td>
  <td>
	  <input type="text" value="${dbName }" name="txtDbName" readonly="readonly" />
  </td>
 </tr>
 <tr>
  <td>��������</td>
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
<input type="button" value="��ԭ" onclick="checkSubmit()" />&nbsp;&nbsp;
<input type="button" value="����" disabled="disabled" />
</div>
</form>
<script type="text/javascript">
//��ԭ
function checkSubmit(){
  if(confirm("ȷ����ԭ���ݿ⣿\n\r\n\rע�⣺��ԭ�󽫸����������ݣ������ñ��ݣ�")){
		document.form1.action+= "/doRestore.do";
		document.form1.submit();	
	  }
 }
</script>					
</body>
</html>
