<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height: 100%;">
	<head>
		<meta charset="UTF-8">
		<title>广告后台管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    
    <script type="text/javascript">
    /* window.onhashchange = function(){
        var hash = location.hash;
        hash = hash.substring(1,hash.length);
        alert(hash);
    } */
    /* document.addEventListener('DOMContentLoaded', function () {
        var hash = location.hash;
        var url = hash.substring(1,hash.length);
        $("#mainFrame").attr("src", url);
   }, false) */
  /*   document.onkeydown = function (e) {
		var ev = window.event || e;
		var code = ev.keyCode || ev.which;
		if (code == 116) {
			document.getElementById("mainFrame").contentWindow.location.reload();
		//history.go(0);
			//window.location.reload();
			//window.location.href =window.location .href;
			//document.frames('mainFrame').location.reload()
		return false;
		}
	} */
    function isLogin(){
    	
    }
    </script>
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>/homepage/css/index.css"/>
	</head>
	<body style="height: 100%;">
		<div id="cc" class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">   
		   <!--顶部-->
		   <div class="north clearfix" data-options="region:'north',split:false" >
			   	<div class="fl">
			   		<img class="logoPic" src="<%=basePath%>/homepage/images/login_logo1.png"/>
			   	</div>
			   	<div class="fr">
			   		<span class="mesIcon" onclick="goMessage()">
			   			<img src="<%=basePath%>/common/images/ic_message.png"/>
			   			<span class="mesNum" id="mesNum"></span>
			   		</span>
			   		<span class="admin">
			   			<img src="<%=basePath%>/common/images/ic_administrator.png"/>
			   			<span id="userName" class="userName">${sessionScope.sessionInfo.realName }</span>
			   		</span>
			   		 <a class="logout" href="nologin.jsp"><span>退出</span></a> 
			   		 <!-- <a class="logout" href="javascript:void(0);" onclick="logout();"><span>退出</span></a> -->   
			   		<!-- <div class="logout" onclick="logout(true);">退出</div> -->
			   	</div>
			   	<div id="mask-top"></div>
		    </div>  
		    <!--左部-->
		    <div class="west" data-options="region:'west',title:'',split:false">
		    	<div>
					<ul class="menuUl">
					<%-- <c:forEach var="item" items="${sessionInfo.authVos}">
						<c:if test="${item.cpid=='0'}">
						
						<li>
						<span class="icon"></span>
						<a class="menuItem" >${item.cname}</a>
						
						
						
						<c:forEach items="${item.authVoList }" var="sm">
						 <div class="submenu">
								 <a class="submenuItem" target="mainFrame" href="${sm.curl}">${sm.cname}</a> 
							</div>
						</c:forEach>
						
						</li>
						</c:if>
					</c:forEach> --%>
					<c:forEach var="item" items="${sessionInfo.authVos}">
						<c:if test="${item.cpid=='0'}">
						<c:if test="${!empty item.curl}">
						    <li>
                                <div class="logo-img-black"><img src="homepage/images/${item.ciconCls}_01.png" alt=""/></div>
                                <div class="logo-img-white"><img src="homepage/images/${item.ciconCls}_02.png" alt=""/></div>
                                <a class="menuItem" target="mainFrame" href="${item.curl}" >${item.cname}</a>
                            </li>
						</c:if>
						<c:if test="${empty item.curl}">
						<li>
                        <div class="logo-img-black"><img src="homepage/images/${item.ciconCls}_01.png" alt=""/></div>
                        <div class="logo-img-white"><img src="homepage/images/${item.ciconCls}_02.png" alt=""/></div>
						<span class="icon"></span>
						<a class="menuItem"  >${item.cname}</a>
						<c:forEach items="${item.authVoList }" var="sm">
						 <div class="submenu">
								 <a class="submenuItem" target="mainFrame" href="${sm.curl}">${sm.cname}</a> 
							</div>
						</c:forEach>
						</li>
						</c:if>
						
						</c:if>
					</c:forEach>
					 
						 <!-- <li><a class="menuItem" target="mainFrame" href="demo1.html">首页</a></li>
						<li><a class="menuItem menuItemActive" target="mainFrame" href="../customer/customer_management.html">客户管理</a></li>
						<li>
							<span class="icon"></span>
							<a class="menuItem">广告管理</a>
							<div class="submenu">
								<a class="submenuItem" target="mainFrame" href="demo2.html">广告列表</a>
								<a class="submenuItem" target="mainFrame" href="demo2.html">广告代办</a>
							</div>
						</li>
						<li><a class="menuItem" target="mainFrame" href="index.html">素材管理</a></li>
						<li>
							<span class="icon"></span>
							<a class="menuItem">终端管理</a>
							<div class="submenu">
								<a class="submenuItem" target="mainFrame" href="demo2.html">终端管理1</a>
								<a class="submenuItem" target="mainFrame" href="demo2.html">终端管理2</a>
						    </div>
						</li>
						<li>
							<span class="icon"></span>
							<a class="menuItem">合同管理</a>
							<div class="submenu">
								<a class="submenuItem" target="mainFrame" href="demo2.html">合同管理1</a>
								<a class="submenuItem" target="mainFrame" href="demo2.html">合同管理2</a>
							</div>
						</li>
						<li><a class="menuItem" target="mainFrame" href="index.html">小区信息管理</a></li>
						<li><a class="menuItem" target="mainFrame" href="index.html">数据统计</a></li>
						<li>
							<span class="icon"></span>
							<a class="menuItem">系统管理</a>
							<div class="submenu">
								<a class="submenuItem" target="mainFrame" href="demo2.html">系统管理1</a>
								<a class="submenuItem" target="mainFrame" href="demo2.html">系统管理2</a>
							</div>
						</li> -->  
					</ul>
				</div>
				 <div id="mask-left"></div>
		    </div>    
		    <div class="center"  data-options="region:'center',iconCls:'icon-reload',title:'',split:false" >
		    	<!-- <iframe name="mainFrame" scrolling="auto" frameborder="0"  src="homepage/welcome.jsp" style="width:100%;height: 100%;"></iframe> -->
		    	<iframe id="mainFrame" name="mainFrame" scrolling="auto"  frameborder="0"  src="homepage/welcome.jsp" style="width:100%;height: 100%;"></iframe>
<!-- 		    	<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="customerAction!goCustomer.html" style="width:100%;height: 98%;"></iframe> -->
                <div class="mask-right"></div>
		    </div>    
		</div> 
		
		<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script src="<%=basePath%>/homepage/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
