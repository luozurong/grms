<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>组织结构管理</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/people-common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/ums/peopleManagement/css/organization.css" />
</head>
<body>
    <div class="wrap">
        <div class="topTitle clearfix">
            <div class="path1 fl">人员管理</div>
            <div class="path2 fl">组织机构管理</div>
        </div>
        <div class="conditions-select clearfix">
            <div class="create fl" id="create" onclick="check('create')">创建</div>
            <div class="search fl" id="edit" onclick="check('edit')">修改</div>
            <div class="delete fl" id="delete" onclick="check('delete')">删除</div>
        </div>
        <div class="body-wrap">
            <input type="hidden"  id="status" />
            <div class="menuL">
                <div class="tree-wrap">
                    <ul id="box"></ul>
                </div>
            </div>
            <div class="content">
                <div class="content-wrap">
                    <div class="inputBox">
                        <label for="parentOrga">上级机构: </label>
                        <input type="hidden"  id="parentOrgaId" />
                        <input type="text" name="" id="parentOrga" value="" disabled="disabled"/>
                    </div>
                    <div class="inputBox">
                        <label for="Orga">机构名称: </label>
                          <input type="hidden"  id="OrgaId" />
                        <input type="text" name="" id="Orga" value="" />
                    </div>
                    <div class="textBox">
                        <label style="display: inline-block;vertical-align: top;">&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</label><textarea name="" rows="" cols="" id="orgaDesc"></textarea>
                    </div>
                    <div class="sure">
                        <input type="button" name="" id="" value="确定" onclick="confirmAdd()"/>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ums/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/ums/peopleManagement/js/organization.js"></script>
</body>
</html>

























