<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
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
			$("#tbl_operation").builderDataTable({tdWidth:[500,200,100]});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
		});
	</script>
  </head>
  
  <body>
  	<div>
		<jsp:include page="../../jsp/common/head.jsp?id=download"></jsp:include>
		<div id="content">
		    <div>
		    	<div align="center" style="padding-top:20px">
		    		<table id="tbl_operation">
			    		<thead>
			    			<tr>
			    				<th>软件名称</th>
			    				<th>大小</th>
			    				<th>链接</th>
			    			</tr>
			    		</thead>
			    		<tbody id="tbody">
			    			<tr>
			    				<td>安捷乐广告Android展示用例</td>
			    				<td>24.1 KB</td>
			    				<td><a href="<%=basePath%>ctrl/downloadFile.ad?file=Uangel.apk">下载</a></td>
			    			</tr>
			    			<tr>
			    				<td>安捷乐广告商加盟申请表</td>
			    				<td>84.3 KB</td>
			    				<td><a href="#">下载</a></td>
			    			</tr>
			    			<tr>
			    				<td>常见问题</td>
			    				<td>135 KB</td>
			    				<td><a href="#">下载</a></td>
			    			</tr>
			    			<tr>
			    				<td>开发者将sdk添加到程序</td>
			    				<td>84.3 KB</td>
			    				<td><a href="#">下载</a></td>
			    			</tr>
			    			<tr>
			    				<td>广告主如何发布广告</td>
			    				<td>84.3 KB</td>
			    				<td><a href="#">下载</a></td>
			    			</tr>
			    			<tr>
			    				<td>开发者将sdk添加到程序</td>
			    				<td>84.3 KB</td>
			    				<td><a href="#">下载</a></td>
			    			</tr>
			    		</tbody>
			    	</table>
		    	</div>
		    	<div align="center" style="margin-top:20px">
		    		<img style="width:850px;height:146px" src="<%=basePath%>images/resolve_2.png"/>
		    	</div>
		    </div>
		    <div align="center" style="margin-top:20px;margin-top:20px">
		    	<span><img src="<%=basePath%>images/resolve_3.png"/></span>
		    	<span style="margin-left:100px;"><img src="<%=basePath%>images/resolve_4.png"/></span>
		    </div>
		</div>
  	</div>
  </body>
</html>
