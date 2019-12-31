<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>个人设置</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/system/css/system_common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/system/css/setPersonal.css" />

</head>
<body>
    <div class="wrap">
        <div class="workorder-breadcrumb">
		    <span class="workorder-breadcrumb-icon"></span>
		    <span>系统设置</span>
		    <span>&nbsp;&gt&nbsp;</span>
		    <span>个人设置</span>
		</div>
        <form action="" method="post">
        <div class="personal">
        	<div class="title1">个人资料</div>
            <input type="hidden" type="text"  id="userDetailId" value="" maxlength="10"/>
        	
        	<ul>
        		<li><label for="pet">帐&emsp;&emsp;号：</label><input type="text"  id="userAccount" value="" maxlength="10"/></li>
        		<li><label for="pet">角&emsp;&emsp;色：</label><input type="text"  id="roleName" value="" maxlength="10"/></li>
        		<li><label for="pet">昵&emsp;&emsp;称：</label><input type="text"  id="nickName" value="" maxlength="10"/></li>
        		<li><label for="name">姓&emsp;&emsp;名：</label><input type="text"  id="name" value="" maxlength="5"/></li>
        		<li><label for="mail">邮&emsp;&emsp;箱：</label><input type="email"  id="mail" value="" /></li>
        	</ul>
        	<div id="alterUserDetail" style="text-align: center;margin-top: 52px;">
        		<input type="submit"  value="确定" class="submitButton"  onclick="alterDetail()" />
        	</div>
        </div>
        </form>
        <div class="tel">
        	<div class="title1">修改手机号</div>
        	<ul>
        		<li><label for="name">验&ensp;证&ensp;码：</label><input type="text"  id="code" value="" maxlength="5"/><a class="number" href="javascript:void(0);" onclick="sendMessage()">获取验证码</a>
        		<p class="ps">请输入旧手机号上获取的验证码</p></li>
        		<li><label for="tel">手&ensp;机&ensp;号：</label><input type="tel"  id="mobileNew" value="" maxlength="11"/></li>
        	</ul>
        	<div id="alterMobile"  style="text-align: center;margin-top: 41px;">
        		<input type="submit"  onclick="alterMobile()"  value="确定"  class="submitButton"/>
        	</div>
        </div>
        <div class="password">
        	<div class="title1">修改密码</div>
        	<ul>
        		<li><label for="">旧&ensp;密&ensp;码：</label><input type="password"  id="password" value="" maxlength="15"/>
        		<p class="ps">请输入当前登录密码</p></li>
        		<li><label for="">新&ensp;密&ensp;码：</label><input type="password"  id="passwordNew" value="" maxlength="15"/>
        		<p class="ps">密码必须为6-15位字母与数字的组合</p></li>
        		<li><label for="">确认密码：</label><input type="password"  id="passwordRe" value="" maxlength="15"/>
        		<p class="ps">请再次输入新密码确认</p></li>
        	</ul>
        	<div id="alterPassword" style="text-align: center;margin-top: 36px;">
        		<input type="submit"  onclick="alterPassword()"  value="确定"  class="submitButton"/>
        	</div>
        </div>
    </div>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/system/js/setPersonal.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/peopleManagement/js/checkForInput.js"></script>
   
</body>
</html>

























