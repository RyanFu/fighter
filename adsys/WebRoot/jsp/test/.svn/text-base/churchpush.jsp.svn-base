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
    <title>Church Push</title>
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
			
			$("#loginSubmit").click(function(){
				var loginEmailSu = $("#msg").val();
				var loginPw = $("#count").val();
				if (loginEmailSu == "") {
					alert("请输入通知内容！");
				} else if (loginPw == "") {
					alert("请输入新书数量！");
				}else{
					$("#loginForm").submit();
				}
			});
		});
		function enterInfo(evt){
  			/*var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
 			if (evt.keyCode==13){
  				var loginEmailSu = $("#loginEmailSu").val();
				var loginPw = $("#loginPw").val();
				if (loginEmailSu == "") {
					$("#userLogin").text("请输入用户名！");
				} else if ($("#loginPw").val() == "") {
				$("#userPass").text("请输入密码！");
				}else{
				$("#loginForm").submit();
				}
			}*/
		}

	</script>
  </head>
  
  <body>
  	<div>
		<div id="content" style="padding-left:150px;margin-top:50px">
			
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;padding-bottom: 20px;">
		    	<div style="margin-bottom: 20px;">
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form id="loginForm" action="<%=basePath%>ctrl/testChurchPushNotice.test" method="post">
		    	
			    <table id="ad" align="center" style="margin-top:20px">
			    <tr><td></td><td><label style="color:black" id="loginFailed" >${loginFailed}</label></td></tr>
			    	<tr>
			    		<td>通知内容：</td>
			    		<td><input name="msg" type="text" size="21" id="msg"></td>
			    	</tr>
			    	<tr>
			    		<td>新书数量：</td>
			    		<td><input name="count" type="text" size="22" id="count"></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="loginSubmit" type="button">Push</button></td>
			    	</tr>
			    	
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
