<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>小区范围-编辑</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/terminal/css/groupmanagement.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/terminal/css/choosearea_compile.css"/>
</head>
<body>
	<div class="header">
	    <div class="goback"><a class="goback-a" onclick="javascript:window.location.href='stratergyBagAction!goParameterstrategyList.html'">返回</a></div>
	    <div class="header-edit">范围小区</div>
	</div>
  <!--<div id="tt" class="easyui-tabs" fit="true" style="width:100%;">-->
    <!--<div title="使用范围" style="display:none;">-->
    <p class="tipsP"><span style="color: red;">*</span>适用区域</p>
    <div class="btnBox">
    	<div class="edit">
    		<span class="editBtn btnStyle1" onclick="onchangeArea(1)">编辑</span>
    	</div>
    	<div class="saveCancel">
    		<span class="saveBtn btnStyle1" onclick="dasave()">保存</span>
    		<span class="cancelBtn btnStyle2" onclick="javascript:history.back(-1)">取消</span>
    	</div>
    </div>
      	
    <div class="wrap">
        <input type="hidden" id="communityCode"/>
        <input type="hidden" id="opendId" value="${opendId}" name="opendId">
        <input type="hidden" id="bagid" value="${id }" name="id">
         <input type="hidden" id="areaIds" value="${areaIds }" name="areaIds">
        <input type="hidden" id="termialIds" value="${termialIds }" name="termialIds">
        <div class="menuL">
            <div class="search-1">
                <input class="easyui-searchbox" data-options="prompt:'小区名称',searcher:doSearch" style="width:150px;height: 26px;padding-left: 26px;"/>

            </div>
            <div class="tree-wrap">
                <ul id="box"></ul>
            </div>
        </div>
        <div class="content">
            <div class="content-wrap">

                <%--<div id="tb" style="height:auto">--%>
                    <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>--%>
                <%--</div>--%>
                <div class="content-body">
                    <table id="dg" class="easyui-datagrid"></table>
                    <div id="pp"></div>
                </div>
            </div>
        </div>
    </div>
    <!--</div>-->
  <!--</div>-->


    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/terminal/js/choosearea_compile.js"></script>
</body>
</html>