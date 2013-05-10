<%@ page language="java" contentType="text/html;1"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" media="screen" type="text/css" href="css/admin.css" />
	
	<style type="text/css">
  		input
  		{
  			width:2.5cm;
  		}
  	</style>
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
        
        function isPrice(e) {
           var k = window.event ? e.keyCode : e.which;
           if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || k == 46) {
           } else {
               if (window.event) {
                   window.event.returnValue = false;
               }
               else {
                   e.preventDefault(); //for firefox 
               }
           }
        } 
        
        //打折验证还不行哈~~
        function checkDis(str) {
            var t1=document.getElementById("dis").value;
				var patrn1=/^0.[0-9]{2}$/;
				var re = new RegExp(patrn1);
				if(!re.test(dis)){
						alert("打折信息有误，请输入如“0.88”");
				}
        }
	</script>

	<form action="searchItems">
		请输入店铺名称：
		<input type="text" name="search_store" />
		请输入商品类别：
		<input type="text" name="search_category" />
		请输入商品名称：
		<input type="text" name="search_item" />
		<input type="submit" value="搜索" />
	</form>
	
	<br/>
	
	<s:actionerror/>
	
	<!-- 商品类别列表 -->
	商品类别：
	<table>
		<s:iterator value="#request.categorylist" id="curr">
			<tr><td><form action="alterCategory" onsubmit="return if_alter();">
				<input type="text" name="categoryName" value="${curr.name}" />
				<input type="hidden" name="alterCategoryId" value="${curr.id}"></input>
				<input type="submit" value="修改"/>
				</form></td>
			<td><form action="removeCategory" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此类别"/>
				<input type="hidden" name="rmvCategoryId" value="${curr.id}"></input>
		 	</form></td></tr>
		</s:iterator>
	</table>
	<form action="addCategory">
		类别名称<input type="text" name="addCateName" />
		<input type="submit" value="添加商品类"/>
	</form>
	<br/>
	
	<!-- 商品总列表 -->
	商品列表
	<form action="maintainItems">
		<select name="category_id2">
			<option value=0>请选择商品类别</option>
		   	<s:iterator value="#request.categorylist" id="currOp">
		   		<option value="${currOp.id}">${currOp.name}</option>	
		   	</s:iterator>
		</select>	
		
		<input type="submit" value="查看"></input>
	</form>
	    
	<table>
		<tr><td>商品名称</td><td>商品类别</td><td>商品价格</td><td>商品进价</td></tr>
	</table>
	<table>
		<s:iterator value="#request.items" id="curr">
			<tr>
			<td><form action="alterItems" onsubmit="return if_alter();">
				<input type="text" name="alterItemsName" value="${curr.name}" />
				<select name="alterItemsCate">
					<option value="${curr.category.id}">${curr.category.name}</option>
		   			<s:iterator value="#request.categorylist" id="currOp">
		   				<option value="${currOp.id}">${currOp.name}</option>	
		   			</s:iterator>
		    	</select>
				<input type="text" name="alterItemsPrice" onkeypress="return isPrice(event)" value="${curr.price}" /> 
				<input type="text" name="alterPurchasePrice" onkeypress="return isPrice(event)" value="${curr.purchasePrice}" />
				<input type="hidden" name="alterItemsId" value="${curr.id}"></input>
				<input type="submit" value="修改"/></form></td>
				
			<td><form action="removeItems" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除此商品"/>
				<input type="hidden" name="rmvItemsId" value="${curr.id}"></input>
		 	</form></td></tr>
		 </s:iterator>
	</table>
	
	<form action="addItems">
		商品名称<input type="text" name="addItemsName" />
		商品类别
			<select name="addItemsCate">
	   			<s:iterator value="#request.categorylist" id="currOp">
	   				<option value="${currOp.id}">${currOp.name}</option>	
	   			</s:iterator>
	    	</select>
	        商品价格<input type="text" onkeypress="return isPrice(event)" name="addItemsPrice" />
	        商品进价<input type="text" onkeypress="return isPrice(event)" name="addPurchasePrice" />    
		<input type="submit" value="添加商品"/>
	</form>
	<br/>

	<!-- 各商店各类别商品列表 -->
	各店铺商品列表
	<form action="maintainItems">
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
	<tr><td>店铺</td><td>名称</td><td>类别</td><td>价格</td><td>数量</td><td>库存</td><td>折扣</td><td>赠品</td><td>赠品数</td>
	</tr>
	</table>
	<table>
	  	<s:iterator value="#request.itemlist" id="curr">
		 <tr><td>${curr.store}</td>
		 <td>${curr.name}</td> 
		 <td>${curr.category}</td>
		 <td>${curr.price}</td>
		 <td>${curr.number}</td>
		 <td>${curr.stock}</td>
			<td><form action="alterItemlist" onsubmit="return if_alter();">
			<input id="dis" type="text" name="alterListDis" value="${curr.discount}" />
			<select name="alterListGift">
				<option value=${curr.gift_id}>${curr.giftName}</option>
				<option value=0>取消赠品</option>
		   		<s:iterator value="#request.items" id="currOp">
		   			<option value="${currOp.id}">${currOp.name}</option>	
		   		</s:iterator>
	    	</select>
			<input type="text" maxlength="2" onkeypress="return isNum(event)" name="alterListGiftNum" value="${curr.giftNum}" />
			<input type="hidden" name="alterListId" value="${curr.id}"></input>
			<input type="submit" value="修改"/>
			</form></td>
		 <td><form action="removeItemlist" onsubmit="return if_rmv();">				 	
				<input type="submit" value="删除该店此商品"/>
				<input type="hidden" name="rmvItemlistId" value="${curr.id}"></input>
		 	 </form></td></tr>
	</s:iterator>
	</table>
	
	<form action="addItemlist">
	店铺
		<select name="addListStore">
	   		<s:iterator value="#request.storelist" id="currOp">
	   			<option value="${currOp.id}">${currOp.name}</option>	
	   		</s:iterator>
	   	</select>
	物品
		<select name="addListItem">
	   		<s:iterator value="#request.items" id="currOp">
	   			<option value="${currOp.id}">${currOp.name}</option>	
	   		</s:iterator>
	   	</select>
	   	<input type="submit" value="添加商品"/>
	</form>
	
	<s:actionmessage/>
	
	<br/>
</body>
</html>