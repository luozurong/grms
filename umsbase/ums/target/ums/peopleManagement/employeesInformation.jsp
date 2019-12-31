<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>员工信息管理</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/people-common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/employeesInformation.css" />

</head>
<body>
    <div class="wrap">
        <div class="topTitle clearfix">
            <div class="path1 fl">人员管理</div>
            <div class="path2 fl">员工信息管理</div>
        </div>
        <div class="conditions-select clearfix">
            <div class="clearfix">
                <div class="account adjust1 fl">姓名：</div>
                <div class="account1 fl">
                    <input value="" id="name"/>
                </div>
                <div class="account fl">手机号：</div>
                <div class="account1 fl">
                    <input value="" id="mobile"/>
                </div>
                <div class="account fl">职务：</div>
                <div class="account1 fl">
                    <input value="" id="post"/>
                </div>
            </div>

            <div class="clearfix">
                <div class="time fl">时间：</div>
                <div class="time-star fl">
                    <div class="datebox-1"></div>
                </div>
                <div class="to-line fl">--</div>
                <div class="time-end fl">
                    <div class="datebox-2"></div>
                </div>
                <div class="create fl" data-id="0" onclick="roleWin(this)" id="create">创建</div>
                <div class="search fl" onclick="selectAll()" id="select">搜索</div>
                <div class="delete fl" onclick="dealDeleteMany()" id="deleteAll">删除</div>
            </div>


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
                <div class="role-name clearfix">
                    <div class="cl1 fl"><span class="cl1-1">*</span>所属机构</div>
                    <div class="cl2 fl cc-tree">
                        <input class="cc-tree"   type="text"  maxlength="20"/>
                    </div>
                </div>
                <div class="role-name clearfix">
                    <div class="cl1 fl"><span class="cl1-1">*</span>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="post1"/>
                    </div>
                </div>
                <div class="role-name clearfix">
                    <div class="cl1 fl"><span class="cl1-1">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="name1"/>
                    </div>
                </div>
                <div class="role-name clearfix">
                    <div class="cl1 fl"><span class="cl1-1">*</span>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="mobile1"/>
                    </div>
                </div>
                <div class="infoTips">用于接收登录验证码及登录运维APP</div>
                <div class="role-name clearfix">
                    <div class="cl1 fl"><span class="cl1-1">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="sex1"/>
                    </div>
                </div>
                <div class="role-name clearfix">
                    <div class="cl1 fl">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="email1"/>
                    </div>
                </div>
               <div class="role-name clearfix"  style="display: none">
                    <div class="cl1 fl"><span class="cl1-1">*</span>创建时间</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="createTime1"/>
                    </div>
                </div>
                   <div class="role-name clearfix"  style="display: none">
                    <div class="cl1 fl"><span class="cl1-1">*</span>用户资料ID</div>
                    <div class="cl2 fl">
                        <input type="text" value="" maxlength="20" id="userDetailId1"/>
                    </div>
                </div>

            </div>
        </div>


    </div>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ums/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/peopleManagement/js/employeesInformation.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/peopleManagement/js/checkForInput.js"></script>
    
</body>
</html>

























