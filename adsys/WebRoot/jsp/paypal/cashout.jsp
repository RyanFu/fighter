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
    <title>财务管理——申请提现</title>
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
			$("#cashOutSubmit").click(function(){
				var money=$("#money").val();
				if(!/^[0-9]+.?[0-9]*$/.test(money)){
					alert('请输入正确的金额！');
				}else if(money.length<3){
					$("#cashoutMany").text("您输入的提现金额必须大于100!");
				}else if(money>${income_balance}){
					$("#cashoutMany").text("您输入的提现金额必须小于或等于可提现金额!");
				}else {
					$.ajax({
					type : 'POST',
					url : "<%=basePath%>ctrl/isBankHere.pay",
					success : function(data){
						
						if(data=='""'){
							alert('请填写银行信息！');
							//
						}else {
						   	$("#cashOutForm").submit();
						}
					}
			});
				}
				});
			$("#checkFinancial").click(function(){
				window.location.href="<%=basePath%>ctrl/adManage.ad";
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
		    		
		    	<h2 style="margin-top:20px;margin-left:20px">提现</h2>
		    	<div style="padding-left:20px;padding-bottom:10px;padding-right:10px">
		    		<!-- 隐藏域 -->
		    		
		    		<form id="cashOutForm" action="<%=basePath%>ctrl/PaypalInfoSave.pay" method="post">
		    		<div class="borderDark" style="margin-left:1px">
							<ul>
						
							<li>
									会员帐号： <span >${email}</span>
						    </li>
							<li>
									收入金额：<span >${income_balance}</span>&nbsp;&nbsp;&nbsp;&nbsp;
							</li>
							<li>
									可提现的金额：<span >${income_balance}</span> &nbsp;&nbsp;&nbsp;&nbsp;
									<span style="color: red;">注意：奖励金额将在月底结算时进行兑现。</span>
							</li>
								
							<li>
									收款帐号： <span >${account_number}</span>

							</li>
							
						
							<li>
									立即提现：
									<br />
									<ul class="balance">
										<span >提现须知</span>
										<li>
											1）收入满100元，即可申请提现。
										</li>
										
										<li>
											2）如不能提供发票，将扣除6%的税点！
										</li>
										
										<li>
											3）我们收到提现申请后，将在七个工作日内完成支付。
										</li>
									</ul>
							</li>
								<li>
									<div class="from_row1" id="moneyInput">
										<div class="f_left">
											提现金额：
										</div>
										<div class="formField">
											<input type="text" name="cashout" id="money" class="type1" />元&nbsp;&nbsp;&nbsp;&nbsp;<label style="color:red" id="cashoutMany"></label>
										</div>
										<div id="money_false" class="false"></div>
									</div>
								</li>
							</ul>

						</div>
						</form>
		    	</div>
		    	<div style="margin-left:150px">
		    	<button type="button" id="checkFinancial">查看填写财务信息</button>
		    	<button type="button" id="cashOutSubmit">提交</button>
		    	</div>
		    	<div style="padding:20px;float:right">
		    		<div id="Pagination"></div>
		    	</div>
		    </div>
		</div>
  	</div>
  </body>
</html>
