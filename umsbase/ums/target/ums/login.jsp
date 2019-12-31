<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page language="java" import="com.hori.service.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	//spring容器
	WebApplicationContext ctx = null;
	ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	UserService userService=(UserService)ctx.getBean("userService");
	//获取密码连续输入错误次数限制后登录需要验证码
	int petl=userService.getPassErrerTimsLimitVevifiCodeNeed();
	request.setAttribute("passErrerTimsLimitVevifiCodeNeed", petl);
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="login">
	<meta http-equiv="description" content="login">
    <title>登录页面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/homepage/css/login.css" />
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/md5.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/homepage/js/login.js"></script>   
       
    <script type="text/javascript">
	$(function() {
		var sessionInfo_userId = '${sessionInfo.userId}';
		if (sessionInfo_userId) {/*目的是，如果已经登陆过了，那么直接跳转到index页面*/
			window.location.href = "index.jsp";
		}
	});
    function  checkValidateCodeShowOrHide(){
    	//多少密码错误显示验证码
    	var passErrerTimsLimitVevifiCodeNeed=${passErrerTimsLimitVevifiCodeNeed};//多少密码错误显示验证码
    	var userAccount=$('#userAccount').val();
    		userAccount=$.trim(userAccount);
    		var isPass = '';
    		/**
    	     * 判断验证码显示区域的显示和隐藏
    	     */
    	     $.ajaxSetup({ 
    	    	    async : false 
    	    	});
    	$.get('/ums/getLoginPassErrerTimes',{
    		userAccount:userAccount,
    		r:new Date().getTime()
    	},function(data){
    		//alert("用户当天连续输入密码错误次:"+data);
    		var passErrerTimes=parseInt(data);
    		$('#passErrerTimes').val(passErrerTimes);
    		if(passErrerTimes>=passErrerTimsLimitVevifiCodeNeed){
    			isPass = 'true';
    		}
    	});

    	return isPass;
    }
    </script>
</head>
<body>
<form id="loginForm" action="/ums/userAction!login.html"
			method="post" >
			 <input type="hidden" id="codeShowOrHide"  value="" />
    <div class="wrap">
        <div class="header">
            <div class="logo-wrap">
                <div class="logo"></div>
            </div>
        </div>
        <div class="content">
            <div class="content-wrap">
                <div class="login-wrap">
                    <div class="account">账号登录</div>
                    <div class="login-phone">
                        <input id = "userAccount" name="userAccount"  class="userAccount" type="text" placeholder="请输入手机号"    autocomplete="new-password"/>
                        <div class="phone-delete"></div>
                    </div>
                    <div class="login-password">
                        <input id = "password" class="password" name="password"  type="password" placeholder="请输入密码"       autocomplete="new-password"/>
                        <div class="password-delete"></div>
                    </div>
                    <div class="verify-info clearfix">
                        <div id="error-info-detail"  class="info-detail fl">${msg}</div>
                        <div class="forget-password fr"><a href="retrievepassword.jsp">忘记密码?</a></div> 
                    </div>
                    <div class="register" >登录</div>
                </div>
            </div>
        </div>
        
        <div class="footer">
            <p class="footer-info">
                Copyright©广州合立正通信息科技有限公司&nbsp;&nbsp;&nbsp;粤ICP备11032073号-2
            </p>
        </div>
    </div>
    </form>
    <!-- 验证码遮罩 -->
    <div class="mask">
    </div>
    <div class="popup">
    <div class="popup-delete"></div>
        <div class="popup-title">请输入图像验证码</div>
        <div class="popup-info clearfix">
            <div class="write-code fl">
                <input class = "verification_code" type="text" placeholder="验证码">
            </div>
            <div class="write-img fl">
                <img id="validateCodeImg" class = "validateCodeImg" alt="验证码" >
            </div>
        </div>
        <div class="popup-error">
            <div class="popup-errordetail"></div>
        </div>
        <div class="confirm">确定</div>
    </div>
</body>
</html>