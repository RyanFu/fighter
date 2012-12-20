<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
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
    <title>安捷乐广告平台</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,400]});
			
			
			$("#finanOpenB").focusout(function(){
				var finanOpenB=$("#finanOpenB").val();
				if(finanOpenB==""){
					alert('请输入开户银行！');
				}
			});
			$("#finanAccountN").focusout(function(){
				var finanAccountN=$("#finanAccountN").val();
				if(finanAccountN==""){
					alert('请输入账号！');
				}else if(!/^[0-9]+.?[0-9]*$/.test(finanAccountN)){
					alert('请输入正确的账号！');
				}
			});
			$("#finanOpenName").focusout(function(){
			    var finanOpenName=$("#finanOpenName").val();
			    if(finanOpenName==""){
			    	alert('请输入开户名称！');
			    }
				
			});
			$("#finanAddressB").focusout(function(){
			    var finanAddressB=$("#finanAddressB").val();
			    if(finanAddressB==""){
			    	alert('请输入邮寄地址！');
			    }
				
			});
			
			$("#finanSubmit").click(function(){
			var finanOpenB=$("#finanOpenB").val();
			var finanAccountN=$("#finanAccountN").val();
		    var finanOpenName=$("#finanOpenName").val();
		    var finanAddressB=$("#finanAddressB").val();
		    
		    if(finanOpenB==""){
				alert('请输入开户银行！');
			}else if(finanAccountN==""){
				alert('请输入账号！');
			}else if(!/^[0-9]+.?[0-9]*$/.test(finanAccountN)){
				alert('请输入正确的账号！');
			}else if(finanOpenName==""){
			    alert('请输入开户名称！');
			}else if(finanAddressB==""){
			    alert('请输入邮寄地址！');
			}else {
			    $("#finanForm").submit();
			}});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			$("#goback").click(function(){
				window.location.href='<%=basePath%>ctrl/rechargeAccountInfoShow.man';
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
		    	<div id="aa" class="easyui-accordion" style="" selected="true">
					<div title="用户管理" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="#">&gt;最近登录时间</a></div>
						<div style="padding:5px"><a href="#">&gt;账户类型</a></div>
						<div style="padding:5px"><a href="#">&gt;账户管理</a></div>
					</div>
					<div title="财务管理" iconCls="icon-remove" selected="true"  style="padding:10px;">
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
		    <div style="float:left;margin-top:80px;margin-left:20px;width:700px;background:white;border-style:solid;border-width:1px;border-color:gray;font-family:Arial, Helvetica, sans-serif;font-size:12px">
		    	<form action="<%=basePath%>ctrl/rechargeAccountInfoSave.man" method="post" id="finanForm">
			    <table id="ad" align="center" style="margin-top:80px" id="finanForm">
			    	
			    	
			    	
			    	
			    	
			    	<tr>
			    		<td>开户银行：</td>
			    		<td><input type="text" name="bank_name" id="finanOpenB" ></td>
			    	</tr>
			    	<tr>
			    		<td>账号：</td>
			    		<td><input type="text" name="bank_num" id="finanAccountN" ></td>
			    	</tr>
			    	<tr>
			    		<td>开户名称：</td>
			    		<td><input type="text" name="open_name" id="finanOpenName"></td>
			    	</tr>
			    	<tr>
			    		<td>银行地址：</td>
			    		<td><input type="text" name="bank_address" id="finanAddressB" ></td>
			    	</tr>
			    	
			    </table>
			         <tr>
			    		<td>&nbsp;</td>
			    		<td><button type="button" id="finanSubmit" style="margin-left:285px;margin-top:20px;margin-bottom:20px">提交</button><button type="button" id="goback" style="margin-left:20px;margin-top:20px;margin-bottom:20px">返回</button></td>
			    	</tr>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
