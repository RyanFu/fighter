<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.com.uangel.adsys.entity.AD"%>
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
			$("#ad").builderFormTable({tdWidth:[150,400]});
			
			$("#choseAll").click(function(){
				if ($("#choseAll").attr("checked")) {
					$("input[name='ads']").attr("checked",true);
				} else {
					$("input[name='ads']").attr("checked",false);
				}
			});
			
			$("input[name='ck_province']").attr("checked",true);
			
			$("#crowd_choseAll").click(function(){
				if ($("#crowd_choseAll").attr("checked")) {
					$("input[name='userCrowd']").attr("checked",true);
				} else {
					$("input[name='userCrowd']").attr("checked",false);
				}
			});
			$("#time_choseAll").click(function(){
				if ($("#time_choseAll").attr("checked")) {
					$("input[name='showtime']").attr("checked",true);
				} else {
					$("input[name='showtime']").attr("checked",false);
				}
			});
			if($("#timeTotal").attr("checked")){
				$("#ap1").attr("disabled",false);
					$("#ap2").attr("disabled",false);
					$("input[name='ck_province']").attr("disabled",true);
					$("input[name='userCrowd']").attr("disabled",true);
					$("input[name='showtime']").attr("disabled",true);
					$("#crowd_choseAll").attr("disabled",true);
					$("#zone_choseAll").attr("disabled",true);
					$("#time_choseAll").attr("disabled",true);
					$("#zoneDiv").hide();
					$("#crowdDiv").hide();
					$("#hourDiv").hide();
			}
			$("#timeTotal").click(function(){
					$("#ap1").attr("disabled",false);
					$("#ap2").attr("disabled",false);
					$("input[name='ck_province']").attr("disabled",true);
					$("input[name='userCrowd']").attr("disabled",true);
					$("input[name='showtime']").attr("disabled",true);
					$("#crowd_choseAll").attr("disabled",true);
					$("#zone_choseAll").attr("disabled",true);
					$("#time_choseAll").attr("disabled",true);
					$("#zoneDiv").hide();
					$("#crowdDiv").hide();
					$("#hourDiv").hide();
					$("#timeDiv").show();
			});
			$("#locationTotal").click(function(){
					$("#ap1").attr("disabled",true);
					$("#ap2").attr("disabled",true);
					$("input[name='ck_province']").attr("disabled",false);
					$("input[name='userCrowd']").attr("disabled",true);
					$("input[name='showtime']").attr("disabled",true);
					$("#crowd_choseAll").attr("disabled",true);
					$("#zone_choseAll").attr("disabled",false);
					$("#time_choseAll").attr("disabled",true);
					$("#zoneDiv").show();
					$("#crowdDiv").hide();
					$("#hourDiv").hide();
					$("#timeDiv").hide();
			});
			$("#crowdTotal").click(function(){
					$("#ap1").attr("disabled",true);
					$("#ap2").attr("disabled",true);
					$("input[name='ck_province']").attr("disabled",true);
					$("input[name='userCrowd']").attr("disabled",false);
					$("input[name='showtime']").attr("disabled",true);
					$("#crowd_choseAll").attr("disabled",false);
					$("#zone_choseAll").attr("disabled",true);
					$("#time_choseAll").attr("disabled",true);
					$("#zoneDiv").hide();
					$("#crowdDiv").show();
					$("#hourDiv").hide();
					$("#timeDiv").hide();
			});
			$("#hourTotal").click(function(){
					$("#ap1").attr("disabled",true);
					$("#ap2").attr("disabled",true);
					$("input[name='ck_province']").attr("disabled",true);
					$("input[name='userCrowd']").attr("disabled",true);
					$("input[name='showtime']").attr("disabled",false);
					$("#crowd_choseAll").attr("disabled",true);
					$("#zone_choseAll").attr("disabled",true);
					$("#time_choseAll").attr("disabled",false);
					$("#zoneDiv").hide();
					$("#crowdDiv").hide();
					$("#hourDiv").show();
					$("#timeDiv").hide();
			});
			
			$("#zone_choseAll").click(function(){
				if ($("#zone_choseAll").attr("checked")) {
					$("input[name='ck_province']").attr("checked",true);
				} else {
					$("input[name='ck_province']").attr("checked",false);
				}
			});
			
			$("#showResult").click(function(){
				var ads = $("input[name='ads']");
				var count = 0;
				for (var i = 0; i < ads.length; i++) {
					if($(ads[i]).attr("checked")) {
						count++;
					}
				}
				
				if (count == 0) {
					alert("至少选择一条广告！");
					return;
				}else if($("#locationTotal").attr("checked")){
					var locations=$("input[name='ck_province']");
					var count1=0;
					for(var i = 0; i < locations.length; i++){
						if($(locations[i]).attr("checked")) {
							count1++;
						}
					}
					if(count1==0){
						alert("至少选择一个地区！");
						return;
					}
					
				}else if($("#crowdTotal").attr("checked")){
					var cro=$("input[name='userCrowd']");
					var count2=0;
					for(var i = 0; i < cro.length; i++){
						if($(cro[i]).attr("checked")) {
							count2++;
						}
					}
					if(count2==0){
						alert("至少选择一个用户群");
						return;
					}
				}else if($("#hourTotal").attr("checked")){
					var hour=$("input[name='showtime']");
					var count3=0;
					for(var i = 0; i < hour.length; i++){
						if($(hour[i]).attr("checked")) {
							count3++;
						}
					}
					if(count3==0){
						alert("至少选择一个时段");
						return;
					}
				}

				
				if($("#timeTotal").attr("checked")){
					$("#adForm").attr("action", "<%=basePath%>ctrl/showStatResult.ad");
				}else if($("#locationTotal").attr("checked")){
					$("#adForm").attr("action", "<%=basePath%>ctrl/adStatisticsByGeography.ad");
				}else if($("#crowdTotal").attr("checked")){
					$("#adForm").attr("action", "<%=basePath%>ctrl/adStatisticsByCrowd.ad");
				}else if($("#hourTotal").attr("checked")){
					$("#adForm").attr("action", "<%=basePath%>ctrl/adStatisticsByTimeInterval.ad");
				}
					
					
				$("#adForm").submit();
					
					
				
			});
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
		<jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
  		<!-- <div id="head_center">
  			<div id="logo">
  			<a href="<%=basePath%>"><img src="<%=basePath%>/styles/images/logo.png" /></a>
			</div>
  		</div>
  		<div id="navigation">
			<div class="left"></div>
			<ul>
				<li >
					<a href="<%=basePath%>index.jsp">&nbsp;首&nbsp;页&nbsp;</a>
				</li>
				<li >
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
				<li class="selected">
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
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=adManage"></jsp:include>
		    <div class="Border" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">设置统计条件</h2>
		    	<form id="adForm" action="" method="post">
		    	<!-- 隐藏域 -->
		    	<input id="adId" name="adId" type="hidden" value="${editedAD.id}">
			    <table id="ad" align="center" style="margin-top:20px">
			    	<tr>
			    		<td>选择广告<input id="choseAll" type="checkbox" checked="checked"></td>
			    		<td>
			    			<div style="width:400px;padding:10px">
			    				<%
			    				java.util.List<AD> adList = (java.util.List<AD>) request.getAttribute("adList");
			    				for (int i = 0; i < adList.size(); i+=3) {
			    				%>
			    				<div>
			    				
			    					<table   width="450" border=0>
			    					<tr >
			    					<%for(int j=0;j<3;j++){
			    						if((i+j)<adList.size()){%>
			    						
			    						<td style="text-align: left" width="33.3%">
			    							<input id="ads" name="ads" type="checkbox" checked="checked" value="<%=adList.get(i+j).getId()%>">
			    							<a title="<%=adList.get(i+j).getName()%>"><%=(adList.get(i+j).getName().length()<=9 ? adList.get(i+j).getName():adList.get(i+j).getName().substring(0,9)+"...")%></a>
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
			    		<td>&nbsp;</td>
			    		<td><div style="font-size:20px;color:red">请选择一种统计方式</div></td>
			    	</tr>
			    	<tr>
			    		<td>按时间统计<input id="timeTotal" checked="checked" name="qwe" type="radio" ></td>
			    		<td>
							<div id="timeDiv">
							<input type="radio" id="ap1" name="statWay" checked="checked" value="01">按日
			    			<input type="radio" id="ap2" name="statWay" value="02">按月
							</div>
						</td>
			    	</tr>
			    	<tr>
			    		<td>按地理统计<input id="locationTotal" name="qwe" type="radio" ></td>
			    		<td>
			    		<div style="font-family:Arial, Helvetica, sans-serif;font-size:12px"" id="zoneDiv">
		    			<div><input id="zone_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<table style="margin-left:40px">
		    				<tr>
							<td><input id="province_0" name="ck_province" value="110000" type="checkbox"> <label for="province_0" style="cursor:pointer">北京</label>&nbsp;&nbsp;</td>
							<td><input id="province_1" name="ck_province" value="120000" type="checkbox"> <label for="province_1" style="cursor:pointer">天津</label>&nbsp;&nbsp;</td>
							<td><input id="province_2" name="ck_province" value="130000" type="checkbox"> <label for="province_2" style="cursor:pointer">河北</label>&nbsp;&nbsp;</td>
							<td><input id="province_3" name="ck_province" value="140000" type="checkbox"> <label for="province_3" style="cursor:pointer">山西</label>&nbsp;&nbsp;</td>
							<td><input id="province_4" name="ck_province" value="150000" type="checkbox"> <label for="province_4" style="cursor:pointer">内蒙古</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_5" name="ck_province" value="210000" type="checkbox"> <label for="province_5" style="cursor:pointer">辽宁</label>&nbsp;&nbsp;</td>
							<td><input id="province_6" name="ck_province" value="220000" type="checkbox"> <label for="province_6" style="cursor:pointer">吉林</label>&nbsp;&nbsp;</td>
							<td><input id="province_7" name="ck_province" value="230000" type="checkbox"> <label for="province_7" style="cursor:pointer">黑龙江</label></td>
							<td><input id="province_8" name="ck_province" value="310000" type="checkbox"> <label for="province_8" style="cursor:pointer">上海</label>&nbsp;&nbsp;</td>
							<td><input id="province_9" name="ck_province" value="320000" type="checkbox"> <label for="province_9" style="cursor:pointer">江苏</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_10" name="ck_province" value="330000" type="checkbox"> <label for="province_10" style="cursor:pointer">浙江</label>&nbsp;&nbsp;</td>
							<td><input id="province_11" name="ck_province" value="340000" type="checkbox"> <label for="province_11" style="cursor:pointer">安徽</label>&nbsp;&nbsp;</td>
							<td><input id="province_12" name="ck_province" value="350000" type="checkbox"> <label for="province_12" style="cursor:pointer">福建</label>&nbsp;&nbsp;</td>
							<td><input id="province_13" name="ck_province" value="360000" type="checkbox"> <label for="province_13" style="cursor:pointer">江西</label>&nbsp;&nbsp;</td>
							<td><input id="province_14" name="ck_province" value="370000" type="checkbox"> <label for="province_14" style="cursor:pointer">山东</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_15" name="ck_province" value="410000" type="checkbox"> <label for="province_15" style="cursor:pointer">河南</label></td>
							<td><input id="province_16" name="ck_province" value="420000" type="checkbox"> <label for="province_16" style="cursor:pointer">湖北</label>&nbsp;&nbsp;</td>
							<td><input id="province_17" name="ck_province" value="430000" type="checkbox"> <label for="province_17" style="cursor:pointer">湖南</label>&nbsp;&nbsp;</td>
							<td><input id="province_18" name="ck_province" value="440000" type="checkbox"> <label for="province_18" style="cursor:pointer">广东</label>&nbsp;&nbsp;</td>
							<td><input id="province_19" name="ck_province" value="450000" type="checkbox"> <label for="province_19" style="cursor:pointer">广西</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_20" name="ck_province" value="460000" type="checkbox"> <label for="province_20" style="cursor:pointer">海南</label>&nbsp;&nbsp;</td>
							<td><input id="province_21" name="ck_province" value="500000" type="checkbox"> <label for="province_21" style="cursor:pointer">重庆</label>&nbsp;&nbsp;</td>
							<td><input id="province_22" name="ck_province" value="510000" type="checkbox"> <label for="province_22" style="cursor:pointer">四川</label>&nbsp;&nbsp;</td>
							<td><input id="province_23" name="ck_province" value="520000" type="checkbox"> <label for="province_23" style="cursor:pointer">贵州</label></td>
							<td><input id="province_24" name="ck_province" value="530000" type="checkbox"> <label for="province_24" style="cursor:pointer">云南</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_25" name="ck_province" value="540000" type="checkbox"> <label for="province_25" style="cursor:pointer">西藏</label>&nbsp;&nbsp;</td>
							<td><input id="province_26" name="ck_province" value="610000" type="checkbox"> <label for="province_26" style="cursor:pointer">陕西</label>&nbsp;&nbsp;</td>
							<td><input id="province_27" name="ck_province" value="620000" type="checkbox"> <label for="province_27" style="cursor:pointer">甘肃</label>&nbsp;&nbsp;</td>
							<td><input id="province_28" name="ck_province" value="630000" type="checkbox"> <label for="province_28" style="cursor:pointer">青海</label>&nbsp;&nbsp;</td>
							<td><input id="province_29" name="ck_province" value="640000" type="checkbox"> <label for="province_29" style="cursor:pointer">宁夏</label>&nbsp;&nbsp;</td>
							</tr>
							<tr>
							<td><input id="province_30" name="ck_province" value="650000" type="checkbox"> <label for="province_30" style="cursor:pointer">新疆</label>&nbsp;&nbsp;</td>
							<td><input id="province_31" name="ck_province" value="710000" type="checkbox"> <label for="province_31" style="cursor:pointer">台湾</label></td>
							<td><input id="province_32" name="ck_province" value="810000" type="checkbox"> <label for="province_32" style="cursor:pointer">香港</label></td>
							<td><input id="province_33" name="ck_province" value="820000" type="checkbox"> <label for="province_33" style="cursor:pointer">澳门</label></td>
							<td><input id="province_34" name="ck_province" value="000000" type="checkbox"> <label for="province_34" style="cursor:pointer">其他</label></td>
		    				</tr>
		    			</table>
		    			</div>
			    		</td>
			    	</tr>
					<tr style="height:0"></tr>
			    	<tr>
			    		<td>按人群统计<input id="crowdTotal" name="qwe" type="radio" ></td>
			    		<td>
							<div id="crowdDiv">
			    			<div><input id="crowd_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    				<div style="margin-left:20px">
		    						<div>
		    							<input name="userCrowd" checked="checked" value="8" type="checkbox">高端商务人群
		    							<input name="userCrowd" checked="checked" value="4" type="checkbox">白领及公务员人群
		    						</div>
		    						<div>
		    							<input name="userCrowd" checked="checked" value="2" type="checkbox">时尚潮流人群
		    							<input name="userCrowd" checked="checked" value="1" type="checkbox">普通工薪人群
		    						</div>
		    				</div>
							</div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>按时段统计<input id="hourTotal" name="qwe" type="radio" ></td>
			    		<td>
							<div id="hourDiv">
			    			<div><input id="time_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
			    		  	<div style="margin-left:20px">
			    		  			<div><input name="showtime" checked="checked" value="1" type="checkbox">上午：06:00~11:59
		    						<input name="showtime" checked="checked" value="2" type="checkbox">下午：12:00~17:59
		    						</div>
		    						<div><input name="showtime" checked="checked" value="3" type="checkbox">晚上：18:00~23:59
		    						<input name="showtime" checked="checked" value="4" type="checkbox">凌晨：00:00~06:00
		    						</div>
			    		  	</div>
							</div>
			    		</td>
			    	</tr>
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
