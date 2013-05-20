<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<link rel="stylesheet" media="screen" type="text/css"
	href="css/MaintainStoresPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/LeftMenuPage.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="css/SuperManagerPage.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script language="JavaScript">
	startList = function() {
			if (document.all && document.getElementById) {
				navRoot = document.getElementById("nav");
				for (i = 0; i < navRoot.childNodes.length; i++) {
					node = navRoot.childNodes[i];
					if (node.nodeName == "LI") {
						node.onmouseover = function() {
							this.className += " over";
						}
						node.onmouseout = function() {
							this.className = this.className
									.replace(" over", "");
						}
					}
				}
			}
		}
		window.onload = startList;
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
		</script>
	
		<div class="showTable">
			<table style="width:700px">
				<tr>
					<th>店铺名称</th>
					<th>店铺地址</th>
					<th>修改</th>
					<th>删除</th>
				</tr>

				<s:iterator value="#request.storelist" id="curr">
					<tr>
						<form action="alterStore" onsubmit="return if_alter();">
						
								<td><input type="text" name="storeName" value="${curr.name}" /></td>
								<td><input type="text" name="storeAddr" value="${curr.address}" /></td>
								<td><input type="hidden" name="alterStoreId" value="${curr.id}"></input> 
								<input type="submit" value="修改" /></td>
							</form>
						
						<form action="removeStore" onsubmit="return if_rmv();">
								<td><input type="submit" value="删除" />
								    <input type="hidden" name="rmvStoreId" value="${curr.id}"></input></td>
							</form>
						
					</tr>
				</s:iterator>
			</table>
		</div>
		</fieldset>
		
		<s:actionmessage/>
		<s:actionerror/>
</body>
</html>