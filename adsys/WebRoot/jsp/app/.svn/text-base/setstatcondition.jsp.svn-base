<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.List,cn.com.uangel.adsys.entity.App"%>
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
    <title>设置统计条件</title>
	
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
			$("#app").builderFormTable({tdWidth:[150,400]});
			
			$("#choseAll").click(function(){
				if ($("#choseAll").attr("checked")) {
					$("input[name='apps']").attr("checked",true);
				} else {
					$("input[name='apps']").attr("checked",false);
				}
			});
			$("#zone_choseAll").click(function(){
				if ($("#zone_choseAll").attr("checked")) {
					$("input[name='ck_province']").attr("checked",true);
				} else {
					$("input[name='ck_province']").attr("checked",false);
				}
			});
			$("#groundcheck1").click(function(){
				if($("#groundcheck1").attr("checked")){
					$("#groundcheck2").attr("checked",false);
					$("#ap1").attr("disabled",false);
					$("#ap2").attr("disabled",false);
				}else {
					$("#groundcheck2").attr("checked",true);
					$("#ap1").attr("disabled",true);
					$("#ap2").attr("disabled",true);
					
				}
			});
			$("#groundcheck2").click(function(){
				if($("#groundcheck2").attr("checked")){
					$("#groundcheck1").attr("checked",false);
					$("#ap1").attr("disabled",true);
					$("#ap2").attr("disabled",true);
				}else {
					$("#groundcheck1").attr("checked",true);
					$("#ap1").attr("disabled",false);
					$("#ap2").attr("disabled",false);
				}
			});
			
			$("#showResult").click(function(){
				var apps = $("input[name='apps']");
				var count = 0;
				for (var i = 0; i < apps.length; i++) {
					if($(apps[i]).attr("checked")) {
						count++;
					}
				}
				if (count == 0) {
					alert("至少选择一个程序！");
				} else {
					if($("#groundcheck2").attr("checked")){
						$("#appForm").attr("action", "<%=basePath%>ctrl/showGroundStatResult.app");
					}else {
						$("#appForm").attr("action", "<%=basePath%>ctrl/showStatResult.app");
					}
					$("#appForm").submit();
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
		    	<h2 style="margin-top:20px;margin-left:20px">设置统计条件</h2>
		    	<form id="appForm" action="" method="post">
		    	<!-- 隐藏域 -->
		    	<input id="appId" name="appId" type="hidden" value="${editedAD.id}">
			    <table id="app" align="center" style="margin-top:20px">
			    	<tr>
			    		<td>选择程序<input id="choseAll" type="checkbox" checked="checked"></td>
			    		<td>
			    			<div style="width:400px;padding:10px">
			    				<%
			    				List<App> appList = (List<App>) request.getAttribute("appList");
			    				for (int i = 0; i < appList.size(); i+=3) {
			    				//App app = appList.get(i);
			    				%>
			    					<div>
			    					<table   width="450" border=0>
			    					<tr >
			    					<%for(int j=0;j<3;j++){
			    						if((i+j)<appList.size()){%>
			    						
			    						<td style="text-align: left" width="33.3%">
			    							<input id="apps" name="apps"  type="checkbox" checked="checked" value="<%=appList.get(i+j).getPid()%>">
			    							<a title="<%=appList.get(i+j).getApp_name()%>"><%=(appList.get(i+j).getApp_name().length()<=9 ? appList.get(i+j).getApp_name():appList.get(i+j).getApp_name().substring(0,9)+"...")%></a>
			    						</td>
			    						
			    					<%
			    						}else {
			    					%>
			    						<td style="padding-left:10px" width="33.3%"></td>
			    						<% 
			    						}
			    					}
			    					%>
			    					</tr>
			    					</table>
			    				</div>
			    				<%
			    				}
			    				%>
			    			</div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>统计时间</td>
			    		<td>
			    			<div style="width:400px;padding:10px">
				    			开始日期<input style="height:12px;margin-left:10px" name="startDate" type="text" readonly="readonly" id="startDate"
				    				 onclick="new Calendar().show(this); " value=""><br>
				    			结束日期<input style="height:12px;margin-left:10px" name="endDate" type="text" readonly="readonly" id="endDate" 
				    				onclick="new Calendar().show(this); " value=""/>
			    			</div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>统计方式</td>
			    		<td><input type="checkbox" checked="checked" id="groundcheck1">按时间
			    		<input type="radio" style="margin-left:20px" id="ap1" name="statWay" checked="checked" value="01">按日
			    		<input type="radio" style="margin-left:20px" id="ap2" name="statWay" value="02">按月</td>
			    		
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><input type="checkbox" id="groundcheck2">按地理
			    	</tr>
			    	
			    	<!--<tr>
			    		<td>按地里统计</td>
			    		<td>
			    		<div style="font-family:Arial, Helvetica, sans-serif;font-size:12px"">
		    			<div><input id="zone_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<div style="margin-left:10px">
							<input id="province_0" name="ck_province" value="0" type="checkbox"> <label for="province_0" style="cursor:pointer">北京</label>
							<input id="province_1" name="ck_province" value="1" type="checkbox"> <label for="province_1" style="cursor:pointer">广东</label>
							<input id="province_2" name="ck_province" value="2" type="checkbox"> <label for="province_2" style="cursor:pointer">上海</label>
							<input id="province_3" name="ck_province" value="3" type="checkbox"> <label for="province_3" style="cursor:pointer">天津</label>
							<input id="province_4" name="ck_province" value="4" type="checkbox"> <label for="province_4" style="cursor:pointer">重庆</label>
							<input id="province_5" name="ck_province" value="5" type="checkbox"> <label for="province_5" style="cursor:pointer">辽宁</label>
							<input id="province_6" name="ck_province" value="6" type="checkbox"> <label for="province_6" style="cursor:pointer">江苏</label>
							
		    			</div>
		    			<div style="margin-left:10px">
		    				<input id="province_7" name="ck_province" value="7" type="checkbox"> <label for="province_7" style="cursor:pointer">湖北</label>
							<input id="province_8" name="ck_province" value="8" type="checkbox"> <label for="province_8" style="cursor:pointer">四川</label>
							<input id="province_9" name="ck_province" value="9" type="checkbox"> <label for="province_9" style="cursor:pointer">陕西</label>
							<input id="province_10" name="ck_province" value="10" type="checkbox"> <label for="province_10" style="cursor:pointer">河北</label>
							<input id="province_11" name="ck_province" value="11" type="checkbox"> <label for="province_11" style="cursor:pointer">山西</label>
							<input id="province_12" name="ck_province" value="12" type="checkbox"> <label for="province_12" style="cursor:pointer">河南</label>
							<input id="province_13" name="ck_province" value="13" type="checkbox"> <label for="province_13" style="cursor:pointer">吉林</label>
							
						</div>
						<div style="margin-left:10px">
							<input id="province_14" name="ck_province" value="14" type="checkbox"> <label for="province_14" style="cursor:pointer">黑龙江</label>
							<input id="province_15" name="ck_province" value="15" type="checkbox"> <label for="province_15" style="cursor:pointer">内蒙古</label>
							<input id="province_16" name="ck_province" value="16" type="checkbox"> <label for="province_16" style="cursor:pointer">山东</label>
							<input id="province_17" name="ck_province" value="17" type="checkbox"> <label for="province_17" style="cursor:pointer">安徽</label>
							<input id="province_18" name="ck_province" value="18" type="checkbox"> <label for="province_18" style="cursor:pointer">浙江</label>
							<input id="province_19" name="ck_province" value="19" type="checkbox"> <label for="province_19" style="cursor:pointer">福建</label>
							<input id="province_20" name="ck_province" value="20" type="checkbox"> <label for="province_20" style="cursor:pointer">云南</label>
							
						</div>
						<div style="margin-left:10px">
							<input id="province_21" name="ck_province" value="21" type="checkbox"> <label for="province_21" style="cursor:pointer">江西</label>
							<input id="province_22" name="ck_province" value="22" type="checkbox"> <label for="province_22" style="cursor:pointer">湖南</label>
							<input id="province_23" name="ck_province" value="23" type="checkbox"> <label for="province_23" style="cursor:pointer">广西</label>
		    				<input id="province_24" name="ck_province" value="24" type="checkbox"> <label for="province_24" style="cursor:pointer">贵州</label>
		    				<input id="province_25" name="ck_province" value="25" type="checkbox"> <label for="province_25" style="cursor:pointer">海南</label>
		    				<input id="province_26" name="ck_province" value="26" type="checkbox"> <label for="province_26" style="cursor:pointer">西藏</label>
		    				<input id="province_27" name="ck_province" value="27" type="checkbox"> <label for="province_27" style="cursor:pointer">新疆</label>
		    				
		    			</div>
		    			<div style="margin-left:10px">
		    				<input id="province_28" name="ck_province" value="28" type="checkbox"> <label for="province_28" style="cursor:pointer">甘肃</label>
		    				<input id="province_29" name="ck_province" value="29" type="checkbox"> <label for="province_29" style="cursor:pointer">宁夏</label>
		    				<input id="province_30" name="ck_province" value="30" type="checkbox"> <label for="province_30" style="cursor:pointer">青海</label>
		    				<input id="province_31" name="ck_province" value="31" type="checkbox"> <label for="province_31" style="cursor:pointer">港澳台</label>
		    			</div>
		    		</div>
			    		
			    		</td>
			    	</tr>-->
			    	<!-- 暂时先不做
			    	<tr>
			    		<td>汇总方式</td>
			    		<td><input type="radio" id="ap1" name="gatherWay" checked="checked" value="01">程序汇总
			    			<input type="radio" id="ap2" name="gatherWay" value="02">程序明细</td>
			    	</tr>
			    	 -->
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="showResult">生成报表</button></td>
			    	</tr>
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
