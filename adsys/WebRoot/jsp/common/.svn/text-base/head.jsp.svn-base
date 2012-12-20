<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
	String id = request.getParameter("id");
%>
<div style="margin: 0 auto;width:972px;white-space:nowrap;">
	<a href="<%=basePath%>index.jsp"><img src="<%=basePath%>img/ad_02.jpg" style="margin:0;float:left"/></a>
	<div id="loginImg" style="background:url(<%=basePath%>img/ad_03.jpg);width:561px;height:90px;float:left;">
		<div style="margin-top:66px;margin-bottom:5px;">
		<input type="text" id="username" style="margin-left:230px;width:130px;height:12px;border:0px">
		<input type="password" id="password" onkeydown="enterIn(event)" style="margin-left:55px;width:130px;height:12px;border:0px">
		</div>
	</div>
	<span id="loginBtn"><a href="javascript:void(0)"><img id="login" src="<%=basePath%>img/ad_04.jpg"/></a></span>
	<div id="loginInfo" style="padding-bottom:0px;width:633px;height:90px;float:left;display:none">
		<div style="padding-top:70px">
			<table border="0">
			<tr>
				<td width="580" align="right"><a style="" href="<%=basePath%>ctrl/info.mem">欢迎您${member.name}</a>&nbsp;&nbsp;|</td>
				<td width="80"><a href="<%=basePath%>ctrl/logout.mem">安全退出</a></td>
			</tr>
			</table>
		</div>
	</div>
</div>
<div id="newNavigation" style="margin: 0 auto;width:980px;white-space:nowrap;">
	<a href="<%=basePath%>index.jsp"><img id="index" src="<%=basePath%>img/ad_06.jpg" style="margin:0;float:left"/></a>
	<a href="<%=basePath%>jsp/webshow/resolvent.jsp"><img id="resolvent" src="<%=basePath%>img/ad_07.jpg" style="margin:0;float:left"/></a>
	<a href="<%=basePath%>jsp/webshow/market.jsp"><img id="market" src="<%=basePath%>img/ad_08.jpg" style="margin:0;float:left"/></a>
	<a href="<%=basePath%>jsp/webshow/news.jsp"><img id="news" src="<%=basePath%>img/ad_09.jpg" style="margin:0;float:left"/></a>
	<a href="<%=basePath%>jsp/webshow/download.jsp"><img id="download" src="<%=basePath%>img/ad_10.jpg" style="margin:0;float:left"/></a>
	<a href="<%=basePath%>ctrl/info.mem"><img id="member" src="<%=basePath%>img/ad_11.jpg" style="margin:0;float:left"/></a>
</div>

<script>
	$(function(){
		$("#loginBtn").click(function(){
			login();
		});
	});
	var id = "<%=id%>";
	if("${member.id}"==""){
		$("#loginImg").show();
		$("#loginBtn").show();
		$("#loginInfo").hide();
	}else if("${member.id}"!=""){
		$("#loginImg").hide();
		$("#loginBtn").hide();
		$("#loginInfo").show();
	}
	if (id == "index") {
		$("#"+id).attr("src","<%=basePath%>img/ad_061.jpg");
	} else if (id == "resolvent") {
		$("#"+id).attr("src","<%=basePath%>img/ad_071.jpg");
	} else if (id == "market") {
		$("#"+id).attr("src","<%=basePath%>img/ad_081.jpg");
	} else if (id == "news") {
		$("#"+id).attr("src","<%=basePath%>img/ad_091.jpg");
	} else if (id == "download") {
		$("#"+id).attr("src","<%=basePath%>img/ad_101.jpg");
	} else if (id == "member") {
		$("#"+id).attr("src","<%=basePath%>img/ad_111.jpg");
	}
	
	
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			alert("请输入用户名(注册邮箱)！");
			return;
		}
		if (password == "") {
			alert("请输入密码！");
			return;
		}
		$.ajax({
			type : 'POST',
			url : "<%=basePath%>ctrl/loginWithoutCode.mem",
			data : {loginEmail:username,loginPassword:password},
			success : function(data){
				data = data.substring(1,data.length-1);
				if (data == "ok") {
					window.location="<%=basePath%>ctrl/info.mem";
					return;
				} else {
					alert(data);
				}
			}
		});
	}
	function enterIn(evt){
  			var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
 			if (evt.keyCode==13){
  				login();
			}
		}
</script>

