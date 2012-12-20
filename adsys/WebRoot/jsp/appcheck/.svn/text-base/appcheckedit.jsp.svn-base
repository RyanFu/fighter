<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("admanage")) {
		basePath += path.substring(1) + "/";
	}
	String tomcatServerIp="http://ad.uangel.com.cn";//服务器ip地址
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
			
			var plat=$("#app_platform").val();
			if(plat=="01"){
				$("#plat").text("Android平台");
			}else if(plat=="02"){
				$("#plat").text("Ophone平台");
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
		    	<h2 style="margin-top:20px;margin-left:20px">程序审核</h2>
		    	<input id="hideType" name="hideType" type="hidden" value="${state}">
		    	<input id="type" name="type" type="hidden" value="${app_type}">
		    	<input id="app_platform" name="app_platform" type="hidden" value="${app_platform}">
		    	
		    	<form id="cashoutForm" action="<%=basePath%>ctrl/appCheckEditNext.man" method="post">
		    	 	<input type="hidden" name="appid" value=${appid}>
		    	 	<table id="ad" align="center" style="margin-top:20px">
		    	 	 	<tr>
		              		<td>应用下载路径</td>
		              		<td><a target="_blank" href="http://ad.uangel.com.cn:80/ctrl/downloadFile.ad?file=app/${apk_url}"><%=tomcatServerIp %>/files/app/${apk_url}</a></td>
		              </tr>
		              <tr>
		              		<td>程序名称</td>
		              		<td><span class="notice">${app_name}</span></td>
		              </tr>
		              <tr>
		              		<td>程序类别</td>
		              		<td><span id="apptype" class="notice"></span></td>
		              </tr>
		              <tr>
		              		<td>程序识别ID</td>
		              		<td><span class="notice">${pid}</span></td>
		              </tr>
		             
		              <tr>
		              		<td>创建时间</td>
		              		<td><span class="notice">${create_time}</span></td>
		              </tr>
		              <tr>
		              		<td>应用平台</td>
		              		<td><span id="plat" class="notice"></span></td>
		              </tr>
		              
		              
		         	</table>
		    	</form>
		    	<div style="margin-left:300px;margin-top:30px;margin-bottom:20px">
		    	<button style="button"  id="cashoutSubmit"/>下一步</button>
		    	<button style="button" id="cashoutBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
