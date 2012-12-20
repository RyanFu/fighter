<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt" %>
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
    <title>程序管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/page/pagination.css"/>
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/page/jquery.pagination.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,300]});
			
			var appType=$("#appType").val();
			$("#appTypeLabel").text(getAppType(appType));
			
			var appPlatForm=$("#appPlatForm").val();
			$("#appPlatFormLabel").text(getAppPlatForm(appPlatForm));
			
			$("#goBack").click(function(){
				window.location.href="<%=basePath%>ctrl/showApps.app";
			});
		});

        function getAppType(typeCode) {
			switch (typeCode) {
				case '01': return "多媒体软件";
				case '02': return "主题桌面";
				case '03': return "电子阅读";
				case '04': return "实用工具";
				case '05': return "网络通讯";
				case '06': return "安全软件";
				case '07': return "系统软件";
				case '08': return "生活信息";
				case '09': return "娱乐休闲";
				case '10': return "新闻资讯";
				case '11': return "其他软件";
				case '12': return "动作格斗";
				case '13': return "模拟经营";
				case '14': return "休闲益智";
				case '15': return "体育竞技";
				case '16': return "角色扮演";
				case '17': return "其他游戏";
			}
		}
		
		function getAppPlatForm(formCode) {
			switch (formCode) {
				case '01': return "Android平台";
				case '02': return "Ophone平台";
			}
		}
	</script>
  </head>
  
  <body>
  	<div>
  		<jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>

		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=appManage"></jsp:include>
		    <div class="Border"  style="font-family:Arial, Helvetica, sans-serif;font-size:12px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">查看程序基本信息</h2>
		    	<div style="padding-left:20px;padding-bottom:10px">
		    		<!-- 隐藏域 -->
		    		<input type="hidden" id="appPlatForm" value="${appPlatform}">
		    		<input type="hidden" id="appType" value="${appType}">
		    	</div>
		    	<table id="ad" align="center" style="margin-top:40px">
			    	<tr>
			    		<td >AppID：</td>
			    		<td><label style="color:black">${pid}</label>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>程序名称：</td>
			    		<td><label style="color:black" >${name}</label></td>
			    	</tr>
			    	<tr>
			    		<td>程序类别：</td>
			    		<td><label style="color:black" id="appTypeLabel" ></label></td>
			    	</tr>
			    	<tr>
			    		<td>应用平台：</td>
			    		<td><label style="color:black" id="appPlatFormLabel" ></label></td>
			    	</tr>
			    	<tr>
			    		<td>创建时间：</td>
			    		<td><label style="color:black">${creatTime}</label></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button type="button" id="goBack">返回</button></td>
			    	</tr>
			    </table>
		    	<div style="padding:20px;float:right">
		    		<div id="Pagination"></div>
		    	</div>
		    </div>
		</div>
  	</div>
  </body>
</html>
