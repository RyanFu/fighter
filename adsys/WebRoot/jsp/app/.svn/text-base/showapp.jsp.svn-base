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
    <title>程序管理</title>
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
			if ($("#appListStr").val() != '') {
				data = eval('('+$("#appListStr").val()+')');
			}
			$("#adtbl").builderDataTable({tdWidth:[180,120,160,80,160]});
			
			var serachState = $("#serachState").val();
			if (serachState == "未完成") {
				$("#op2").attr("selected", "selected");
			} else if (serachState == "待审批") {
				$("#op3").attr("selected", "selected");
			} else if (serachState == "运行中") {
				$("#op4").attr("selected", "selected");
			} else if (serachState == "暂停") {
				$("#op5").attr("selected", "selected");
			} else if (serachState == "删除") {
				$("#op6").attr("selected", "selected");
			} else if (serachState == "未通过") {
				$("#op7").attr("selected", "selected");
			} else if (serachState == "结束") {
				$("#op8").attr("selected", "selected");
			}
			
			// Create pagination element with options from form
			var optInit = getOptionsFromForm();
			
			if ($("#appListStr").val() != '') {
				$("#Pagination").pagination(data.length, optInit);
			}
            
			// Event Handler for for button
			$("#setoptions").click(function(){
                var opt = getOptionsFromForm();
                // Re-create pagination content with new parameters
                if ($("#appListStr").val() != '') {
					$("#Pagination").pagination(data.length, opt);
				}
            }); 
            
            if ($("#appListStr").val() != '') {
				pageselectCallback(0, null);
			}
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
		});
		
		function deleteApp(pid) {
			if (confirm("确定删除此应用吗？")) {
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/testAppIDCouldDelete.ad",
					dataType: "text",
					data : {appid:pid},
					success : function(data){
						if(data == 'true'){
							window.location="<%=basePath%>ctrl/deleteAppByAppID.app?appID="+pid;
						} else {
							alert('此应用已被使用，不可删除！');
						}
					}
				});
			}
		}
		function editApp(id) {
			if (confirm("编辑后程序将会重新审核！")) {
				window.location="<%=basePath%>ctrl/editAppInfo.app?ID="+id;
			}
		}
		
		function checkApp(id) {
			window.location="<%=basePath%>ctrl/checkAppInfo.app?ID="+id;
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
                newcontent += '<tr>'
                newcontent += '<td align="center">' +'<a title="'+data[i].app_name+'">'+(data[i].app_name.length<9?data[i].app_name:(data[i].app_name.substring(0,9)+"...")) +'</a>'  +'</td>';
                newcontent += '<td align="center">' + getAppType(data[i].app_type); + '</td>';
                //newcontent += '<td align="center">' + getAppPlatForm(data[i].app_platform) + '</td>';
                //newcontent += '<td align="center">' +'<a title="'+data[i].create_time+'">'+data[i].create_time.substring(0,10)+'...' +'</a>' + '</td>';
                newcontent += '<td align="center">' + data[i].pid + '</td>';
                newcontent += '<td align="center">' + data[i].app_state + '</td>';
                newcontent += '<td align="center"><a href="javascript:void(0)" onclick="checkApp(\'' + data[i].id + '\')">查看</a>&nbsp;‖&nbsp;<a href="javascript:void(0)" onclick="editApp(\'' + data[i].id + '\')">编辑</a>&nbsp;‖&nbsp;<a href="javascript:void(0)" onclick="deleteApp(\'' + data[i].pid + '\')">删除</a></td>';
                newcontent += '</tr>'
            }
            
            // Replace old content with new content
            $("#tbody").html(newcontent);
            $("#adtbl").builderDataTable();
            
            // Prevent click event propagation
            return false;
        }
        
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
		
		function getAppPlatForm(formCode) {
			switch (formCode) {
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
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=appManage"></jsp:include>
		    <div class="Border" style="width:700px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">程序管理</h2>
		    	<div style="padding-left:20px;padding-bottom:10px">
		    		<!-- 隐藏域 -->
		    		<input type="hidden" id="appListStr" value='${appListStr}'>
		    		<a href="<%=basePath%>jsp/app/saveappinfo.jsp" class="easyui-linkbutton" iconCls="icon-add">添加新程序</a>
		    	</div>
		    	<table id="adtbl">
		    		<thead>
		    			<tr>
		    				<th>程序名称</th>
		    				<th>程序类别 </th>
		    				<th>AppID</th>
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
