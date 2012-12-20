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
    <title>程序统计-报表</title>
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
			data = eval('('+$("#statInfo").val()+')');
			$("#appstattbl").builderDataTable({tdWidth:[200,80,80,80,80,80,90]});
			
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
                newcontent += '<td>' + parseProvinceCode(data[i].zone) + '</td>';
                newcontent += '<td>' + data[i].request_times + '</td>';
                newcontent += '<td>' + data[i].show_times + '</td>';
                newcontent += '<td>' + data[i].click_times + '</td>';
                newcontent += '<td>' + data[i].cpm_commision + '</td>';
                newcontent += '<td>' + data[i].cpc_commision + '</td>';
                newcontent += '<td>' + data[i].total_income + '</td>';
                newcontent += '</tr>'
            }
            
            // Replace old content with new content
            $("#tbody").html(newcontent);
            $("#appstattbl").builderDataTable({tdWidth:[200,80,80,80,80,80,90]});
            
            // Prevent click event propagation
            return false;
        }
        function  parseProvinceCode(code){
        	var provinceName ="";
        	switch(code){
        	case "110000" : 
        		provinceName="北京" 
        	break;
        	case "120000" : 
        		provinceName="天津" 
        	break;
        	case "130000" : 
        		provinceName="河北" 
        	break;
        	case "140000" : 
        		provinceName="山西" 
        	break;
        	case "150000" : 
        		provinceName="内蒙" 
        	break;
        	case "210000" : 
        		provinceName="辽宁" 
        	break;
        	case "220000" : 
        		provinceName="吉林" 
        	break;
        	case "230000" : 
        		provinceName="黑龙江" 
        	break;
        	case "310000" : 
        		provinceName="上海" 
        	break;
        	case "320000" : 
        		provinceName="江苏" 
        	break;
        	case "330000" : 
        		provinceName="浙江" 
        	break;
        	case "340000" : 
        		provinceName="安徽" 
        	break;
        	case "350000" : 
        		provinceName="福建" 
        	break;
        	case "360000" : 
        		provinceName="江西" 
        	break;
        	case "370000" : 
        		provinceName="山东省" 
        	break;
        	case "410000" : 
        		provinceName="河南" 
        	break;
        	case "420000" : 
        		provinceName="湖北" 
        	break;
        	case "430000" : 
        		provinceName="湖南" 
        	break;
        	case "440000" : 
        		provinceName="广东" 
        	break;
        	case "450000" : 
        		provinceName="广西" 
        	break;
        	case "460000" : 
        		provinceName="海南" 
        	break;
        	case "500000" : 
        		provinceName="重庆" 
        	break;
        	case "510000" : 
        		provinceName="四川" 
        	break;
        	case "520000" : 
        		provinceName="贵州" 
        	break;
        	case "530000" : 
        		provinceName="云南" 
        	break;
        	case "540000" : 
        		provinceName="西藏" 
        	break;
        	case "610000" : 
        		provinceName="陕西" 
        	break;
        	case "620000" : 
        		provinceName="甘肃" 
        	break;
        	case "630000" : 
        		provinceName="青海" 
        	break;
        	case "640000" : 
        		provinceName="宁夏" 
        	break;
        	case "650000" : 
        		provinceName="新疆" 
        	break;
        	case "710000" : 
        		provinceName="台湾" 
        	break;
        	case "810000" : 
        		provinceName="香港" 
        	break;
        	case "820000" : 
        		provinceName="澳门" 
        	break;
        	case "000000" : 
        		provinceName="其他" 
        	break;
        	
        	}
        	return provinceName;
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
		    	<h2 style="margin-top:20px;margin-left:20px">程序统计-报表</h2>
		    	<input type="hidden" id="statInfo" value='${appStatStr}'>
		    	<table id="appstattbl">
		    		<thead>
		    			<tr>
		    				<th>地区</th>
		    				<th>请求次数</th>
		    				<th>展示次数</th>
		    				<th>点击次数</th>
		    				<th>CPM佣金</th>
		    				<th>CPC佣金</th>
		    				<th>收入金额(￥)</th>
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
