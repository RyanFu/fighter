<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<title>安捷乐广告平台</title>
<meta name="keywords" content="smart4me" />
<meta name="description" content="smart4me" />
<link href="<c:url value='/styles/css/smart/base.css'/>" rel="stylesheet" type="text/css" media="all,screen" />
<link href="<c:url value='/styles/css/smart/top.css'/>" rel="stylesheet" type="text/css" media="all,screen" />
<link href="<c:url value='/styles/css/smart/start.css'/>" rel="stylesheet" type="text/css" media="all,screen" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/css/public.css'/>"/>

<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery.easing.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/thickbox.js'/>"></script>

<script src="<c:url value='/scripts/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/top.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/scripts/ymweb.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/styles/css/smart/thickbox.css'/>" type="text/css" media="all" />

<script>
	$(function(){
		if("${member.id}"==""){
			$("#loginImg").show();
			$("#loginBtn").show();
			$("#loginInfo").hide();
			$("#mainnav").show();
		}else if("${member.id}"!=""){
			$("#loginImg").hide();
			$("#loginBtn").hide();
			$("#loginInfo").show();
			$("#mainnav").hide();
		}
		$("#loginBtn").click(function(){
			login();
		});
		getCurShowCount()
	});
	
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			alert("请输入用户名(注册邮箱)！");
			return;
		}
		if (password == "") {
			alert("请输入密码！");
			return;
		}
		$.ajax({
			type : 'POST',
			url : "<%=basePath%>ctrl/loginWithoutCode.mem",
			data : {loginEmail:username,loginPassword:password},
			success : function(data){
				data = data.substring(1,data.length-1);
				if (data == "ok") {
					window.location="<%=basePath%>ctrl/info.mem";
					return;
				} else {
					alert(data);
					$("#loader").hide();
				}
			}
		});
	}
	function enterIn(evt){
 		var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
		if (evt.keyCode==13){
 			login();
		}
	}
	function getCurShowCount(){
		$.ajax({
			type : 'POST',
			url : "<%=basePath%>ctrl/getCurShowCount.ad",
			dataType: "text",
			success : function(data){
				$("#showCount").text("当前广告展示次数： " + data);
			}
		});
	}
</script>
</head>
<body>

<div id="loader">
<p><img src="<%=basePath%>styles/images/smart/loader.gif" alt="" width="32" height="32" /></p>
</div>
<div id="wrapper">
	<div id="container">
		<div id="header">
			<div style="margin: 0 auto;width:972px;white-space:nowrap;">
				<a href="<%=basePath%>index.jsp"><img src="<%=basePath%>img/ad_02.jpg" style="margin:0;float:left"/></a>
				<div id="loginImg" style="background:url(<%=basePath%>img/ad_03.jpg);width:561px;height:90px;float:left;">
					<div style="margin-top:66px;margin-bottom:5px;">
					<input type="text" id="username" style="margin-left:230px;width:130px;height:13px;border:0px"/>
					<input type="password" id="password" onkeydown="enterIn(event)" style="margin-left:55px;width:130px;height:13px;border:0px"/>
					</div>
				</div>
				<span id="loginBtn"><a href="javascript:login()"><img id="login" src="<%=basePath%>img/ad_04.jpg"/></a></span>
				
				<div id="loginInfo" style="padding-bottom:0px;width:633px;height:90px;float:left;display:none">
					<div style="padding-top:70px;font-family:'黑体 ', 'Courier   New ', 'tahoma ';font-size: 14px">
						<table border="0">
						<tr>
							<td width="580" align="right"><a style="" href="<%=basePath%>ctrl/info.mem">欢迎您${member.name}</a>&nbsp;&nbsp;|</td>
							<td width="80"><a href="<%=basePath%>ctrl/logout.mem">安全退出</a></td>
						</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="newNavigation" style="margin: 0 auto;width:980px;white-space:nowrap;">
				<a href="<%=basePath%>index.jsp"><img id="index" src="<%=basePath%>img/ad_061.jpg" style="margin:0;float:left"/></a>
				<a href="<%=basePath%>jsp/webshow/resolvent.jsp"><img id="resolvent" src="<%=basePath%>img/ad_07.jpg" style="margin:0;float:left"/></a>
				<a href="<%=basePath%>jsp/webshow/market.jsp"><img id="market" src="<%=basePath%>img/ad_08.jpg" style="margin:0;float:left"/></a>
				<a href="<%=basePath%>jsp/webshow/news.jsp"><img id="news" src="<%=basePath%>img/ad_09.jpg" style="margin:0;float:left"/></a>
				<a href="<%=basePath%>jsp/webshow/download.jsp"><img id="download" src="<%=basePath%>img/ad_10.jpg" style="margin:0;float:left"/></a>
				<a href="<%=basePath%>ctrl/info.mem"><img id="member" src="<%=basePath%>img/ad_11.jpg" style="margin:0;float:left"/></a>
			</div>
			<!-- hdrnv_end -->
		</div>
		<!-- header_end -->
		<div id="topMain" class="png_bg">
		<div id="topMainInner">
		<div id="Smartphone" class="png_bg"></div>
		<!-- Smartphone_end -->
		<div id="mainnav" class="png_bg">
			<div style="width:310px;height:100px;margin-top:250px;margin-left:120px;cursor:hand">
				<a href="<%=basePath%>jsp/member/register.jsp"><div style="width:310px;height:100px"></div></a>
			</div>
		</div>
		<!-- mainnav_end -->
			</div>
		</div>
	<!-- topMain_end -->
	</div>
	<!-- container_end -->

	<div id="mainvisual">
	<div id="slide" class="clfix">
	<div id="slide_inner">
	    <img id="img_1" src="<%=basePath%>styles/images/smart/unit_01.jpg" alt="" width="167" height="221" />
		<img id="img_2" src="<%=basePath%>styles/images/smart/unit_02.jpg" alt="" width="167" height="221" />
		<img id="img_3" src="<%=basePath%>styles/images/smart/unit_03.jpg" alt="" width="167" height="221" />
		<img id="img_4" src="<%=basePath%>styles/images/smart/unit_04.jpg" alt="" width="167" height="221" />
		<img id="img_5" src="<%=basePath%>styles/images/smart/unit_05.jpg" alt="" width="167" height="221" />
		<img id="img_6" src="<%=basePath%>styles/images/smart/unit_06.jpg" alt="" width="167" height="221" />
	 	<img id="img_7" src="<%=basePath%>styles/images/smart/unit_07.jpg" alt="" width="167" height="221" />
	   	<img id="img_8" src="<%=basePath%>styles/images/smart/unit_08.jpg" alt="" width="167" height="221" />
	   	<img id="img_9" src="<%=basePath%>styles/images/smart/unit_09.jpg" alt="" width="167" height="221" />
	   	<img id="img_10" src="<%=basePath%>styles/images/smart/unit_07.jpg" alt="" width="167" height="221" />
	   	<img id="img_11" src="<%=basePath%>styles/images/smart/unit_06.jpg" alt="" width="167" height="221" />
	   	<img id="img_12" src="<%=basePath%>styles/images/smart/unit_05.jpg" alt="" width="167" height="221" />
		<img id="img_13" src="<%=basePath%>styles/images/smart/unit_04.jpg" alt="" width="167" height="221" />
		<img id="img_14" src="<%=basePath%>styles/images/smart/unit_03.jpg" alt="" width="167" height="221" />
		<img id="img_15" src="<%=basePath%>styles/images/smart/unit_02.jpg" alt="" width="167" height="221" />
		<img id="img_16" src="<%=basePath%>styles/images/smart/unit_01.jpg" alt="" width="167" height="221" />
	</div>
	</div>
	<!-- slide_end -->
	   </div>
	<!-- mainvisual -->
	<div id="point_block_wr">
	<div id="point_block" class=" clfix">
	<div id="point1">
	<h2 class="png_bg01">All you need is just 10 minutes.</h2>
	<p><a href="about.html#t01"><img src="<%=basePath%>styles/images/smart/h2_01.jpg" alt="All you need is just 10 minutes." width="219px" height="170" /></a></p>
	<p class="point_test">
		按照您产品的定位，选择不同的用户群，
		手机型号，运营商，用户性别，应用类型，
		消费人群，时间段，地理位置等等。
	</p>
	</div>
	<div id="point2">
	<h2 class="png_bg02">Make your site free of charge!</h2>
	<p><a href="about.html#t02"><img src="<%=basePath%>styles/images/smart/h2_02.jpg" alt="Make your site free of charge!" width="219px" height="170" /></a></p>
	<p class="point_test">
		可以按照日期，省份，用户群，时间段等<br/>
		多种方式进行统计，您可以更好的了解<br/>
		您的产品受到哪些人关注。
	</p>
	</div>
	<div id="point3">
	<h2 class="png_bg03">Twitter,blogs,and more!</h2>
	<p><a href="about.html#t03"><img src="<%=basePath%>styles/images/smart/h2_03.jpg" alt="Twitter,blogs,and more!" width="219px" height="170" /></a></p>
	<p class="point_test">
		我们为开发者提供高达80%的收入分红<br/>
		赶快在您的应用，游戏中嵌入我们的<br/>
		广告吧！
	</p>
	</div>
	<div id="point4">
	<h2 class="png_bg04_2">Gallery page!!</h2>
	<p><a href="gallery.html"><img src="<%=basePath%>styles/images/smart/h2_04_2.jpg" alt="smart4me Site Gallery" width="219px" height="170" /></a></p>
	<p class="point_test">
		看看您的哪款应用受欢迎，<br/>
		大家都对哪一类广告感兴趣。
	</p>
	</div>
	</div>
	<!-- point_block_end -->
	</div>
	<!-- point_block_wr_end -->

	<div style="margin: 0px auto;position: relative;width:972px">
	<!-- 合作伙伴 -->
			<div class="pattern" style="width:200px">
				<div id=partnerbar>
					<div id=move_logo>
						<div class=scroller>
							<table>
								<tbody>
									<tr>
										<td>
											<a class=ml_1 title="合作媒体 - 艾媒网"
												href="http://www.iimedia.cn/" target=_blank>艾媒网</a>
										</td>
										<td>
											<a class=ml_2 title=诺基亚Nokia中文网
												href="http://nokia.tgbus.com/" target=_blank>诺基亚Nokia中文网</a>
										</td>
										<td>
											<a class=ml_3 title=Android中文网
												href="http://android.tgbus.com/" target=_blank>Android中文网</a>
										</td>
										<td>
											<a class=ml_4 title=Ophone中文网
												href="http://ophone.tgbus.com/" target=_blank>Ophone中文网</a>
										</td>
										<td>
											<a class=ml_5 title=聚购手机内容管理系统 href="http://e.juugoo.com/"
												target=_blank>聚购手机</a>
										</td>
										<td>
											<a class=ml_6 title=中国领先移动应用市场监测门户
												href="http://www.mmclick.com/" target=_blank>MMclick</a>
										</td>
										<td>
											<a class=ml_7 title=安智网 href="http://bbs.goapk.com/"
												target=_blank>安智网</a>
										</td>
										<td>
											<a class=ml_8 title=机锋网 href="http://www.gfan.com/"
												target=_blank>机锋网</a>
										</td>
										<td>
											<a class=ml_9 title=N多网 href="http://www.nduoa.com/"
												target=_blank>N多网</a>
										</td>
										<td>
											<a class=ml_a title=ChinaMeizu
												href="http://www.chinameizu.com/" target=_blank>ChinaMeizu</a>
										</td>
										<td>
											<a class=ml_b title=中国手机开发者联盟,做最好的服务!
												href="http://www.cmd100.com/" target=_blank>中国手机开发者联盟</a>
										</td>
										<td>
											<a class=ml_c title=发现分享安卓乐趣
												href="http://www.starandroid.com/" target=_blank>安卓星空</a>
										</td>
										<td>
											<a class=ml_d title=手机广告聚合平台-果合 href="http://guohead.com/"
												target=_blank>果合</a>
										</td>
										<td>
											<a class=ml_e title=移动广告聚合平台-Adview
												href="http://www.adview.cn/" target=_blank>AdView</a>
										</td>
										<td>
											<a class=ml_f title=广告聚合平台-芒果MOGO
												href="http://www.adsmogo.com/" target=_blank>芒果MOGO</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class=scroller>
							<table>
								<tbody>
									<tr>
										<td>
											<a class=ml_1 title="合作媒体 - 艾媒网"
												href="http://www.iimedia.cn/" target=_blank>艾媒网</a>
										</td>
										<td>
											<a class=ml_2 title=诺基亚Nokia中文网
												href="http://nokia.tgbus.com/" target=_blank>诺基亚Nokia中文网</a>
										</td>
										<td>
											<a class=ml_3 title=Android中文网
												href="http://android.tgbus.com/" target=_blank>Android中文网</a>
										</td>
										<td>
											<a class=ml_4 title=Ophone中文网
												href="http://ophone.tgbus.com/" target=_blank>Ophone中文网</a>
										</td>
										<td>
											<a class=ml_5 title=聚购手机内容管理系统 href="http://e.juugoo.com/"
												target=_blank>聚购手机</a>
										</td>
										<td>
											<a class=ml_6 title=中国领先移动应用市场监测门户
												href="http://www.mmclick.com/" target=_blank>MMclick</a>
										</td>
										<td>
											<a class=ml_7 title=安智网 href="http://bbs.goapk.com/"
												target=_blank>安智网</a>
										</td>
										<td>
											<a class=ml_8 title=机锋网 href="http://www.gfan.com/"
												target=_blank>机锋网</a>
										</td>
										<td>
											<a class=ml_9 title=N多网 href="http://www.nduoa.com/"
												target=_blank>N多网</a>
										</td>
										<td>
											<a class=ml_a title=ChinaMeizu
												href="http://www.chinameizu.com/" target=_blank>ChinaMeizu</a>
										</td>
										<td>
											<a class=ml_b title=中国手机开发者联盟,做最好的服务!
												href="http://www.cmd100.com/" target=_blank>中国手机开发者联盟</a>
										</td>
										<td>
											<a class=ml_c title=发现分享安卓乐趣
												href="http://www.starandroid.com/" target=_blank>安卓星空</a>
										</td>
										<td>
											<a class=ml_d title=手机广告聚合平台-果合 href="http://guohead.com/"
												target=_blank>果合</a>
										</td>
										<td>
											<a class=ml_e title=移动广告聚合平台-Adview
												href="http://www.adview.cn/" target=_blank>AdView</a>
										</td>
										<td>
											<a class=ml_f title=广告聚合平台-芒果MOGO
												href="http://www.adsmogo.com/" target=_blank>芒果MOGO</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
	</div>

	<!--thickbox -->
	<div id="page_wr">
	<div id="page_com" class="clfix">
	</div>
	</div>
	<div id="footer_navi_wrapper">
	<div id="footer_navi" class="clfix">
		
	</div>
	<!-- footer_nav_end -->
	</div>
	<!-- footer_navi_wrapper_end -->
	<div id="footer_wrapper">
	<div id="footer" class="clfix">
	<ul>
	<li class="company_menu"><a class="blank" href="#/">关于我们</a></li>
	<li><a href="#">联系我们</a></li>
	<li class="footer_copy_last"><a href="#">常见问题</a></li>
	<li class="copyright">京ICP备09034609号-7 京公网安备 110105011217 <a class="blank" href="http://www.uangel.com/">安捷乐通信技术有限公司</a> 版权所有 ©2010-2012</li>
	</ul>
	</div>
	<div class="copyright">
	
	</div>
	<!-- footer_end -->
	</div>
	<!-- footer_wrapper_end -->
</div>
<!-- wrapper_end -->

<div id="footer_info_wrapper">
<div id="foot_info" class="clfix">
	<div id="showCount" style="color:white;font-size: 20px;FONT-FAMILY: '黑体 ', 'Courier   New ', 'tahoma ';text-align: center;">
	</div>
<!-- sherbox_end -->	
</div>
<!-- foot_info_end -->
</div>
<!-- footer_info_wrapper_end -->
<script type="text/javascript">// <![CDATA[
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-23232579-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
// ]]></script>

</body>
</html>