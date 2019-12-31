<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站连接 - ITeye</title>
<link type="text/css" href="css/web/oauth2/oauth_web.css" rel="stylesheet" />
<!--<style>
	body { padding-bottom:300px; }
</style>-->
</head>
<body class="WB_UIbody WB_widgets">
<div class="WB_xline1 oauth_xline" id="outer">
 <div class="oauth_wrap">
    <div class="oauth_header clearfix">
      <h1 class="WB_logo" title="新浪微博"><a href="http://weibo.com">新浪微博</a></h1>
		<p class="login_account"><a class="sign_up_link" href="http://weibo.com/signup/signup.php?from=zw&appsrc=6caxCa&backurl=https%3A%2F%2Fapi.weibo.com%2F2%2Foauth2%2Fauthorize%3Fclient_id%3D3842512498%26response_type%3Dcode%26display%3Ddefault%26redirect_uri%3Dhttp%3A%2F%2Fwww.iteye.com%2Fauth%2Fweibo%2Fcallback%26from%3D%26with_cookie%3D" target="_blank">注册</a>
		</p>
    </div>
    <!-- 带头像  -->
    <div class="WB_panel oauth_main">
    <form name="authZForm" id="authZForm" action="oauth2/authorize" method="post" node-type="form">
      <div class="oauth_content">
        <p class="oauth_main_info">使用你的微博帐号访问  <a href="http://app.weibo.com/t/feed/6caxCa"  target="_blank" class="app_name">ITeye</a> 
        ，并同时登录新浪微博</p>
        	
        <!-- 登录 -->
        	<div class="oauth_login clearfix">
	          <!-- <form name="authZForm" action="authorize" method="post" node-type="form"> -->
				   <input type="hidden" name="action"  id="action" value="login"/>
				   <input type="hidden" id="display" name="display" value="default"/>
				   <input type="hidden" name="withOfficalFlag"  id="withOfficalFlag" value="0"/>
				    <input type="hidden" name="withOfficalAccount"  id="withOfficalAccount" value=""/>
				   <input type="hidden" name="scope"  id="scope" value=""/>
				   <input type="hidden" name="ticket" id="ticket" value=""/>
				   <input type="hidden" name="isLoginSina"  id="isLoginSina" value=""/>
				   <input type="submit" style="position:absolute; top:-9999px"/>
				   <input type="hidden" name="response_type" value="code"/>
				   <input type="hidden" name="regCallback" value="https%3A%2F%2Fapi.weibo.com%2F2%2Foauth2%2Fauthorize%3Fclient_id%3D3842512498%26response_type%3Dcode%26display%3Ddefault%26redirect_uri%3Dhttp%3A%2F%2Fwww.iteye.com%2Fauth%2Fweibo%2Fcallback%26from%3D%26with_cookie%3D"/>	
	       	       <input type="hidden" name="redirect_uri" value="http://www.iteye.com/auth/weibo/callback"/>
	       	       <input type="hidden" name="client_id" value="3842512498"/>
	       	       <input type="hidden" name="appkey62" value="6caxCa"/>
	       	       <input type="hidden" name="state" value=""/>
	       	       <input type="hidden" name="verifyToken" value="null"/>
	       	       <input type="hidden" name="from" value=""/>
					<div class="oauth_login_form">
			            <p class="oauth_login_01" >
			              <label class="oauth_input_label">帐号：</label>
			              <input type="text" class="WB_iptxt oauth_form_input" id="userId" name="userId"  value="请用新浪微博帐号登录" node-type="userid" autocomplete="off" tabindex="1" />
			            </p>
			            <p>
			              <label class="oauth_input_label">密码：</label>
			              <input type="password" class="WB_iptxt oauth_form_input" id="passwd" name="passwd" node-type="passwd" autocomplete="off" tabindex="2"/>
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
				<!-- </form> -->
				<div class="tips WB_tips_yls WB_oauth_tips" node-type="tipBox" style="display:none">
					<span class="WB_tipS_err"></span><span class="WB_sp_txt" node-type="tipContent" ></span>
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
 		$('#authZForm').submit();
 	}
 
 </script>
</body>
</html>

