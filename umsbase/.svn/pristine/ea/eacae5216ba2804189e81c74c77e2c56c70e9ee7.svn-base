<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>小区信息管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/community/css/community.css" />
</head>
<body> 
 		<input type="hidden" name="province" id="provinceCode" value="${community.province }"/>
 		<input type="hidden" name="city" id="cityCode" value="${community.city }"/>
 		<input type="hidden" name="country" id="areaCode" value="${community.country }"/>
 		<%-- <input type="hidden" name="peopleKey" id="peopleKey" value="${community.peopleKey }"/>
 		<input type="hidden" name="terminalKey" id="terminalKey" value="${community.terminalKey }"/> --%>
      <div class="wrap">
        <div class="header">
            <div class="line-1 clearfix">
                <div class="community-name fl">小区名称：</div>
                <div class="namevalue fl">
                    <input  class="easyui-validatebox" type="text" value="${community.communityName}"
                     placeholder="请输入小区名称，支持模糊查询" id="communityName"/>
                </div>
                <div class="community-area fl">区域：</div>
                <div class="areavalue fl">
                    <input id="province_selt" name="ADtime" type="text" />
                </div>
                <div class="areavalue fl">
                    <input id="city_selt" name="ADtime" type="text" />
                </div>
                <div class="areavalue fl">
                    <input id="area_selt" name="ADtime" type="text" />
                </div>
            </div>
            <div class="line-2 clearfix">
                <div class="communitytype-1 fl">入户数：</div>
                <div class="typevalue-1 fl">
                    <input id="cc2" type="text" />
                </div>
                <div class="conditionvalue-1 fl">
                    <input type="text" value="${community.familyCount}" id="familyCount" name="familyCount"/>
                </div>
                <div class="unit-1 fl">户</div>
            </div>
            <div class="line-3 clearfix">
                <div class="communitytype-1 fl">终端机数：</div>
                <div class="typevalue-1 fl">
                    <input id="cc3" type="text" />
                </div>
                <div class="conditionvalue-1 fl">
                    <input type="text" value="${community.terminalCount}" id="terminalCount" name="terminalCount"/>
                </div>
                <div class="unit-1 fl">台</div>
                <div class="guide-in fl btnStyle1" onclick="serch();">筛选</div>
                <div class="guide-out fl btnStyle1" onclick="exportData();">导出</div>
            </div>
        </div>
        <div class="content">
            <table id="dg"></table>
            <div id="pp"></div>
        </div>
    </div>

    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/community/js/community.js"></script>
</body>
</html>