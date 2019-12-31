<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head lang="en">
		<meta charset="UTF-8">
		<title>功能按钮管理</title>
		<meta http-equiv="X-UA-Compatible" content="edge" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/default.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/people-common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/functionButtonManagement.css" />
	</head>


	<body>
       <input type="text" id="menuId" value="${menuId}" disabled="disabled" hidden="false"/>
		<div class="wrap">
			<div id="" class="fl back">
                <a  onclick="javascript :history.back(-1);">返回</a>

			</div>
            <div class="border fl"></div>
			<div class="topTitle clearfix">
				<div class="path1 fl">人员管理</div>
				<div class="path2 fl">功能按钮管理</div>
				<div class="path2 fl">按钮管理</div>
			</div>
			<div class="conditions-select clearfix">
				<div class="create create1 fl" data-id="0" onclick="roleWin(1)">添加</div>
			</div>
			<div class="content-wrap">
				<table id="dg"></table>
			</div>
		</div>
				<div id="roleWin_change" style="display: none">
			<div class="roleWin-wrap roleWin-wrapadjust">
				<div id="content-box" class="content-box" id="content_box_1">
					<ul>
						<li style="display:none">按钮id<input type="text" id="btnId1" value="" /></li>
                        <li style="display:none">菜单id<input type="text" id="MenuId1" value="" /></li>
				       	<li style="height: auto;"><span class="special">按钮名称</span><input type="text" id="btnName1" value="" /></li>
				       	<li style="height: auto;"><span class="special">按钮代码</span><input type="text" id="btnCode1" value="" /></li>
						<li style="height: auto;"><span class="special">按钮地址</span><textarea name="" rows="4" cols="" id="btnUrl1" ></textarea></li>
						<li>按钮状态<input type="text" id="btnStatus1" value="" /></li>
						<li>菜单图标<input type="text" id="btnIcon1" value="" /></li>
						<li>按钮顺序<input type="text" id="btnOrder1" value="" /></li>
						<li style="display:none">创建时间<input type="text" id="createTime1" value="" /></li>
<!-- 						<li><label>备注</label><textarea name="" rows="4" cols="" id="note1" ></textarea></li>
 -->					</ul>
					
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/plugin/common.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/peopleManagement/js/buttonManagement.js"></script>
	</body>

</html>
