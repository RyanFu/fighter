<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>测试Test</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/scripts/jquery/themes/gray/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/scripts/jquery/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/scripts/table/buildertable.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/css/common.css'/>">
	
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.4.2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/table/buildertable.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/date/calendar.js'/>"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,300]});
		});
	</script>
  </head>
  
  <body>
  	<!-- Head -->
  	<div>
  	<jsp:include page="../common/head.jsp"></jsp:include>
  	<div>
  	<!-- Body -->
    <h1>Test</h1>
    <form action="<%=basePath%>ctrl/testAddAd.test" method="post">
    <table id="ad">
    	<tr>
    		<td>广告名称</td>
    		<td><input type="text" name="name"></td>
    	</tr>
    	<tr>
    		<td>广告类型</td>
    		<td>
    			<select name="type">
    				<option value="生活资讯">生活资讯</option>
    				<option value="影音娱乐">影音娱乐</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>开始时间</td>
    		<td><input name="startDate" type="text" readonly="readonly" id="startDate" onclick="new Calendar().show(this); "/></td>
    	</tr>
    	<tr>
    		<td>结束时间</td>
    		<td><input name="endDate" type="text" readonly="readonly" id="endDate" onclick="new Calendar().show(this); "/></td>
    	</tr>
    	<tr>
    		<td>每日预算</td>
    		<td><input type="text" name="maxPayPerDay"></td>
    	</tr>
    	<tr>
    		<td>当前支持媒体</td>
    		<td><input type="checkbox" name="appPlatform" value="1">Android平台
    			<input type="checkbox" name="appPlatform" value="2">Ophone平台</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td><button type="submit">下一步</button></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
