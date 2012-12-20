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
			$("#reinitSubmit").click(function(){
				var reinitEmailSu = $("#reinitEmailSu").val();
				if (reinitEmailSu == "") {
					$("#userLogin").text("请输入电子邮箱！");
				} else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(reinitEmailSu))){
                   	$("#userLogin").text("邮箱格式错误！");
                }else {
				   $("#reinitForm").submit();
				}
				
                 
			});
			$("#reinitEmailSu").focusout(function(){
			var reinitEmailSu = $("#reinitEmailSu").val();
			    if (reinitEmailSu == "") {
					$("#userLogin").text("请输入电子邮箱！");
				}else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(reinitEmailSu))){
                   $("#userLogin").text("邮箱格式错误！");
                }else {
				    $("#userLogin").text("");
				}
				
			});
			
		});
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div id="content">
			
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;width:700px;">
		    	<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form id="reinitForm" action="<%=basePath%>ctrl/forgetPasswordAndSendEmail.mem" method="post">
		    	
			    <table id="ad" align="center" style="margin-top:20px">
			    <tr><td></td><td><label style="color:red" id="loginFailed" >${loginFailed}</label></td></tr>
			    	<tr>
			    		<td>电子邮件地址：</td>
			    		<td><input name="reinitEmail" type="text" id="reinitEmailSu"><label style="color:red" id="userLogin">${inexistence}</label></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="reinitSubmit" type="button">提交</button></td>
			    	</tr>
			    </table>
			        
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
