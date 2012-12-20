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
    <title>程序审批——审核</title>
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
			$("#appcheckSubmit").click(function(){
				if($("#rad1").attr("checked")||$("#rad2").attr("checked")){
					$("#cashoutForm").submit();
				}else {
					alert("请选择审批结果");
					
				}
			   	
			});
			$("#cashoutBack").click(function(){
				window.location.href='<%=basePath%>ctrl/appCheckInfoShow.man';
			});
			
			
			
			var hideType = $("#hideType").val();
			if (hideType == "待审批") {
				$("#op1").attr("selected", "selected");
			} else if (hideType == "通   过") {
				$("#op2").attr("selected", "selected");
			} else if (hideType == "未通过") {
				$("#op3").attr("selected", "selected");
			}
			/**
			var type=$("#type").val();
			if(type=="01"){
              	 var audit ="多媒体软件";
            }else if(type=="02"){
              	 var audit ="主题桌面";
            }else if(type=="03"){
      			 var audit ="电子阅读";
            }else if(type=="04"){
              	 var audit ="实用工具";
            }else if(type=="05"){
              	 var audit ="网络通讯";
            }else if(type=="06"){
               	var audit ="安全软件";
            }else if(type=="07"){
                var audit ="系统软件";
            }else if(type=="08"){
              	var audit ="生活信息";
            }else if(type=="09"){
               	var audit ="娱乐休闲";
            }else if(type=="10"){
               	var audit ="新闻资讯";
            }else if(type=="11"){
               	var audit ="其他软件";
            }else if(type=="12"){
               	var audit ="动作格斗";
            }else if(type=="13"){
                var audit ="模拟经营";
            }else if(type=="14"){
              	var audit ="休闲益智";
            }else if(type=="15"){
               	var audit ="体育竞技";
            }else if(type=="16"){
               	var audit ="角色扮演";
            }else if(type=="17"){
                var audit ="其他游戏";
            }else {
            	var audit ="其他游戏";
            }
			$("#apptype").text(audit);
			**/
			
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
					<div title="财务管理" iconCls="icon-remove" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechargeInfoShow.man">&gt;充值记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/paypalInfoShow.man">&gt;提现记录</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/rechargeAccountInfoShow.man">&gt;公司银行账户</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/adTypePriceShow.man">&gt;广告收费方式</a></div>
					</div>
					<div title="审批" iconCls="icon-remove" selected="true" style="padding:10px;">
						<div style="padding:5px"><a href="<%=basePath%>ctrl/adCheckShow.man">&gt;广告审批</a></div>
						<div style="padding:5px"><a href="<%=basePath%>ctrl/appCheckInfoShow.man">&gt;程序审批</a></div>
					</div>
				</div>
		    </div>
		    <div style="float:left;margin-top:60px;margin-left:20px;width:700px;background:white;
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px;font-family:Arial, Helvetica, sans-serif;font-size:12px">
            
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">程序审核</h2>
		    	<input id="hideType" name="hideType" type="hidden" value="${state}">
		    	<input id="type" name="type" type="hidden" value="${app_type}">
		    	
		    	<form id="cashoutForm" action="<%=basePath%>ctrl/appCheckUpdate.man" method="post">
		    	 	<input type="hidden" name="id" value=${id}>
		    	 	<table id="ad" align="center" style="margin-top:20px">
		              <tr>
		              		<td>程序名称</td>
		              		<td><span class="notice">${app_name}</span></td>
		              </tr>
		              <tr>
		              		<td>程序包名</td>
		              		<td><span id="apptype" class="notice">${pakage_name }</span></td>
		              </tr>
		               <tr>
		              		<td><label style="color:blue;font:italic bold">选择主要用户群</label></td>
		              		<td>
		              		<div>
		    					<div style="margin-left:20px">
		    					<table border=0>
		    					<tr>
		    						<td width="200px"><input name="userCrowd" checked="checked" value="1" type="radio">高端商务人群</td>
		    						<td width="200px"><input name="userCrowd"  value="2" type="radio">白领及公务员人群</td>
		    						
		    					</tr>
		    					<tr>
		    						<td width="200px"><input name="userCrowd"  value="3" type="radio">时尚潮流人群</td>
		    						<td width="200px"><input name="userCrowd"  value="4" type="radio">普通工薪人群</td>
		    					</tr>
		    					</table>
		    					</div>
		    				</td>
		              </tr>
		              <tr>
		              		<td><label style="color:blue;font:italic bold">选择用户性别</label></td>
		              		<td>
		              		<div style="margin-left:20px">
		    					<table border=0>
		    						<tr>
		    							<td width="200px"><input name="gender" checked="checked" value="0" type="radio">全部</td>
		    							<td width="200px"><input name="gender" value="1" type="radio">男性为主</td>
		    							<td width="200px"><input name="gender" value="2" type="radio">女性为主</td>
		    						</tr>
		    					</table>
		    				</div>
		    				</td>
		             	</tr>
		             	<tr>
		             	<tr>
		             		<td><label style="color:black;font:italic bold">审批</label></td>
		             		<td>
		             			<input id="rad1" name="app_state" value="通过" type="radio">通过
		    					<input id="rad2" name="app_state" value="未完成" type="radio" style="margin-left:80px">未完成
		             		</td>
		             	</tr>
		         	</table>
		    	</form>
		    	<div style="margin-left:300px;margin-top:30px;margin-bottom:20px">
		    		<button style="button"  id="appcheckSubmit"/>保存</button>
		    		<button style="button" id="cashoutBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
