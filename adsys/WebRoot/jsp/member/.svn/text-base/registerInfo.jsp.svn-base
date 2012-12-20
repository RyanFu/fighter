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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#ad").builderFormTable({tdWidth:[200,300]});
		});
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
  	    
		<div id="content">
			
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;width:700px;">
		    	<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form action="<%=basePath%>ctrl/testAddAd.test" method="post">
			    <table id="ad" align="center" style="margin-top:20px">
			    	<tr>
			    		<td>注册链接已发送到您的邮箱，请登录邮箱激活.</td>
			    		<td><input name="test" type="text" value=${email}></td>
			    	</tr>
			    	
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
