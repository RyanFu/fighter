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
    <title>财务管理——充值</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/table/buildertable.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/page/pagination.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/css/border.css">
	
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/table/buildertable.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/page/jquery.pagination.js"></script>
	
	<script type="text/javascript">
		var data;
		$(function(){
			//data = eval('('+$("#adListString").val()+')');
			$("#adtbl").builderDataTable();
			$("#rechargeSubmit").click(function(){
				var rechUpdate=$("#rechUpdate").val();
				if(!/^[0-9]+.?[0-9]*$/.test(rechUpdate)){
					alert('请输入正确的金额！');
				}else {
					$("#rechargeForm").submit();
				}
			   
			});
			$("#rechargeBack").click(function(){
			window.location.href='<%=basePath%>ctrl/rechargeInfoShow.pay';
			});
			
			$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/adBalance.mem",
					success : function(data){
						$("#admany").text(data);
						
					}
			});
		});
		
	
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=paypalManage"></jsp:include>
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px;width:700px;">
		    	<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
             <div class="conter_c"><div class="balance" style="margin-left:10px">  广告帐户余额：<span id="admany"></span>元</div></div>
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">账户充值</h2>
		    	
		    	<form id="rechargeForm" action="<%=basePath%>ctrl/rechargeInfoUpdate.pay" method="post">
		               <input type="hidden" name="rechIdEdit" value=${rechId}>
		               <div class="borderDark">
		               <div>订单号：<span class="notice">${ord_number}</span></div>
		               <div style="margin-top:10px">金额：<input type="text" name="rechUpdate" id="rechUpdate" value="${rechCount }"></div>
		               <div style="margin-top:10px">时间：<span class="notice">${rechTime}</span></div>
		               <div style="margin-top:10px">银行信息：<span class="notice">${bankNumber}</span></div>
		               </div>
		    	</form>
		    	<div style="margin-left:150px;margin-bottom:10px">
		    	<button style="button" id="rechargeSubmit"/>保存</button>
		    	<button style="button" id="rechargeBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
