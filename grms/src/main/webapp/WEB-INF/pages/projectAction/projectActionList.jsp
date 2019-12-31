<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/projectAction/css/projectActionList.css" />
    <link rel="shortcut icon" href="<%=basePath%>/homepage/images/ywhlzt.ico" type="image/x-icon">


	    <link rel="stylesheet" type="text/css" href="<%=basePath%>/homepage/css/index.css"/>
	</head>
	<body >
		<div class="wrap">
			<div class="topTitle clearfix">
				<div class="path1 fl">执行管理 </div>
				<div class="path2 fl">执行清单列表</div>
			</div>
			<div class="conditions-select">
				<div class="accountBox">
					    <div class="accountBox2">
							<div class="account fl">
							  <span class="accounttext1">项目名称:</span><input value="${querryBean.projectName}" style="height: 32px; width: 150px;border-radius: 5px;" id="projectName"  />
							</div>
							<div class="account fl">
							  	<span class="accounttext1">创建时间:</span>
							  	<input style="width:115px;height:34px;" id="startTime" value="<fmt:formatDate value='${querryBean.beginDate}' type='date' pattern='yyyy-MM-dd'/>" /> --至-- 
        			          	<input style="width:115px;height:34px;" id="endTime" value="<fmt:formatDate value='${querryBean.endDate}' type='date' pattern='yyyy-MM-dd'/>"  />
							</div>
							<div class="account fl">
							  <span class="accounttext1">项目状态:</span>
								<select id="actionStatus" style="height:32px;" class="easyui-combobox" name="actionStatus" data-options="editable:false,panelHeight:90,width:120">   
								    <option value="-1" <c:if test="${-1 eq querryBean.actionStatus}">selected</c:if> >全部</option>
								    <option value="0" <c:if test="${0 eq querryBean.actionStatus}">selected</c:if> >未执行</option>   
								    <option value="1" <c:if test="${1 eq querryBean.actionStatus}">selected</c:if> >执行中</option>   
								    <option value="2" <c:if test="${2 eq querryBean.actionStatus}">selected</c:if> >已完成</option>   
								</select>
							</div>
							<div class="account fl">
							  <span class="accounttext1">项目异常:</span>
									<select id="projectExceptionStatus" style="height:32px;" class="easyui-combobox" name="exceptionStatus" data-options="editable:false,panelHeight:70,width:120">   
									    <option value="-1" <c:if test="${-1 eq querryBean.projectExceptionStatus}">selected</c:if>>全部</option>
									    <option value="0" <c:if test="${0 eq querryBean.projectExceptionStatus}">selected</c:if> >正常</option>   
									    <option value="1" <c:if test="${1 eq querryBean.projectExceptionStatus}">selected</c:if> >异常</option>   
									</select>
							</div>
							
							<input type="hidden" id="pageNumber" value="${querryBean.pageNumber}">
				        	<input type="hidden" id="pageSize" value="${querryBean.pageSize}">
							
						</div>
					</div>
					<div class="searchBox" style="height: 72px;" >
						<a id="searchBtn" style="width: 120px;height: 34px;line-height: 32px;" onClick="findMenu()" class="search" href="#">查询</a>
					</div>																						
			</div>
			<div class="content-wrap" style="margin-top: 20px;">
				<table id="dg"></table>
				<div id="pp" style="position: relative;margin-top: 20px;"></div>
			</div>
		</div>
		
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
        <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/projectAction/js/projectActionList.js"></script>
        <script src="<%=basePath%>/homepage/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
