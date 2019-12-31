<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.jlit.uaas.util.*" %>
<%@ page language="java" import="com.jlit.uaas.enums.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取存在cookie中的用户账号
String loginName=CookieUtil.getCookieValue(request, response, LoginKeys.USERACCOUNT.getValue());
request.setAttribute("loginName", loginName);

%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站连接 - ${application.name}</title>
<link type="text/css" href="css/web/oauth2/oauth_web.css" rel="stylesheet" />
<!--<style>
	body { padding-bottom:300px; }
</style>-->
</head>
<body class="WB_UIbody WB_widgets">
<div class="WB_xline1 oauth_xline" id="outer">
 <div class="oauth_wrap">
    <div class="oauth_header clearfix">
      <h1 class="WB_logo" title="新浪微博"><a href="http://www.kinglian.cn">景联科技</a></h1>
		<p class="login_account"><a class="sign_up_link" href="http://www.kinglian.net" target="_blank">官网</a>
		</p>
    </div>
    <!-- 带头像  -->
    <div class="WB_panel oauth_main">
    <form name="authZForm" id="authZForm" action="oauth2/authorize" method="post" node-type="form">
      <div class="oauth_content">
        <p class="oauth_main_info">欢迎使用帐号访问  <a href="javascript:;"  class="app_name">${application.name }</a> 
        ，并同时登录健康医疗服务平台系统</p>
        	
        <!-- 登录 -->
        	<div class="oauth_login clearfix">
	           <form name="authZForm" action="oauth2/authorize" method="post" > 
	           	   <input type="hidden" name="action" value="login" />
	           	   <input type="hidden" name="client_id" value="${client_id}" />
				   <input type="hidden" name="redirect_uri"   value="${redirect_uri}"/>
				   <input type="hidden" name="response_type" value="${response_type}"/>
	       	       <input type="hidden" name="state" value="${state }"/>
					<div class="oauth_login_form">
			            <p class="oauth_login_01" >
			              <label class="oauth_input_label">帐号：</label>
			              <input type="text" class="WB_iptxt oauth_form_input" id="userAccount" name="userAccount" value="${loginName}"  onkeypress="if(event.keyCode==13) {login();}"   autocomplete="off" tabindex="1" />
			            </p>
			            <p>
			              <label class="oauth_input_label">密码：</label>
			              <input type="password" class="WB_iptxt oauth_form_input" id="password" name="password"  onkeypress="if(event.keyCode==13) {login();}" autocomplete="off" tabindex="2"/>
			            </p>
						<p class="oauth_code" node-type="validateBox" style="display:none" >
						  <label class="oauth_input_label">验证码：</label>
						  <input type="text" tabindex="3" node-type="vcode" class="WB_iptxt oauth_form_input oauth_form_code"><span class="code_img"><img node-type="pincode" width="75" height="30" /></span><a class="WB_text2"  node-type="changeCode" href="#">换一换</a>
						</p>
						<p class="oauth_code" node-type="vdunBox" style="display:none">
						  <label class="oauth_input_label">微盾动态码：</label>
						  <input type="text" tabindex="3" class="WB_iptxt oauth_form_input oauth_form_wd" node-type="vdun" maxlength=6>
						</p>
						</div>
				 </form>
				<div class="tips WB_tips_yls WB_oauth_tips" node-type="tipBox" style="display: none;">
					<span class="WB_tipS_err"></span>
					<span class="WB_sp_txt" node-type="tipContent" ></span>
					<span class="arr" node-type="tipArrow"></span>
					<a href="javascript:;" class="close" node-type="tipClose"></a>
				</div>
        </div>
        <div class="oauth_login_box01 clearfix">
          <div class="oauth_login_submit">
              <p class="oauth_formbtn">
              <a node-type="submit" tabindex="4" action-type="submit"  href="javascript:;" onclick="login()"  class="WB_btn_login formbtn_01"></a>
              <a node-type="cancel" tabindex="5" href="javascript:;" action-type="cancel" class="WB_btn_cancel"></a></p>
          </div>
          <!-- todo 添加appkey 白名单判断 -->
          </div>
        <!-- /登录 --> 
      </div>
      </form>
    </div>
    <!-- /带头像 -->
    <!-- 根据域名修改文案 -->
    	
    	</div>
  </div>
 <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
 <script type="text/javascript">
 	//登录
 	function login(){
 		$('.WB_oauth_tips').hide();
 		var userAccount=$('#userAccount').val();
 		userAccount=$.trim(userAccount);
 		var password=$('#password').val();
 		password=$.trim(password);
 		if(!userAccount){
 			//alert('请输入用户账号');
 			$('.WB_sp_txt').html('请输入用户账号');
 			$('.WB_oauth_tips').show();
 			$('#userAccount').focus();
 			return ;
 		}
 		if(!password){
 			//alert('请输入密码');
 			$('.WB_sp_txt').html('请输入密码');
 			$('.WB_oauth_tips').show();
 			$('#password').focus();
 			return;
 		}
 		//验证登录
 		$.getJSON('oauth2/login',{
 			userAccount:userAccount,
 			password:password,
 			r:new Date().getTime()
 		},function(data){
 			if(data.result=='0'){
 				//登录成功
 				$('#authZForm').submit();
 			}else{
 				//alert(data.error);
 				$('.WB_sp_txt').html(data.error);
 	 			$('.WB_oauth_tips').show();
 			}
 			
 		});
 		//$('#authZForm').submit();
 	}
 
 </script>
</body>
</html>

