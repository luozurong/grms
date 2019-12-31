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

		<div class="wrap">
			<div class="topTitle clearfix">
				<div class="path1 fl">人员管理</div>
				<div class="path2 fl">功能按钮管理</div>
			</div>
			<div class="conditions-select clearfix">

				<div class="account fl">系统名称：</div>
				<div class="account1 fl">
					<input value="" id="systemName"/>
				</div>
				<div class="account fl">菜单名称：</div>
				<div class="account1 fl">
					<input value="" id="menuname" />
				</div>
			<!-- 	<div class="account fl">账号：</div>
				<div class="account1 fl">
					<input value="" />
				</div> -->
				<div class="fl status">
					菜单状态：
					<input type="checkbox" name="" id="status1" value="" checked="checked"/>开启
					<input type="checkbox" name="" id="status2" value="" />关闭
				</div>
				<div class="create fl" data-id="0" onclick="roleWin(1,'')">添加</div>
				<div class="search fl" onclick="selectAll()">搜索</div>
				<div class="search delete fl" onclick="dealDeleteMany()">批量删除</div>
			</div>
			<div class="content-wrap">
				<table id="dg"></table>
				<div id="pp" style="position: relative;"></div>
			</div>
		</div>

		<div id="roleWin_change" style="display: none">
			<div id="roleWin-wrap" class="roleWin-wrap">
				<div id="content-box" class="content-box" id="content_box_1">
					<ul>
						<li style="height: auto;"><span class="special"></span>系统名称<input type="text" id="select_1"/>
						</li>
						<li style="height: auto;"><span class="special">菜单类别</span><input type="text" id="select_4"/>
						<li>上级菜单名称<input type="text" id="select_2"/>
						</li>
						<li style="height: auto;"><span class="special">菜单名称</span><input type="text" id="menuname1" value="" /></li>
						<li><label>菜单地址</label><textarea name="" id="menuUrl1" rows="3" cols=""></textarea></li>
						<li>菜单图标<input type="text"  id="menuIcon1" value="" /></li>
						<li style="height: auto;"><span class="special">菜单状态</span><input type="text" id="select_3"/>
						</li>
						<li>菜单顺序<input type="text" id="menuOrder1" value="" /></li>
						<li style="display:none" >菜单Id<input type="text" id="menuId1" value="" /></li>
					    <li style="display:none" >创建时间<input type="text" id="createTime1" value="" /></li>
						<li><label>备&emsp;&emsp;注</label><textarea name="" rows="3" id="note21" ></textarea></li>
						
					</ul>
				</div>
			</div>
		</div>
<!-- 		<div id="roleWin_check" style="display: none">
			<div class="roleWin-wrap">
				<div class="content-box">
					<ul>
						<li>系&emsp;&emsp;统<input type="text" disabled="disabled"/>
						</li>
						<li>上级菜单名称<input type="text" id="" disabled="disabled"/>
						</li>
						<li>菜单名称<input type="text" id="" value=""  disabled="disabled"/></li>
						<li style="height: auto;"><span class="special"></span><label>菜单地址</label><textarea name="" rows="3" cols=""  disabled="disabled"></textarea></li>
						<li>菜单图标<input type="text" id="" value=""  disabled="disabled"/></li>
						<li>菜单状态<input type="text" id="" disabled="disabled"/>
						</li>
						<li>菜单顺序<input type="text" id="" value=""  disabled="disabled"/></li>
						<li><label>备&emsp;&emsp;注</label><textarea name="" rows="3" cols=""  disabled="disabled"></textarea></li>
					</ul>
					<div class="the-bottom">
						<span class="comfirm">关闭</span>
					</div>
				</div>
			</div>
		</div>
 -->		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/common/plugin/common.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ums/peopleManagement/js/functionButtonManagement.js"></script>
	</body>

</html>