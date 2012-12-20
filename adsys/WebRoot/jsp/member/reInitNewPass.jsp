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
				var reinitNewPw=$("#reinitNewPw").val();
			    var reinitConfirm=$("#reinitConfirm").val();
			    if(reinitNewPw==""){
			     $("#reinitLabel1").text("请输入新密码！");
			    }else if(reinitConfirm != "" && reinitConfirm!=reinitNewPw){
			     $("#reinitLabel2").text("两次密码不一致!");
			    }if(reinitConfirm==""){
			     $("#reinitLabel2").text("请再次输入密码！");
			    }else if(reinitConfirm!=reinitNewPw){
			     $("#reinitLabel2").text("两次密码不一致！");
			    }else {
			     $("#reinitForm").submit();
			    }
				  
				
				
                 
			});
			$("#reinitNewPw").focusout(function(){
			var reinitNewPw=$("#reinitNewPw").val();
			var reinitConfirm=$("#reinitConfirm").val();
			if(reinitNewPw==""){
			$("#reinitLabel1").text("请输入新密码！");
			}else if(reinitConfirm != "" && reinitConfirm!=reinitNewPw){
			$("#reinitLabel2").text("两次密码不一致");
			}else {
			$("#reinitLabel1").text("");
			$("#reinitLabel2").text("");
			}
			
			});
			$("#reinitConfirm").focusout(function(){
			var reinitConfirm=$("#reinitConfirm").val();
			var reinitNewPw=$("#reinitNewPw").val();
			if(reinitConfirm==""){
			$("#reinitLabel2").text("请再次输入密码！");
			}else if(reinitConfirm!=reinitNewPw){
			$("#reinitLabel2").text("两次密码不一致！");
			}else {
			$("#reinitLabel2").text("");
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
		    	<form id="reinitForm" action="<%=basePath%>ctrl/reInitPassword.mem" method="post">
		    	<input id="idInit" name="idInit1" type="hidden" value="<%=request.getParameter("id")%>"/>
		    	<input id="setNumInit" name="setNumInit1" type="hidden" value="<%=request.getParameter("ser_num")%>"/>
			    <table id="ad" align="center" style="margin-top:20px">
			    <tr><td></td><td><label style="color:red" id="loginFailed" >${loginFailed}</label></td></tr>
			    	<tr>
			    		<td>请输入新密码：</td>
			    		<td><input name="reinitNewPw" type="password" id="reinitNewPw"><label style="color:red" id="reinitLabel1"></label></td>
			    	</tr>
			    	<tr>
			    		<td>再次输入密码：</td>
			    		<td><input name="reinitConfirm" type="password" id="reinitConfirm"><label style="color:red" id="reinitLabel2"></label></td>
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
