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
    <title>添加程序——基本信息设置</title>
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
			$("#app").builderFormTable({tdWidth:[200,300]});
			
			var apf = $("#AppPlatform").val();
			if (apf != "") {
				if (apf == '01') {
					$("#ap1").attr("checked",true);
				}
				if (apf == '02') {
					$("#ap2").attr("checked",true);
				}
			}
			
			var hideType = $("#AppType").val();
			if("" != hideType){
				if (hideType == "01") {
					$("#op1").attr("selected", "selected");
				} else if (hideType == "02") {
					$("#op2").attr("selected", "selected");
				} else if (hideType == "03") {
					$("#op3").attr("selected", "selected");
				} else if (hideType == "04") {
					$("#op4").attr("selected", "selected");
				} else if (hideType == "05") {
					$("#op5").attr("selected", "selected");
				} else if (hideType == "06") {
					$("#op6").attr("selected", "selected");
				} else if (hideType == "07") {
					$("#op7").attr("selected", "selected");
				} else if (hideType == "08") {
					$("#op8").attr("selected", "selected");
				} else if (hideType == "09") {
					$("#op9").attr("selected", "selected");
				} else if (hideType == "10") {
					$("#op10").attr("selected", "selected");
				} else if (hideType == "11") {
					$("#op11").attr("selected", "selected");
				} else if (hideType == "12") {
					$("#op12").attr("selected", "selected");
				} else if (hideType == "13") {
					$("#op13").attr("selected", "selected");
				} else if (hideType == "14") {
					$("#op14").attr("selected", "selected");
				} else if (hideType == "15") {
					$("#op15").attr("selected", "selected");
				}else if (hideType == "16") {
					$("#op16").attr("selected", "selected");
				}else if (hideType == "17") {
					$("#op17").attr("selected", "selected");
				}else  {
					$("#pc").attr("selected", "selected");
				}
			
			}
			$("#next").click(function(){
				var appId = $("#appId").val();
				if ($("#name").val() == "") {
					alert("请填写程序名称！");
				} else if ($("#type").val() == "pc") {
					alert("请选择程序类型！");
				} else if (!$("#ap1").attr("checked") && !$("#ap2").attr("checked")) {
					alert("请选择开发平台！");
				} else {
					if (appId != "") {
						$("#appForm").attr("action","<%=basePath%>ctrl/updateBasicAppInfo.app");
						$("#appForm").submit();
					}else {
						$("#appForm").attr("action","<%=basePath%>ctrl/saveAppBasicInfo.app");
						$("#appForm").submit();
					}
				}
			});
			
		});
	</script>
  </head>
  
  <body>
  	<div>
  		<jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>

		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=appManage"></jsp:include>
		    <div class="Border" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">添加程序——设置基本信息</h2>
		    	<form id="appForm" action="" method="post">
		    	<!-- 隐藏域 -->
		    	<input id="appId" name="Id" type="hidden" value="${Id}">
		    	<input id="AppType" name="AppType" type="hidden" value="${AppType}">
		    	<input id="AppPlatform" name="AppPlatform" type="hidden" value="${AppPlatform}">
			    <table id="app" align="center" style="margin-top:20px">
			    	<tr>
			    		<td>程序名称</td>
			    		<td>
			    			<input type="text" id="name" name="name" value="${name}">
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>程序类别</td>
			    		<td>
			    			<select name="type" id="type">
			    				<option id="pc"  value="pc">------------请选择------------</option>
	    						<option id="op1" value="01">多媒体软件</option>
							    <option id="op2" value="02">主题桌面</option>
							    <option id="op3" value="03">电子阅读</option>
							    <option id="op4" value="04">实用工具</option>
							    <option id="op5" value="05">网络通讯</option>
							    <option id="op6" value="06">安全软件</option>
							    <option id="op7" value="07">系统软件</option>
							    <option id="op8" value="08">生活信息</option>
							    <option id="op9" value="09">娱乐休闲</option>
							    <option id="op10" value="10">新闻资讯</option>
							    <option id="op11" value="11">其他软件</option>
							    <option id="op12" value="12">动作格斗</option>
							    <option id="op13" value="13">模拟经营</option>
							    <option id="op14" value="14">休闲益智</option>
							    <option id="op15" value="15">体育竞技</option>
							    <option id="op16" value="16">角色扮演</option>
							    <option id="op17" value="17">其他游戏</option>
			    			</select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>应用平台</td>
			    		<td><input type="radio" id="ap1" name="appPlatform" checked="checked" value="01">Android平台
			    			<input type="radio" id="ap2" name="appPlatform" value="02">Ophone平台</td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="next">下一步</button></td>
			    	</tr>
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
