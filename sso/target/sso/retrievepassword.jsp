<%@ page language="java" pageEncoding="UTF-8"%>
<%
	 String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>输入手机验证码</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="bin/loginuoms/css/retrievepassword.css" />
    
    <link rel="shortcut icon"  href="bin/loginuoms/images/ywhlzt.ico" />

</head>
<body>
    <div class="wrap">
        <div class="header">
            <div class="logo-wrap">
                <div class="logo"></div>
            </div>
        </div>
        <div class="content-wrap">
            <div class="content">
                <div class="password-title">找回密码</div>
                <div class="title-border"></div>
                <!--  输入手机验证码 -->
                <div class="find-box">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log2"></div>
                            <div class="flow-title2">输入手机验证码</div>
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
                    <!-- 
                    <div class="first-password">
                        <input  id="account" class ="inphone"  type="text" placeholder="请输入账号"  autocomplete="new-password"/>
                        <div class="delete-three"></div>
                    </div> -->
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
                    
                     <div class="next-step clearfix">
                        <div id = "return-step" class="back-to fl">取消</div>
                        <div id="next-step" class="next-to fr">下一步</div>
                    </div>
                </div>
                <!-- 重置密码 -->
                <div class="reset-box">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">输入手机验证码</div>
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
                         <div id='reset-password-info' class="error-tip"></div> 
                    </div>
                    <div class="next-step clearfix">
                        <div id="return-next-step" class="back-to fl">上一步</div>
                        <div id="reset-next-step" class="next-to fr">下一步</div>
                    </div>
                </div>
                <!-- 重置密码--完成 -->
                <div class="reset-ok" style="display: none">
                    <div class="reset-flow clearfix">
                        <div class="flow1 fl">
                            <div class="flow-log1"></div>
                            <div class="flow-title1">输入手机验证码</div>
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
                        <div class="complete-text">完成！请重新登录</div>
                    </div>
                     <div id="gotoLogin" class="next-step next-to">重新登录</div>
                </div>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript"	src="bin/loginuoms/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="bin/loginuoms/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="bin/loginuoms/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="bin/loginuoms/common/plugin/common.js"></script>
    <script type="text/javascript"	src="js/uoms/retrievepassword.js"></script>
</body>
</html>