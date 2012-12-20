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
			$("#loginSubmit").click(function(){
				var loginEmailSu = $("#loginEmailSu").val();
				var loginPw = $("#loginPw").val();
				if (loginEmailSu == "") {
					$("#userLogin").text("请输入用户名！");
				} else if ($("#loginPw").val() == "") {
				$("#userPass").text("请输入密码！");
				}else{
				$("#loginForm").submit();
				}
			});
			$("#loginEmailSu").focusout(function(){
			var loginEmailSu = $("#loginEmailSu").val();
			    if (loginEmailSu == "") {
					$("#userLogin").text("请输入用户名！");
				}else {
				    $("#userLogin").text("");
				    $("#loginFailed").text("");
				
				}
				
			});
			$("#loginPw").focusout(function(){
			var loginPw = $("#loginPw").val();
				if (loginPw == "") {
					$("#userPass").text("请输入密码！");
				}else {
				    $("#userPass").text("");
				
				}
			});
			$("#authCodeImg").click(function(){
				$("#authCodeImg").attr("src","<%=basePath%>ctrl/authCode.mem?id="+new Date());
			});
			$("#changeCode").click(function(){
				$("#authCodeImg").attr("src","<%=basePath%>ctrl/authCode.mem?id="+new Date());
			});
			
			
			
		});
		function enterInfo(evt){
  			var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
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
			}
		}

	</script>
  </head>
  
  <body>
  	<div>
  	    <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div>
			<div align="right" style="padding-top:5px;padding-right:50px">
				
			</div>
		</div>
		<div id="content" style="padding-left:150px;">
			
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;padding-bottom: 20px;">
		    	<div style="margin-bottom: 20px;">
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form id="loginForm" action="<%=basePath%>ctrl/login.mem" method="post">
		    	
			    <table id="ad" align="center" style="margin-top:20px">
			    <tr><td></td><td><label style="color:black" id="loginFailed" >${loginFailed}</label></td></tr>
			    	<tr>
			    		<td>用户名：</td>
			    		<td><input name="loginEmail" type="text" size="21" id="loginEmailSu"><label style="color:red" id="userLogin"></label></td>
			    	</tr>
			    	<tr>
			    		<td>密码：</td>
			    		<td><input name="loginPassword" type="password" size="22" id="loginPw"><label style="color:red" id="userPass"></label></td>
			    	</tr>
			    	<tr>
			    		<td>验证码：</td>
			    		<td><input name="authCode" size="6" id="authCode" onkeydown="enterInfo(event);"><img id="authCodeImg" style="margin-left:10px;margin-bottom:-5px" src="<%=basePath%>ctrl/authCode.mem"/>
			    		<label style="color:red" id="authCodeLabel">${loginAuthCode}</label>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="loginSubmit" type="button">登录</button><label id="changeCode" style="color:red;margin-left:20px;cursor:hand;" >点击换验证码</label></td>
			    	</tr>
			    	
			    </table>
			    <div style="margin-top:20px;">
			    <a href="<%=basePath%>jsp/member/register.jsp" style="margin-left:390px;">没有注册？去注册！</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=basePath%>jsp/member/reInitPassword.jsp">忘记密码？</a>
			    </div>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
