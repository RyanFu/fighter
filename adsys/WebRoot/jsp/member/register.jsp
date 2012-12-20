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
			$("#ad").builderFormTable({tdWidth:[200,300]});
			$("#email_id").focusout(function(){
				 var email = $("#email_id").val();
				 if(email==""){
				 	$("#t_email").text("请输入电子邮箱");
				 }else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-zA-Z0-9_-])+/.test(email))){
                 	$("#t_email").text("请输入正确的邮箱");
                 }else {
                 	$.ajax({
						type : 'POST',
						url : "<%=basePath%>ctrl/testMemberExist.mem",
						dataType: "text",
						data : {email:email},
						success : function(data){
							var data = eval('('+data+')');
							if (data == 'true') {
								$("#t_email").text("邮箱已注册");
							}else {
								$("#t_email").text("");
							}
						}
					});
                 	
                 }
				
			});
			$("#registerPw").focusout(function(){
				var registerPw=$("#registerPw").val();
				var registerCf=$("#registerCf").val();
				if(registerPw==""){
					$("#t_password").text("密码不能为空");
				}else if(registerPw.length<6){
					$("#t_password").text("密码需大于六位");
				}else if(registerCf != "" && registerPw!=registerCf){
					$("#t_password").text("");
					$("#t_confirm").text("两次密码不一致");
				}else {
					$("#t_password").text("");
				}
			});
			$("#registerCf").focusout(function(){
				var registerPw=$("#registerPw").val();
				var registerCf=$("#registerCf").val();
				if(registerCf== "" ){
					$("#t_confirm").text("请输入确认密码");
				}else if(registerCf!=registerPw){
					$("#t_confirm").text("两次密码不一致");
				}else {
					$("#t_confirm").text("");
				}
			});
			$("#registerName").focusout(function(){
			   var registerName=$("#registerName").val();
			   if(registerName==""){
			   		$("#t_name").text("请输入姓名");
			   }else {
			   		$("#t_name").text("");
			   }				
			});
			$("#registerCellp").focusout(function(){
				var registerCellp=$("#registerCellp").val();
				if(registerCellp==""){
					$("#t_cellphone").text("请输入移动电话");
				}else if(registerCellp.length!=11){
					$("#t_cellphone").text("请输入正确的移动电话");
				}else {
					$("#t_cellphone").text("");
				}
			});
			$("#registerSubmit").click(function(){
				var email = $("#email_id").val();
				var registerPw=$("#registerPw").val();
				var registerCf=$("#registerCf").val();
				var registerName=$("#registerName").val();
				var registerCellp=$("#registerCellp").val();
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/testMemberExist.mem",
					dataType: "text",
					data : {email:email},
					success : function(data){
						var data = eval('('+data+')');
						if(email==""){
							$("#t_email").text("请输入电子邮箱");
						}else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-zA-Z0-9_-])+/.test(email))){
                            $("#t_email").text("请输入正确的邮箱");
                        }else if (data == 'true') {
							$("#t_email").text("邮箱已注册");
						} else {
							if(registerPw==""){
				            	$("#t_password").text("密码不能为空");
				            }else if(registerPw.length<6){
				            	$("#t_password").text("密码需大于六位");
				            }else if(registerCf != "" && registerPw!=registerCf){
				            	$("#t_confirm").text("两次密码不一致");
				            }else if(registerCf== "" ){
				            	$("#t_confirm").text("请输入确认密码");
				            }else if(registerCf!=registerPw){
				            	$("#t_confirm").text("两次密码不一致");
				            }else if(registerName==""){
			                	$("#t_name").text("请输入姓名");
			                }else if(registerCellp==""){
			                	$("#t_cellphone").text("请输入移动电话");
			                }else if(registerCellp.length!=11){
				            	$("#t_cellphone").text("请输入正确的移动电话");
				            }else if($("#registerCheck1").attr("checked")==false && $("#registerCheck2").attr("checked")==false){
				            	$("#t_accountType").text("请选择身份");
				            }else {
				            	$("#registerForm").submit();
				            }
						}
					}
				});
				
				
				
				
			});
			
		});
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
  	     
		<div id="content">
			
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;width:900px;">
		    	<div style="margin-bottom: 20px">
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 882px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form action="<%=basePath%>ctrl/memberInfoSave.mem" method="post" id="registerForm">
			    <table id="ad" align="center" style="margin-top:20px">
			    	<tr>
			    		<td>电子邮箱：</td>
			    		<td><input type="text" name="email" id="email_id"><label style="color:red" id="t_email"></label></td>
			    	</tr>
			    	<tr>
			    		<td>密码：</td>
			    		<td><input type="password" name="password" id="registerPw"><label style="color:red" id="t_password"></label></td>
			    	</tr>
			    	<tr>
			    		<td>确认密码：</td>
			    		<td><input name="confirm" type="password" id="registerCf"><label style="color:red" id="t_confirm"></label></td>
			    	</tr>
			    	<tr>
			    		<td>姓名：</td>
			    		<td><input name="name" type="text" id="registerName"><label style="color:red" id="t_name"></label></td>
			    	</tr>
			    	<tr>
			    		<td>移动电话：</td>
			    		<td><input name="cellphone" type="text" id="registerCellp"><label style="color:red" id="t_cellphone"></label></td>
			    	</tr>
			    	<tr>
			    		<td>公司名称：</td>
			    		<td><input name="com_name" type="text" id="registerCom_n"></td>
			    	</tr>
			    	<tr>
			    		<td>账户类型:</td>
			    		<td><input type="radio" name="memAccountType" value="1" id="registerCheck1">广告商
			    			<input type="radio" name="memAccountType" value="2" id="registerCheck2">程序开发者
			    			<label style="color:red" id="t_accountType"></label></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button type="button" id="registerSubmit">提交</button></td>
			    	</tr>
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
