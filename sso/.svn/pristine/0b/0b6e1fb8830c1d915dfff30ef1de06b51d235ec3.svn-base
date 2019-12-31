<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="com.jlit.uaas.util.*" %>
<%@ page language="java" import="com.jlit.uaas.enums.*" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page language="java" import="com.jlit.uaas.service.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取存在cookie中的用户账号
String loginName=CookieUtil.getCookieValue(request, response, LoginKeys.USERACCOUNT.getValue());
request.setAttribute("loginName", loginName);
//spring容器
WebApplicationContext ctx = null;
ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
LoginService loginService=(LoginService)ctx.getBean("loginService");
//获取密码连续输入错误次数限制后登录需要验证码
int petl=loginService.getPassErrerTimsLimitVevifiCodeNeed();
request.setAttribute("passErrerTimsLimitVevifiCodeNeed", petl);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>登录页面</title>
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/css/login.css" />
    <link rel="shortcut icon"  href="bin/loginuoms/images/ywhlzt.ico" />
</head>
<body>
    <div class="wrap">
        <div class="header">
            <div class="logo-wrap">
                <div class="logo"></div>
            </div>
        </div>
        <div class="content">
            <div class="content-wrap">
                <div class="login-wrap">
                	<form id="authZForm" action="oauth2/authorize" method="post" >
	                    <div class="account">综合业务管理平台</div>
	                    <div class="login-phone">
	                        <input id = "userAccount" name="userAccount" value="${loginName}"  class="phone" type="text" placeholder="请输入手机号"    autocomplete="new-password"/>
	                        <div class="phone-delete"></div>
	                    </div>
	                    <div class="login-password">
	                         <input id = "password" class="password" name="password"  type="password" placeholder="请输入密码"       autocomplete="new-password"/>
	                        <div class="password-delete"></div>
	                    </div>
	                    <div class="popup-info clearfix" id="vevifiCode_div" style="display:none;">
	                        <div class="write-code fl">
	                            <input type="text" class = "verification_code" id="vevifiCode" name="vevifiCode" placeholder="验证码" >
	                        </div>
	                        <div class="write-img fr">
	                            <img id="validateCodeImg" alt="验证码图" src="/sso/validateCode" class = "validateCodeImg">
	                        </div>
	                    </div>
	                    <div class="verify-info clearfix">
	                    	<div id="error-info-detail"  class="info-detail fl">${msg}</div>
	                        <div class="forget-password fr"><a href="retrievepassword.jsp">忘记密码?</a></div>
	                    </div>
	                    <div class="register" id = "login_btn">登录</div>
	                    <div class="register" id = "login_loading" style="display: none;"><img alt="" src="/sso/images/loading.gif" style="width: 30px;height: 30px;margin-top: 5px;"></div>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer">
            <p class="footer-info">
                Copyright©广州合立正通信息科技有限公司&nbsp;&nbsp;&nbsp;粤ICP备11032073号-2
            </p>
        </div>
    </div>

    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript"	src="bin/loginuoms/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="bin/loginuoms/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="bin/loginuoms/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="bin/loginuoms/common/plugin/common.js"></script>
    <script type="text/javascript"	src="js/uoms/login.js"></script>
</body>
</html>