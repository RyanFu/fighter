<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.List,cn.com.uangel.adsys.entity.RechargeAccount"%>
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
			
			$("#adtbl").builderDataTable();
			$("#rechargeSubmit").click(function(){
			var radioes = $("input[name='bankPayValue']");
			var choseCount = 0;
			var addMoney=$("#addMoney").val();
			for (var i=0; i < radioes.length; i++) {
				if($(radioes[i]).attr("checked")) {
					choseCount++;
				}
			}
			if(!/^[0-9]+.?[0-9]*$/.test(addMoney)){
				alert('请输入正确的金额！');
			}else if(parseFloat(addMoney)<100){
				alert('充值的金额必须大于100，请重新输入');
			}else if(choseCount == 0){
				alert('请选择您要充值的账户！');
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
	             <div class="conter_c"><div class="balance" style="margin-left:10px">  广告帐户余额：<span id="admany"></span>元</div>
	             </div>
		    	<h2 style="margin-top:20px;margin-left:20px">充值</h2>
		    	<input type="hidden" id="accountListString" value='${accountListString}'>
		    	<input type="hidden" id="sccountList" value='${sccountList}'>
		    	
		    	<form id="rechargeForm" action="<%=basePath%>ctrl/rechargeInfoSave.pay" method="post">
		    	<div style="padding-left:50px;margin-bottom:20px;width=700px">充值金额：
                    <input type="text" name="rechargeMoney" value="1000.00" id="addMoney"/>&nbsp;&nbsp;<b>元</b>
                </div>
                <div style="padding-left:50px">
		        <table border="0" cellpadding="0" cellspacing="0"  width="600px">
		        <%
		        	List<RechargeAccount> accountList=(List<RechargeAccount>)request.getAttribute("sccountList");
		        	for(int i=0;i<accountList.size();i++){
		        		RechargeAccount reAcc=accountList.get(i);
		        	
		         %>
		        
	              <tr style="margin-left:150px;padding-bottom:0;" onmouseover="this.style.backgroundColor='#ddd'" class="borderDark" onmouseout="this.style.backgroundColor='#F6F6F6'">
	                    <td width="40" align="right">
	                    
	                 	<input  type="radio" name="bankPayValue"  id="bankPayId" value="<%=reAcc.getBank_num()%>"/>
	                    </td>
	                    <td><ul >
	                    <li>开户名称：<%=reAcc.getOpen_name()%></li>
	                    <li>开户银行：<%=reAcc.getBank_name()%></li>
	                    <li>银行帐号：<%=reAcc.getBank_num()%></li>
	                    </ul></td>
	              </tr>
	             <% }%>
	            
             	</table>
             	</div>
		    	</form>
		    	<div style="margin-left:150px;margin-bottom:20px">
		    	<button style="button" id="rechargeSubmit"/>充值</button>
		    	<button style="button" id="rechargeBack"/>返回</button><label>${error}</label>
		    	</div>
		</div>
	</div>	
  </div>	
  </body>
</html>
