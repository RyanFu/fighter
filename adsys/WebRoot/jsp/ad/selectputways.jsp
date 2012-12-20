<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,cn.com.uangel.adsys.entity.*"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
	Object obj = request.getSession().getAttribute("putwayInfo");
	List<String[]> putwayInfo = null;
	if (obj != null) {
		putwayInfo = (List<String[]>) obj;
	}
	String[] ars = null;
	String[] phoneTypes = null;
	String[] zones = null;
	String crowds = null;
	String gender = null;
	String times = null;
	if (putwayInfo != null) {
		ars = putwayInfo.get(0);
		phoneTypes = putwayInfo.get(1);
		zones = putwayInfo.get(2);
		AD curAd = (AD) request.getSession().getAttribute("currentAd");
		crowds = curAd.getPut_crowd();
		gender = curAd.getPut_gender();
		times = curAd.getPut_time();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>广告发布——选择投放目标</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?key=32ce28edca8ac5443b5d8d0a9ff91d00&v=1.1&services=true" ></script>
	<script type="text/javascript">
		$(function(){
			$("input[name='zoneType']").click(function(){
				if ($(this).val()=="按省选择") {
					$("#specialZone").hide();
					$("#originalZone").show();
				} else if ($(this).val()=="局部选择") {
					$("#specialZone").show();
					$("#originalZone").hide();
				}
			});
			var edit = <%=putwayInfo == null%>;
			if(!edit){
				// 设置运营商
				$("input[name='ars']").attr("checked",false);
				<%
				if (ars != null) {
					for (int i=0; i<ars.length; i++) {
						out.println("$(\"input[name='ars'][value='" + ars[i] + "']\").attr(\"checked\",true);");
					}
				}
				%>
				// 设置机型
				$("input[name='phoneType']").attr("checked",false);
				<%
				if (phoneTypes != null) {
					for (int i=0; i<phoneTypes.length; i++) {
						out.println("$(\"input[name='phoneType'][value='" + phoneTypes[i] + "']\").attr(\"checked\",true);");
					}
				}
				%>
				// 设置地区
				<%
				if (zones != null && zones.length > 0 && zones[0].length() < 7) {// 因为省code最多2为
					out.println("$(\"input[name='zoneType'][value='按省选择']\").trigger('click');");
					out.println("$(\"input[name='ck_province']\").attr(\"checked\",false);");
					for (int i=0; i<zones.length; i++) {
						out.println("$(\"input[name='ck_province'][value='" + zones[i] + "']\").attr(\"checked\",true);");
					}
				} else if (zones != null && zones.length > 0) {
					out.println("$(\"input[name='zoneType'][value='局部选择']\").trigger('click');");
					String[] vals = zones[0].split(":");
					out.println("$('#lng').val('"+vals[0]+"');");
					out.println("$('#lat').val('"+vals[1]+"');");
					out.println("$('#radius').val('"+vals[2]+"');");
					out.println("$('#chooseCoords').text('经度:"+vals[0]+" 纬度:"+vals[1]+"');");
				}
				%>
				
				// 设置用户群
				$("input[name='userCrowd']").attr("checked",false);
				<%
				if (crowds != null) {
					int crowd = Integer.parseInt(crowds);
					for (int i=1; i<=4; i++) {
						out.println("$(\"input[name='userCrowd'][value='" + i + "']\").attr(\"checked\"," + ((crowd >> (4 - i)) % 2 == 1 ? "true" : "false") +");");
					}
				}
				%>
				// 设置性别
				<%
				if (gender != null) {
					out.println("$(\"input[name='gender'][value='" + gender + "']\").attr(\"checked\",true);");
				}
				%>
				// 设置时间段
				$("input[name='showtime']").attr("checked",false);
				<%
				if (times != null) {
					int time = Integer.parseInt(times);
					for (int i=1; i<=4; i++) {
						out.println("$(\"input[name='showtime'][value='" + i + "']\").attr(\"checked\"," + ((time >> (4 - i)) % 2 == 1 ? "true" : "false") +");");
					}
				}
				%>
			} else {
				$("input[name='ck_province']").attr("checked",true);
			}
			$("#ars_choseAll").click(function(){
				if ($("#ars_choseAll").attr("checked")) {
					$("input[name='ars']").attr("checked",true);
				} else {
					$("input[name='ars']").attr("checked",false);
				}
			});
			$("#phoneType_choseAll").click(function(){
				if ($("#phoneType_choseAll").attr("checked")) {
					$("input[name='phoneType']").attr("checked",true);
				} else {
					$("input[name='phoneType']").attr("checked",false);
				}
			});
			$("#zone_choseAll").click(function(){
				if ($("#zone_choseAll").attr("checked")) {
					$("input[name='ck_province']").attr("checked",true);
				} else {
					$("input[name='ck_province']").attr("checked",false);
				}
			});
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
			
			// 表单验证
			$("#next").click(function(){
				var selectCount = 0;
				$("input[name='ars']").each(function(){
					if($(this).attr('checked')){
						selectCount++;
					}
				});
				if (selectCount == 0) {
					alert('请至少选择一个运营商');
					return;
				}
				selectCount = 0;
				$("input[name='phoneType']").each(function(){
					if($(this).attr('checked')){
						selectCount++;
					}
				});
				if (selectCount == 0) {
					alert('请至少选择一个手机型号');
					return;
				}
				selectCount = 0;
				var zoneType = null;
				$("input[name='zoneType']").each(function(){
					if ($(this).attr('checked')) {
						zoneType = $(this).val();
					}
				});
				if ("按省选择"==zoneType) {
					$("input[name='ck_province']").each(function(){
						if($(this).attr('checked')){
							selectCount++;
						}
					});
					if (selectCount == 0) {
						alert('请至少选择一个投放地区');
						return;
					}
				} else {
					if ($("#lng").val()=="" || $("#lat").val()=="") {
						alert('请选择投放区域中心点！');
						return;
					}
					var radius = $("#radius").val();
					if(radius==""||!/^[0-9]+.?[0-9]*$/.test(radius)){
						alert('请输入正确的区域半径！');
						return;
					}
				}
				selectCount = 0;
				$("input[name='userCrowd']").each(function(){
					if($(this).attr('checked')){
						selectCount++;
					}
				});
				if (selectCount == 0) {
					alert('请至少选择一个用户群');
					selectCount = 0;
					return;
				}
				selectCount = 0;
				$("input[name='showtime']").each(function(){
					if($(this).attr('checked')){
						selectCount++;
					}
				});
				if (selectCount == 0) {
					alert('请至少选择一个时间段');
					selectCount = 0;
					return;
				}
				selectCount = 0;
				$("#putwayForm").submit();
			});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			openBaiduMap();
		});
		
		var dist = null;
		function openBaiduMap() {
			var map=new BMap.Map("container");
			   window.map;// 创建Map实例
			    <%
					if (zones != null && zones.length > 0 && zones[0].length() > 7) {
					String[] vals = zones[0].split(":");
				%>
					map.clearOverlays();
					var point2 = new BMap.Point('<%=vals[0]%>',  '<%=vals[1]%>');
					var circle2 = new BMap.Circle(point2,<%=vals[2]%>);
					map.addOverlay(circle2);
					map.centerAndZoom(new BMap.Point('<%=Double.parseDouble(vals[0])%>',  '<%=Double.parseDouble(vals[1])%>'),12);
				<%
					} else {
				%>
		       var lc=new BMap.LocalCity();//(Bmap.LocalCityOptions.renderOptions.map);
		       lc.get(function(lcr){
		         if(lcr){
		         	var temp = lcr.center;
		         	temp.lng-=0.17;
		         	temp.lat+=0.1;
		           map.centerAndZoom(temp,12);
		           //alert(lcr.center.toString());
		           //alert(lcr.name);
		           //alert("当前城市:" + lrpoi.title + "\n结果标识符uid:" + lrpoi.uid);
		           }else{
		             alert("没能获得正确地址");   
		           }
		         })
		         <%
					}
				%>

				dist = new BMap.DistanceTool(map);  // 初始化地图,设置中心点坐标和地图级别。
				
				map.enableScrollWheelZoom();     // 启用滚轮放大缩小。
				map.enableKeyboard();                         // 启用键盘操作。      
				//var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL} //修改控件配置  
				map.addControl(new BMap.NavigationControl());   //地图平移缩放控件
				map.addControl(new BMap.ScaleControl());   // 地图缩放比例尺
				//点击获取坐标
				function onclicked(e){ 
				   // alert(e.point.lng + ", " + e.point.lat); 
				    map.clearOverlays();
					var point = new BMap.Point(e.point.lng,  e.point.lat); 
					$("#lng").val(e.point.lng);
					$("#lat").val(e.point.lat);
					$("#chooseCoords").text("经度:"+e.point.lng+" 纬度:"+e.point.lat);
					var radius = $("#radius").val();
					if(radius==""||!/^[0-9]+.?[0-9]*$/.test(radius)){
						alert('请输入正确的区域半径！');
						return;
					}
					var circle = new BMap.Circle(point,radius);
					map.addOverlay(circle);
				 } 
				map.addEventListener("click", onclicked);
				 
				//鼠标移动获取坐标
				function mousemove(e){
				 //var say = document.getElementById("coords");
				 //say.innerHTML = "经度:"+e.point.lng+" 纬度:"+e.point.lat;
				 //say.style.position = "relative";
				 //say.style.marginLeft = e.clientX - 320+"px";
				 //say.style.top = e.clientY + 370 + "px";		
				
				}
				map.addEventListener("mousemove", mousemove);
		}
	</script>
  </head>
  
  <body>
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
		    	<h2 style="margin-top:20px;margin-left:20px">广告发布——选择投放目标</h2>
		    	<img style="margin-left:20px" src="<%=basePath%>images/newad_step2.png" width="650">
		    	<form id="putwayForm" action="<%=basePath%>ctrl/saveAdPutWays.ad" method="post">
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择运营商</label>
		    			<div><input id="ars_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<div style="margin-left:20px"><input name="ars" checked="checked" value="中国移动" type="checkbox">中国移动</div>
		    			<div style="margin-left:20px"><input name="ars" checked="checked" value="中国联通" type="checkbox">中国联通</div>
		    			<div style="margin-left:20px"><input name="ars" checked="checked" value="中国电信" type="checkbox">中国电信</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择手机型号</label>
		    			<div><input id="phoneType_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<div style="margin-left:20px">
		    				<table border=0>
		    					<tr>
		    						<td width="200px"><input name="phoneType" checked="checked" value="索尼爱立信" type="checkbox">索尼爱立信</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="三星" type="checkbox">三星</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="LG" type="checkbox">LG</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="HTC" type="checkbox">HTC</td>
		    					</tr>
		    					<tr>
		    						<td width="200px"><input name="phoneType" checked="checked" value="摩托罗拉" type="checkbox">摩托罗拉</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="联想" type="checkbox">联想</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="金立" type="checkbox">金立</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="华为" type="checkbox">华为</td>
		    					</tr>
		    					<tr>
		    						<td width="200px"><input name="phoneType" checked="checked" value="中兴" type="checkbox">中兴</td>
		    						<td width="200px"><input name="phoneType" checked="checked" value="其它" type="checkbox">其它</td>
		    						<td width="200px">&nbsp;</td>
		    						<td width="200px">&nbsp;</td>
		    					</tr>
		    				</table>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择投放地区</label>
		    			<div><input name="zoneType" checked="checked" value="按省选择" type="radio">按省选择</div>
		    			<div id="originalZone">
		    			<div style="margin-left:20px"><input id="zone_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
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
							<td><input id="province_33" name="ck_province" value="000000" type="checkbox"> <label for="province_34" style="cursor:pointer">其他</label></td>
							<td>&nbsp;</td>
		    				</tr>
		    			</table>
		    			</div>
		    			
		    			<div><input name="zoneType" value="局部选择" type="radio">局部选择</div>
		    			<div id="specialZone" style="display:none">
		    				<input id="lng" name="lng" type="hidden" value="" />
		    				<input id="lat" name="lat" type="hidden" value="" />
		    				<div style="margin-left:30px;margin-top:20px;margin-bottom:20px;width:560px;height:400px;border:1px solid gray" id="container"></div>
		    				<div style="margin-left:30px;">
		    					区域半径：<input id="radius" name="radius" type="text" value="1000">米
		    					<input type="button" value="测距开启" onclick="dist.open()" /><input type="button" value="测距关闭" onclick="dist.close()" />
		    				</div>
		    				<p id="coords"></p>
		    				<p id="chooseCoords"></p>
		    			</div>
		    		</div>
		    		
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择用户群</label>
		    			<div><input id="crowd_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<div style="margin-left:20px">
		    				<table border=0>
		    					<tr>
		    						<td width="200px"><input name="userCrowd" checked="checked" value="1" type="checkbox">高端商务人群</td>
		    						<td width="200px"><input name="userCrowd" checked="checked" value="2" type="checkbox">白领及公务员人群</td>
		    						<td width="200px"><input name="userCrowd" checked="checked" value="3" type="checkbox">时尚潮流人群</td>
		    						<td width="200px"><input name="userCrowd" checked="checked" value="4" type="checkbox">普通工薪人群</td>
		    					</tr>
		    				</table>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择用户性别</label>
		    			<div style="margin-left:20px">
		    				<table border=0>
		    					<tr>
		    						<td width="200px"><input name="gender" checked="checked" value="0" type="radio">全部</td>
		    						<td width="200px"><input name="gender" value="1" type="radio">男性为主</td>
		    						<td width="200px"><input name="gender" value="2" type="radio">女性为主</td>
		    					</tr>
		    				</table>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font:italic bold">选择时间段</label>
		    			<div><input id="time_choseAll" checked="checked" value="全部" type="checkbox">全部</div>
		    			<div style="margin-left:20px">
		    				<table border=0>
		    					<tr>
		    						<td width="200px"><input name="showtime" checked="checked" value="1" type="checkbox">上午：06:00~11:59</td>
		    						<td width="200px"><input name="showtime" checked="checked" value="2" type="checkbox">下午：12:00~17:59</td>
		    						<td width="200px"><input name="showtime" checked="checked" value="3" type="checkbox">晚上：18:00~23:59</td>
		    						<td width="200px"><input name="showtime" checked="checked" value="4" type="checkbox">凌晨：00:00~06:00</td>
		    					</tr>
		    				</table>
		    			</div>
		    		</div>
		    		<div style="margin-top:20px;margin-left:20px;padding-top:20px;padding-left:20px;
		    				width:640px;">
		    			<button type="button" id="next">下一步</button>
		    		</div>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
