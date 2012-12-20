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
    <title>财务管理——充值</title>
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
			$("#ad").builderFormTable({tdWidth:[200,400]});
			$("#adtbl").builderDataTable();
			
			$("#rechargeSubmit").click(function(){
				var state=$("#state").val();
				var money=$("#realmoney").val();
				var state=$("#state").val();
				var realmany=$("#realmoney").val();
				var recharge_count=$("#recharge_count").text();
				if(!/^[0-9]+.?[0-9]*$/.test(money)){
					alert("请输入数字！");
				}else if(state==2&&realmany!=0.0){
					alert('未通过状态，实际金额应为 0.0！');
				}else if(parseFloat(realmany)>parseFloat(recharge_count)){
					alert('实际金额应该小于或等于汇入金额');
				} else if(state==3&&realmany==0.0){
					alert('金额为0，请重新核查');
				}else {
					$("#rechargeForm").submit();
				}
			   	
			});
			$("#rechargeBack").click(function(){
				window.location.href='<%=basePath%>ctrl/rechargeInfoShow.man';
			});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			var hideType = $("#hideType").val();
			if (hideType == "2") {
				$("#op2").attr("selected", "selected");
			} else if (hideType == "3") {
				$("#op3").attr("selected", "selected");
			}
			$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/adBalance.man?rechAdId="+${rechId},
					success : function(data){
						
						$("#admemany").text(data);
						
					}
			});
			//!/^[0-9]+.?[0-9]*$/.test(cur_money)
			$("#state").change(function(){
				var state=$("#state").val();
				if(state==2){
					$("#realmoney").val("0.0");
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
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px;font-family:Arial, Helvetica, sans-serif;font-size:12px">
             <div class="conter_c"><div class="balance">  广告帐户余额：<span id="admemany"></span>元</div></div>
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">账户充值</h2>
		    	
		    	<form id="rechargeForm" action="<%=basePath%>ctrl/rechargeInfoUpdate.man" method="post">
		    			<!-- 隐藏域 -->
		               <input type="hidden" name="rechIdEdit"  value=${rechId}>
		               <input id="hideType" name="hideType" type="hidden" value="${state}">
		               <table id="ad" align="center" style="margin-top:50px">
		               <tr>
		               		<td>用户账号：</td>
		               		<td><span class="notice">${email}</span></td>
		               </tr>
		               <tr>
		               		<td>用户姓名：</td>
		               		<td><span class="notice">${name}</span></td>
		               </tr>
		               <tr>
		               		<td>订单号：</td>
		               		<td><span class="notice">${ord_number}</span></td>
		               </tr>
		               <tr>
		               		<td>汇入金额：</td>
		               		<td><span class="notice" id="recharge_count">${recharge_count}</span></td>
		               </tr>
		               <tr>
		               		<td>实际金额：</td>
		               		<td><input type="text" id="realmoney" name="real_count" value="${real_count}"></td>
		               </tr>
		               
		               
		               
		               <tr>
		               		<td>操作：</td>
			    	   		<td>
			    				<select name="state" id="state">
	    							<option id="op2" value="2">未通过</option>
	    							<option id="op3" value="3">已成功</option>
			    				</select>
			    			</td>
			    	   </tr>
			    	   <tr>
		               		<td>时间：</td>
		               		<td><span class="notice">${recharge_time}</span></td>
		               </tr>
		               <tr>
		               		<td>充值账号：</td>
		               		<td><span class="notice">${bank}</span></td>
		               </tr>
		               </table>
		    	</form>
		    	<div style="margin-left:150px">
		    	<button style="button" id="rechargeSubmit"/>保存</button>
		    	<button style="button" id="rechargeBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
