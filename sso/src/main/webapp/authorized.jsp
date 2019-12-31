<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站连接 - 58同城</title>
<link type="text/css" href="css/web/oauth2/oauth_web.css" rel="stylesheet" />
</head>
<body class="WB_UIbody WB_widgets">
<div class="WB_xline1 oauth_xline" id="outer">
 <div class="oauth_wrap">
    <div class="oauth_header clearfix">
      <h1 class="WB_logo" title="新浪微博"><a href="http://weibo.com">新浪微博</a></h1>
     	 <p class="login_account"><span class="account_name">暗里着迷86</span><span class="vline">|</span><a href="http://app.weibo.com/my" target="_blank">我的应用</a></span><span class="vline">|</span><a href="#" action-type="logout">换个帐号</a></p>
    </div>
    <!-- 带头像  -->
    <div class="WB_panel oauth_main">
    <form name="authZForm" action="authorize" method="post" node-type="form">
      <div class="oauth_content">
        <div class="oauth_main_content clearfix">
        	<div class="app_info clearfix">
        		<div class="app_info_main clearfix">
        			<div class="app_icon">
	        			<img class="app_img" src="https://upload.api.weibo.com/square/6a3f0653jw1dkpu7gmfdjj.jpg" alt="app">
	        		</div>
	        		<div class="app_intro">
	        			<h3><a href="http://app.weibo.com/t/feed/48eFA8"  target="_blank">58同城</a></h3>
	        			<div class="app_des"></div>
	        		</div>
        		</div>
        		<div class="app_info_plus">
        			<div class="plus_tit">
        			http://app.weibo.com/t/feed/48eFA8<br/>共有 100000+ 人连接
								</div>
        			<div class="app_user_list">
        				<ul class="clearfix">
			              </ul>
        			</div>
				</div>
        	</div>
        	<div class="oauth_info clearfix">
        		<div class="oauth_list">
        			<div class="list_tit">将允许<a href="http://app.weibo.com/t/feed/48eFA8"  target="_blank">58同城</a>进行以下操作：</div>
        			<ul class="do_list">
			            <li>
			              <i class="icon_user"></i>获得你的个人信息，好友关系
			            </li>
			            <li>
			              <i class="icon_rss"></i>分享内容到你的微博
			            </li>
			            <li>
			              <i class="icon_comm"></i>获得你的评论
			            </li>
		          </ul>
        		</div>
        		</div>
        </div>
           <!-- 登录 -->
        	<div class="oauth_login_box01 clearfix">    	
	          
	          <input type="hidden" id="display" name="display" value="default"/>
				   <input type="hidden" name="action"  id="action" value="authorize"/>
				   <input type="hidden" name="scope"  id="scope" value=""/>
				   <input type="hidden" name="withOfficalFlag"  id="withOfficalFlag" value="0"/>
				   <input type="hidden" name="withOfficalAccount"  id="withOfficalAccount" value=""/>
				   <input type="hidden" name="ticket" id="ticket" value=""/>
				   <input type="hidden" name="isLoginSina"  id="isLoginSina" value=""/>
				   <input type="submit" style="position:absolute; top:-9999px"/>
				   <input type="hidden" name="response_type" value="code"/>
				   <input type="hidden" name="regCallback" value="https%3A%2F%2Fapi.weibo.com%2F2%2Foauth2%2Fauthorize%3Fclient_id%3D2563496436%26response_type%3Dcode%26display%3Ddefault%26redirect_uri%3Dhttp%3A%2F%2Fpassport.58.com%2Fsinalogin%26from%3D%26with_cookie%3D"/>	
	       	       <input type="hidden" name="redirect_uri" value="http://passport.58.com/sinalogin"/>
	       	       <input type="hidden" name="client_id" value="2563496436"/>
	       	       <input type="hidden" name="appkey62" value="48eFA8"/>
	       	       <input type="hidden" name="state" value=""/>
	       	       <input type="hidden" name="from" value=""/>
					<input type="hidden" name="uid" value="2504785972"/>
					<input type="hidden" name="url" id="url" value=""/>
					<input type="hidden" name="verifyToken" value="24e3de36d727b117d5392675f9ecf1a3"/>
					<input type="hidden" name="visible" id="visible" value="0"/>
				<!-- </form> -->
            
          <div class="oauth_login_submit">
              <p class="oauth_formbtn"><a node-type="submit" href="#" onclick="return false;"  action-type="submit"   class="WB_btn_link formbtn_01"></a><a node-type="cancel" href="javascript:;" action-type="cancel" class="WB_btn_cancel"></a></p>
          </div>
        </div>
        </div>
        </form>
        <!-- /登录 --> 
      </div>
      <!-- 根据域名修改文案 -->
    	
    	</div>
  </div>
 
<script type="text/javascript">
	(function() {
	if(self !== top) {
	var img = new Image();
	var src = 'https://api.weibo.com/oauth2/images/bg_layerr.png?oauth=1&page=web&refer=' + document.referrer + '&rnd=' + (+new Date());
	img.src = src
	img = null; //释放局部变量
	}
	})();
</script>
</body>
</html>

