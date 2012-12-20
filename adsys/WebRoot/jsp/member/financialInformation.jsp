<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>安捷乐广告平台</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,400]});
			var id=$("#id").val();
			$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/isTestBankInfo.mem",
					dataType: "text",
					data : {id:id},
					success : function(data){
						var data = eval('('+data+')');
						if (data == 'true') {
							$("#accountAttrCheck1").attr("disabled",true);
							$("#accountAttrCheck2").attr("disabled",true);
							$("#invoice_ableCheck").attr("disabled",true);
							$("#getMoneyCheck1").attr("disabled",true);
							$("#getMoneyCheck2").attr("disabled",true);
							$("#finanOpenB").attr("disabled",true);
							$("#finanAccountN").attr("disabled",true);
							$("#finanOpenName").attr("disabled",true);
							$("#finanAddressB").attr("disabled",true);
							$("#finanZipcodeB").attr("disabled",true);
							$("#finanSubmit").hide();  
							
						} 
					}
				});
			if("${member.account_attr}"=="10" || "${member.account_attr}"==""){
			    $("#accountAttrCheck1").attr("checked",true);
			}else if("${member.account_attr}"=="01"){
			    $("#accountAttrCheck2").attr("checked",true);
			}
			
			if("${member.get_money_mode}"=="10" || "${member.get_money_mode}"==""){
			    $("#getMoneyCheck1").attr("checked",true);
			}else if("${member.get_money_mode}"=="01"){
			    $("#getMoneyCheck2").attr("checked",true);
			}
			
			if("${member.invoice_able}"=="1" ){
			     $("#invoice_ableCheck").attr("checked",true);
			}else if("${member.invoice_able}"=="0"||"${member.invoice_able}"==""){
			     $("#invoice_ableCheck").attr("checked",false);
			}
			
			$("#finanOpenB").focusout(function(){
				var finanOpenB=$("#finanOpenB").val();
				if(finanOpenB==""){
				alert('请输入开户银行！');
				}
			});
			$("#finanAccountN").focusout(function(){
				var finanAccountN=$("#finanAccountN").val();
				if(finanAccountN==""){
				alert('请输入账号！');
				}
			});
			$("#finanOpenName").focusout(function(){
			    var finanOpenName=$("#finanOpenName").val();
			    if(finanOpenName==""){
			    alert('请输入开户名称！');
			    }
				
			});
			$("#finanAddressB").focusout(function(){
			    var finanAddressB=$("#finanAddressB").val();
			    if(finanAddressB==""){
			    alert('请输入邮寄地址！');
			    }
				
			});
			$("#finanZipcodeB").focusout(function(){
			    var finanZipcodeB=$("#finanZipcodeB").val();
			    if(finanZipcodeB==""){
			    alert('请输入邮编！');
			    }else if(finanZipcodeB.length!=6){
			    alert('请输入正确的邮编！');
			    }
			});
			$("#finanSubmit").click(function(){
			var finanOpenB=$("#finanOpenB").val();
			var finanAccountN=$("#finanAccountN").val();
		    var finanOpenName=$("#finanOpenName").val();
		    var finanAddressB=$("#finanAddressB").val();
		    var finanZipcodeB=$("#finanZipcodeB").val();
		    if(finanOpenB==""){
				alert('请输入开户银行！');
			}else if(finanAccountN==""){
				alert('请输入账号！');
			}else if(finanOpenName==""){
			    alert('请输入开户名称！');
			}else if(finanAddressB==""){
			    alert('请输入邮寄地址！');
			}else if(finanZipcodeB==""){
			    alert('请输入邮编！');
			}else if(finanZipcodeB.length!=6){
			    alert('请输入正确的邮编！');
			}else {
			    $("#finanForm").submit();
			}});
			
		});
		
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=baseInfo"></jsp:include>
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px">
		    	<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<input type="hidden" id="id" value="${member.id}">
		    	<form action="<%=basePath%>ctrl/updateMemberFinancialInfo.mem" method="post" id="finanForm">
			    <table id="ad" align="center" style="margin-top:40px" id="finanForm">
			    	<tr>
			    		<td>注册账号：</td>
			    		<td><label style="color:black" >${member.email}</label></td>
			    	</tr>
			    	
			    	<tr>
			    		<td>账户属性：</td>
			    		<td><input type="radio" name="account_attr" value="1" id="accountAttrCheck1">企业账户
			    			<input type="radio" name="account_attr" value="2" id="accountAttrCheck2">个人账户</td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><input type="checkbox" name="invoice_able" value="1" id="invoice_ableCheck">我可以提供发票（发票内容为服务费，如不能提供发票，将扣除6%的税点！）</td>
			    	</tr>
			    	<tr>
			    		<td>月末结算方式：</td>
			    		<td><input type="radio" name="get_money_mode" value="1" id="getMoneyCheck1">月底自动把本月的收入存入我的指定帐户<br>
			    			<input type="radio" name="get_money_mode" value="2" id="getMoneyCheck2">每个月我主动提出提现申请时再给我结算，否则累计到下次结算</td>
			    	</tr>
			    	<tr>
			    		<td>开户银行：</td>
			    		<td><input type="text" name="open_bank" id="finanOpenB" value="${member.open_bank}"></td>
			    	</tr>
			    	<tr>
			    		<td>账号：</td>
			    		<td><input type="text" name="account_number" id="finanAccountN" value="${member.account_number}"></td>
			    	</tr>
			    	<tr>
			    		<td>开户名称：</td>
			    		<td><input type="text" name="open_name" id="finanOpenName" value="${member.open_name}"></td>
			    	</tr>
			    	<tr>
			    		<td>邮寄地址：</td>
			    		<td><input type="text" name="address_bank" id="finanAddressB" value="${member.address_bank}"></td>
			    	</tr>
			    	<tr>
			    		<td>邮编：</td>
			    		<td><input type="text" name="zipcode_bank" id="finanZipcodeB" value="${member.zipcode_bank}"></td>
			    	</tr>
			    </table>
			         <tr>
			    		<td>&nbsp;</td>
			    		<td><button type="button" id="finanSubmit" style="margin-left:295px;margin-top:20px;margin-bottom:20px">保存</button></td>
			    	</tr>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
