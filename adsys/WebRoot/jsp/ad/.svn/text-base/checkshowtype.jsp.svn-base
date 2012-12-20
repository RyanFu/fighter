<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,cn.com.uangel.adsys.entity.*"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
	Object obj = request.getAttribute("showTypes");
	List<ADShowType> showTypes = null;
	if (obj != null) {
		showTypes = (List<ADShowType>) obj;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>广告发布——查看广告</title>
	
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
			$("#imgAndWord").find("input").attr("disabled","disabled");
			$("#imgAndWord2").find("input").attr("disabled","disabled");
			$("#msgDiv").hide();
			$("#msgDiv2").hide();
			$("#musicsrc").hide();
			$("#musicsrc2").hide();
			$("#adId").hide();
			$("#balance").hide();
			$("#success").click(function(){
				var balance=$("#balance").val();
				if(parseFloat(balance)<0.5){
					alert("广告余额不足！");
				}else if($("#adpass2").attr("checked")||$("#adpass1").attr("checked")){
					$("#showTypeForm").submit();
				}else {
					alert("请选择审批结果！");
				}
			});
			
			$("input[name='clickshowtype']").click(function(){
				changeInfo(this,'info','lab1','msgDiv','linkDiv','showSrc','showContent','lab2','video1','mapAddress');
			});
			$("input[type=text]").attr("disabled","disabled");
			
			$("input[name='clickshowtype2']").click(function(){
				changeInfo(this,'info3','lab12','msgDiv2','linkDiv2','showSrc2','showContent2','lab22','video2','mapAddress2');
			});
			
			$("input[name='contenttype']").click(function(){
				if ($(this).val() == '全屏图片') {
					$("#info").text('当用户点击此广告链接时,将会跳转到新的页面');
					$("#lab4").text('全屏图片');
					$("#insertUploadText").text('上传全屏图片');
					$("#insertSize").text('（尺寸：480x800）');
				} else if ($(this).val() == '视频动画') {
					$("#info").text('当用户点击此广告链接时,将会链接到程序下载页面');
					$("#lab4").text('视频动画');
					$("#insertUploadText").text('上传全屏视频');
					$("#insertSize").text('');
				}
				$("#uploadInsertPath").val('');
			});
			
			$("input[name='imgWordType']").click(function(){
				if ($(this).val() == '仅广告文字') {
					$("#wordOnly").find("input").attr("disabled","disabled");
					$("#imgOnly").find("input").attr("disabled","disabled");
					$("#imgAndWord").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '仅显示图片') {
					$("#wordOnly").find("input").attr("disabled","disabled");
					$("#imgOnly").find("input").attr("disabled","disabled");
					$("#imgAndWord").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '显示文字＋图片') {
					$("#wordOnly").find("input").attr("disabled","disabled");
					$("#imgOnly").find("input").attr("disabled","disabled");
					$("#imgAndWord").find("input").attr("disabled","disabled");
				}
			});
			
			$("input[name='imgWordType2']").click(function(){
				if ($(this).val() == '仅广告文字') {
					
				} else if ($(this).val() == '仅显示图片') {
					$("#wordOnly2").find("input").attr("disabled","disabled");
					$("#imgOnly2").find("input").attr("disabled","disabled");
					$("#imgAndWord2").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '显示文字＋图片') {
					$("#wordOnly2").find("input").attr("disabled","disabled");
					$("#imgOnly2").find("input").attr("disabled","disabled");
					$("#imgAndWord2").find("input").attr("disabled","disabled");
				}
			});
			
			$("#back").click(function(){
				window.location.href="<%=basePath%>ctrl/adManage.ad";
			});
			
			
			
			if("${member.id}"==""){
					$("#loginHref2").hide();
					$("#loginHref3").hide();
				}else if("${member.id}"!=""){
					$("#loginHref1").hide();
					$("#loginHref4").hide();
			}
			
			$("#adpass1").click(function(){
				$("#adpass2").attr("checked",false);
			});
			$("#adpass2").click(function(){
				$("#adpass1").attr("checked",false);
			});
			var adId=$("#adId").val();
			$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/adCheckOtherInfo.ad?adId="+adId,
					success : function(data){
						
						var temp = '{"value":100,"src":"http://www.baidu.com"}';
						//var aaa = eval('('+data+')');
						data = data.replace('"', '').replace('"', '');
						var str = data.split('‖');
						$("#adname").text(str[0]);
						$("#adtype").text(str[1]);
						$("#adBalance").text(str[2]);
						$("#balance").val(str[2]);
					}
			});
			<%
			// 解析展现方式
			if (showTypes != null) {
				for (int i = 0; i < showTypes.size(); i++) {
					ADShowType ast = showTypes.get(i);
					String [] showTypeNames = ast.getShow_type_name().split(":");
					if ("条幅型".equals(showTypeNames[0])) {
						out.println("$('#banner').show();");
						out.println("$('#checkBanner').attr('checked',true);");
						out.println("$(\"input[name='clickshowtype'][value='"+ast.getClick_effect()+"']\").trigger('click');");
						if (ast.getClick_url() != null) {
							String[] clickInfos = ast.getClick_url().split("¿");
							out.println("$('#showSrc').val('"+clickInfos[0]+"');");
							out.println("$('#href1').text('"+clickInfos[0]+"');");
							out.println("$('#href1').attr('href','#');");
						} else {
							out.println("$('#showSrc').val('"+ast.getClick_url()+"');");
							out.println("$('#href1').text('"+ast.getClick_url()+"');");
							out.println("$('#href1').attr('href','"+ast.getClick_url()+"');");
						}
						if ("发送短信".equals(ast.getClick_effect()) || "发送邮件".equals(ast.getClick_effect()) 
							|| "播放音乐".equals(ast.getClick_effect())) {
							
							String[] clickInfos = ast.getClick_url().split("¿");
							if("播放音乐".equals(ast.getClick_effect())){
								out.println("$('#musicsrc').show();");
								out.println("$('#msgDiv').hide();");
								out.println("$('#musicSrc').attr('href','"+clickInfos[1]+"');");
								out.println("$('#musicSrc').text('"+clickInfos[1]+"');");
							}else {
								out.println("$('#showContent').val('"+clickInfos[1]+"');");
							}
						}
						if ("显示GoogleMap地址".equals(ast.getClick_effect())) {
							String[] clickInfos = ast.getClick_url().split(":");
							out.println("$('#lat').val('"+clickInfos[1]+"');");
							out.println("$('#lng').val('"+clickInfos[2]+"');");
						}
						out.println("$(\"input[name='imgWordType'][value='"+showTypeNames[1]+"']\").trigger('click');");
						if ("显示文字＋图片".equals(showTypeNames[1])) {
							out.println("$('#title3').val('"+ast.getTitle()+"');");
							out.println("$('#imgPath3').attr('src','"+basePath+"/files/ad/"+ast.getAd_info_url()+"');");
							out.println("$('#imgOnly').hide();");
						} else if ("仅显示图片".equals(showTypeNames[1])) {
							out.println("$('#imgPath2').attr('src','"+basePath+"/files/ad/"+ast.getAd_info_url()+"');");
							out.println("$('#imgAndWord').hide();");
						}
						out.println("$(\"input[name='paymode'][value='"+ast.getPay_mode()+"']\").trigger('click');");
						out.println("$('#price').val('"+ast.getPrice()+"');");
					} else if ("插屏型".equals(showTypeNames[0])) {
						out.println("$('#insert').show();");
						out.println("$('#checkInsert').attr('checked',true);");
						out.println("$(\"input[name='contenttype'][value='"+showTypeNames[1]+"']\").trigger('click');");
						out.println("$('#uploadInsertPath').attr('src','"+basePath+"/files/fullad/"+ast.getAd_info_url()+"');");
						out.println("$('#price2').val('"+ast.getPrice()+"');");
					} else if ("悬浮型".equals(showTypeNames[0])) {
						out.println("$('#float').show();");
						out.println("$('#checkFloat').attr('checked',true);");
						out.println("$(\"input[name='clickshowtype2'][value='"+ast.getClick_effect()+"']\").trigger('click');");
						String[] clickInfos = ast.getClick_url().split("¿");
						out.println("$('#showSrc2').val('"+clickInfos[0]+"');");
						out.println("$('#href2').text('"+clickInfos[0]+"');");
						out.println("$('#href2').attr('href','"+clickInfos[0]+"');");
						if ("发送短信".equals(ast.getClick_effect()) || "发送邮件".equals(ast.getClick_effect()) 
							|| "播放音乐".equals(ast.getClick_effect())) {
							
							String[] clickInfos2 = ast.getClick_url().split("¿");
							if("播放音乐".equals(ast.getClick_effect())){
								out.println("$('#musicsrc2').show();");
								out.println("$('#msgDiv2').hide();");
								out.println("$('#musicSrc2').attr('href','"+clickInfos2[1]+"');");
								out.println("$('#musicSrc2').text('"+clickInfos2[1]+"');");
							}else {
								out.println("$('#showContent2').val('"+clickInfos2[1]+"');");
							}
						}
						if ("显示GoogleMap地址".equals(ast.getClick_effect())) {
							String[] clickInfos2 = ast.getClick_url().split(":");
							out.println("$('#lat2').val('"+clickInfos2[1]+"');");
							out.println("$('#lng2').val('"+clickInfos2[2]+"');");
						}
						out.println("$(\"input[name='imgWordType2'][value='"+showTypeNames[1]+"']\").trigger('click');");
						if ("显示文字＋图片".equals(showTypeNames[1])) {
							out.println("$('#title32').val('"+ast.getTitle()+"');");
							out.println("$('#imgPath32').attr('src','"+basePath+"/files/ad/"+ast.getAd_info_url()+"');");
							out.println("$('#imgOnly2').hide();");
						} else if ("仅显示图片".equals(showTypeNames[1])) {
							out.println("$('#imgPath22').attr('src','"+basePath+"/files/ad/"+ast.getAd_info_url()+"');");
							out.println("$('#imgAndWord2').hide();");
						}
						out.println("$(\"input[name='paymode2'][value='"+ast.getPay_mode()+"']\").trigger('click');");
						out.println("$('textarea').text('"+ast.getAd_detail()+"');");
						out.println("$('#price3').val('"+ast.getPrice()+"');");
					}
				}
			}
			%>
			$("input[type=radio]").attr("disabled","disabled");
			openBaiduMap();
			openBaiduMap2();
		});
		
		function changeInfo (which,info,lab,msgDiv,linkDiv,showSrc,showContent,lab2,video,mapAddress) {
			if ($(which).val() == '手机网络') {
				$("#"+info).text('当用户点击此广告链接时,将会跳转到新的页面');
				$("#"+lab).text('手机网络');
				$("#"+msgDiv).hide();
				$("#"+video).hide();
				$("#"+linkDiv).text("跳转地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == 'Android程序') {
				$("#"+info).text('当用户点击此广告链接时,将会链接到程序下载页面');
				$("#"+lab).text('Android程序');
				$("#"+msgDiv).hide();
				$("#"+video).hide();
				$("#"+linkDiv).text("程序下载地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == 'OPhone程序') {
				$("#"+info).text('当用户点击此广告链接时,将会链接到程序下载页面');
				$("#"+lab).text('OPhone程序');
				$("#"+msgDiv).hide();
				$("#"+video).hide();
				$("#"+linkDiv).text("程序下载地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '点击通话') {
				$("#"+info).text('当用户点击此广告链接时,将会立即拨打我们设置的电话号码');
				$("#"+lab).text('点击通话');
				$("#"+msgDiv).hide();
				$("#"+video).hide();
				$("#"+linkDiv).text("设置电话号码:（如：01066668888）");
				$("#"+showSrc).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '发送短信') {
				$("#"+info).text('当用户点击此广告链接时,将会调用短信发送程序');
				$("#"+lab).text('发送短信');
				$("#"+msgDiv).show();
				$("#"+video).hide();
				$("#"+lab2).text("短信内容:");
				$("#"+linkDiv).text("收信人手机:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '发送邮件') {
				$("#"+info).text('当用户点击此广告链接时,将会调用邮件发送程序');
				$("#"+lab).text('发送邮件');
				$("#"+msgDiv).show();
				$("#"+video).hide();
				$("#"+linkDiv).text("收信人邮箱地址:");
				$("#"+lab2).text("邮件内容:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '显示GoogleMap地址') {
				$("#"+info).text('当用户点击此广告链接时,将会显示您设定的地址');
				$("#"+lab).text('显示GoogleMap地址');
				$("#"+msgDiv).show();
				$("#"+video).hide();
				$("#"+linkDiv).text("显示名称:");
				$("#"+lab2).text("设定地址:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).show();
				$("#"+showContent).hide();
				$("#"+mapAddress).attr('disabled','disabled');
			}else if ($(which).val() == '播放音乐') {
				$("#"+info).text('当用户点击此广告链接时,将会播放您设定的音乐。注：只有用户连接Wifi的条件下才能看到此广告!');
				$("#"+lab).text('播放音乐');
				$("#"+msgDiv).show();
				$("#"+video).hide();
				$("#"+linkDiv).text("音乐名称:");
				$("#"+lab2).text("下载地址:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '全屏图片') {
				$("#"+info).text('当用户点击此广告链接时,将会播放您设定的图片。注：只有用户连接Wifi的条件下才能看到此广告!');
				$("#"+lab).text('全屏图片');
				$("#"+msgDiv).hide();
				$("#"+video).hide();
				$("#"+linkDiv).text("图片地址:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '视频动画') {
				$("#"+info).text('当用户点击此广告链接时,将会播放您设定的视频。注：只有用户连接Wifi的条件下才能看到此广告!');
				$("#"+lab).text('视频动画');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).hide();
				$("#"+hrefdiv).hide();
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			}
		}
		
		var dist = null;
		function openBaiduMap() {
			var map=new BMap.Map("container");
			   window.map;// 创建Map实例
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
		         });

				dist = new BMap.DistanceTool(map);  // 初始化地图,设置中心点坐标和地图级别。
				
				map.enableScrollWheelZoom();     // 启用滚轮放大缩小。
				map.enableKeyboard();                         // 启用键盘操作。      
				//var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL} //修改控件配置  
				map.addControl(new BMap.NavigationControl());   //地图平移缩放控件
				map.addControl(new BMap.ScaleControl());   // 地图缩放比例尺
				
				if($("#lng").val() != null && $("#lng").val() != "") {
					var longitude = $('#lng').val();
					var latitude = $('#lat').val();
					var point = new BMap.Point(longitude,  latitude); 
					$("#chooseCoords").text("经度:"+longitude+" 纬度:"+latitude);
					var marker = new BMap.Marker(point);
					var opts2 = {
					  width : 250,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "地点名称"  // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow($("#href1").text(), opts2);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				}
		}
		
		var dist2 = null;
		function openBaiduMap2() {
			var map=new BMap.Map("container2");
			   window.map;// 创建Map实例
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
		         });

				dist = new BMap.DistanceTool(map);  // 初始化地图,设置中心点坐标和地图级别。
				
				map.enableScrollWheelZoom();     // 启用滚轮放大缩小。
				map.enableKeyboard();                         // 启用键盘操作。      
				//var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL} //修改控件配置  
				map.addControl(new BMap.NavigationControl());   //地图平移缩放控件
				map.addControl(new BMap.ScaleControl());   // 地图缩放比例尺
				
				if($("#lng2").val() != null && $("#lng2").val() != "") {
					var longitude = $('#lng2').val();
					var latitude = $('#lat2').val();
					var point = new BMap.Point(longitude,  latitude); 
					$("#chooseCoords2").text("经度:"+longitude+" 纬度:"+latitude);
					var marker = new BMap.Marker(point);
					var opts2 = {
					  width : 250,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "地点名称"  // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow($("#href2").text(), opts2);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				}
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
		    	<h2 style="margin-top:20px;margin-left:60px">广告发布——查看广告</h2>
		    	<div >
		    	
		    	<form id="showTypeForm" action="<%=basePath%>ctrl/adCheckUpdate.man" method="post"><!--  enctype="multipart/form-data" -->
		    	<input id="adId" type="password" name="adId" value="${adId}">
		    	<input id="balance" type="password" name="balance" value="">
		    	<div class="borderDark">
		    	<div>广告名称：<label id="adname"></label></div>
		    	<div>广告类型：<label id="adtype"></label></div>
		    	<div>广告账户余额：<label id="adBalance"></label></div>
		    	</div>
		    	<div style="margin:20px;background:#aaaaaa"><input id="checkBanner" name="showType" value="条幅型" type="checkbox">条幅型</div>
		    	<div id="banner" style="display:none">
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">请选择一种点击效果：</label>
		    			<div style="margin-left:20px"><input name="clickshowtype" checked="checked" value="手机网络" type="radio">手机网络</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="Android程序" type="radio">Android程序</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="OPhone程序" type="radio">OPhone程序</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="点击通话" type="radio">点击通话</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="发送短信" type="radio">发送短信</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="发送邮件" type="radio">发送邮件</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="显示GoogleMap地址" type="radio">显示GoogleMap地址</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="播放音乐" type="radio">播放音乐（Wifi条件下）</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="全屏图片" type="radio">全屏图片（Wifi条件下）</div>
		    			<div style="margin-left:20px"><input name="clickshowtype" value="视频动画" type="radio">视频动画（Wifi条件下）</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				说明： <label id="info" style="color:red">当用户点击此广告链接时,将会跳转到新的页面</label>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">设置链接地址： </label><label id="lab1">手机网络</label>
		    			<div id="linkDiv" style="margin-left:20px;margin-top:20px">跳转地址:</div>
		    			<div id="hrefdiv" style="margin-left:40px"><a id="href1"></a></div>
		    			<div id="msgDiv">
		    			<div style="margin-left:20px;margin-top:20px"><label id="lab2">短信内容:</label></div>
		    			<div style="margin-left:40px"><input id="showContent" name="msgContent" value="" type="text" style="width:400px"></div>
		    			</div>
						<div id=mapAddress style="display:none">
		    				<input id="lng" name="lng" type="hidden" value="" />
		    				<input id="lat" name="lat" type="hidden" value="" />
		    				<div style="margin-left:30px;margin-top:20px;margin-bottom:20px;width:560px;height:400px;border:1px solid gray" id="container"></div>
		    				<p id="chooseCoords"></p>
		    			</div>
		    			<div id="musicsrc">
		    			 	<div id="linkDiv" style="margin-left:20px;margin-top:20px">下载地址:</div>
		    				<div style="margin-left:40px"><a id="musicSrc"></a></div>
		    			</div>
		    			<dir id="video1">
		    				<object align=middle classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" class=OBJECT id=MediaPlayer width=400 height=400> 
									<param name=ShowStatusBar value=0> 
									<param name="AUTOSTART" value="false"/>
									<param name=Filename value="C:\Users\dev\Desktop\asdf.3gp"> 
									<embed type=application/x-oleobject codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" > 
									</embed> 
							</object>
		    			</dir>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">选择显示方式： </label>
		    			
		    			<!-- 仅广告文字 
		    			<div style="margin-left:20px;margin-top:20px">
		    				<input id="onlyWord" name="imgWordType" checked="checked" value="仅广告文字" type="radio">仅广告文字
		    			</div>
		    			<div id="wordOnly">
		    			<div style="margin-left:40px;margin-top:10px">广告文字内容:</div>
		    			<div style="margin-left:60px"><input id="title1" name="title1" value="" type="text" style="width:300px">（最多15个汉字）</div>
		    			</div>-->
		    			
		    			<!-- 仅显示图片 -->
		    			<div style="margin-left:20px;margin-top:20px">
		    				<input id="onlyImg" name="imgWordType" checked="checked" value="仅显示图片" type="radio">仅显示图片
		    			</div>
		    			<div id="imgOnly">
			    			<div style="margin-left:40px;margin-top:10px">设置显示图片:</div>
			    			<div style="margin-left:60px">
			    				<img id="imgPath2" style="margin-left:10px;margin-bottom:-5px"/>
			    			</div>
		    			</div>
		    			
		    			<!-- 显示文字＋图片 -->
		    			<div style="margin-left:20px;margin-top:20px">
		    				<input id="wordAndImg" name="imgWordType" value="显示文字＋图片" type="radio">显示文字＋图片
		    				
		    			</div>
		    			<div id="imgAndWord">
			    			<div style="margin-left:40px;margin-top:10px">广告文字内容:</div>
			    			<div style="margin-left:60px"><input id="title3" name="title3" value="" type="text" style="width:300px">（最多15个汉字）</div>
			    			<div style="margin-left:40px;margin-top:10px">设置显示图片:</div>
			    			<div style="margin-left:60px">
			    				<img id="imgPath3" style="margin-left:10px;margin-bottom:-5px"/>
			    			</div>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">付费模式：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<input name="paymode" checked="checked" value="按印象付费" type="radio">按印象付费（每1000次印象付费金额）
		    			</div>
		    			<div style="margin-left:20px">
		    				<input name="paymode" value="按单次点击付费" type="radio">按单次点击付费
		    			</div>
		    			<div style="margin:20px">
		    				出价：<input id="price" name="price" value="" type="text">元 <label id="lab3">（5元起）</label>
		    			</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				注意：<label id="waring" style="color:red">
		    						当对广告投放目标进行较多的限定时，建议广告出价金额设定高一些，否则可以能影响广告投放效果！
		    					 </label>
		    			</div>
		    		</div>
		    	</div>
		    	
		    	<div style="margin:20px;background:#aaaaaa"><input id="checkInsert" name="showType" value="插屏型" type="checkbox">插屏型</div>
		    	<div id="insert" style="display:none">
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">选择内容形式：（Wifi条件下）</label>
		    			<div style="margin-left:20px"><input id="insertRadio1" name="contenttype" checked="checked" value="全屏图片" type="radio">全屏图片：480*800</div>
		    			<div style="margin-left:20px"><input id="insertRadio2" name="contenttype" value="视频动画" type="radio">视频动画：暂支持3gp格式</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				说明： <label id="info2" style="color:red">插屏广告只有用户在连接Wifi的条件下才能显示</label>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">上传全屏内容： </label><label id="lab4">全屏图片</label>
		    			<div id="insertUploadText" style="margin-left:20px;margin-top:20px">上传全屏图片:</div>
		    			<div style="margin-left:60px">
		    				<img id="uploadInsertPath" style="margin-left:10px;margin-bottom:-5px"/>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">付费模式：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<input name="paymode_Insert" checked="checked" value="按印象付费" type="radio">按印象付费（每1次印象付费金额）
		    			</div>
		    			<div style="margin:20px">
		    				出价：<input id="price2" name="price2" value="" type="text">元 <label id="lab3">（0.5元起）</label>
		    			</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				注意：<label id="waring" style="color:red">
		    						当对广告投放目标进行较多的限定时，建议广告出价金额设定高一些，否则可以能影响广告投放效果！
		    					 </label>
		    			</div>
		    		</div>
		    	</div>
		    	<div style="margin:20px;background:#aaaaaa"><input id="checkFloat" name="showType" value="悬浮型" type="checkbox">飘动悬浮型（半屏）</div>
		    	<div id="float" style="display:none">
		    		<div class="borderDark">
		    			<label style="color:red;font-weight:bold">提示：在屏幕角落出现一个小图标，有可能用户看不到，但用户体验非常好，一旦用户发现，<br>
		    														   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    														   点击广告的概率非常高，适合程序下载类广告。</label><br><br>
		    			<label style="color:blue;font-weight:bold">请选择一种点击效果：</label>
		    			<div style="margin-left:20px"><input name="clickshowtype2" checked="checked" value="手机网络" type="radio">手机网络</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="Android程序" type="radio">Android程序</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="OPhone程序" type="radio">OPhone程序</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="点击通话" type="radio">点击通话</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="发送短信" type="radio">发送短信</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="发送邮件" type="radio">发送邮件</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="显示GoogleMap地址" type="radio">显示GoogleMap地址</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="播放音乐" type="radio">播放音乐（Wifi条件下）</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="全屏图片" type="radio">全屏图片（Wifi条件下）</div>
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="视频动画" type="radio">视频动画（Wifi条件下）</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				说明： <label id="info3" style="color:red">当用户点击此广告链接时,将会跳转到新的页面</label>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">设置链接地址： </label><label id="lab12">手机网络</label>
		    			<div id="linkDiv2" style="margin-left:20px;margin-top:20px">跳转地址:</div>
		    			<div style="margin-left:40px"><a id="href2"></a></div>
		    			<div id="msgDiv2">
		    			<div style="margin-left:20px;margin-top:20px"><label id="lab22">短信内容:</label></div>
		    			<div style="margin-left:40px"><input id="showContent2" name="msgContent2" value="" type="text" style="width:400px"></div>
		    			</div>
						<div id=mapAddress2 style="display:none">
		    				<input id="lng2" name="lng2" type="hidden" value="" />
		    				<input id="lat2" name="lat2" type="hidden" value="" />
		    				<div style="margin-left:30px;margin-top:20px;margin-bottom:20px;width:560px;height:400px;border:1px solid gray" id="container2"></div>
		    				<p id="chooseCoords2"></p>
		    			</div>
		    			<div id="musicsrc2">
		    			 	<div id="linkDiv" style="margin-left:20px;margin-top:20px">下载地址:</div>
		    				<div style="margin-left:40px"><a id="musicSrc2"></a></div>
		    			</div>
		    			<dir id="video2">
		    				<object align=middle classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" class=OBJECT id=MediaPlayer width=400 height=400> 
									<param name=ShowStatusBar value=0> 
									<param name="AUTOSTART" value="false"/>
									<param name=Filename value="C:\Users\dev\Desktop\asdf.3gp"> 
									<embed type=application/x-oleobject codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" > 
									</embed> 
								</object>
		    			</dir>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">选择显示方式： </label>
		    			
		    			<!-- 仅显示图片 -->
		    			<div style="margin-left:20px;margin-top:20px">
		    				<input id="onlyImg2" name="imgWordType2" checked="checked" value="仅显示图片" type="radio">仅显示图片
		    			</div>
		    			<div id="imgOnly2">
			    			<div style="margin-left:40px;margin-top:10px">设置显示图片:</div>
			    			<div style="margin-left:60px">
			    				<img id="imgPath22" style="margin-left:10px;margin-bottom:-5px"/>
			    			</div>
		    			</div>
		    			
		    			<!-- 显示文字＋图片 -->
		    			<div style="margin-left:20px;margin-top:20px">
		    				<input id="wordAndImg2" name="imgWordType2" value="显示文字＋图片" type="radio">显示文字＋图片
		    			</div>
		    			<div id="imgAndWord2">
			    			<div style="margin-left:40px;margin-top:10px">广告文字内容:</div>
			    			<div style="margin-left:60px"><input id="title32" name="title32" value="" type="text" style="width:300px">（最多15个汉字）</div>
			    			<div style="margin-left:40px;margin-top:10px">设置显示图片:</div>
			    			<div style="margin-left:60px">
			    				<img id="imgPath32" style="margin-left:10px;margin-bottom:-5px"/>
			    			</div>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">广告详细描述：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<textarea cols="50" rows="6" name="addetail" disabled="disabled"></textarea>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">付费模式：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<input name="paymodeFloat" checked="checked" value="按印象付费" type="radio">按点击付费
		    			</div>
		    			<div style="margin:20px">
		    				出价：<input id="price3" name="price3" value="" type="text">元 <label id="lab32">（0.3元起）</label>
		    			</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				注意：<label id="waring" style="color:red">
		    						当对广告投放目标进行较多的限定时，建议广告出价金额设定高一些，否则可以能影响广告投放效果！
		    					 </label>
		    			</div>
		    		</div>
		    	</div>
		    	<div><button style="margin-left: 300px" id="back" type="button">返回</button></div>
			    </form>
			    </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
