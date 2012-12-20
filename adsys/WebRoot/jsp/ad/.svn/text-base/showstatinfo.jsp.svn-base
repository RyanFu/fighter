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
    <title>广告统计-报表</title>
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
	<script type="text/javascript" src="<%=basePath%>scripts/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/highcharts/exporting.js"></script>
	
	<script type="text/javascript">
		var data;
		var chart;
		$(function(){
			data = eval('('+$("#statInfo").val()+')');
			$("#adstattbl").builderDataTable({tdWidth:[200,80,80,80,80,80,90]});
			
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
			
			pageLine(10, 0);
		});
		
		// pageSize: 每页多少行； whichPage: 第几页
		function pageLine(pageSize, whichPage) {
			if (data.length <= 0) {
				return;
			}
			// 折线图
			var date = [];
			var cpm = [];
			var cpc = [];
			
			var totalSize = data.length;
			var start = whichPage * pageSize;
			var end = start + pageSize < totalSize ? start + pageSize : totalSize;
			
			for(var i=start,j=0;i<end;i++,j++){
				date[j] = data[i].date.replace('-','<br>');
				cpm[j] = data[i].cpmPay;
				cpc[j] = data[i].cpcPay;
			}
			var statWay = data[0].date.length == 7 ? '按月统计' : '按日统计';
			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					defaultSeriesType: 'line',
					marginRight: 130,
					marginBottom: 35
				},
				title: {
					text: 'CPM和CPC的消费情况',
					x: -20 //center
				},
				subtitle: {
					text: statWay,
					x: -20
				},
				xAxis: {
					categories: date
				},
				yAxis: {
					title: {
						text: ''
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+
							this.x +': '+ this.y +'元';
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -10,
					y: 100,
					borderWidth: 0
				},
				series: [{
					name: 'CPM消费金额',
					data: cpm
				}, {
					name: 'CPC消费金额',
					data: cpc
				}]
			});
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
			// 更新折线图
			pageLine(10, page_index);
			
            // Get number of elements per pagionation page from form
            var items_per_page = 10;
            var max_elem = Math.min((page_index+1) * items_per_page, data.length);
            var newcontent = '';
            
            // Iterate through a selection of the content and build an HTML string
            for(var i=page_index*items_per_page;i<max_elem;i++)
            {
                newcontent += '<tr>'
                newcontent += '<td>' + data[i].date + '</td>';
                newcontent += '<td>' + data[i].show_times + '</td>';
                newcontent += '<td>' + data[i].click_times + '</td>';
                newcontent += '<td>' + data[i].click_rate + '%</td>';
                newcontent += '<td>' + data[i].cpmPay + '</td>';
                newcontent += '<td>' + data[i].cpcPay + '</td>';
                newcontent += '</tr>'
            }
            
            // Replace old content with new content
            $("#tbody").html(newcontent);
            $("#adstattbl").builderDataTable({tdWidth:[200,80,80,90]});
            
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
		    <div class="Border" style="width:720px">
				<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 702px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<h2 style="margin-top:20px;margin-left:20px">广告统计-报表</h2>
		    	<input type="hidden" id="statInfo" value='${adStatStr}'>

				<div id="container" style="width: 700px; height: 400px; margin: 0 auto"></div>
				<div style="width: 700px; height: 100; margin: 20 auto; text-align: right">
					<input id="cost" checked="checked" name="statInfo" type="radio"/>查看消费情况
					<input id="times" name="statInfo" type="radio"/>查看展示点击情况
				</div>

		    	<table id="adstattbl">
		    		<thead>
		    			<tr>
		    				<th>日期</th>
		    				<th>展示次数</th>
		    				<th>点击次数</th>
		    				<th>点击率</th>
		    				<th>CPM消费金额(￥)</th>
		    				<th>CPC消费金额(￥)</th>
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
