<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>责任区域管理</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/people-common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/areaManagement.css" />

</head>
<body>
    <div class="wrap">
        <div class="topTitle clearfix">
            <div class="path1 fl">人员管理</div>
            <div class="path2 fl">责任区域管理</div>
        </div>
        <div class="conditions-select clearfix">
            <div class="account adjust1 fl">账号：</div>
            <div class="account1 fl">
                <input id='userAccount' value=""/>
            </div>
            <div class="account fl">姓名：</div>
            <div class="account1 fl">
                <input id='name' value=""/>
            </div>
            <div class="account fl">手机号：</div>
            <div class="account1 fl">
                <input id='mobile' value=""/>
            </div>
            <div class="account fl">角色：</div>
            <div class="account1 fl">
                <input id='roleName' value=""/>
            </div>
            <div class="search fl"  id="select" onclick="selectAll()">搜索</div>
            <div class="create fl"  id="assigned" data-id="0" onclick="roleWin(this,'')">分配责任区域</div>

        </div>

        <div class="body-wrap">
            <div class="menuL">
                <div class="tree-wrap">
                    <ul id="box"></ul>
                </div>
            </div>
            <div class="content">
                <div class="content-wrap">
                    <table id="dg"></table>
                    <div id="pp" style="position: relative;width: 100%;"></div>
                </div>
            </div>

        </div>

    </div>
    <div id="roleWin" style="display: none">
        <div class="roleWin-wrap">
            <div class="detail-wrap">
                <div class="area-title clearfix">
                    <div class="area-title1 fl">区域列表</div>
                    <div class="area-title2 fl">已选</div>
                </div>
                <div class="area clearfix">
                    <div class="left-name fl">
                        <ul id="box2"></ul>
                    </div>
                    <div class="to-right fl"></div>
                    <div class="right-name fl">
                        <!--<div class="list">
                            <div>小花</div>
                            <div class="list-delete"></div>
                        </div>-->
                    </div>
                </div>

            </div>

<!--             <div  id="button"  class="the-bottom clearfix">
                <span class="quxiao fl">取消</span>
                <span class="comfirm fl">保存</span>

            </div> -->
        </div>


    </div>

    <!--加载中 遮罩-->
    <div class="shadeBox">
        <div class="tipsInfo">
            <p>正在处理中......</p>
        </div>
    </div>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ums/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/peopleManagement/js/areaManagement.js"></script>
</body>
</html>

























