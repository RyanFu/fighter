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
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,300]});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			
		});
		
		
	</script>
  </head>
  
  <body>
  	<div>
		<jsp:include page="../../jsp/common/head.jsp?id=resolvent"></jsp:include>
  		<!-- <div id="head_center">
  			<div id="logo">
  			<a href="<%=basePath%>"><img src="<%=basePath%>/styles/images/logo.png" /></a>
			</div>
  		</div>
  		<div id="navigation">
			<div class="left"></div>
			<ul>
				<li>
					<a href="<%=basePath%>index.jsp">&nbsp;首&nbsp;页&nbsp;</a>
				</li>
				<li class="selected">
					<a href="<%=basePath%>jsp/webshow/resolvent.jsp">解决方案</a>
				</li>
				<li >
					<a href="<%=basePath%>jsp/webshow/market.jsp">市场分析</a>
				</li>
				<li >
					<a href="<%=basePath%>jsp/webshow/news.jsp">新闻中心</a>
				</li>
				<li>
					<a href="<%=basePath%>jsp/webshow/download.jsp">下载中心</a>
				</li>
				<li class="last">
					<a href="<%=basePath%>ctrl/info.mem">会员中心</a>
				</li>
			</ul>
			<div class="right"></div>
		</div>
		<div id="loginInfo">
			<div align="right" style="padding-top:5px;padding-right:50px">
				<a id="loginHref1" href="<%=basePath%>jsp/member/login.jsp">登录</a>
				<a id="loginHref2" href="<%=basePath%>ctrl/info.mem">欢迎您${member.name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a id="loginHref3" href="<%=basePath%>ctrl/logout.mem">安全退出</a>
				<a id="loginHref4" href="<%=basePath%>jsp/member/register.jsp">注册</a>
			</div>
		</div> -->
		<div id="content">
			
		    <div style="float:left;margin-top:20px;margin-bottom:20px;margin-left:20px;width:900px;height:550px;background:white;border-style:solid;border-width:1px;border-color:gray">
		    	<div style="padding:20px">
		    		<div><img style="width:850px;height:340px" src="<%=basePath%>images/resolve_1.png"/></div>
		    		<div><img style="width:850px;height:146px" src="<%=basePath%>images/resolve_2.png"/></div>
		    	</div>
		    </div>
		    <div align="center">
		    	<span><img src="<%=basePath%>images/resolve_3.png"/></span>
		    	<span style="margin-left:100px;"><img src="<%=basePath%>images/resolve_4.png"/></span>
		    </div>
		</div>
  	</div>
  </body>
</html>
