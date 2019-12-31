<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>小区信息</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/community/css/communityview.css" />
</head>
<body>
<div class="wrap">
	<div class="header">
        <div class="goback" onclick="javascript:history.back(-1);">返回</div>
        <div class="header-edit">查看</div>
    </div>
    <!--<div class="header">
        <div class="goback" onclick="goback()">&nbsp;&nbsp;返回</div>
        <div class="header-edit">查看</div>
    </div>-->
    <div class="content1">
        <div class="box1 clearfix">
            <div class="vertical-1 fl">
					<div class="cl1-1">小区名称</div>
					<div class="cl1-2">区域</div>
					<div class="cl1-3">入住户数</div>
					<div class="cl1-4">媒体终端数量</div>
					<div class="cl1-5">是否能做活动</div>
				</div>
            <div class="vertical-2 fl">
                <div class="cl2-1"><input type="text" value="${community.communityName}" readonly="readonly"/></div>
                    <div class="cl2-2"><input type="text" value="${area}"  readonly="readonly"/></div>
                    <div class="cl2-3"><input type="text" value="${community.familyCount}" readonly="readonly"/></div>
                    <div class="cl2-4"><input type="text" value="${community.terminalCount}" readonly="readonly"/></div>
                    <div class="cl2-5">
						<c:if test="${community.enablePromotionActive==0}">
							<input type="text" value="能"  readonly="readonly"/>
						</c:if>
						<c:if test="${community.enablePromotionActive==1}">
							<input type="text" value="否"  readonly="readonly"/>
						</c:if>
					</div>
            </div>
            <div class="vertical-3 fl">
					<div class="cl3-1">城市</div>
					<div class="cl3-2">地址</div>
					<div class="cl3-3">入住人数</div>
					<div class="cl3-4">门禁卡数</div>
					<div class="cl3-5">活动场地大小</div>
				</div>
            <div class="vertical-4 fl">
                <div class="cl4-1"><input type="text" value="${city}" readonly="readonly"/></div>
                <div class="cl4-2"><input type="text" value="${community.address}" title="${community.address}" readonly="readonly"/></div>
                <div class="cl4-3"><input type="text" value="${community.peopleCount}" readonly="readonly"/></div>
                <div class="cl4-4"><input type="text" value="${community.grandCardCount}" readonly="readonly"/></div>
                <div class="cl4-5"><input type="text" value="${community.activeLocationDetail}" readonly="readonly"/></div>
            </div>
        </div>
    </div>
    <div class="content2">
        <div class="box2 clearfix">
            <div class="mall fl">
                <div class="cl1">商场/购物中心名称</div>
                <div class="cl2">
                    <div class="textarea">${community.mallName }</div>
                </div>
            </div>
            <div class="hospital fl">
                <div class="cl1">医院/医疗机构名称</div>
                <div class="cl2">
                    <div class="textarea">${community.treatmentDepartmentName }</div>
                </div>
            </div>
            <div class="school fl">
                <div class="cl1">周边学校/教育机构名称</div>
                <div class="cl2">
                    <div class="textarea">${community.educationDepartmentName }</div>
                </div>
            </div>
            <div class="banck fl">
                <div class="cl1">银行营业厅/证券机构名称</div>
                <div class="cl2">
                    <div class="textarea">${community.bankingDepartmentName }</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
<script type="text/javascript"	src="<%=basePath%>/community/js/communityview.js"></script>
</body>
</html>