<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@page import="java.util.*"%>
<%
	String host = request.getServerName();
	int port = request.getServerPort();
	String contextPath = request.getContextPath();
	request.setAttribute("host", host);
	request.setAttribute("port", port);
	request.setAttribute("contextPath", contextPath);
	//当前系统模块
	String crruApp =  (String) request.getSession().getAttribute("appStr");
	//随机数
	long random=System.currentTimeMillis();
	request.setAttribute("random", random);
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="http://${host}:${port}${contextPath}/" /><!-- 注意写好base -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>众创服务平台</title>
<!--框架必需start-->
<link href="bin/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="bin/skins/sky/import_skin.css" rel="stylesheet" type="text/css" id="skin" themeColor="blue" prePath="<%=contextPath%>/bin/"/>

<script type="text/javascript" src="bin/js/jquery-1.4.js"></script>
<script type="text/javascript" src="bin/js/bsFormat.js"></script>
<!--框架必需end-->

<!--引入组件start-->
<script type="text/javascript" src="bin/js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="bin/js/attention/zDialog/zDialog.js"></script>
<script type="text/javascript" src="bin/js/attention/floatPanel.js"></script>
<!--引入弹窗组件end-->
<script type="text/javascript" src="bin/js/form/loadmask.js"></script>
<script>
	$(function(){
		//当前进入的平台中心
		var selected_platform='${selected_platform}';
		
		$(".tab_bar li a").each(function(i){//点击横向主tab
			
			$(this).click(function(){
				$(".tab_bar li a").removeClass("current");
				$(this).addClass("current");
				//alert(i);
				var code=$(this).attr('code');
				//alert(code);
				//当前点击的中心与已经进入的平台中心不一致时才切换平台中心
				if(selected_platform!=code){
					changeSysCenter(code);
				}
				//jQuery.jCookie('htabIndex',i.toString());
			})
		})
		$(".tab_bar_content li a").each(function(i){//点击tab下的横向子栏目
			$(this).click(function(){
				$(".tab_bar_content li a").removeClass("current");
				$(this).addClass("current");
				document.getElementById("frmleft").contentWindow.showTab(i)
				jQuery.jCookie('htabLiIndex',i.toString());
			})
		})
	})
</script>
<script>
	/**
	*最顶层的Dialog弹出mask
	*/
	function topDialogMask(msg){
		//_DialogDiv_0是最顶层的dialog的id
		$("#_DialogDiv_0").mask(msg);
	}
	/*
	*去除最顶层Dialog的遮盖层
	*/
	function topDialogUnMask(){
		$("#_DialogDiv_0").unmask();
	}
	/*
	*修改密码
	*/
	function changeMyPassword(urladdr){
		var diag = new top.Dialog();
		diag.Title = "修改用户密码";
		diag.URL = urladdr;
		diag.Width = 460;
		diag.Height= 220;
		
		
		diag.OKEvent = function(){
			var commitResult = diag.innerFrame.contentWindow.commit();//提交结果  只有提交成功才返回true
			if(commitResult){
				diag.close();
				location.href=location.href;
			}
		};
		diag.show();
		showProgressBar();
	}
	//切换页面
	function qhPage(url) {
		location.href = url;
	}
	//设置cookie
	function setCookie(c_name,value,expiredays)
	{
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+expiredays)
		document.cookie=c_name+ "=" +escape(value)+
		((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	}
	/**
	* 切换导航中心
	*/
	function changeSysCenter(center){
		var accessToken=$('#accessToken').val();
		if(center){
			$.get('/uaas/changeSysCenter',{
				sysCenter:center,
				accessToken:accessToken,
				r:new Date().getTime()
				},function(data){
					//alert(data);
					location.href='main.html?r='+new Date().getTime() ;
			});
		}
		
		//location.href='/uaas/changeSysCenter?sysCenter='+center+'&accessToken='+accessToken+'&r='+new Date().getTime();
		
	}
	//页面加载完成执行
	$(document).ready(function(){
		  setInterval("checkUserAccountInValid()",3000);//1000为1秒钟
	});
	//判断账号是否失效
	//当前页面保存的用户账号和session中不一致则失效
	var loginedAccount='${userAccount}';
	function  checkUserAccountInValid(){
		$.get('/uaas/res/session/getLoginUserAccount',{
			r:new Date().getTime()
		},function(userAccount){
			if(userAccount && userAccount!=loginedAccount ){
				alert('用户已下线');
				location.href='/uaas/';
			}
		});
	}
	
</script>
<style>
a {
	behavior:url(bin/js/method/focus.htc)
}
</style>

</head>
<body id="body">
<input type="hidden" id="accessToken" value="${accessToken}"/><!-- 隐藏域 accessToken -->
<input type="hidden" id="returnMsg" value="${returnMsg }"/>	
<div id="floatPanel-1"></div>		
<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter" class="tab_barBanner">
	<div id="bs_bannerleft">
	<div>
		<div class="bs_banner_logo_hmenu" style="width: 124px;top: 20px;left: 5px;display: none;"></div>
		<div class="bs_banner_title" style="top: 25px;left: 5px;"></div>
		<c:forEach items="${platforms}"  var="p">
		    <c:if test="${selected_platform==p.code}">
    		   <div class="tabBarUserInfo" style="left:195px;top:35px;font-size: 18px;font-family:黑体;">${p.url}</div>
		    </c:if> 
		</c:forEach>
		
		<div class="tabBarFunction">
				<span class="icon_key hand" onclick="changeMyPassword('/uums/updateMyPassword.html?accessToken=${accessToken}');">修改密码</span>
				<span class="icon_exit hand" onclick='top.Dialog.confirm("确定要退出系统吗",function(){window.location="logout.jsp"});'>退出系统</span>
				<div class="clear"></div>
		</div>
		
		<div class="tabBarUserInfo" style="right:10px;">
				<li>
					欢迎${userName}用户，用户类型：【${userTypeName}】，今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>
				</li>
		</div>
	</div>
	</div>
	</div>
	<!-- 只有用户有权限的 -->
	<c:if test="${not empty platforms  && fn:length(platforms) > 1}">
	<div id="bs_navcenter" class="tab_barNav" style="height: 30px !important">
	<div id="bs_navleft">
	<div id="bs_navright">
		<div class="tab_bar">
			<ul>
				<!--各中心的系统 -->
				<c:forEach items="${platforms}"  var="p">
				    <li><a href="javascript:;"  <c:if test="${selected_platform==p.code}">class="current"</c:if> code="${p.code}">${p.name}</a></li>
				</c:forEach>
				
				<div class="clear"></div>
			</ul>
		</div>
		<div class="tab_bar_content" style="height: 1px;">
		</div>
	</div>
	</div>
	</div>
	</c:if>
</div>
<!--头部与导航end-->

<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
								<div id="lbox_topleft">
								<div id="lbox_topright">
									<div class="lbox_title">操作菜单</div>
								</div>
								</div>
								</div>
								<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
								<div id="lbox_middleright">
										<div id="bs_left">
										<IFRAME scrolling="no" width="100%"  frameBorder=0 id=frmleft name=frmleft src="left.html?r=${random}"  allowTransparency="true"></IFRAME>
										</div>
										<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
								</div>
								</div>
								<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
								</div>
								</div>
							</div>
		</td>
		<!--左侧区域end-->
		
		<!--中间栏区域start-->
		<td class="main_shutiao">
			<div class="bs_leftArr" id="bs_center" title="收缩面板"></div>
		</td>
		<!--中间栏区域end-->
		
		<!--右侧区域start-->
		<td class="ali01 ver01"  width="100%">
							<div id="rbox">
								<div id="rbox_topcenter">
								<div id="rbox_topleft">
								<div id="rbox_topright">
									<div class="rbox_title">
										操作内容
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_middlecenter">
								<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right" style="height:100%">
									       <IFRAME scrolling="scroll"  style="" width="100%"  height="100%" frameBorder=0 id=frmright name=frmright src="/uaas/welcome.html"  allowTransparency="true"></IFRAME>
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_bottomcenter" >
								<div id="rbox_bottomleft">
								<div id="rbox_bottomright">

								</div>
								</div>
								</div>
							</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>

<!--尾部区域start-->
<div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright" class="white" style="font-size:12px;">
		Copyright ©2016&nbsp;&nbsp;<a href="http://www.hori-gz.com" target="_blank" >广州合立正通信息科技有限公司</a>
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->
</body>
</html>
