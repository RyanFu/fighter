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
			$("#oldPassword").focusout(function(){
				if ($("#oldPassword").val()!="" && $("#opwd").val() != $("#oldPassword").val()) {
					$("#oldAlert").text("输入原密码错误！");
				}else if($("#oldPassword").val()==""){
					$("#oldAlert").text("请输入原密码！");
				} else {
					$("#oldAlert").text("");
				}
			});
			$("#newPassword").focusout(function(){
				var newPassword = $("#newPassword").val();
				var confirm = $("#confirm").val();
				if (newPassword == "") {
					$("#newLabel").text("请输入新密码！");
					$("#confLabel").text("");
				} else if(newPassword == confirm  ){
					$("#confLabel").text("");
					$("#newLabel").text("");
				} else {
					$("#newLabel").text("");
				}
			});
			$("#confirm").focusout(function(){
				if ($("#newPassword").val() != $("#confirm").val()) {
					$("#confLabel").text("两次输入密码不一致！");
				} else {
					$("#confLabel").text("");
				}
			});
			
			$("#submitzzz").click(function(){
					var oldPassword = $("#oldPassword").val();
					var newPassword = $("#newPassword").val();
					var confirm = $("#confirm").val();
					if ($("#oldPassword").val() == "") {
						$("#oldAlert").text("输入原密码错误！");
					}else if ($("#newPassword").val() == "") {
						$("#newLabel").text("请输入新密码！");
					}else if ($("#confirm").val() == "") {
						$("#confLabel").text("请输入确认密码！");
					} else if (confirm!=newPassword) {
						$("#confLabel").text("两次输入密码不一致！");
					}else{
						$("#memberForm").submit();
					}
			});
			
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
		    	<form  id="memberForm" action="<%=basePath%>ctrl/changePassword.mem" method="post">
		    	<input id="opwd" type="hidden" value="${member.password}"/>
			    <table id="ad" align="center" style="margin-top:40px">
			    	<tr>
			    		<td>*原密码：</td>
			    		<td><input type="password" name="oldPassword" id="oldPassword" ><label style="color:red" id="oldAlert"></label></td>
			    	</tr>
			    	
			    	<tr>
			    		<td>*新密码：</td>
			    		<td><input name="newPassword" type="password"  id="newPassword"  /><label style="color:red" id="newLabel"></label></td>
			    	</tr>
			    	<tr>
			    		<td>*确认密码：</td>
			    		<td><input name="confirm" type="password"  id="confirm"  /><label style="color:red" id="confLabel"></label></td>
			    	</tr>
			    	
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="submitzzz" >提交</button></td>
			    	</tr>
			    </table>
			    </form>  
		    </div>
		</div>
  	</div>
  </body>
</html>
