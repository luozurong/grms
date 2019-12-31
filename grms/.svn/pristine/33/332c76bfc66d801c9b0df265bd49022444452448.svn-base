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
	<title>创建产品配置</title>
	<meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="shortcut icon" href="<%=basePath%>/homepage/images/ywhlzt.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/systemSettings/css/addDepartmenBusiness.css" />
	</head>
	<body>
		<div class="wrap-container">
			<div>
				<span class="department-name">执行部门：</span>
				<span id="departmentName">${departmentName}</span>
				<input type="hidden" id="departmentId" value="${departmentId}"></span>
				<input type="hidden" id="businessTypeId" value="${businessTypeId}"></span>
			</div>
			<div>
				<span class="department-name">执行业务类型：</span>
				<span class="department-choose">
					<span data-value="1">社区运营</span>
					<span data-value="2">媒管</span>
					<span data-value="3">用户运营</span>
					<span data-value="4">电商运营</span>
				</span>
			</div>
			<div>
				<span class="department-name" style="vertical-align:top;">执行范围：</span>
				<span style="display:inline-block;margin-top:13px;">
					<ul id="box2"></ul>
				</span>
			</div>
			<div class="department">
				<button onclick="cancel()">取消</button>
				<button onclick="saveData()">确定</button>
			</div>
		</div>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
        <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/systemSettings/js/editDepartmentBusiness.js"></script>
        <script src="<%=basePath%>/homepage/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
