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
    <title>广告发布——基本信息设置</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/date/calendar.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#maxPayPerDay").hide();
			$("#ad").builderFormTable({tdWidth:[200,300]});
			
			var apf = $("#apf").val();
			if (apf != "") {
				if (apf.substring(0,1) == '1') {
					$("#ap1").attr("checked",true);
				}
				if (apf.substring(1,2) == '1') {
					$("#ap2").attr("checked",true);
				}
			}
			
			var mpd = $("#maxPerDay").val();
			if (mpd != "" && mpd != 0) {
				$("#maxCheck").attr("checked",true);
				$("#maxPayPerDay").val(mpd);
				$("#maxPayPerDay").show();
			}
			
			var hideType = $("#hideType").val();
			if (hideType == "生活资讯") {
				$("#op1").attr("selected", "selected");
			} else if (hideType == "影音娱乐") {
				$("#op2").attr("selected", "selected");
			} else if (hideType == "教育培训") {
				$("#op3").attr("selected", "selected");
			} else if (hideType == "动漫游戏") {
				$("#op4").attr("selected", "selected");
			} else if (hideType == "网站博客") {
				$("#op5").attr("selected", "selected");
			} else if (hideType == "网上商城") {
				$("#op6").attr("selected", "selected");
			} else if (hideType == "投资理财") {
				$("#op7").attr("selected", "selected");
			} else if (hideType == "医疗健康") {
				$("#op8").attr("selected", "selected");
			} else if (hideType == "数码产品") {
				$("#op9").attr("selected", "selected");
			} else if (hideType == "软件产品") {
				$("#op10").attr("selected", "selected");
			} else if (hideType == "两性生活") {
				$("#op11").attr("selected", "selected");
			} else if (hideType == "房地产") {
				$("#op12").attr("selected", "selected");
			} else if (hideType == "汽车") {
				$("#op13").attr("selected", "selected");
			} else if (hideType == "运动体育") {
				$("#op14").attr("selected", "selected");
			} else if (hideType == "旅游户外") {
				$("#op15").attr("selected", "selected");
			}
			
			$("#next").click(function(){
				var adId = $("#adId").val();
				
				if (adId != "") {
					$("#adForm").attr("action","<%=basePath%>ctrl/saveEditAD.ad");
				}
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var now = new Date();
				
				var _startDate = new Date(Date.parse(startDate.replace(/-/g, "/")));
				var _endDate = new Date(Date.parse(endDate.replace(/-/g, "/")));
				var _now = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				
				var budgetValue = $("#maxPayPerDay").val();
				
				if ($("#name").val() == "") {
					alert("请填写广告名称！");
				} else if ($("#type").val() == "pc") {
					alert("请选择广告类型！");
				} else if ($("#startDate").val() == "") {
					alert("请选择开始日期！");
				} else if (${editedAD eq null} && _startDate.getTime() - _now.getTime() < 0) {
					alert("开始日期不能早于当天！");
				} else if ($("#endDate").val() == "") {
					alert("请选择结束日期！");
				} else if (_endDate.getTime() - _now.getTime() < 0) {
					alert("结束日期不能早于当天！");
				}  else if (_startDate.getTime() - _endDate.getTime() > 0) {
					alert("结束日期应大于开始日期！");
				} else if ($("#maxCheck").attr("checked") && (isNaN(budgetValue)||parseInt(budgetValue).toString().match('NaN'))) {
					alert("您输入的是非数字！");
				} else if ($("#maxCheck").attr("checked") && (budgetValue.indexOf('.') != -1 && budgetValue.length-budgetValue.indexOf('.')>2)) {
					alert("请精确到小数点后1位！");
				} else if ($("#maxCheck").attr("checked") && budgetValue < 50) {
					alert("您输入的数字没有达到最低限度！");
				} else if (!$("#ap1").attr("checked") && !$("#ap2").attr("checked")) {
					alert("请选择投放媒体！");
				} else {
					$("#adForm").submit();
				}
			});
			
			$("#maxCheck").click(function(){
				if ($("#maxCheck").attr("checked")) {
					if (confirm("您是否确定设置日消费上限?（最低50） \n 设定上限可能影响到您广告的投放效果!")) {
						$("#maxPayPerDay").show();
					} else {
						$("#maxCheck").attr("checked",false);
					}
				} else {
					$("#maxPayPerDay").hide();
					$("#maxPayPerDay").val("");
				}
			});
		});
	</script>
  </head>
  
  <body>
  	<input id="error" name="error" type="hidden" value="${error}">
		    <script type="text/javascript">
		    	if($("#error").val() != "") {
		    		alert($("#error").val());
		    		window.location="<%=basePath%>ctrl/adManage.ad";
		    	}
		    </script>
  	<div>
		<jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=adManage"></jsp:include>
		    <div class="Border" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>

		    	<h2 style="margin-top:20px;margin-left:20px">广告发布——设置基本信息</h2>
		    	<img style="margin-left:20px" src="<%=basePath%>images/newad_step1.png" width="650">
		    	<form id="adForm" action="<%=basePath%>ctrl/saveAdBasicInfo.ad" method="post">
		    	<!-- 隐藏域 -->
		    	<input id="adId" name="adId" type="hidden" value="${editedAD.id}">
		    	<input id="apf" name="apf" type="hidden" value="${editedAD.app_platform}">
		    	<input id="maxPerDay" name="maxPerDay" type="hidden" value="${editedAD.max_pay_perday}">
		    	<input id="hideType" name="hideType" type="hidden" value="${editedAD.type}">
			    <table id="ad" align="center" style="margin:50px">
			    	<tr>
			    		<td>广告名称</td>
			    		<td>
			    			<input type="text" id="name" name="name" value="${editedAD.name}">
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>广告类型</td>
			    		<td>
			    			<select name="type" id="type">
			    				<option value="pc">------------请选择------------</option>
	    						<option id="op1" value="生活资讯">生活资讯</option>
	    						<option id="op2" value="影音娱乐">影音娱乐</option>
	    						<option id="op3" value="教育培训">教育培训</option>
	    						<option id="op4" value="动漫游戏">动漫游戏</option>
	    						<option id="op5" value="网站博客">网站博客</option>
	    						<option id="op6" value="网上商城">网上商城</option>
	    						<option id="op7" value="投资理财">投资理财</option>
	    						<option id="op8" value="医疗健康">医疗健康</option>
	    						<option id="op9" value="数码产品">数码产品</option>
	    						<option id="op10" value="软件产品">软件产品</option>
	    						<option id="op11" value="两性生活">两性生活</option>
	    						<option id="op12" value="房地产">房地产</option>
	    						<option id="op13" value="汽车">汽车</option>
	    						<option id="op14" value="运动体育">运动体育</option>
	    						<option id="op15" value="旅游户外">旅游户外</option>
			    			</select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>开始日期</td>
			    		<td><input name="startDate" type="text" readonly="readonly" id="startDate"
			    				 onclick="new Calendar().show(this); " value="${editedAD.start_date}"/></td>
			    	</tr>
			    	<tr>
			    		<td>结束日期</td>
			    		<td><input name="endDate" type="text" readonly="readonly" id="endDate" 
			    				onclick="new Calendar().show(this); " value="${editedAD.end_date}"/></td>
			    	</tr>
			    	<tr>
			    		<td><input id="maxCheck" type="checkbox">每日预算</td>
			    		<td><input id="maxPayPerDay" type="text" name="maxPayPerDay" value=""></td>
			    	</tr>
			    	<tr>
			    		<td>当前支持媒体</td>
			    		<td><input type="checkbox" id="ap1" name="appPlatform" value="1">Android平台
			    			<input type="checkbox" id="ap2" name="appPlatform" value="2">Ophone平台</td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="next" type="button">下一步</button></td>
			    	</tr>
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
