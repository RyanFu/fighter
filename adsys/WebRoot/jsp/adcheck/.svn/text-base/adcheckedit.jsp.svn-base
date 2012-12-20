<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("admanage")) {
		basePath += path.substring(1) + "/";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>广告审批——审核</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/page/pagination.css"/>
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/page/jquery.pagination.js"></script>
	
	<script type="text/javascript">
		var data;
		$(function(){
		
		
			//data = eval('('+$("#adListString").val()+')');
			$("#ad").builderFormTable({tdWidth:[200,300]});
			$("#adtbl").builderDataTable();
			$("#cashoutSubmit").click(function(){
			   	$("#cashoutForm").submit();
			});
			$("#cashoutBack").click(function(){
				window.location.href='<%=basePath%>ctrl/adCheckShow.man';
			});
			
			if("${img_word_type}"=="仅显示图片"){
				$("#title").hide();
			}else if("${img_word_type}"=="仅广告文字"){
				$("#img").hide();
			}
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			var hideType = $("#hideType").val();
			if (hideType == "待审批") {
				$("#op1").attr("selected", "selected");
			} else if (hideType == "通   过") {
				$("#op2").attr("selected", "selected");
			} else if (hideType == "未通过") {
				$("#op3").attr("selected", "selected");
			}
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
		});
		
	
	</script>
  </head>
  
  <body>
  	<div id="head">
  		<div id="head_center">
  			<div id="logo">
  			<a href="<%=basePath%>"><img src="<%=basePath%>/styles/images/logo.png" /></a>
			</div>
  		</div>
  		<div id="navigation">
			<div class="left"></div>
			<ul>
				<li class="selected">
					<a href="<%=basePath%>index.jsp">&nbsp;首&nbsp;页&nbsp;</a>
				</li>
				<li >
					<a href="#">解决方案</a>
				</li>
				<li >
					<a href="#">市场分析</a>
				</li>
				<li >
					<a href="#">新闻中心</a>
				</li>
				<li>
					<a href="#">下载中心</a>
				</li>
				<li >
					<a href="#">会员中心</a>
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
		</div>
		<div id="content">
			<div style="width:150px;float:left;padding-top:80px">
		    	<div id="aa" class="easyui-accordion" style="">
					<div title="用户管理" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="#">&gt;最近登录时间</a></div>
						<div style="padding:5px"><a href="#">&gt;账户类型</a></div>
						<div style="padding:5px"><a href="#">&gt;账户管理</a></div>
					</div>
					<div title="财务管理" iconCls="icon-remove" selected="true" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechargeInfoShow.man">&gt;充值记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/paypalInfoShow.man">&gt;提现记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechargeAccountInfoShow.man">&gt;公司银行账户</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/adTypePriceShow.man">&gt;广告收费方式</a></div>
					</div>
					<div title="审批" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>ctrl/adCheckShow.man">&gt;广告审批</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/appCheckInfoShow.man">&gt;程序审批</a></div>
					</div>
				</div>
		    </div>
		    <div style="float:left;margin-top:60px;margin-left:20px;width:700px;background:white;
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px;font-family:Arial, Helvetica, sans-serif;font-size:12px">
            
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">广告审核</h2>
		    	<input id="hideType" name="hideType" type="hidden" value="${state}">
		    	<form id="cashoutForm" action="<%=basePath%>ctrl/adCheckUpdate.man" method="post">
		    	 	<input type="hidden" name="adIdEdit" value=${adId}>
		    	 	<table id="ad" align="center" style="margin-top:20px">
		              <tr>
		              		<td>广告名称：</td>
		              		<td><span class="notice">${name}</span></td>
		              </tr>
		              <tr>
		              		<td>广告类型：</td>
		              		<td><span class="notice">${type}</span></td>
		              </tr>
		              <tr>
		              		<td>展现方式：</td>
		              		<td><span class="notice">${show_type}</span></td>
		              </tr>
		              <tr>
		              		<td>点击后地址：</td>
		              		<td><a href="${show_src}" target="_blank">${show_src}</a></td>
		              </tr>
		              <tr>
		              		<td>图文组合方式：</td>
		              		<td><span class="notice">${img_word_type}</span></td>
		              </tr>
		              <tr id="title">
		              		<td>广告词：</td>
		              		<td><span class="notice">${title}</span></td>
		              </tr>
		              <tr id="img">
		              		<td>图片：</td>
		              		<td><img id="authCodeImg" style="margin-left:10px;margin-bottom:-5px" src="http://192.168.0.119:8080/adsys/files/ad/${img_path}"/></td>
		              </tr>
		              <tr>
		              		<td>付费模式：</td>
		              		<td><span class="notice">${pay_mode}</span></td>
		              </tr>
		              <tr>
		              		<td>&nbsp;</td>
		              		<td>
		              		<div>
		              			<object align=middle classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" class=OBJECT id=MediaPlayer width=400 height=400> 
									<param name=ShowStatusBar value=0> 
									<param name="AUTOSTART" value="false"/>
									<param name=Filename value="C:\Users\dev\Desktop\asdf.3gp"> 
									<embed type=application/x-oleobject codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" > 
									</embed> 
								</object>
							</div>
							</td>
		              </tr>
		               <tr>
		              		<td>处理：</td>
		              		<td width="1">
			    				<select  name="state" id="state">
			    					
	    							<option id="op1" value="待审批">待审批</option>
	    							<option id="op2" value="运行中">通&nbsp;&nbsp;&nbsp;过</option>
	    							<option id="op3" value="未通过">未通过</option>
			    				</select>
			    			</td>
		              </tr>
		              
		         	</table>
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
		    			<div style="margin-left:40px"><input id="showSrc" name="showSrc" value="http://" type="text" style="width:400px"></div>
		    			<div id="msgDiv">
		    			<div style="margin-left:20px;margin-top:20px"><label id="lab2">短信内容:</label></div>
		    			<div style="margin-left:40px"><input id="showContent" name="msgContent" value="" type="text" style="width:400px"></div>
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
		    			<div style="margin-left:40px"><input id="showSrc2" name="showSrc2" value="http://" type="text" style="width:400px"></div>
		    			<div id="msgDiv2">
		    			<div style="margin-left:20px;margin-top:20px"><label id="lab22">短信内容:</label></div>
		    			<div style="margin-left:40px"><input id="showContent2" name="msgContent2" value="" type="text" style="width:400px"></div>
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
		    				出价：<input id="price3" name="price3" value="" type="text">元 <label id="lab32">（0.3元起）</label>
		    			</div>
		    			<div style="margin-left:20px;margin-top:20px">
		    				注意：<label id="waring" style="color:red">
		    						当对广告投放目标进行较多的限定时，建议广告出价金额设定高一些，否则可以能影响广告投放效果！
		    					 </label>
		    			</div>
		    		</div>
		    	</div>
		    	</form>
		    	<div style="margin-left:300px;margin-top:30px;margin-bottom:20px">
		    	<button style="button"  id="cashoutSubmit"/>保存</button>
		    	<button style="button" id="cashoutBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
