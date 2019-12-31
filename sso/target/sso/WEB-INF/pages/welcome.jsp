<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%
	String host = request.getServerName();
	int port = request.getServerPort();
	String contextPath = request.getContextPath();
	request.setAttribute("host", host);
	request.setAttribute("port", port);
	request.setAttribute("contextPath", contextPath);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="http://${host}:${port}${contextPath}/" /><!-- 注意写好base -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="bin/js/jquery-1.4.js"></script>
<script type="text/javascript" src="bin/js/framework.js"></script>
<link href="bin/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=contextPath%>/bin/"/>
<!--框架必需end-->

<!--修正IE6不支持PNG图start-->
<script type="text/javascript" src="bin/js/method/pngFix/supersleight.js"></script>
<!--修正IE6不支持PNG图end-->

<!--鼠标移入变色start-->
<script>
	$(function(){
		$(".navIcon").hover(function(){
			$(this).addClass("navIcon_hover");
		},function(){
			$(this).removeClass("navIcon_hover");
		})
	})
</script>
<!--鼠标移入变色end-->
</head>
<body>
<div  id="scrollContent">
	<div class="welcome">
		 <div class="welcomeTitle"></div>
	</div>	
</div>
</body>
</html>