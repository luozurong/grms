<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>分组管理</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/terminal/css/groupmanagement.css" />
</head>
<body>
  <div id="tt" class="easyui-tabs" style="width:100%;height: 100%;">
    <div title="视频&音频" style="display:none;">
        <div class="content-header clearfix">
            <div class="export fr btnStyle1" onclick="exportData();">导出同步结果</div>
            <div class="search fr">
                <input class="easyui-searchbox" name="terminalPage.fuzzyKey" value="${terminalPage.fuzzyKey}" data-options="prompt:'请输入搜索内容',searcher:searchKey" style="width:280px;height: 36px;padding-left: 40px;"/>
            </div>
        </div>
        <div class="wrap">
            <input type="hidden" id="communityCode" value="${terminalPage.organizationSeq }"/>
            <input type="hidden" id="opendId" value="${opendId }" name="opendId">
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
                        <table id="dg" class="easyui-datagrid"  style="width: 100%;"></table>
                        <div id="pp"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </div>


    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/terminal/js/groupmanagement.js"></script>
</body>
</html>