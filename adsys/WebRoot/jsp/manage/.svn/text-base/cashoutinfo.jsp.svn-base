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
    <title>财务管理——提现记录</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/page/pagination.css"/>
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/page/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/date/calendar.js"></script>
	
	<script type="text/javascript">
		var data;
		$(function(){
			data = eval('('+$("#paypalListString").val()+')');
			
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
		function del(myid) {
			if (confirm("确定删除此提现记录吗？")) {
				window.location="<%=basePath%>ctrl/paypalInfoDelete.man?paypalId=" + myid;
				
			}
		}
		// The form contains fields for many pagiantion optiosn so you can 
        // quickly see the resuluts of the different options.
        // This function creates an option object for the pagination function.
        // This will be be unnecessary in your application where you just set
        // the options once.
        function getOptionsFromForm(){
            var opt = {callback: pageselectCallback};
            // Collect options from the text fields - the fields are named like their option counterparts
            opt['items_per_page'] = 10;
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
            var items_per_page = 10;
            var max_elem = Math.min((page_index+1) * items_per_page, data.length);
            var newcontent = '';
            
            // Iterate through a selection of the content and build an HTML string
            for(var i=page_index*items_per_page;i<max_elem;i++)
            {
            
                if(data[i].state=="1"){
                   var audit ="待审";
                }else if(data[i].state=="2"){
                   var audit ="未通过";
                }else if(data[i].state=="3"){
                   var audit ="已成功";
                }else{
                   var audit ="未通过";
                }
               
                newcontent += '<tr>'
                newcontent += '<td>' + data[i].apply_time + '</td>';
                newcontent += '<td>' + data[i].bod_number + '</td>';
                newcontent += '<td>' + data[i].apply_amount + '</td>';
                newcontent += '<td>' + data[i].allow_amount + '</td>';
                newcontent += '<td>' + audit + '</td>';
                newcontent += '<td>' + data[i].pay_date + '</td>';
                
                
               
                
                newcontent += '<td><a href="<%=basePath%>ctrl/paypalInfoEdit.man?rechId='+ data[i].id+'">编辑</a>&nbsp‖&nbsp<a href="javascript:void(0)" onclick="del(\'' + data[i].id + '\')">删除</a></td>';
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
		    <div style="float:left;margin-top:60px;margin-left:20px;width:700px;background:white;
		    		border-style:solid;border-width:1px;border-color:gray;padding:10px;font-family:Arial, Helvetica, sans-serif;font-size:12px">
		    	<h2 style="margin-top:20px;margin-left:20px">提现记录</h2>
		    	<div style="padding-left:20px;padding-bottom:10px">
		    		<!-- 隐藏域 -->
		    		<input type="hidden" id="serachState" value="${serachState}">
		    		<input type="hidden" id="paypalListString" value='${paypalListString}'>
		    		<input id="hideType" name="hideType" type="hidden" value="${state}">
		    		<form id="payForm" action="<%=basePath%>ctrl/paypalInfoSearch.man" method="post">
		    		<tr>
			    		<td>开始日期</td>
			    		<td><input name="startDate" type="text" size="7" value="${startDate}" readonly="readonly" id="startDate" onclick="new Calendar().show(this); "/></td>
			    	</tr>
			    	<tr>
			    		<td>结束日期</td>
			    		<td><input name="endDate" type="text" size="7" value="${endDate}" readonly="readonly" id="endDate" onclick="new Calendar().show(this); "/></td>
			    	</tr>
			    	<tr>
			    	    <td>结算单号</td>
			    	    <td><input type="text" name=bod_number size="14"></td>
			    	</tr>
			    	<tr>
			    	   	<td>
			    			<select name="state" id="state">
	    						<option id="op1" value="1">待审</option>
	    						<option id="op2" value="2">未通过</option>
	    						<option id="op3" value="3">已成功</option>
			    			</select>
			    		</td>
			    	</tr>
		    		<a style="padding-left:10px" href="#" id="searchPay" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		    		</form>
		    	</div>
		    	<table id="adtbl">
		    		<thead>
		    			<tr>
		    				<th>申请日期</th>
		    				<th>单据号 </th>
		    				<th>申请额度</th>
		    				<th>批准额度</th>
		    				<th>状态</th>
		    				<th>支付日期</th>
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
