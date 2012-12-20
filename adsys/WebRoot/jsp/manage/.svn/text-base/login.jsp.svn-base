<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("admanage")) {
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
				$("#authCodeImg").attr("src","<%=basePath%>ctrl/authCode.man?id="+new Date());
			});
			$("#changeCode").click(function(){
				$("#authCodeImg").attr("src","<%=basePath%>ctrl/authCode.man?id="+new Date());
			});
		});
	</script>
  </head>
  
  <body>
  	<div id="head">
  		<div id="head_center">
  			<div id="logo">
  			<a href="<%=basePath%>"><img src="<%=basePath%>/styles/images/logo.png" /></a>
			</div>
  		</div>
  		<div id="navigation">
			<div class="left"></div>
			<ul>
				<li class="selected">
					<a href="<%=basePath%>index.jsp">&nbsp;首&nbsp;页&nbsp;</a>
				</li>
				<li >
					<a href="#">解决方案</a>
				</li>
				<li >
					<a href="#">市场分析</a>
				</li>
				<li >
					<a href="#">新闻中心</a>
				</li>
				<li>
					<a href="#">下载中心</a>
				</li>
				<li >
					<a href="#">会员中心</a>
				</li>
			</ul>
			<div class="right"></div>
		</div>
		<div id="loginInfo">
			<div align="right" style="padding-top:5px;padding-right:50px">
				<a href="<%=basePath%>jsp/manage/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">注册</a>
			</div>
		</div>
		<div>
			<div align="right" style="padding-top:5px;padding-right:50px">
				
			</div>
		</div>
		<div id="content">
			
		    <div style="float:left;margin-top:60px;margin-left:20px;width:700px;background:white;border-style:solid;border-width:1px;border-color:gray;font-family:Arial, Helvetica, sans-serif;font-size:12px">
		    	<form id="loginForm" action="<%=basePath%>ctrl/login.man" method="post">
		    	
			    <table id="ad" align="center" style="margin-top:20px">
			    <tr><td></td><td><label style="color:red" id="loginFailed" >${loginFailed}</label></td></tr>
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
			    		<td><input name="authCode" size="6" id="authCode"><img id="authCodeImg" style="margin-left:10px;margin-bottom:-5px" src="<%=basePath%>ctrl/authCode.man"/>
			    		<label style="color:red" id="authCodeLabel">${loginAuthCode}</label>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="loginSubmit" type="button">登录</button><label id="changeCode" style="color:red;margin-left:38px;cursor:hand;" >点击换验证码</label></td>
			    	</tr>
			    	
			    </table>
			        
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
