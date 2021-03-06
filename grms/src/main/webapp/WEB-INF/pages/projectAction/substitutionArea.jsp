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
	<title>场地置换</title>
	<meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/projectAction/css/substitutionArea.css" />
    <link rel="shortcut icon" href="<%=basePath%>/homepage/images/ywhlzt.ico" type="image/x-icon">


	    <link rel="stylesheet" type="text/css" href="<%=basePath%>/homepage/css/index.css"/>
	</head>
	<body >
		<div class="workorder-breadcrumb">
	    	<span class="go-back"  onClick="goback()">返回</span>
	 	    <span class="workorder-breadcrumb-icon"></span>
		    <span>执行清单列表</span>
		    <span class="ic_right"></span>
		    <span>场地置换</span>
		</div>
		<div class="wrap">
			<div class="conditions-select">
				<div style="height: 60px;">
					<div class="account fl">小区名称： </div>
					<div class="account1 fl"><input  id="areaName" class="easyui-textbox" style="width:120px;height:32px;" /></div>
					<div class="account fl">区域： </div>
					<div class="account fl">
						<input style="height: 34px;" id="province"  />
					</div>
					<div class="account fl">
						<input id="city" class="easyui-combobox" style="height: 34px;" data-options="valueField:'0',textField: '请选择城市'" />
					</div>
					<div class="account fl">
						<input id="country" class="easyui-combobox" style="height: 34px;" data-options="valueField:'0',textField: '请选择区/县'" />
					</div>
				</div>
				<div style="height: 60px;">
					<div class="account fl">实际户数： </div>
					<div class="account fl" style="margin-left: 0">
						<select id="householdNumSign" style="height: 34px;" class="easyui-combobox" name="businessType" data-options="editable:false,panelHeight:50,width:70">   
						    <option value="1">大于</option>   
						    <option value="2">小于</option>
						</select>
					</div>
					<div class="account fl" style="margin-left: 5px;">
						<input id="householdNum" style="height: 34px;" class="easyui-textbox" style="width:150px" /> 户
					</div>
					<div class="account fl" style="margin-left: 60px;">小区类型：  </div>
					<div class="account1 fl">
						<select id="areaCategory" style="height: 34px;" class="easyui-combobox" name="status" data-options="editable:false,panelHeight:50,width:120">   
						    <option value="0">测试小区</option>
						    <option value="1">云对讲项目</option>
						    <option value="2">工程项目</option>
						    <option value="3">样板演示小区</option>
						    <option value="4">产品销售小区</option>
						</select>
					</div>
				</div>
				<div style="height: 60px;">
					<div class="account fl">终端机数： </div>
					<div class="account fl" style="margin-left: 0">
						<select style="height: 34px;" id="advertisingTerminalNumSign" class="easyui-combobox" name="status" data-options="editable:false,panelHeight:50,width:70">   
						    <option value="1">大于</option>
						    <option value="2">小于</option>
						</select>
						<input style="height: 34px;" id="advertisingTerminalNum" class="easyui-textbox" style="width:150px" />
					</div> 
					<div class="account fl" style="margin-left: 60px;">转化情况：  </div>
					<div class="account1 fl">
						<select style="height: 34px;" id="translateStatus" class="easyui-combobox" name="status" data-options="editable:false,panelHeight:50,width:80">   
						    <option value="1">已转化</option>
						    <option value="0">未转化</option>
						    <option value="2">部分转化(试点转化)</option>
						</select>
					</div> 
					<div class="account1 fl" >
						<a id="searchBtn" onClick="findMenu()" style="height: 34px;width:120px;" class="search" href="#">筛选</a>
					</div>
				</div>
				<div style="height: 45px;padding: 15px 0 10px;margin-top:15px; border-top: 1px solid #eee;">
					<a style="height: 34px;width:120px;" id="substitutionArea" onClick="substitutionArea()" class="substitutionArea" href="#">确定置换</a>
				</div>
				<input id="actionCode" type="hidden" value="${actionCode}">
				<input id="provinceCode" type="hidden">
				<input id="cityCode" type="hidden">
				<input id="countryCode" type="hidden">
				<input id="exceptionId" type="hidden" value="${exceptionId}">
			</div>
			<div class="content-wrap">
				<table id="dg"></table>
				<div id="pp" style="position: relative;"></div>
			</div>
		</div>
		
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
        <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
        <script type="text/javascript"	src="<%=basePath%>/projectAction/js/substitutionArea.js"></script>
        <script src="<%=basePath%>/homepage/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
