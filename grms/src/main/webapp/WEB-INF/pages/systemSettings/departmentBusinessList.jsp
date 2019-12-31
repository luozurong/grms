<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.hori.vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height: 100%;">
	<head>
	<meta charset="UTF-8">
	<title>项目管理</title>
	<meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/systemSettings/css/organization.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/systemSettings/css/people-common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/systemSettings/css/departmentBusiness.css" />
    <link rel="shortcut icon" href="<%=basePath%>/homepage/images/ywhlzt.ico" type="image/x-icon">
	</head>
	<body >
		<div class="search-btn">
			<input id="condition" type="text" value=""/>
		    <span id="search" onclick="search()">搜索</span>
		    <span id="create" onclick="jumpToAddPage()">创建</span>
		    <span id="batchDel" onclick="batchDel()">批量删除</span>
		</div>
		
	 	<div class="body-wrap">
           <input type="hidden"  id="status" />
           <div class="menuL">
               <div class="tree-wrap">
                   <ul id="box"></ul>
               </div>
           </div>
           <div class="content-wrap">
				<table id="dg"></table>
				<div id="pp" style="position: relative;"></div>
			</div> 
        </div>  
       
	  	
		<!--删除（确认）-->
		<div id="delStatistics" style="display: none">
		    <div>确认删除所选项吗？</div>
		</div>
		
		<!--删除（提示）-->
		<div id="hintStatistics"  style="display: none">
		    <div>操作失败，无勾选项</div>
		</div>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
        <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/systemSettings/js/departmentBusinessList.js"></script>
        <script src="<%=basePath%>/homepage/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
