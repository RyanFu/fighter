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
    <title>安捷乐广告平台</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/scripts/jquery/themes/gray/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/scripts/jquery/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/scripts/table/buildertable.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/css/public.css'/>">
	
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery.easing.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/table/buildertable.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/ymweb.min.js'/>"></script>
	
	<script type="text/javascript">
		$(function(){
			getCurShowCount();
		});
		
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

	<style type="text/css">
		BODY {
			MIN-WIDTH: 1000px; FONT: 12px/1.2 微软雅黑,helvetica,tahoma,arial,宋体; COLOR: #444; BACKGROUND-COLOR: #fff
		}
		#content {
			Z-INDEX: 1; MIN-HEIGHT: 372px; PADDING-BOTTOM: 256px; HEIGHT: auto! important
		}
	</style>
  </head>
  
  <body>
  	<div>
		<jsp:include page="./jsp/common/head.jsp?id=index"></jsp:include>

		<div id="content">
			<div id=slider>
				<ul>
					<li class=hide id=slider-1>
						<div class=bd>
							<h2>
								想怎么投，就怎么投，随心所欲
							</h2>
							<p>
								按照您产品的定位，选择不同的用户群，
								<br>
								手机型号，运营商，用户性别，应用类型，
								<br>
								消费人群，时间段，地理位置等等。
							</p>
							<a class=sc_register href="jsp/member/register.jsp" style="margin-left:50px">报名</a>
						</div>
					</li>
					<li class=hide id=slider-2>
						<div class=bd>
							<h2>
								精确的统计数据让您跟好的了解您的产品
							</h2>
							<p>
								毕业后教师仍然关注学员，返回最新喜报！
								<br>
								多样化课程适合多级别人群，高薪工作不再是梦想。
							</p>
							<a class=sc_register href="jsp/member/register.jsp" style="margin-left:50px">报名</a>
						</div>
					</li>
					<li class=hide id=slider-3>
						<div class=bd>
							<h2>
								这么高的收入分红
								<br>
								开发者们你们还在等什么？
							</h2>
							<p>
								我们为开发者提供高达80%的收入分红
								<br>
								赶快在您的应用，游戏中
								<br>
								嵌入我的的广告吧！
							</p>
							<a class=sc_register href="jsp/member/register.jsp">报名</a>
						</div>
					</li>
					<li class=hide id=slider-4>
						<div class=bd>
							<h2>
								帮助您分析当前大众的爱好
							</h2>
							<p>
								看看您的那款应用受欢迎，
								<br>
								大家都对哪一类广告感兴趣。
							</p>
							<a class=sc_register href="jsp/member/register.jsp">报名</a>
						</div>
					</li>
				</ul>
			</div>
			
			<!-- 宣传栏导航 -->
			<div id=indicator>
				<ul>
					<li class=light id=indicator-1>
						<a class=it-link href="http://127.0.0.1:8080/uandroid/###" data-to="0"><strong>精确投放</strong><span>多种选择按您的产品定位投放</span>
						</a>
					</li>
					<li id=indicator-2>
						<a class=it-link href="http://127.0.0.1:8080/uandroid/###" data-to="1"><strong>统计方便</strong><span>让您跟好的了解您的产品</span>
						</a>
					</li>
					<li id=indicator-3>
						<a class=it-link href="http://127.0.0.1:8080/uandroid/###" data-to="2"><strong>开发者高收入</strong><span>您将获得80%的广告收入比例</span>
						</a>
					</li>
					<li id=indicator-4>
						<a class=it-link href="http://127.0.0.1:8080/uandroid/###" data-to="3"><strong>了解大众</strong><span>了解当前哪些类型的应用游戏受欢迎</span>
						</a>
					</li>
				</ul>
				<div class=clear>
					&nbsp;
				</div>
			</div>
		    <div style="background:url('<%=basePath%>img/ad_14.gif');float:left;margin-top:70px;width:964px;height:48px">
		    	<div id="showCount" style="padding:15px;color:white;font-size: 20px;FONT-FAMILY: '黑体 ', 'Courier   New ', 'tahoma '">
		    		
		    	</div>
		    </div>
			<!-- 合作伙伴 -->
			<DIV class="pattern" style="width:200px">
				<H3 style="margin-top:30px">
					合作伙伴
				</H3>
				<DIV id=partnerbar>
					<DIV id=move_logo>
						<DIV class=scroller>
							<TABLE>
								<TBODY>
									<TR>
										<TD>
											<A class=ml_1 title="合作媒体 - 艾媒网"
												href="http://www.iimedia.cn/" target=_blank>艾媒网</A>
										</TD>
										<TD>
											<A class=ml_2 title=诺基亚Nokia中文网
												href="http://nokia.tgbus.com/" target=_blank>诺基亚Nokia中文网</A>
										</TD>
										<TD>
											<A class=ml_3 title=Android中文网
												href="http://android.tgbus.com/" target=_blank>Android中文网</A>
										</TD>
										<TD>
											<A class=ml_4 title=Ophone中文网
												href="http://ophone.tgbus.com/" target=_blank>Ophone中文网</A>
										</TD>
										<TD>
											<A class=ml_5 title=聚购手机内容管理系统 href="http://e.juugoo.com/"
												target=_blank>聚购手机</A>
										</TD>
										<TD>
											<A class=ml_6 title=中国领先移动应用市场监测门户
												href="http://www.mmclick.com/" target=_blank>MMclick</A>
										</TD>
										<TD>
											<A class=ml_7 title=安智网 href="http://bbs.goapk.com/"
												target=_blank>安智网</A>
										</TD>
										<TD>
											<A class=ml_8 title=机锋网 href="http://www.gfan.com/"
												target=_blank>机锋网</A>
										</TD>
										<TD>
											<A class=ml_9 title=N多网 href="http://www.nduoa.com/"
												target=_blank>N多网</A>
										</TD>
										<TD>
											<A class=ml_a title=ChinaMeizu
												href="http://www.chinameizu.com/" target=_blank>ChinaMeizu</A>
										</TD>
										<TD>
											<A class=ml_b title=中国手机开发者联盟,做最好的服务!
												href="http://www.cmd100.com/" target=_blank>中国手机开发者联盟</A>
										</TD>
										<TD>
											<A class=ml_c title=发现分享安卓乐趣
												href="http://www.starandroid.com/" target=_blank>安卓星空</A>
										</TD>
										<TD>
											<A class=ml_d title=手机广告聚合平台-果合 href="http://guohead.com/"
												target=_blank>果合</A>
										</TD>
										<TD>
											<A class=ml_e title=移动广告聚合平台-Adview
												href="http://www.adview.cn/" target=_blank>AdView</A>
										</TD>
										<TD>
											<A class=ml_f title=广告聚合平台-芒果MOGO
												href="http://www.adsmogo.com/" target=_blank>芒果MOGO</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class=scroller>
							<TABLE>
								<TBODY>
									<TR>
										<TD>
											<A class=ml_1 title="合作媒体 - 艾媒网"
												href="http://www.iimedia.cn/" target=_blank>艾媒网</A>
										</TD>
										<TD>
											<A class=ml_2 title=诺基亚Nokia中文网
												href="http://nokia.tgbus.com/" target=_blank>诺基亚Nokia中文网</A>
										</TD>
										<TD>
											<A class=ml_3 title=Android中文网
												href="http://android.tgbus.com/" target=_blank>Android中文网</A>
										</TD>
										<TD>
											<A class=ml_4 title=Ophone中文网
												href="http://ophone.tgbus.com/" target=_blank>Ophone中文网</A>
										</TD>
										<TD>
											<A class=ml_5 title=聚购手机内容管理系统 href="http://e.juugoo.com/"
												target=_blank>聚购手机</A>
										</TD>
										<TD>
											<A class=ml_6 title=中国领先移动应用市场监测门户
												href="http://www.mmclick.com/" target=_blank>MMclick</A>
										</TD>
										<TD>
											<A class=ml_7 title=安智网 href="http://bbs.goapk.com/"
												target=_blank>安智网</A>
										</TD>
										<TD>
											<A class=ml_8 title=机锋网 href="http://www.gfan.com/"
												target=_blank>机锋网</A>
										</TD>
										<TD>
											<A class=ml_9 title=N多网 href="http://www.nduoa.com/"
												target=_blank>N多网</A>
										</TD>
										<TD>
											<A class=ml_a title=ChinaMeizu
												href="http://www.chinameizu.com/" target=_blank>ChinaMeizu</A>
										</TD>
										<TD>
											<A class=ml_b title=中国手机开发者联盟,做最好的服务!
												href="http://www.cmd100.com/" target=_blank>中国手机开发者联盟</A>
										</TD>
										<TD>
											<A class=ml_c title=发现分享安卓乐趣
												href="http://www.starandroid.com/" target=_blank>安卓星空</A>
										</TD>
										<TD>
											<A class=ml_d title=手机广告聚合平台-果合 href="http://guohead.com/"
												target=_blank>果合</A>
										</TD>
										<TD>
											<A class=ml_e title=移动广告聚合平台-Adview
												href="http://www.adview.cn/" target=_blank>AdView</A>
										</TD>
										<TD>
											<A class=ml_f title=广告聚合平台-芒果MOGO
												href="http://www.adsmogo.com/" target=_blank>芒果MOGO</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
		</div>
  	</div>
  </body>
</html>
