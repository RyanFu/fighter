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
    <title>审批——程序审批</title>
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
			data = eval('('+$("#appListString").val()+')');
			
			$("#adtbl").builderDataTable();
			// Create pagination element with options from form
			var optInit = getOptionsFromForm();
			
			$("#Pagination").pagination(data.length, optInit);
            
			// Event Handler for for button
			$("#setoptions").click(function(){
                var opt = getOptionsFromForm();
                // Re-create pagination content with new parameters
                $("#Pagination").pagination(data.length, opt);
            }); 
            
            pageselectCallback(0, null);
            if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			var hideType = $("#hideType").val();
			if (hideType == "1") {
				$("#op1").attr("selected", "selected");
			} else if (hideType == "2") {
				$("#op2").attr("selected", "selected");
			} else if (hideType == "3") {
				$("#op3").attr("selected", "selected");
			}
			$("#searchPay").click(function(){
				$("#payForm").submit();
			});
		});
		
		// The form contains fields for many pagiantion optiosn so you can 
        // quickly see the resuluts of the different options.
        // This function creates an option object for the pagination function.
        // This will be be unnecessary in your application where you just set
        // the options once.
        function getOptionsFromForm(){
            var opt = {callback: pageselectCallback};
            // Collect options from the text fields - the fields are named like their option counterparts
            opt['items_per_page'] = 5;
            opt['num_display_entries'] = 5;
            opt['num_edge_entries'] = 1;
            opt['prev_text'] = '上一页';
            opt['next_text'] = '下一页';
            
            // Avoid html injections in this demo
            var htmlspecialchars ={ "&":"&amp;", "<":"&lt;", ">":"&gt;", '"':"&quot;"}
            $.each(htmlspecialchars, function(k,v){
                opt.prev_text = opt.prev_text.replace(k,v);
                opt.next_text = opt.next_text.replace(k,v);
            })
            return opt;
        }
        
        // This file demonstrates the different options of the pagination plugin
        // It also demonstrates how to use a JavaScript data structure to 
        // generate the paginated content and how to display more than one 
        // item per page with items_per_page.
                
        /**
         * Callback function that displays the content.
         *
         * Gets called every time the user clicks on a pagination link.
         *
         * @param {int}page_index New Page index
         * @param {jQuery} jq the container with the pagination links as a jQuery object
         */
		function pageselectCallback(page_index, jq){
            // Get number of elements per pagionation page from form
            var items_per_page = 5;
            var max_elem = Math.min((page_index+1) * items_per_page, data.length);
            var newcontent = '';
            // Iterate through a selection of the content and build an HTML string
            for(var i=page_index*items_per_page;i<max_elem;i++)
            {	
            	if(data[i].app_type=="01"){
                   var audit ="多媒体软件";
                }else if(data[i].app_type=="02"){
                   var audit ="主题桌面";
                }else if(data[i].app_type=="03"){
                   var audit ="电子阅读";
                }else if(data[i].app_type=="04"){
                   var audit ="实用工具";
                }else if(data[i].app_type=="05"){
                   var audit ="网络通讯";
                }else if(data[i].app_type=="06"){
                   var audit ="安全软件";
                }else if(data[i].app_type=="07"){
                   var audit ="系统软件";
                }else if(data[i].app_type=="08"){
                   var audit ="生活信息";
                }else if(data[i].app_type=="09"){
                   var audit ="娱乐休闲";
                }else if(data[i].app_type=="10"){
                   var audit ="新闻资讯";
                }else if(data[i].app_type=="11"){
                   var audit ="其他软件";
                }else if(data[i].app_type=="12"){
                   var audit ="动作格斗";
                }else if(data[i].app_type=="13"){
                   var audit ="模拟经营";
                }else if(data[i].app_type=="14"){
                   var audit ="休闲益智";
                }else if(data[i].app_type=="15"){
                   var audit ="体育竞技";
                }else if(data[i].app_type=="16"){
                   var audit ="角色扮演";
                }else if(data[i].app_type=="17"){
                   var audit ="其他游戏";
                }
                newcontent += '<tr>'
                newcontent += '<td>' + data[i].app_name + '</td>';
                newcontent += '<td>' + audit + '</td>';
                newcontent += '<td>' + data[i].create_time + '</td>';
                newcontent += '<td>' + data[i].app_state + '</td>';
                
                newcontent += '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>ctrl/appCheckEdit.man?appId='+ data[i].id+'">审批</a></td>';
                newcontent += '</tr>'
            }
            
            // Replace old content with new content
            $("#tbody").html(newcontent);
            $("#adtbl").builderDataTable();
            
            // Prevent click event propagation
            return false;
        }
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
					<div title="财务管理" iconCls="icon-remove"   style="padding:10px;">
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
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px">
		    	<h2 style="margin-top:20px;margin-left:20px">程序审批</h2>
		    	<div style="padding-left:20px;padding-bottom:10px">
		    		<!-- 隐藏域 -->
		    		<input type="hidden" id="appListString" value='${appListString}'>
		    		<form id="payForm" action="<%=basePath%>ctrl/paypalInfoSearch.man" method="post">
		    		
		    		</form>
		    	</div>
		    	<table id="adtbl">
		    		<thead>
		    			<tr>
		    				<th>程序名称</th>
		    				<th>程序类别</th>
		    				<th>上传时间</th>
		    				<th>状态</th>
		    				<th>操作</th>
		    			</tr>
		    		</thead>
		    		<tbody id="tbody">
		    		</tbody>
		    	</table>
		    	
		    	<div style="padding:20px;float:right">
		    		<div id="Pagination"></div>
		    	</div>
		    </div>
		</div>
  	</div>
  </body>
</html>
