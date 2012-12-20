<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.com.uangel.adsys.entity.*"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	if (path != null && path.contains("adsys")) {
		basePath += path.substring(1) + "/";
	}
	String id = request.getParameter("id");
	Member mem = (Member) request.getSession().getAttribute("member");
	if (mem == null) {
		out.println("<script>window.location='"+basePath+"jsp/member/login.jsp';</script>");
	}
%>
<div class="Border" style="width:182px;float:left">
	<div>
   		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
   		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 164px;" class="center"></span>
   		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
   	</div>
	<div style="width:182px;float:left;" id="out">
    	<div id="aa" class="easyui-accordion" style="" >
			<%
			String memType = mem.getAccount_type();
			if ("10".equals(memType)) {
			%>
			<div title="基本信息" iconCls="icon-remove" style="padding:10px;" id="baseInfo">
				<div style="padding:5px" id="baseInfo1"><a href="<%=basePath%>jsp/member/accountInformation.jsp">&gt;账号信息</a></div>
				<div style="padding:5px" id="baseInfo3"><a href="<%=basePath%>jsp/member/changePassword.jsp">&gt;修改密码</a></div>
			</div>
			<div title="广告管理" iconCls="icon-remove"  style="padding:10px;" id="adManage">
				<div style="padding:5px" id="adManage1"><a href="<%=basePath%>jsp/ad/saveadinfo.jsp">&gt;发布广告</a></div>
				<div style="padding:5px" id="adManage2"><a href="<%=basePath%>ctrl/adManage.ad">&gt;管理广告</a></div>
				<div style="padding:5px" id="adManage3"><a href="<%=basePath%>ctrl/setStatCondition.ad">&gt;广告统计</a></div>
			</div>
			<div title="财务管理" iconCls="icon-remove"  style="padding:10px;" id="paypalManage">
				<div style="padding:5px" id="paypalManage1"><a href="<%=basePath%>ctrl/rechInfoShow.pay">&gt;充值</a></div>
				<div style="padding:5px" id="paypalManage2"><a href="<%=basePath%>ctrl/rechargeInfoShow.pay">&gt;充值记录</a></div>
			</div>
			<%
			} else if ("01".equals(memType)) {
			%>
			<div title="基本信息" iconCls="icon-remove" style="padding:10px;" id="baseInfo">
				<div style="padding:5px" id="baseInfo1"><a href="<%=basePath%>jsp/member/accountInformation.jsp">&gt;账号信息</a></div>
				<div style="padding:5px" id="baseInfo2"><a href="<%=basePath%>jsp/member/financialInformation.jsp">&gt;财务信息</a></div>
				<div style="padding:5px" id="baseInfo3"><a href="<%=basePath%>jsp/member/changePassword.jsp">&gt;修改密码</a></div>
			</div>
			<div title="程序管理" iconCls="icon-remove" style="padding:10px;" id="appManage">
				<div style="padding:5px" id="appManage1"><a href="<%=basePath%>jsp/app/saveappinfo.jsp">&gt;添加程序</a></div>
				<div style="padding:5px" id="appManage2"><a href="<%=basePath%>ctrl/showApps.app">&gt;管理程序</a></div>
				<div style="padding:5px" id="appManage3"><a href="<%=basePath%>ctrl/setStatCondition.app">&gt;程序统计</a></div>
			</div>
			<div title="下载中心" iconCls="icon-remove" style="padding:10px;" id="downloadCenter">
				<div style="padding:5px" id="downloadCenter1"><a href="#">&gt;SDK更新下载</a></div>
			</div>
			<div title="财务管理" iconCls="icon-remove"  style="padding:10px;" id="paypalManage">
				<div style="padding:5px" id="paypalManage3"><a href="<%=basePath%>ctrl/paypalShow.pay">&gt;提现</a></div>
				<div style="padding:5px" id="paypalManage4"><a href="<%=basePath%>ctrl/paypalInfoShow.pay">&gt;提现记录</a></div>
				<div style="padding:5px" id="paypalManage5"><a href="<%=basePath%>ctrl/commisionInfoShow.pay">&gt;雇佣金发放</a></div>
			</div>
			<%
			} else {
			%>
			<div title="基本信息" iconCls="icon-remove" style="padding:10px;" id="baseInfo">
				<div style="padding:5px" id="baseInfo1"><a href="<%=basePath%>jsp/member/accountInformation.jsp">&gt;账号信息</a></div>
				<div style="padding:5px" id="baseInfo2"><a href="<%=basePath%>jsp/member/financialInformation.jsp">&gt;财务信息</a></div>
				<div style="padding:5px" id="baseInfo3"><a href="<%=basePath%>jsp/member/changePassword.jsp">&gt;修改密码</a></div>
			</div>
			<div title="广告管理" iconCls="icon-remove"  style="padding:10px;" id="adManage">
				<div style="padding:5px" id="adManage1"><a href="<%=basePath%>jsp/ad/saveadinfo.jsp">&gt;发布广告</a></div>
				<div style="padding:5px" id="adManage2"><a href="<%=basePath%>ctrl/adManage.ad">&gt;管理广告</a></div>
				<div style="padding:5px" id="adManage3"><a href="<%=basePath%>ctrl/setStatCondition.ad">&gt;广告统计</a></div>
			</div>
			<div title="程序管理" iconCls="icon-remove" style="padding:10px;" id="appManage">
				<div style="padding:5px" id="appManage1"><a href="<%=basePath%>jsp/app/saveappinfo.jsp">&gt;添加程序</a></div>
				<div style="padding:5px" id="appManage2"><a href="<%=basePath%>ctrl/showApps.app">&gt;管理程序</a></div>
				<div style="padding:5px" id="appManage3"><a href="<%=basePath%>ctrl/setStatCondition.app">&gt;程序统计</a></div>
			</div>
			<div title="下载中心" iconCls="icon-remove" style="padding:10px;" id="downloadCenter">
				<div style="padding:5px" id="downloadCenter1"><a href="#">&gt;SDK更新下载</a></div>
			</div>
			<div title="财务管理" iconCls="icon-remove"  style="padding:10px;" id="paypalManage">
				<div style="padding:5px" id="paypalManage1"><a href="<%=basePath%>ctrl/rechInfoShow.pay">&gt;充值</a></div>
				<div style="padding:5px" id="paypalManage2"><a href="<%=basePath%>ctrl/rechargeInfoShow.pay">&gt;充值记录</a></div>
				<div style="padding:5px" id="paypalManage3"><a href="<%=basePath%>ctrl/paypalShow.pay">&gt;提现</a></div>
				<div style="padding:5px" id="paypalManage4"><a href="<%=basePath%>ctrl/paypalInfoShow.pay">&gt;提现记录</a></div>
				<div style="padding:5px" id="paypalManage5"><a href="<%=basePath%>ctrl/commisionInfoShow.pay">&gt;雇佣金发放</a></div>
			</div>
			<%
			}
			%>
		</div>
    </div>
</div>

<script>
	$(function(){
	});
	var id = "<%=id%>";
	$("#"+id).attr("selected", "true");
</script>

