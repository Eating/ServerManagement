<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<script language="JavaScript">
		function if_alter()
		{
			var r=confirm("确认修改？");
			return r ;
		}
		
		function isNum(e) {
           var k = window.event ? e.keyCode : e.which;
           if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
           } else {
               if (window.event) {
                   window.event.returnValue = false;
               }
               else {
                   e.preventDefault(); //for firefox 
               }
           }
        }
	</script>
	
	<form action="maintainStock">
		<select name="store_id">
			<option value=0>请选择店铺</option>
	   		<s:iterator value="#request.storelist" id="currOp">
	   			<option value="${currOp.id}">${currOp.name}</option>	
	   		</s:iterator>
	   	</select>
	
		<select name="category_id">
			<option value=0>请选择商品类别</option>
	   		<s:iterator value="#request.categorylist" id="currOp">
	   			<option value="${currOp.id}">${currOp.name}</option>	
	   		</s:iterator>
	    </select>	
	   
	   	<input type="submit" value="查看"></input>
	</form>

	<table>
	<tr><td>店铺</td><td>名称</td><td>类别</td><td>数量</td><td>库存</td><td>进货</td><td>出库</td>
	</tr>
	</table>
	
	<s:actionmessage/>
	
	<table>
	  	<s:iterator value="#request.itemlist" id="curr">
		 <tr><td>${curr.store}</td>
		 <td>${curr.name}</td> 
		 <td>${curr.category}</td>
		 <td>${curr.number}</td>
		 <td>${curr.stock}</td>
		 <td><form action="alterStock" onsubmit="return if_alter();">
				<input type="text" name="stockInNum" maxlength="3" onkeypress="return isNum(event)" value="0"></input>
				<input type="text" name="stockOutNum" maxlength="3" onkeypress="return isNum(event)" value="0"></input>
				<input type="hidden" name="alterStockId" value="${curr.id}"></input>
				<input type="submit" value="进货/出库"/>
			 </form></td>
	</s:iterator>
	</table>
</body>
</html>