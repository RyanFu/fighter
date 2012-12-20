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
    <title>财务管理——提现</title>
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
			$("#cashoutSubmit").click(function(){
				var cashoutUpdate=$("#cashoutUpdate").val();
				if(!/^[0-9]+.?[0-9]*$/.test(cashoutUpdate)){
					alert('请输入正确的金额！');
				}else {
					$("#cashoutForm").submit();
				}
			   
			});
			$("#cashoutBack").click(function(){
				window.location.href='<%=basePath%>ctrl/paypalInfoShow.pay';
			});
			if("${member.id}"==""){
				$("#loginHref2").hide();
				$("#loginHref3").hide();
			}else if("${member.id}"!=""){
				$("#loginHref1").hide();
				$("#loginHref4").hide();
			}
			$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/incomeBalance.mem",
					success : function(data){
						$("#incomeBalance").text(data);
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
             <div class="conter_c"><div class="balance" style="margin-left:10px">  收入帐户余额：<span id="incomeBalance"></span>元</div></div>
             <div style="clear:both"></div>
		    	<h2 style="margin-top:20px;margin-left:20px">提现</h2>
		    	
		    	<form id="cashoutForm" action="<%=basePath%>ctrl/paypalInfoUpdate.pay" method="post">
		               <input type="hidden" name="paypalIdEdit" value=${paypalId}>
		               <div class="borderDark">
		               <div>单据号：<span class="notice">${bod_number}</span></div>
		               <div style="margin-top:10px">申请额度：<input type="text" name="cashoutUpdate" id="cashoutUpdate" value="${apply_amount}"></div>
		               <div style="margin-top:10px">申请日期：<span class="notice">${applyTime}</span></div>
		               <div style="margin-top:10px">银行账号：<span class="notice">${member.account_number}</span></div>
		               </div>
		    	</form>
		    	<div style="margin-left:150px;margin-bottom:10px">
		    	<button style="button" id="cashoutSubmit"/>保存</button>
		    	<button style="button" id="cashoutBack"/>返回</button>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
