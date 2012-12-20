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
    <title>广告发布——选择投放目标</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/page/pagination.css"/>
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/page/jquery.pagination.js"></script>
	
	<script type="text/javascript">
		var data;
		$(function(){
			data = eval('('+$("#adListString").val()+')');
			$("#adtbl").builderDataTable();
			$("#search").click(function(){
				$("#queryForm").submit();
			});
			$("#sellAllAds").click(function(){
				if ($("#sellAllAds").attr("checked")) {
					$("input[name='selectIds']").attr("checked",true);
				} else {
					$("input[name='selectIds']").attr("checked",false);
				}
			});
			
			var searchState = $("#searchState").val();
			$("#run").hide();
			$("#pause").hide();
			if (searchState == "未完成") {
				$("#op2").attr("selected", "selected");
			} else if (searchState == "待审批") {
				$("#op3").attr("selected", "selected");
			} else if (searchState == "运行中") {
				$("#pause").show();
				$("#op4").attr("selected", "selected");
			} else if (searchState == "暂停") {
				$("#run").show();
				$("#op5").attr("selected", "selected");
			} else if (searchState == "删除") {
				$("#op6").attr("selected", "selected");
			} else if (searchState == "未通过") {
				$("#op7").attr("selected", "selected");
			} else if (searchState == "结束") {
				$("#op8").attr("selected", "selected");
			} else if (searchState == "可用") {
				$("#run").show();
				$("#pause").show();
			} 
			
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
			
			$("#pause").click(function(){
				var allIDs = $("input[name='selectIds']");
				var ads = "";
				var selectIDs;
				var index = 0;
				for (var i=0; i<allIDs.length; i++){
					if($(allIDs[i]).attr("checked")) {
						ads += allIDs[i].value + ',';
					}
				}
				if (ads.length > 0){
					ads = ads.substring(0,ads.length - 1);
				}
				if (ads == "") {
					alert('请至少选择一条广告进行处理！');
					return;
				}
				
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/doChangeADsState.ad",
					dataType: "text",
					data : {ads:ads,newState:"暂停"},
					success : function(data){
						ads = ads.split(",");
						for(var i=0; i<ads.length; i++){
							$("#tsState"+ads[i]).text("暂停");
						}
					}
				});
			});
			
			$("#run").click(function(){
				var allIDs = $("input[name='selectIds']");
				var ads = "";
				var selectIDs;
				var index = 0;
				for (var i=0; i<allIDs.length; i++){
					if($(allIDs[i]).attr("checked")) {
						ads += allIDs[i].value + ',';
					}
				}
				if (ads.length > 0){
					ads = ads.substring(0,ads.length - 1);
				}
				if (ads == "") {
					alert('请至少选择一条广告进行处理！');
					return;
				}
				
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/testADBalance.ad",
					dataType : "text",
					async : false,
					success : function(data){
						if (data == 'true') {
							alert('您当前广告账户余额小于0.5元，请充值！');
							return;
						} else {
							$.ajax({
								type : 'POST',
								url : "<%=basePath%>ctrl/doChangeADsState.ad",
								dataType: "text",
								data : {ads:ads,newState:"运行中"},
								success : function(data){
									ads = ads.split(",");
									for(var i=0; i<ads.length; i++){
										$("#tsState"+ads[i]).text("运行中");
									}
								}
							});
						}
					}
				});
			});
			
			$("#del").click(function(){
				var allIDs = $("input[name='selectIds']");
				var ads = "";
				var selectIDs;
				var index = 0;
				for (var i=0; i<allIDs.length; i++){
					if($(allIDs[i]).attr("checked")) {
						ads += allIDs[i].value + ',';
					}
				}
				if (ads.length > 0){
					ads = ads.substring(0,ads.length - 1);
				}
				if (ads == "") {
					alert('请至少选择一条广告进行处理！');
					return;
				}
				
				if (confirm("确定删除选定的广告吗？")){
					$.ajax({
						type : 'POST',
						url : "<%=basePath%>ctrl/doChangeADsState.ad",
						dataType: "text",
						data : {ads:ads,newState:"删除"},
						success : function(data){
							ads = ads.split(",");
							for(var i=0; i<ads.length; i++){
								$("#tsState"+ads[i]).text("删除");
							}
						}
					});
				}
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
                newcontent += '<tr>'
                newcontent += '<td align="center"><input name="selectIds" type="checkbox" value="' + data[i].id + '"/></td>';
                newcontent += '<td>' +'<a title="'+data[i].name+'">'+(data[i].name.length<6?data[i].name:(data[i].name.substring(0,6)+"...")) +'</a>'  + '</td>';
                newcontent += '<td>' + data[i].start_date + '</td>';
                newcontent += '<td id="tsState'+ data[i].id +'">' + data[i].state + '</td>';
                var mpp = data[i].max_pay_perday == '0'?'&nbsp;':data[i].max_pay_perday;
                newcontent += '<td>' + mpp + '</td>';
                newcontent += '<td>' + data[i].pay_mode + '</td>';
                newcontent += '<td>' + data[i].showTimes + '</td>';
                newcontent += '<td>' + data[i].clickTimes + '</td>';
                newcontent += '<td>' + data[i].clickRate + '%</td>';
                newcontent += '<td>' + data[i].cost + '</td>';
                if (data[i].state == "删除") {
                	newcontent += '<td>&nbsp;</td>';
                } else {
                	newcontent += '<td><a href="<%=basePath%>ctrl/getAdPutWays.ad?adId=' + data[i].id + '">查看</a>&nbsp;&nbsp;‖&nbsp;&nbsp;<a href="<%=basePath%>ctrl/editAD.ad?adId=' + data[i].id + '">编辑</a></td>';
                }
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
  	<div>
		<jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=adManage"></jsp:include>
		    <div class="Border" style="width:700px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">广告管理</h2>
		    	<div style="padding-left:20px;padding-bottom:10px">
		    		<!-- 隐藏域 -->
		    		<input type="hidden" id="searchState" value="${searchState}">
		    		<input type="hidden" id="adListString" value='${adListString}'>
		    		<form id="queryForm" action="<%=basePath%>ctrl/adManageByCondition.ad" method="post">
		    		状态：&nbsp;&nbsp;&nbsp;
		    			<select name="state">
		    				<option id="op1" value="可用">可用</option>
		    				<option id="op2" value="未完成">未完成</option>
		    				<option id="op3" value="待审批">待审批</option>
		    				<option id="op4" value="运行中">运行中</option>
		    				<option id="op5" value="暂停">暂停</option>
		    				<option id="op6" value="删除">删除</option>
		    				<option id="op7" value="未通过">未通过</option>
		    				<option id="op8" value="结束">结束</option>
		    			</select>
		    		<a style="padding-left:20px" href="javascript:void(0)" id="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		    		</form>
		    	</div>
		    	<form action="" method="post" id="listForm"></form>
		    	<table id="adtbl">
		    		<thead>
		    			<tr>
		    				<th><input id="sellAllAds" type="checkbox"/></th>
		    				<th>广告名称</th>
		    				<th>起始日期 </th>
		    				<th>状态</th>
		    				<th>日预算</th>
		    				<th>付费方式</th>
		    				<th>展示次数</th>
		    				<th>点击次数 </th>
		    				<th>点击率 </th>
		    				<th>金额</th>
		    				<th>操作</th>
		    			</tr>
		    		</thead>
		    		<tbody id="tbody">
		    		</tbody>
		    	</table>
		    	<div style="padding:20px;float:left">
		    		<button id="run" style="width:50px;margin-right:20px">运行</button>
		    		<button id="pause" style="width:50px;margin-right:20px">暂停</button>
		    		<button id="del" style="width:50px;margin-right:20px">删除</button>
		    	</div>
		    	<div style="padding:20px;float:right">
		    		<div id="Pagination"></div>
		    	</div>
		    </div>
		</div>
  	</div>
  </body>
</html>
