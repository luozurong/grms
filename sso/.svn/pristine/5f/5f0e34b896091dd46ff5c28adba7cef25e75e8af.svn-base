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
<title>网站连接 - ${application.name}</title>
<link type="text/css" href="css/web/oauth2/oauth_web.css" rel="stylesheet" />
</head>
<body class="WB_UIbody WB_widgets">
<div class="WB_xline1 oauth_xline" id="outer">
 <div class="oauth_wrap">
    <div class="oauth_header clearfix">
      <h1 class="WB_logo" title="新浪微博"><a href="http://www.kinglian.cn">景联科技</a></h1>
     	 <p class="login_account"><span class="account_name">${user.userName}</span><span class="vline" style="display: none;">|</span><a href="#" action-type="logout" style="display: none;">换个帐号</a></p>
    </div>
    <!-- 带头像  -->
    <div class="WB_panel oauth_main">
    <form id="authZForm" name="authZForm" action="oauth2/authorize" method="post" >
      <div class="oauth_content">
        <div class="oauth_main_content clearfix">
        	<div class="app_info clearfix">
        		<div class="app_info_main clearfix">
        			<div class="app_icon">
	        			<img class="app_img" src="https://upload.api.weibo.com/square/6a3f0653jw1dkpu7gmfdjj.jpg" alt="app">
	        		</div>
	        		<div class="app_intro">
	        			<h3><a href="http://app.weibo.com/t/feed/48eFA8"  target="_blank">${application.name}</a></h3>
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
        			<div class="list_tit">将允许<a href="http://app.weibo.com/t/feed/48eFA8"  target="_blank">${application.name}</a>进行以下操作：</div>
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
	          	   <input type="hidden" name="action" value="authorize" />
	           	   <input type="hidden" name="client_id" value="${client_id}" />
				   <input type="hidden" name="redirect_uri"   value="${redirect_uri}"/>
				   <input type="hidden" name="response_type" value="${response_type}"/>
	       	       <input type="hidden" name="state" value="${state }"/>
          <div class="oauth_login_submit">
              <p class="oauth_formbtn"><a node-type="submit" href="javascript:authorize();"   action-type="submit"   class="WB_btn_link formbtn_01"></a><a node-type="cancel" href="javascript:;" action-type="cancel" class="WB_btn_cancel"></a></p>
          </div>
        </div>
        </div>
        </form>
        <!-- /登录 --> 
      </div>
      <!-- 根据域名修改文案 -->
    	
    	</div>
  </div>
  <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
	function authorize(){
		$('#authZForm').submit();
	}
</script>
</body>
</html>

