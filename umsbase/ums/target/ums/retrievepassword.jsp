<%@ page language="java" pageEncoding="UTF-8"%>
<%
	 String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
 <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>找回密码</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/homepage/css/retrievepassword.css" />
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/homepage/js/retrievepassword.js"></script>
</head>
<body>
 <input type="hidden" id="codeShowOrHide"  value="" />
    <div class="wrap">
        <div class="header">
            <div class="logo-wrap">
                <div class="logo fl"><img src="<%=basePath%>/homepage/images/login_logo1.png"/></div>
            </div>
        </div>
        <div class="content-wrap">
            <div class="content">
                <div class="password-title">找回密码</div>
                <div class="title-border"></div>
                <!--  找回密码 -->
                <div class="find-box">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log2"></div>
                            <div class="flow-title2">找回密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow2 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">重置密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow3 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">完成</div>
                        </div>
                    </div>
                    <div class="first-password">
                        <input  id="inphone" class ="inphone"  type="text" placeholder="请输入手机号码"  autocomplete="new-password"/>
                        <div class="delete-first"></div>
                    </div>
                    <div id="get-message-parentdiv" class="second-password clearfix">
                        <input id = "message-fl" class="fl" type="text" placeholder="请输入短信验证码"  autocomplete="new-password"/>
                        <div id = "get-message" class="get-message fr">获取验证码</div>
                        <div class="delete-second"></div>
                    </div>
                    <div class="error-info">
                        <div class="error-tip"></div>
                    </div>
                    <div id="next-step" class="next-step">下一步</div>
                    <!-- <div class="return-step">返回</div> -->
                </div>
                <!-- 重置密码 -->
                <div class="reset-box">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">找回密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow2 fl">
                            <div class="flow-log2"></div>
                            <div class="flow-title2">重置密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow3 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">完成</div>
                        </div>
                    </div>
                    <div class="first-password">
                        <input id="first-password-fl" type="password" placeholder="请输入6-20字母与数字组合的密码"   autocomplete="new-password"/>
                        <div class="delete-first"></div>
                    </div>
                    <div class="second-password">
                        <input id="second-password-fl"  type="password" placeholder="请再次确认密码"  autocomplete="new-password"/>
                        <div class="delete-second"></div>
                    </div>
                    <div class="error-info">
                       <!--  <div class="error-tip">两次输入密码不一致</div> -->
                         <div id='reset-password-info' class="error-tip"></div> 
                    </div>
                    <div id="reset-next-step" class="next-step">下一步</div>
                </div>
                <!-- 重置密码--完成 -->
                <div class="reset-ok">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">找回密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow2 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">重置密码</div>
                        </div>
                        <div class="flow-line fl">
                            <div class="line-style"></div>
                        </div>
                        <div class="flow3 fl">
                            <div class="flow-log2"></div>
                            <div class="flow-title2">完成</div>
                        </div>
                    </div>
                    <div class="complete">
                        <div class="complete-logo">
                        </div>
                        <div class="complete-text">重置密码成功！</div>
                    </div>
                    <div id="gotoLogin" class="next-step">前往登录</div>
                </div>
            </div>
        </div>
    </div>
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
               <!-- <div id= "validateCodeImg"  class="code fr">动态验证码</div> src="/uoms/validateCode"-->
               <img id="validateCodeImg" class = "validateCodeImg" alt="验证码"    style=" height: 40px;vertical-align:bottom;">
        </div>
        <div class="popup-error">
            <div class="popup-errordetail"></div>
        </div>
        <div class="confirm">确定</div>
    </div>
</body>
</html>