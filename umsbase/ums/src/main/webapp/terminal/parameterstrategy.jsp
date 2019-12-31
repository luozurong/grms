<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>终端机参数策略</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="terminal/css/parameterstrategy.css" />

</head>

<body>
<div class="wrap">
    <div class="header clearfix">
    	<input type="hidden" id="birghtness" value="${parameters.birghtness}"/>
        <input type="hidden" id="volume" value="${parameters.volume}"/>
        <%--<div class="add fl"><a href="javascript:window.location.href='terminalStatergyAction!getTermialDefaultParameters.html'">默认策略</a></div>--%>
        <div id="add_bt" class="add marginLeft fl btnStyle1"><a href="javascript:window.location.href='terminalStatergyAction!goParameterstrategyAdd.html'">新增策略</a></div>
        <%--<div id="del_bt" class="delete fl">删除</div>--%>
        <%--<div class="search fr">--%>
            <%--<input class="easyui-searchbox" name="forwardParams" data-options="prompt:'请输入搜索内容',searcher:doSearch2" style="width:280px;height: 36px;padding-left: 40px;" />--%>
        <%--</div>--%>
        <script>
           /*  function doSearch2(value) {
                alert('You input: ' + value);
            } */
        </script>
    </div>
    <div class="content-body">
        <table id="dg"></table>
        <div id="pp"></div>
    </div>
</div>
    <script type="text/javascript" src="common/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="common/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="common/plugin/common.js"></script>
    <script type="text/javascript" src="terminal/js/parameterstrategy.js"></script>
</body>

</html>