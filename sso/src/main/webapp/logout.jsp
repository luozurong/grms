<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.jlit.oauth.*"%>
<%
//spring容器
WebApplicationContext ctx = null;
ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
AbstractOauth2Handler oauth2Handler= (AbstractOauth2Handler)ctx.getBean("uaasOauth2Handler");
//在uaas系统登出系统的uri
request.setAttribute("oaas_loginout_uri", oauth2Handler.getLogoutUri());
//发起oauth认证请求的地址
String toAuthorizeUri= oauth2Handler.getToAuthorizeUri();
request.setAttribute("toAuthorizeUri", toAuthorizeUri);
//清楚session信息
response.addDateHeader("Expires",0);
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-cache");
session.invalidate();
%>  
<script type="text/javascript" src="bin/js/jquery-1.4.js"></script>  
<script type="text/javascript">
	//ajax跨域调用uaas登出系统的接口
	$.getScript('${oaas_loginout_uri}'+'?r='+new Date().getTime(),function(data){
			top.location.href="${toAuthorizeUri}";//重新发起oauth认证请求
	});
</script>

