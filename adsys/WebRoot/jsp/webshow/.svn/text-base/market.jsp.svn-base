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
		<jsp:include page="../../jsp/common/head.jsp?id=market"></jsp:include>
		<div id="content">
			<img src="<%=basePath%>images/market_03.jpg" style="margin-left:20px"/>
		    <div style="font-family:Arial, Helvetica, sans-serif;font-size:12px;padding:30px;float:left;margin-top:20px;margin-left:20px;width:900px;background:white;border-style:solid;border-width:1px;border-color:gray">
		  	<!-- 开始 -->
		  	 <div class="left_box" style="margin-top:0; ">
		  		<h3>智能手机爆破式增长</h3>           
           		<ul>             
                             <li>根据易观《2010年中国移动互联网发展报告》2010 年上半年中国市场智能手机的销量增长十分迅速，销售量为24,054,000部。超过了2009 年全年的销售量，预计2010年全年销量将突破4000万部。</li>
              	</ul>
                 <ul><img src="<%=basePath%>images/market_08_2.jpg" /></ul>           
          		<h5>图表  中国智能手机销售市场规模</h5>
		  
		  	<div class="market_bt"> <h3>手机上网整体用户规模</h3> </div>
                     
             	<ul>             
              		<li>根据CNNIC最新数据2010年，我国手机网民规模继续扩大，截至2010年12月，手机网民达3.03亿，较2009年底增加了6930万人。</li>
          		</ul>
				<ul>
					<img src="<%=basePath%>images/market_09_1.jpg"/>
				</ul>
				<h5>图表  各种手机上网应用的用户规模</h5>
          		<div class="market_bt"><h3>操作系统市场－Android 前景不可限量</h3></div>
          		<ul>             
              		<li>根据易观《2010年中国移动互联网发展报告》研究发现，iOS 和Android 等操作系统的全球和国内的市场份额都有了一定提升，特别是Android 系统在过去两年中市场份额已经翻了近34 倍，增长速度迅猛。</li>
           		</ul>
          		<ul>
            		<img src="<%=basePath%>images/market_10_1.jpg" />
          		</ul>
          		<h5>图表  各种手机上网应用的用户规模</h5>
          		<div class="market_bt"><h3>手机上网的粘性分析——频次和时长</h3></div>
         
                   
            	<ul>             
            	<li>根据CNNIC第24次互联网统计显示，网民平均每周上网时间18.0小时，本次调查显示，手机网民平均每天上网达97分钟，每周上网达11.3小时，手机作为新媒体的影响力逐步提高。</li></ul>
             	<div class="box_img">
             		<ul>
						<img src="<%=basePath%>images/market_11_1.jpg" />
					</ul>
          			<h5>图表  不同手机网站偏好的上网粘性分析</h5>
          		</div>
           		<div class="market_bt"><h3>手机上网的粘性分析——使用对比</h3></div>          
             		<ul>             
              			<li>调查数据显示，目前主流手机网站的用户粘性都较高，尤其是工具类型的手机网站，由于其用户的使用需求多元，粘性相对更强。
						</li>
             		</ul>
					<ul>
						<img src="<%=basePath%>images/market_12_1.jpg"  />
					</ul>
					<h5>图表&nbsp; 手机上网主要网站的使用粘性对比</h5>
				<div class="market_bt"><h3>手机上网网络应用</h3></div>
					<h4>各手机上网网络应用的用户规模</h4>
          			<ul>             
              			<li>下图黄色柱图为手机网民应用的第一梯队，分别为手机上网浏览新闻，手机在线聊天和手机搜索，各自比例为89.8%，47.9%，46.9%。</li>
			  			<li>下图红色柱图（手机网游和手机电视）将在下文中进行重点行为分析。</li>
			  			<img src="<%=basePath%>images/market_13_1.jpg"/>
			  			<h5>图表&nbsp; 各手机上网网络应用的用户规模</h5>
            		</ul>
 
        
            	<!--<h3>手机上网的粘性分析——频次和时长</h3>
                 
             	<ul><li>据CNNIC第24次互联网统计显示：网民平均每周上网时长为18.0小时。本次调查显示，手机网民平均每天上网达97分钟，每周上网达11.3小时，手机作为新媒体的影响力逐步提高。</li>
             	</ul>
              	<div class="box_img"><img src="/themes/default/images/market/market_42.jpg" />
          		<h5>图表  不同手机网站偏好的上网粘性分析</h5>-->
            	
		  	<!-- 结束 -->
		    </div>
		</div>
  	</div>
  </body>
</html>
