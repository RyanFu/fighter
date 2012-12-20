<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,cn.com.uangel.adsys.entity.*"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
	Object obj = request.getSession().getAttribute("showTypes");
	List<ADShowType> showTypes = null;
	if (obj != null) {
		showTypes = (List<ADShowType>) obj;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>广告发布——设置展现方式</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/ajaxupload.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?key=32ce28edca8ac5443b5d8d0a9ff91d00&v=1.1&services=true" ></script>
	
	<script type="text/javascript">
		$(function(){
			$("#imgAndWord").find("input").attr("disabled","disabled");
			$("#imgAndWord2").find("input").attr("disabled","disabled");
			$("#msgDiv").hide();
			$("#msgDiv2").hide();
			
			$("input[name='clickshowtype']").click(function(){
				changeInfo(this,'info','lab1','msgDiv','linkDiv','showSrc','showContent','lab2','mapAddress');
				if ($(this).val() == '显示GoogleMap地址') {
					$("#banner").animate({height:"1450"},500,function(){});
				} else {
					$("#banner").animate({height:"1000"},500,function(){});
				}
			});
			$("input[name='clickshowtype2']").click(function(){
				changeInfo(this,'info3','lab12','msgDiv2','linkDiv2','showSrc2','showContent2','lab22','mapAddress2');
				if ($(this).val() == '显示GoogleMap地址') {
					$("#float").animate({height:"1700"},500,function(){});
				} else {
					$("#float").animate({height:"1250"},500,function(){});
				}
			});
			$("input[name='paymode']").click(function(){
				if ($(this).val() == '按印象付费') {
					$("#lab3").text("（5元起）");
				} else if ($(this).val() == '按单次点击付费') {
					$("#lab3").text("（0.5元起）");
				}
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
					$("#wordOnly").find("input").attr("disabled","");
					$("#imgOnly").find("input").attr("disabled","disabled");
					$("#imgAndWord").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '仅显示图片') {
					$("#wordOnly").find("input").attr("disabled","disabled");
					$("#imgOnly").find("input").attr("disabled","");
					$("#imgAndWord").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '显示文字＋图片') {
					$("#wordOnly").find("input").attr("disabled","disabled");
					$("#imgOnly").find("input").attr("disabled","disabled");
					$("#imgAndWord").find("input").attr("disabled","");
				}
			});
			
			$("input[name='imgWordType2']").click(function(){
				if ($(this).val() == '仅广告文字') {
					
				} else if ($(this).val() == '仅显示图片') {
					$("#wordOnly2").find("input").attr("disabled","disabled");
					$("#imgOnly2").find("input").attr("disabled","");
					$("#imgAndWord2").find("input").attr("disabled","disabled");
				} else if ($(this).val() == '显示文字＋图片') {
					$("#wordOnly2").find("input").attr("disabled","disabled");
					$("#imgOnly2").find("input").attr("disabled","disabled");
					$("#imgAndWord2").find("input").attr("disabled","");
				}
			});
			
			// 上传条幅型-->仅显示图片320*50
			new AjaxUpload('upload2', {
		           action: '<%=basePath%>ctrl/uploadImage2.ad',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
					if (ext && /^(png|PNG)$/.test(ext)){
						/* Setting data */
						this.setData({
							'type' : 'banner'
						});
						//$('#errorBox3').text('正在上传 ' + file +'...');	
					} else {
						alert('格式错误 : 只能上传图片类型 \n正确格式为: .png,请重新上传!');
						return false;				
					}		
				},
				onComplete : function(file,response){
					//服务器返回路径 
					var  tempVal =  eval(response);
					var isOk = tempVal.substring(0, 2);
					tempVal = tempVal.substring(2);
		
					if(isOk == "ok"){
						//文本显示
						$('#imgPath2').val(tempVal);
					}else{
						//错误显示
						alert(tempVal);
					}
				}
			});
			
			// 上传条幅型-->图片+文字38*38
			new AjaxUpload('upload3', {
		           action: '<%=basePath%>ctrl/uploadImage3.ad',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
					//if (ext && /^(jpg|png|jpeg|gif|JPG|PNG|JPEG|GIF)$/.test(ext)){
					if (ext && /^(png|PNG)$/.test(ext)){
						/* Setting data */
						this.setData({
							'type' : 'banner'
						});
						//$('#errorBox3').text('正在上传 ' + file +'...');	
					} else {
						//alert('格式错误 : 只能上传图片类型 \n正确格式为: .jpg .png .jpeg .gif,请重新上传!');
						alert('格式错误 : 只能上传图片类型 \n正确格式为: .png,请重新上传!');
						return false;				
					}		
				},
				onComplete : function(file,response){
					//服务器返回路径 
					var  tempVal =  eval(response);
					var isOk = tempVal.substring(0, 2);
					tempVal = tempVal.substring(2);
		
					if(isOk == "ok"){
						//文本显示
						$('#imgPath3').val(tempVal);
					}else{
						//错误显示
						alert(tempVal);
					}
				}
			});
			
			// 上传悬浮型-->仅显示图片320*50
			new AjaxUpload('upload22', {
		           action: '<%=basePath%>ctrl/uploadImage2.ad',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
					if (ext && /^(png|PNG)$/.test(ext)){
						/* Setting data */
						this.setData({
							'type' : 'float'
						});
						//$('#errorBox3').text('正在上传 ' + file +'...');	
					} else {
						alert('格式错误 : 只能上传图片类型 \n正确格式为: .png,请重新上传!');
						return false;				
					}		
				},
				onComplete : function(file,response){
					//服务器返回路径 
					var  tempVal =  eval(response);
					var isOk = tempVal.substring(0, 2);
					tempVal = tempVal.substring(2);
		
					if(isOk == "ok"){
						//文本显示
						$('#imgPath22').val(tempVal);
					}else{
						//错误显示
						alert(tempVal);
					}
				}
			});
			
			// 上传悬浮型-->图片+文字38*38
			new AjaxUpload('upload32', {
		           action: '<%=basePath%>ctrl/uploadImage3.ad',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
					if (ext && /^(png|PNG)$/.test(ext)){
						/* Setting data */
						this.setData({
							'type' : 'float'
						});
						//$('#errorBox3').text('正在上传 ' + file +'...');	
					} else {
						alert('格式错误 : 只能上传图片类型 \n正确格式为: .png,请重新上传!');
						return false;				
					}		
				},
				onComplete : function(file,response){
					//服务器返回路径 
					var  tempVal =  eval(response);
					var isOk = tempVal.substring(0, 2);
					tempVal = tempVal.substring(2);
		
					if(isOk == "ok"){
						//文本显示
						$('#imgPath32').val(tempVal);
					}else{
						//错误显示
						alert(tempVal);
					}
				}
			});
			
			// 上传插屏型--> 图片480*800 或 视频（分辨率，格式待定）
			new AjaxUpload('uploadInsert', {
		           action: '<%=basePath%>ctrl/uploadInsert.ad',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
				    if ($("#insertRadio1").attr('checked')) {
				    	if (ext && /^(jpg|png|jpeg|gif|JPG|PNG|JPEG|GIF)$/.test(ext)){
							/* Setting data */
							this.setData({
								//'pixel' : "320x50,216x36,168x28,120x20"
								'fileType' : ext,
								'type' : 'image'
							});
							//$('#errorBox3').text('正在上传 ' + file +'...');	
						} else {
							alert('格式错误 : 只能上传图片类型 \n正确格式为: .jpg .png .jpeg .gif,请重新上传!');
							return false;				
						}
				    } else {
				    	if (ext && /^(3gp|3GP)$/.test(ext)){
							/* Setting data */
							this.setData({
								//'pixel' : "320x50,216x36,168x28,120x20"
								'fileType' : ext,
								'type' : 'view'
							});
							//$('#errorBox3').text('正在上传 ' + file +'...');	
						} else {
							alert('格式错误 : 只能上传图片类型 \n正确格式为: .3gp,请重新上传!');
							return false;				
						}
				    }
				},
				onComplete : function(file,response){
					//服务器返回路径 
					var  tempVal =  eval(response);
					var isOk = tempVal.substring(0, 2);
					tempVal = tempVal.substring(2);
		
					if(isOk == "ok"){
						//文本显示
						$('#uploadInsertPath').val(tempVal);
					}else{
						//错误显示
						alert(tempVal);
					}
				}
			});
			
			if("${member.id}"==""){
					$("#loginHref2").hide();
					$("#loginHref3").hide();
				}else if("${member.id}"!=""){
					$("#loginHref1").hide();
					$("#loginHref4").hide();
			}
			
			$("#commit").click(function(){
				if (!$("#checkBanner").attr('checked')&&!$("#checkInsert").attr('checked')&&!$("#checkFloat").attr('checked')) {
					alert("请至少选择一种展现方式！");
					return;
				}
				if ($("#checkBanner").attr('checked')) {
					var clickUrl = $("#showSrc").val();
					if (clickUrl == null || clickUrl == "") {
						alert('请输入点击后跳转的地址!');
						return;
					}
					var clickshowtype = null;
					$("input[name='clickshowtype']").each(function(){
						if ($(this).attr('checked')) {
							clickshowtype = $(this).val();
						}
					});
					if (clickshowtype=='发送短信'||clickshowtype=='发送邮件'||clickshowtype=='播放音乐') {
						if ($("#showContent").val() == null || $("#showContent").val() == "") {
							alert('请输入'+$('#lab2').text().substring(0,$('#lab2').text().length-1)+'!');
							return;
						}
						if ($("#showContent").val().indexOf('¿') != -1 || $("#showContent").val().indexOf('︴') != -1) {
							alert('您输入的'+$('#lab2').text().substring(0,$('#lab2').text().length-1)+'包含非法符号"¿","︴"!');
							return;
						}
					}
					if (clickshowtype=='显示GoogleMap地址') {
						if ($("#lat").val() == null || $("#lat").val() == "") {
							alert('请点击地图，设定您要推荐的位置！');
							return;
						}
					}
					if ($("#onlyWord").attr("checked")) {
						if ($("#title1").val() == null || $("#title1").val() == "") {
							alert('请输入广告标题!');
							return;
						}
						if ($("#title1").val() != null && $("#title1").val().length > 15) {
							alert('标题最多15个字符!');
							return;
						}
					}
					if ($("#onlyImg").attr("checked")) {
						if ($("#imgPath2").val() == null || $("#imgPath2").val() == "") {
							alert('请输入图片路径!');
							return;
						}
					}
					if ($("#wordAndImg").attr("checked")) {
						if ($("#title3").val() == null || $("#title3").val() == "") {
							alert('请输入广告标题!');
							return;
						}
						if ($("#title3").val() != null && $("#title3").val().length > 15) {
							alert('标题最多15个字符!');
							return;
						}
						if ($("#imgPath3").val() == null || $("#imgPath3").val() == "") {
							alert('请输入图片路径!');
							return;
						}
					}
					if ($("#price").val() == null || $("#price").val() == "") {
						alert('请输入出价!');
						return;
					} else {
						if(!/^[0-9]+.?[0-9]*$/.test($("#price").val())){
							alert('请输入正确的金额！');
							return;
						}
						if ($("#price").val().indexOf('.') != -1 && $("#price").val().length-$("#price").val().indexOf('.')>2) {
							alert('请精确到小数点后1位!');
							return;
						}
						if ($("input[name='paymode'][checked='true']").val()=='按印象付费' && $("#price").val() < 5) {
							alert('竞价5元起!');
							return;
						}
						if ($("input[name='paymode'][checked='true']").val()=='按单次点击付费' && $("#price").val() < 0.5) {
							alert('竞价0.5元起!');
							return;
						}
					}
				}
				if ($("#checkInsert").attr('checked')) {
					if ($("#uploadInsertPath").val() == null || $("#uploadInsertPath").val() == "") {
						alert('请输入上传内容路径!');
						return;
					}
					if ($("#price2").val() == null || $("#price2").val() == "") {
						alert('请输入出价!');
						return;
					} else {
						if(!/^[0-9]+.?[0-9]*$/.test($("#price2").val())){
							alert('请输入正确的金额！');
							return;
						}
						if ($("#price2").val().indexOf('.') != -1 && $("#price2").val().length-$("#price2").val().indexOf('.')>2) {
							alert('请精确到小数点后1位!');
							return;
						}
						if ($("#price2").val() < 0.5) {
							alert('竞价0.5元起!');
							return;
						}
					}
				}
				if ($("#checkFloat").attr('checked')) {
					var clickUrl = $("#showSrc2").val();
					if (clickUrl == null || clickUrl == "") {
						alert('请输入'+$('#linkDiv2').text().substring(0,$('#linkDiv2').text().length-1)+'!');
						return;
					}
					var clickshowtype = null;
					$("input[name='clickshowtype2']").each(function(){
						if ($(this).attr('checked')) {
							clickshowtype = $(this).val();
						}
					});
					if (clickshowtype=='发送短信'||clickshowtype=='发送邮件'||clickshowtype=='播放音乐') {
						if ($("#showContent2").val() == null || $("#showContent2").val() == "") {
							alert('请输入'+$('#lab22').text().substring(0,$('#lab22').text().length-1)+'!');
							return;
						}
						if ($("#showContent2").val().indexOf('¿') != -1 || $("#showContent2").val().indexOf('︴') != -1) {
							alert('您输入的'+$('#lab22').text().substring(0,$('#lab22').text().length-1)+'包含非法符号"¿","︴"!');
							return;
						}
					}
					if (clickshowtype=='显示GoogleMap地址') {
						if ($("#lat2").val() == null || $("#lat2").val() == "") {
							alert('请点击地图，设定您要推荐的位置！');
							return;
						}
					}
					if ($("#onlyImg2").attr("checked")) {
						if ($("#imgPath22").val() == null || $("#imgPath22").val() == "") {
							alert('请输入图片路径!');
							return;
						}
					}
					if ($("#wordAndImg2").attr("checked")) {
						if ($("#title32").val() == null || $("#title32").val() == "") {
							alert('请输入广告标题!');
							return;
						}
						if ($("#title32").val() != null && $("#title32").val().length > 15) {
							alert('标题最多15个字符!');
							return;
						}
						if ($("#imgPath32").val() == null || $("#imgPath32").val() == "") {
							alert('请输入图片路径!');
							return;
						}
					}
					if ($("textarea").text() == null || $("textarea").text() == "") {
						alert('请输入广告详细描述!');
						return;
					}
					if ($("#price3").val() == null || $("#price3").val() == "") {
						alert('请输入出价!');
						return;
					} else {
						if(!/^[0-9]+.?[0-9]*$/.test($("#price3").val())){
							alert('请输入正确的金额！');
							return;
						}
						if ($("#price3").val().indexOf('.') != -1 && $("#price3").val().length-$("#price3").val().indexOf('.')>2) {
							alert('请精确到小数点后1位!');
							return;
						}
						if ($("#price3").val() < 0.3) {
							alert('竞价0.3元起!');
							return;
						}
					}
				}
				$("#showTypeForm").submit();
			});
			
			$("#checkBanner").click(function(){
				if($("#checkBanner").attr("checked")){
					$("#banner").animate({height:"1000"},800,function(){$("#banner").show();});
					$("#banner").show();
				}else{
					$("#banner").animate({height:"0"},800,function(){$("#banner").hide();});
				}
			});
			
			$("#checkInsert").click(function(){
				if($("#checkInsert").attr("checked")){
					$("#insert").animate({height:"500"},800,function(){$("#insert").show();});
					$("#insert").show();
				}else{
					$("#insert").animate({height:"0"},800,function(){$("#insert").hide();});
				}
			});
			
			$("#checkFloat").click(function(){
				if($("#checkFloat").attr("checked")){
					$("#float").animate({height:"1250"},800,function(){});
					$("#float").show();
				}else{
					$("#float").animate({height:"0"},800,function(){$("#float").hide();});
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
						} else {
							out.println("$('#showSrc').val('"+ast.getClick_url()+"');");
						}
						if ("发送短信".equals(ast.getClick_effect()) || "发送邮件".equals(ast.getClick_effect())
							|| "播放音乐".equals(ast.getClick_effect())) {
							String[] clickInfos = ast.getClick_url().split("¿");
							out.println("$('#showContent').val('"+clickInfos[1]+"');");
						}
						if ("显示GoogleMap地址".equals(ast.getClick_effect())) {
							String[] clickInfos = ast.getClick_url().split(":");
							out.println("$('#lat').val('"+clickInfos[1]+"');");
							out.println("$('#lng').val('"+clickInfos[2]+"');");
						}
						out.println("$(\"input[name='imgWordType'][value='"+showTypeNames[1]+"']\").trigger('click');");
						if ("显示文字＋图片".equals(showTypeNames[1])) {
							out.println("$('#title3').val('"+ast.getTitle()+"');");
							out.println("$('#imgPath3').val('"+ast.getAd_info_url()+"');");
						} else if ("仅显示图片".equals(showTypeNames[1])) {
							out.println("$('#imgPath2').val('"+ast.getAd_info_url()+"');");
						}
						out.println("$(\"input[name='paymode'][value='"+ast.getPay_mode()+"']\").trigger('click');");
						out.println("$('#price').val('"+ast.getPrice()+"');");
					} else if ("插屏型".equals(showTypeNames[0])) {
						out.println("$('#insert').show();");
						out.println("$('#checkInsert').attr('checked',true);");
						out.println("$(\"input[name='contenttype'][value='"+showTypeNames[1]+"']\").trigger('click');");
						out.println("$('#uploadInsertPath').val('"+ast.getAd_info_url()+"');");
						out.println("$('#price2').val('"+ast.getPrice()+"');");
					} else if ("悬浮型".equals(showTypeNames[0])) {
						out.println("$('#float').show();");
						out.println("$('#checkFloat').attr('checked',true);");
						out.println("$(\"input[name='clickshowtype2'][value='"+ast.getClick_effect()+"']\").trigger('click');");
						String[] clickInfos = ast.getClick_url().split("¿");
						out.println("$('#showSrc2').val('"+clickInfos[0]+"');");
						if ("发送短信".equals(ast.getClick_effect()) || "发送邮件".equals(ast.getClick_effect())
							|| "播放音乐".equals(ast.getClick_effect())) {
							out.println("$('#showContent2').val('"+clickInfos[1]+"');");
						}
						if ("显示GoogleMap地址".equals(ast.getClick_effect())) {
							String[] clickInfos2 = ast.getClick_url().split(":");
							out.println("$('#lat2').val('"+clickInfos2[1]+"');");
							out.println("$('#lng2').val('"+clickInfos2[2]+"');");
						}
						out.println("$(\"input[name='imgWordType2'][value='"+showTypeNames[1]+"']\").trigger('click');");
						if ("显示文字＋图片".equals(showTypeNames[1])) {
							out.println("$('#title32').val('"+ast.getTitle()+"');");
							out.println("$('#imgPath32').val('"+ast.getAd_info_url()+"');");
						} else if ("仅显示图片".equals(showTypeNames[1])) {
							out.println("$('#imgPath22').val('"+ast.getAd_info_url()+"');");
						}
						out.println("$(\"input[name='paymode2'][value='"+ast.getPay_mode()+"']\").trigger('click');");
						out.println("$('textarea').text('"+ast.getAd_detail()+"');");
						out.println("$('#price3').val('"+ast.getPrice()+"');");
					}
				}
			}
			%>
			openBaiduMap();
			openBaiduMap2();
		});
		
		function changeInfo (which,info,lab,msgDiv,linkDiv,showSrc,showContent,lab2,mapAddress) {
			if ($(which).val() == '手机网络') {
				$("#"+info).text('当用户点击此广告链接时,将会跳转到新的页面');
				$("#"+lab).text('手机网络');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).text("跳转地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == 'Android程序') {
				$("#"+info).text('当用户点击此广告链接时,将会链接到程序下载页面');
				$("#"+lab).text('Android程序');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).text("程序下载地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == 'OPhone程序') {
				$("#"+info).text('当用户点击此广告链接时,将会链接到程序下载页面');
				$("#"+lab).text('OPhone程序');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).text("程序下载地址:");
				$("#"+showSrc).val("http://");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '点击通话') {
				$("#"+info).text('当用户点击此广告链接时,将会立即拨打我们设置的电话号码');
				$("#"+lab).text('点击通话');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).text("设置电话号码:（如：01066668888）");
				$("#"+showSrc).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '发送短信') {
				$("#"+info).text('当用户点击此广告链接时,将会调用短信发送程序');
				$("#"+lab).text('发送短信');
				$("#"+msgDiv).show();
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
				$("#"+linkDiv).text("收信人邮箱地址:");
				$("#"+lab2).text("邮件内容:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).hide();
				$("#"+showContent).show();
			} else if ($(which).val() == '显示GoogleMap地址') {
				$("#"+info).text('当用户点击此广告链接时,将会显示您设定的地址');
				$("#"+lab).text('显示地址');
				$("#"+msgDiv).show();
				$("#"+linkDiv).text("显示名称:");
				$("#"+lab2).text("设定地址:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+mapAddress).show();
				$("#"+showContent).hide();
			} else if ($(which).val() == '播放音乐') {
				$("#"+info).text('当用户点击此广告链接时,将会播放您设定的音乐。注：只有用户连接Wifi的条件下才能看到此广告!');
				$("#"+lab).text('播放音乐');
				$("#"+msgDiv).show();
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
				$("#"+linkDiv).text("图片地址:");
				$("#"+showSrc).val("");
				$("#"+showContent).val("");
				$("#"+showContent).show();
			} else if ($(which).val() == '视频动画') {
				$("#"+info).text('当用户点击此广告链接时,将会播放您设定的视频。注：只有用户连接Wifi的条件下才能看到此广告!');
				$("#"+lab).text('视频动画');
				$("#"+msgDiv).hide();
				$("#"+linkDiv).text("视频地址:");
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
					var infoWindow = new BMap.InfoWindow($("#showSrc").val(), opts2);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				}
				
				//点击获取坐标
				function onclicked(e){ 
				   // alert(e.point.lng + ", " + e.point.lat); 
				    map.clearOverlays();
				    if ($("#showSrc").val() == null || $("#showSrc").val() == "") {
				    	alert("请填写显示名称！");
				    	return;
				    }
					var point = new BMap.Point(e.point.lng,  e.point.lat); 
					$("#lng").val(e.point.lng);
					$("#lat").val(e.point.lat);
					$("#chooseCoords").text("经度:"+e.point.lng+" 纬度:"+e.point.lat);
					var marker = new BMap.Marker(point);
					var opts = {
					  width : 250,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "地点名称"  // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow($("#showSrc").val(), opts);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				 } 
				map.addEventListener("click", onclicked);
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
					var infoWindow = new BMap.InfoWindow($("#showSrc2").val(), opts2);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				}
				
				//点击获取坐标
				function onclicked(e){ 
				   // alert(e.point.lng + ", " + e.point.lat); 
				    map.clearOverlays();
				    if ($("#showSrc2").val() == null || $("#showSrc2").val() == "") {
				    	alert("请填写显示名称！");
				    	return;
				    }
					var point = new BMap.Point(e.point.lng,  e.point.lat); 
					$("#lng2").val(e.point.lng);
					$("#lat2").val(e.point.lat);
					$("#chooseCoords2").text("经度:"+e.point.lng+" 纬度:"+e.point.lat);
					var marker = new BMap.Marker(point);
					var opts = {
					  width : 250,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "地点名称"  // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow($("#showSrc2").val(), opts);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point);      // 打开信息窗口
					map.addOverlay(marker);
				 } 
				map.addEventListener("click", onclicked);
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
		    	<h2 style="margin-top:20px;margin-left:20px">广告发布——设置展现方式</h2>
		    	<img style="margin-left:20px" src="<%=basePath%>images/newad_step3.png" width="660">
		    	<form id="showTypeForm" action="<%=basePath%>ctrl/saveAdShowType.ad" method="post"><!--  enctype="multipart/form-data" -->
		    	
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
		    			<div style="margin-left:20px"><input name="clickshowtype" value="显示GoogleMap地址" type="radio">显示地址</div>
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
		    			<div style="margin-left:40px"><input id="showSrc" name="showSrc" value="http://" type="text" style="width:400px"></div>
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
			    				<input id="imgPath2" name="imgPath2" value="" type="text" readonly="readonly" style="width:250px">
			    				<button id="upload2">上传</button>（尺寸：320x50）
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
			    				<input id="imgPath3" name="imgPath3" value="" type="text" readonly="readonly" style="width:250px">
			    				<button id="upload3">上传</button>（尺寸：38x38）
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
		    				<input id="uploadInsertPath" name="uploadInsertPath" value="" type="text" readonly="readonly" style="width:250px">
		    				<button id="uploadInsert">上传</button><label id="insertSize">（尺寸：480x800）</label>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">付费模式：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<input name="paymode_Insert" checked="checked" value="按印象付费" type="radio">按印象付费（每1次印象付费金额）
		    			</div>
		    			<div style="margin:20px">
		    				出价：<input id="price2" name="price2" value="" type="text">元 <label id="lab32">（0.5元起）</label>
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
		    			<div style="margin-left:20px"><input name="clickshowtype2" value="显示GoogleMap地址" type="radio">显示地址</div>
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
		    			<div style="margin-left:40px"><input id="showSrc2" name="showSrc2" value="http://" type="text" style="width:400px"></div>
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
			    				<input id="imgPath22" name="imgPath22" value="" type="text" readonly="readonly" style="width:250px">
			    				<button id="upload22">上传</button>（尺寸：320x50）
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
			    				<input id="imgPath32" name="imgPath32" value="" type="text" readonly="readonly" style="width:250px">
			    				<button id="upload32">上传</button>（尺寸：38x38）
			    			</div>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">广告详细描述：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<textarea cols="50" rows="6" name="addetail"></textarea>
		    			</div>
		    		</div>
		    		<div class="borderDark">
		    			<label style="color:blue;font-weight:bold">付费模式：</label>
		    			<div style="margin-left:20px;margin-top:10px">
		    				<input name="paymodeFloat" checked="checked" value="按印象付费" type="radio">按点击付费
		    			</div>
		    			<div style="margin:20px">
		    				出价：<input id="price3" name="price3" value="" type="text">元 <label id="lab33">（0.3元起）</label>
		    			</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				注意：<label id="waring" style="color:red">
		    						当对广告投放目标进行较多的限定时，建议广告出价金额设定高一些，否则可以能影响广告投放效果！
		    					 </label>
		    			</div>
		    		</div>
		    	</div>
		    	<div style="margin-left:20px;padding-top:20px;padding-left:20px;width:640px;">
	    			<button type="button" id="commit">完成</button>
	    		</div>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
