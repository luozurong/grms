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
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>众创服务平台-登录</title>
     <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
	<link rel="stylesheet" href="bin/login/skin4/common.css">   
	<link rel="stylesheet" href="bin/login/skin4/style.css"> 
	
	<style>
	/*提示信息*/	
	.login_info{
		color:red;
		/*position:absolute;*/
		left:580px;/*提示信息x坐标*/
		top:360px;/*提示信息y坐标*/
		line-height:150%;
		overflow:hidden;
	}
	 .login_con .inp{
	 line-height: 38px;
	 }
	/*提示信息*/
	</style>	
	<!--修正IE6支持透明png图片start-->
	<!--修正IE6支持透明png图片end-->
	

</head>
<body>
    <!-- header 头部-->
	<div class="header">
        <h1>
            <span>欢迎来到</span>
           众创服务平台
        </h1>
    </div>
    <!-- main 主体-->
    <div class="main_con clearfix" id="main_div">
        <div class="login_main">
            <div class="login_con" style="height: 410px;">
                <h2>账号登录<span id="error" class="login_info" style="font-size: 14px;text-align: center;margin-left: 60px;display: none;">请输入账号</span></h2>
                <div class="form_out">
                    <form id="authZForm" action="oauth2/authorize" method="post" >
                    	
                       <input type="hidden" name="action" value="login" />
		           	   <input type="hidden" name="client_id" value="${client_id}" />
					   <input type="hidden" name="redirect_uri"   value="${redirect_uri}"/>
					   <input type="hidden" name="response_type" value="${response_type}"/>
		       	       <input type="hidden" name="state" value="${state }"/>
                        <div class="inp_div">
                            <label class="lab_left">账号：</label> 
                            <input type="text"  class="inp" id="userAccount" name="userAccount" value="${loginName}"  onkeypress="if(event.keyCode==13) {login();}" onblur="checkValidateCodeShowOrHide()" onkeyup="checkValidateCodeShowOrHide()">
                        </div>
                        <div class="inp_div">
                            <label class="lab_left">密码：</label> 
                            <input type="password"  class="inp" id="password" name="password"  onkeypress="if(event.keyCode==13) {login();}">
                        </div>
                        <!-- 验证码显示区域 -->
                         <div class="inp_div" id="vevifiCode_div" style="display: none;">
                            <label class="lab_left"></label> 
                            <input type="text" id="vevifiCode" name="vevifiCode" style="width: 50px;vertical-align:bottom" class="inp" onkeypress="if(event.keyCode==13) {login();}"   >
                            <img id="validateCodeImg" alt="验证码" src="/uaas/validateCode" style=" height: 40px;vertical-align:bottom;">
                            <span style="vertical-align:bottom;margin-left: 5px;"><a href="javascript:;" onclick="changValidateCode()">换一个？</a></span>
                        </div>
                        <div class="inp_div">
                            <input type="button" class="btn" value="登录" onclick="login()" id="login_btn"  />
                            <span id="logining_span"  class="btn" style="text-align: center;display: none;"><img alt="" src="/uaas/images/loading.gif" style="width: 30px;height: 30px;margin-top: 5px;"></span>
                        </div>
                    
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- footer 底部-->
    <div class="footer" >
        <p><a href="http://www.hori-gz.com" target="_blank" style="color: black;">广州景联信息科技有限公司</a></p>
    </div>
    <div class="footer" id ="spank">
       
    </div>
	 <script type="text/javascript">
	 	//登录
	 	function login(){
	 		$('.login_info').hide();
	 		var userAccount=$('#userAccount').val();
	 		userAccount=$.trim(userAccount);
	 		var password=$('#password').val();
	 		password=$.trim(password);
	 		if(!userAccount){
	 			//alert('请输入用户账号');
	 			$('.login_info').html('请输入用户账号');
	 			$('#userAccount').focus();
	 			$('.login_info').show();
	 			return ;
	 		}
	 		if(!password){
	 			//alert('请输入密码');
	 			$('.login_info').html('请输入密码');
	 			$('#password').focus();
	 			$('.login_info').show();
	 			return;
	 		}
	 		//验证码，当验证码区域是显示时，才需要验证
	 		var vevifiCode_div_visible=$('#vevifiCode_div').is(':visible');
	 		var vevifiCode='';
	 		if(vevifiCode_div_visible){
	 			vevifiCode=$('#vevifiCode').val();
		 		vevifiCode=$.trim(vevifiCode);
		 		if(!vevifiCode){
		 			$('.login_info').html('请输入验证码');
		 			$('#vevifiCode').focus();
		 			$('.login_info').show();
		 			return;
		 		}
	 		}
	 		
	 		
	 		//隐藏登录按钮,显示登录请求中状态
	 		$('#login_btn').hide();
	 		$('#logining_span').show();
	 		//验证登录
	 		$.post('oauth2/login',{
	 			userAccount:userAccount,
	 			password:password,
	 			vevifiCode:vevifiCode,
	 			r:new Date().getTime()
	 		},function(data){
	 			
	 			if(data.result=='0'){
	 				//登录成功
	 				$('#authZForm').submit();
	 			}else{//登录失败
	 				//重新显示登录按钮
	 				$('#login_btn').show();
			 		$('#logining_span').hide();
	 				//alert(data.error);
	 				$('.login_info').html(data.error);
	 				$('.login_info').show();
	 				//判断验证码显示区域的显示和隐藏
	 				checkValidateCodeShowOrHide();
	 				//验证码不正确，则重新生成一个验证码
	 				if(data.result=='3'){
	 					changValidateCode();
	 				}
	 				
	 			}
	 			
	 		},'json');
	 	}
	 	
		/*
		切换验证码
		*/
		function changValidateCode(){
			$('#validateCodeImg').attr('src','/uaas/validateCode?'+new Date().getTime());
			$('#vevifiCode').val('');
		}
		//判断验证码显示区域的显示和隐藏
		function  checkValidateCodeShowOrHide(){
			var passErrerTimsLimitVevifiCodeNeed=${passErrerTimsLimitVevifiCodeNeed};//多少密码错误显示验证码
			var userAccount=$('#userAccount').val();
	 		userAccount=$.trim(userAccount);
			$.get('/uaas/getLoginPassErrerTimes',{
				userAccount:userAccount,
				r:new Date().getTime()
			},function(data){
				//alert("用户当天连续输入密码错误次:"+data);
				var passErrerTimes=parseInt(data);
				$('#passErrerTimes').val(passErrerTimes);
				if(passErrerTimes>=passErrerTimsLimitVevifiCodeNeed){
					$('#vevifiCode_div').show();
				}else{
					$('#vevifiCode_div').hide();
				}
			});
		
		}
		
		$(function(){ 
			//窗口高度
			var $windowHeight = jQuery(window).height();
			//alert(($windowHeight));
			if($windowHeight>696){
				$('#spank').css({height: ($windowHeight-696)+"px"});
			}
			checkValidateCodeShowOrHide();
		}); 
	 
	 </script>
</body>
</html>
