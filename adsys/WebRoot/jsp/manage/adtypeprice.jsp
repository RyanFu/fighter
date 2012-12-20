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
    <title>财务管理——广告收费方式</title>
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
			$("#adtbl").builderDataTable();
			$("#cashoutSubmit").click(function(){
				var ad_type=$("#ad_type").val();
				var cur_money=$("#cur_money").val();
				
				 
				
				if(ad_type==""){
					alert('请输入广告收费方式！');
				}else if(cur_money==""){
					alert('请输入金额！');
				}else if(!/^[0-9]+.?[0-9]*$/.test(cur_money)) {
					alert('请输入正确的金额！');
				}else {
					$("#cashoutForm").submit();
				}
				
			   
			});
			$("#cashoutBack").click(function(){
			window.location.href='<%=basePath%>ctrl/paypalInfoShow.man';
			});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			$("#ad_typestate").change(function(){
				var type=$("#ad_typestate").val();
				window.location.href='<%=basePath%>ctrl/adTypePriceShow.man?type='+type;
			});
			var hideType = $("#hidentype").val();
			if (hideType == "CPC") {
				$("#op1").attr("selected", "selected");
			} else if (hideType == "CPM") {
				$("#op2").attr("selected", "selected");
			}
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
				<a id="loginHref1" href="<%=basePath%>jsp/manage/login.jsp">登录</a>
				<a id="loginHref2" href="#">欢迎您${member.name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a id="loginHref3" href="<%=basePath%>ctrl/logout.man">安全退出</a>
				<a id="loginHref4" href="#">注册</a>
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
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px;;font-family:Arial, Helvetica, sans-serif;font-size:12px">
             
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">广告收费方式</h2>
		    	<form id="cashoutForm" action="<%=basePath%>ctrl/adTypePriceUpdate.man" method="post">
		               <input type="hidden" name="adtypeid" value="${id}">
		               <input type="hidden" name="paypalIdEdit" value=${paypalId}>
		               <input type="hidden" name="type" id="hidentype" value="${ad_type}">
		               <div class="borderDark">
		               <div>收费方式：
			    			<select name="ad_type" id="ad_typestate">
	    						<option id="op1" value="CPC">CPC</option>
	    						<option id="op2" value="CPM">CPM</option>
			    			</select>
			    		</div><br>
		               <div>价格：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="cur_money" name="cur_money" value="${cur_money}"><span class="notice">元</span></div><br>
		               <div>修改时间：<span class="notice">${change_time}</span></div>
		               </div>
		    	</form>
		    	<div style="margin-left:150px">
		    	<button style="button" id="cashoutSubmit"/>保存</button>
		    	
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
