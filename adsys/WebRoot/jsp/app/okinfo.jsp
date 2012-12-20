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
	<script type="text/javascript" src="<%=basePath%>scripts/ajaxupload.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#app").builderFormTable({tdWidth:[200,400]});
			$("#app2").builderFormTable({tdWidth:[200,400]});
			
			var appType = $("#appType").text();
			$("#appType").text(getAppType(appType));
			
			var appPlatform = $("#appPlatform").text();
			$("#appPlatform").text(getAppPlatform(appPlatform));
			
			$("#contiAdd").click(function() {
				window.location = "<%=basePath%>jsp/app/saveappinfo.jsp";
			});
			$("#uploadok").click(function(){
				var pakage_name = $("#namepakage").val();
				var apkname=$("#apkname").val();
				var pid=$("#pid").val();
				var uploading=$('#uploading').text();
				
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/checkPackageNameExist.app",
					dataType: "text",
					data : {pakage_name:pakage_name,pid:pid},
					success : function(data){
						var data = eval('('+data+')');
						var id=$("#id").val();
						if (data == 'true') {
							alert('已存在包名！');
							$("#namepakage").val("");
							return;
						}else {
							if(pakage_name==""){
								alert("包名不能为空");
							}else if(apkname=="") {
								alert("请上传应用");
							}else if(uploading!="") {
								alert("正在上传,请稍后... ");
							}else{
								if(id==""){
									$("#saveAppPathInfo").attr("action","<%=basePath%>ctrl/saveAppApkInfo.app");
									$("#saveAppPathInfo").submit();
								}else {
									$("#saveAppPathInfo").attr("action","<%=basePath%>ctrl/updateOkInfo.app");
									$("#saveAppPathInfo").submit();
								}
								
							}
						}
					}
				});
			});
			$("#namepakage").focusout(function(){
				var pakage_name = $("#namepakage").val();
				var pid=$("#pid").val();
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/checkPackageNameExist.app",
					dataType: "text",
					data : {pakage_name:pakage_name,pid:pid},
					success : function(data){
						var data = eval('('+data+')');
						if (data == 'true') {
							alert('已存在包名！');
							$("#namepakage").val("");
						}
					}
				});
			});
			
			new AjaxUpload('uploadapk', {
		           action: '<%=basePath%>ctrl/uploadApk.app',
		           autoSubmit: true,
		           name:'image',
				   onSubmit : function(file , ext,filename){
					if (ext && /^(apk|zip|APK|ZIP)$/.test(ext)){
						/* Setting data */
						this.setData({
							//'pixel' : "320x50,216x36,168x28,120x20"
							'fileType' : ext
						});
						$('#uploading').text('正在上传,请稍后... ');	
					} else {
						alert('格式错误 :  \n正确格式为: .apk .zip ,请重新上传!');
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
						$('#apkname').val(tempVal);
						$('#uploading').text("");
					}else{
						//错误显示
						alert(tempVal);
					}
				}
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
		
		function getAppPlatform(typeCode) {
			switch (typeCode) {
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
			<div class="Border" style="width:182px;float:left">
			<div>
	    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
	    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 164px;" class="center"></span>
	    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
	    	</div>
			<div style="width:182px;float:left">
		    	<div id="aa" class="easyui-accordion" style="">
					<div title="基本信息" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>jsp/member/accountInformation.jsp">&gt;账号信息</a></div>
						<div style="padding:5px"><a href="<%=basePath%>jsp/member/financialInformation.jsp">&gt;财务信息</a></div>
						<div style="padding:5px"><a href="<%=basePath%>jsp/member/changePassword.jsp">&gt;修改密码</a></div>
					</div>
					<div title="广告管理" iconCls="icon-remove"  style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>jsp/ad/saveadinfo.jsp">&gt;发布广告</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/adManage.ad">&gt;管理广告</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/setStatCondition.ad">&gt;广告统计</a></div>
					</div>
					<div title="程序管理" iconCls="icon-remove"  selected="true" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>jsp/app/saveappinfo.jsp">&gt;添加程序</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/showApps.app">&gt;管理程序</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/setStatCondition.app">&gt;程序统计</a></div>
					</div>
					<div title="下载中心" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="#">&gt;SDK更新下载</a></div>
					</div>
					<div title="财务管理" iconCls="icon-remove"  style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechInfoShow.pay">&gt;充值</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechargeInfoShow.pay">&gt;充值记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/paypalShow.pay">&gt;提现</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/paypalInfoShow.pay">&gt;提现记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/commisionInfoShow.pay">&gt;雇佣金发放</a></div>
					</div>
				</div>
		    </div>
		    </div>
		    
		    
		    <div class="Border" style="width:700px;font-family:Arial, Helvetica, sans-serif;font-size:12px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
				<form id="saveAppPathInfo" action="" method="post">
				<input id="id"  name="id" type="hidden" value="${id}">
				<input id="pid"  name="pid" type="hidden" value="${currentApp.pid}">
		    	<h2 style="margin-top:20px;margin-left:20px">添加程序——提取AppID</h2>
		    	<div class="borderDark" style="color:red" >
		    		
			    		<span style="margin-left:85px">AppID：</span>
			    		<span>${currentApp.pid}</span>
			    	
		    	</div>
			    <table id="app" align="center" style="margin:20px">
			    	<tr>
			    		<td>程序名称</td>
			    		<td id="appName">${currentApp.app_name}</td>
			    	</tr>
			    	<tr>
			    		<td>程序类别</td>
			    		<td id="appType">${currentApp.app_type}</td>
			    	</tr>
			    	<tr>
			    		<td>应用平台</td>
			    		<td id="appPlatform">${currentApp.app_platform}</td>
			    	</tr>
			    	<tr>
			    		<td>应用包名</td>
			    		<td><input id="namepakage" name="pakage_name" type="text" value="${currentApp.pakage_name}"></td>
			    	</tr>
			    	
			    	
			    </table> <br>
			     <table id="app2" align="center" style="margin:20px">
			    	
			    	<tr>
			    		<td ><b>上传apk</b></td>
			    		<td>&nbsp;</td>
			    		
			    	</tr>
			    	<tr>
			    		<td>选择文件:</td>
			    		<td><input id="apkname" name="apkname" type="text" readonly="readonly" value="${currentApp.apk_url}"><button id="uploadapk">选择文件</button><label id="uploading"></label></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button id="uploadok" type="button">确认</button><button id="contiAdd">返回列表</button></td>
			    	</tr>
			    </table>
			     </form>
		    </div>
		 
		</div>
  	</div>
  </body>
</html>
