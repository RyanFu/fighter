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
			$("#ad").builderFormTable({tdWidth:[200,300]});
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
		<jsp:include page="../../jsp/common/head.jsp?id=news"></jsp:include>
  		<!-- <div id="head_center">
  			<div id="logo">
  			<a href="<%=basePath%>"><img src="<%=basePath%>/styles/images/logo.png" /></a>
			</div>
  		</div>
  		<div id="navigation">
			<div class="left"></div>
			<ul>
				<li >
					<a href="<%=basePath%>index.jsp">&nbsp;首&nbsp;页&nbsp;</a>
				</li>
				<li >
					<a href="<%=basePath%>jsp/webshow/resolvent.jsp">解决方案</a>
				</li>
				<li >
					<a href="<%=basePath%>jsp/webshow/market.jsp">市场分析</a>
				</li>
				<li class="selected">
					<a href="<%=basePath%>jsp/webshow/news.jsp">新闻中心</a>
				</li>
				<li>
					<a href="<%=basePath%>jsp/webshow/download.jsp">下载中心</a>
				</li>
				<li class="last">
					<a href="<%=basePath%>ctrl/info.mem">会员中心</a>
				</li>
			</ul>
			<div class="right"></div>
		</div>
		<div id="loginInfo">
			<div align="right" style="padding-top:5px;padding-right:50px">
				<a id="loginHref1" href="<%=basePath%>jsp/member/login.jsp">登录</a>
				<a id="loginHref2" href="<%=basePath%>ctrl/info.mem">欢迎您${member.name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a id="loginHref3" href="<%=basePath%>ctrl/logout.mem">安全退出</a>
				<a id="loginHref4" href="<%=basePath%>jsp/member/register.jsp">注册</a>
			</div>
		</div> -->
		<div id="content">
			
			<div style="margin-left:20px;margin-top:10px"><img src="<%=basePath%>images/news_1.png" width="900px"></div>
		    <div style="font-family:Arial, Helvetica, sans-serif;font-size:12px;float:left;margin-top:10px;margin-left:20px;width:900px;background:white;border-style:solid;border-width:1px;border-color:gray">
		    	<div id="showCount" style="padding:50px">
		    		<div class="news_list">
           				<h1><a target="_blank" href="#">我国3G手机阅读用户已达5700万 手机视频3500万</a><span class="news_date">[2011-07-26]</span></h1>
           				<div class="around_news">
           				<p>根据工信部的相关公告，我国3G用户和手机应用发展迅猛，其中3G用户已超过8000万，手机阅读用户规模达到5700万。
						</p>
           				</div>
           				<a target="_blank" href="#" class="more_new"><img src="<%=basePath%>images/more.gif" width="46" height="16" alt="详细新闻内容" /></a>
           			</div>

           			<div class="news_list">
           				<h1><a target="_blank" href="#">移动应用收入预计在2015年可达400亿美元</a><span class="news_date">[2011-07-01]</span></h1>
           				<div class="around_news">
           				<p>北京时间6月30日消息，据国外媒体报道，调查公司Canalys周三发表最新分析报告称，苹果移动应用店App Store以及Android市场等软件销售额，未来几年将持续大幅增长，预计到2012年可达141亿美元，2015年为近400亿美元。
						</p>
           				</div>
           				<a target="_blank" href="#" class="more_new"><img src="<%=basePath%>images/more.gif" width="46" height="16" alt="详细新闻内容" /></a>
           			</div>
       	
       				<div class="news_list">
           				<h1><a target="_blank" href="#">电信安捷乐齐聚首 开户移动盈利新时代</a><span class="news_date">[2011-05-05]</span></h1>
           				<div class="around_news">
          	 			<p>日前，国内知名移动广告平台安捷乐传媒宣布，已与中国电信达成战略合作，成为天翼空间所属天翼广告平台的独家合作运营商，与中国电信联合运营和推广“天翼空间手机广告平台”。</p>
           			</div>
           				<a target="_blank" href="#" class="more_new"><img src="<%=basePath%>images/more.gif" width="46" height="16" alt="详细新闻内容" /></a>
           			</div>
       	
       				<div class="news_list">
           				<h1><a target="_blank" href="#">“给力2011 成就卓越”安捷乐年会隆重举行</a><span class="news_date">[2011-01-24]</span></h1>
           				<div class="around_news">
           				<p>2011年1月22日，安捷乐以“给力2011 成就卓越”为主题的集团年会隆重举行。</p>
           				</div>
           				<a target="_blank" href="#" class="more_new"><img src="<%=basePath%>images/more.gif" width="46" height="16" alt="详细新闻内容" /></a>
           			</div>
		    	</div>
		    </div>
		</div>
  	</div>
  </body>
</html>
