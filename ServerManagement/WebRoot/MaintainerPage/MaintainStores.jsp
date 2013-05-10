<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<script language="JavaScript">
		function if_rmv()
		{
			var r=confirm("确认删除？");
			return r ;
		}
		
		function if_alter()
		{
			var r=confirm("确认修改？");
			return r ;
		}
		
		function addAll(){
			var iHeight = 400 ;
			var iWidth = 600 ;
			var iTop = (window.screen.availHeight-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-iWidth)/2; //获得窗口的水平位置;
	     	window.open("addStoreDetail.action","添加新店铺",
			"height="+iHeight+",width="+iWidth+",top="+iTop+", left="+iLeft+",status=yes,toolbar=no,menubar=no,location=no");
	  	}
	</script>

	<form action="searchItems">
		请输入店铺名称：
		<input type="text" name="search_store" />
		<input type="submit" value="搜索" />
	</form>
	
	<br/>
	
	<table>
	<tr><td>店铺名称</td><td>店铺地址</td></tr>
	</table>
	<table>
		<s:iterator value="#request.storelist" id="curr">
			<tr><td><form action="alterStore" onsubmit="return if_alter();">
				<input type="text" name="storeName" value="${curr.name}" />
				<input type="text" name="storeAddr" value="${curr.address}" />
				<input type="hidden" name="alterStoreId" value="${curr.id}"></input>
				<input type="submit" value="修改"/>
				</form></td>
			<td><form action="removeStore" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此店铺"/>
				<input type="hidden" name="rmvStoreId" value="${curr.id}"></input>
		 	</form></td></tr>
		</s:iterator>
	</table>
	
	<form action="addStore">
		店铺名称<input type="text" name="addStoreName"/>
		店铺地址<input type="text" name="addStoreAddr"/>
		<input type="submit" value="添加店铺"/>
	</form>
	
	<a href="javascript:;" onclick="addAll()">点此添加新店铺及其商品</a>
</body>
</html>