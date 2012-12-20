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
			$("#accountName").focusout(function(){
				var accountName=$("#accountName").val();
				if(accountName==""){
					alert('姓名不能为空');
				}
			});
			$("#accountTell").focusout(function(){
				var reg1=/(\(\d{3}\)|\d{3}-)?\d{8}/g;//八位电话号码
                var reg2=/(\(\d{3}\)|\d{3}-)?\d{7}/g;//七位电话号码
                var accountTell=$("#accountTell").val();
                if (accountTell!="") {
                   if (!reg1.test(accountTell)||!reg2.test(accountTell)){
                  	alert('电话格式错误');
                   }
               }
				
			});
			if("${member.account_type}"=="10"){
				$("#account_type").text("广告商");
			}else if("${member.account_type}"=="01"){
				$("#account_type").text("程序开发者");
			}else {
				$("#account_type").text("广告商  ，程序开发者");
			}
			$("#accountCell").focusout(function(){
				var accountCell=$("#accountCell").val();
				if(accountCell.length!=11){
					alert('请输入正确的移动电话');
				}
			});
			$("#accountHpage").focusout(function(){
				 var pattern=/^(http:\/\/)?[a-zA-Z0-9-]+(\.[a-zA-z0-9-]+)+\/?$/gi;
				 var accountHpage=$("#accountHpage").val();
                 if(!pattern.test(accountHpage))
                 {
                  	alert('网址错误');
                 }
				
			});
			$("#accountZipcode").focusout(function(){
				 var reg=/\d{6}/g;
				 var accountZipcode=$("#accountZipcode").val();
                 if (accountZipcode!="") {
                    if (!reg.test(accountZipcode)){
                    	alert('邮编错误');
                    }
                 }
			});
			$("#accountSubmit").click(function(){
			
			     var accountName=$("#accountName").val();
			     var accountTell=$("#accountTell").val();
			     var accountCell=$("#accountCell").val();
			     var accountHpage=$("#accountHpage").val();
			     var accountZipcode=$("#accountZipcode").val();
			     
                 var reg2=/(\(\d{3}\)|\d{3}-)?\d{7}/g;//七位电话号码
                 var pattern=/^(http:\/\/)?[a-zA-Z0-9-]+(\.[a-zA-z0-9-]+)+\/?$/gi;//网址
                 var reg=/\d{6}/g;//邮编
			     if(accountName==""){
				 alert('姓名不能为空');
				 }else if (accountTell!="" && !reg2.test(accountTell)) {
                    
                    alert('电话格式错误');
                   
                }else if(accountCell!=""  && accountCell.length!=11){
				    
				    alert('请输入正确的移动电话');
				    
				}else if(accountHpage!="" && !pattern.test(accountHpage)){
				
				   
                    alert('网址格式错误');
                    
			    }else if (accountZipcode!="" && !reg.test(accountZipcode)) {
                    
                    alert('邮编错误');
                   
                }else {
                $("#accountForm").submit();
                }
			
			});
			
		});
	</script>
  </head>
  
  <body>
  	<div>
  	     <jsp:include page="../../jsp/common/head.jsp?id=member"></jsp:include>
  	
		<div id="content" style="margin: 0 auto;width:972px;white-space:nowrap;">
			<jsp:include page="../../jsp/common/leftmenu.jsp?id=baseInfo"></jsp:include>
		    <div class="Border" style="font-family:Arial, Helvetica, sans-serif;font-size:12px">
		    	<div>
		    		<span style="background:url(<%=basePath%>/styles/images/lelfround.png);" class="left"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/centerround.png) repeat-x 0 0;width: 682px;" class="center"></span>
		    		<span style="background:url(<%=basePath%>/styles/images/rightround.png);" class="right"></span>
		    	</div>
		    	<form action="<%=basePath%>ctrl/updateMemberInfo.mem" method="post" id="accountForm">
			    <table id="ad" align="center" style="margin-top:40px">
			    	<tr>
			    		<td>注册账号：</td>
			    		<td><label style="color:black" >${member.email}</label></td>
			    	</tr>
			    	<tr>
			    		<td>姓名：</td>
			    		<td><input name="name" type="text" value="${member.name}" id="accountName" onclick="new Calendar().show(this); "/></td>
			    	</tr>
			    	<tr>
			    		<td>固定电话：</td>
			    		<td><input name="telephone" type="text" value="${member.telephone}" id="accountTell" onclick="new Calendar().show(this); "/></td>
			    	</tr>
			    	<tr>
			    		<td>移动电话：</td>
			    		<td><input type="text" name="cellphone" value="${member.cellphone}" id="accountCell"></td>
			    	</tr>
			    	<tr>
			    		<td>账户类型：</td>
			    		<td><label style="color:black" id="account_type" ></label>
			    			</td>
			    	</tr>
			    	<tr>
			    		<td>QQ：</td>
			    		<td><input type="text" value="${member.qq}" name="qq"></td>
			    	</tr>
			    	<tr>
			    		<td>企业名称：</td>
			    		<td><input type="text" name="com_name" value="${member.com_name}"></td>
			    	</tr>
			    	<tr>
			    		<td>企业网址：</td>
			    		<td><input type="text" value="${member.com_homepage}" name="com_homepage" id="accountHpage"></td>
			    	</tr>
			    	<tr>
			    		<td>公司地址：</td>
			    		<td><input type="text" value="${member.com_address}" name="com_address"></td>
			    	</tr>
			    	<tr>
			    		<td>邮编：</td>
			    		<td><input type="text" value="${member.zipcode}" name="zipcode" id="accountZipcode"></td>
			    	</tr>
			    	<tr>
			    		<td>&nbsp;</td>
			    		<td><button type="button" id="accountSubmit">保存</button></td>
			    	</tr>
			    </table>
			    </form>
		    </div>
		</div>
  	</div>
  </body>
</html>
