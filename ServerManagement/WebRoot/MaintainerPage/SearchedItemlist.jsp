<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<link rel="stylesheet" media="screen" type="text/css"
	href="css/MaintainItemsPage.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	
<div class="maintainItems" style="height:330px;overflow:auto;">	
<table>
	<tr>
		<th>店铺</th>
		<th>名称</th>
		<th>类别</th>
		<th>价格</th>
		<th>数量</th>
		<th>库存</th>
		<th>折扣</th>
		<th>赠品</th>
		<th>赠品数</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<s:iterator value="#request.itemlist" id="curr">
		<tr>
			<td>${curr.store}</td>
			<td>${curr.name}</td>
			<td>${curr.category}</td>
			<td>${curr.price}</td>
			<td>${curr.number}</td>
			<td>${curr.stock}</td>
			<form action="alterItemlist" onsubmit="return if_alter();">
			<td>
					<input style="width:40px;" id="dis" type="text" name="alterListDis"
						value="${curr.discount}" /> 
			</td>
				<td>
				<select name="alterListGift">
					<option value=${curr.gift_id}>${curr.giftName}</option>
					<option value=0>取消赠品</option>
					<s:iterator value="#request.items" id="currOp">
						<option value="${currOp.id}">${currOp.name}</option>
					</s:iterator>
				</select>
				</td>
				<td>
				 <input type="text" maxlength="2" style="width:40px;"
					onkeypress="return isNum(event)" name="alterListGiftNum"
					value="${curr.giftNum}" /> <input type="hidden"
					name="alterListId" value="${curr.id}"></input> 
				</td>	
				
				<td>	
					<input type="submit" value="修改" />
					</td>
				</form>
			
			<form action="removeItemlist" onsubmit="return if_rmv();">
				<td>
					<input type="submit" value="删除" /> <input type="hidden"
						name="rmvItemlistId" value="${curr.id}"></input>
				</td>
			</form>
			
		</tr>
	</s:iterator>
</table>
<s:actionerror/>
<s:actionmessage/>
</div>
</fieldset>

</body>
</html>