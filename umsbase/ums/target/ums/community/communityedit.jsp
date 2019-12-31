<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/community/css/communityedit.css" />
</head>
<body>
    <div class="wrap">
	    <div class="header">
	        <div class="goback" onclick="javascript:history.back(-1);">返回</div>
	        <div class="header-edit">编辑</div>
	    </div>
        <!--<div class="header">
            <div class="goback" onclick="goback()"><&nbsp;&nbsp;返回</div>
            <div class="header-edit">编辑</div>
        </div>-->
      <form id="communityForm" method="post" enctype="multipart/form-data">   
      	<input type="hidden" value="${community.enablePromotionActive}" id="enablePromotionActive" name="enablePromotionActive"/>
        <input type="hidden" value="${community.id}" name="id"  id="communityId"/>
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
                    <div class="cl2-2"><input type="text" name="area" value="${area}" readonly="readonly" /></div>
                    <div class="cl2-3"><input class="input1" type="text" name="familyCount" value="${community.familyCount}" maxlength='10'/></div>
                    <div class="cl2-4"><input type="text" name="terminalCount" value="${community.terminalCount}" readonly="readonly"  /></div>
                    <div class="cl2-5" >
							<input id="cc1" type="text" name="enablePromotionActive"/> 
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
                <div class="cl4-1"><input type="text" name="city"  value="${city}" readonly="readonly"/></div>
                <div class="cl4-2"><input type="text" name="address" value="${community.address}" readonly="readonly"/></div>
                <div class="cl4-3"><input class="input1"  type="text" name="peopleCount" value="${community.peopleCount}" maxlength='10'/></div>
                <div class="cl4-4"><input type="text" name="grandCardCount" value="${community.grandCardCount}" readonly="readonly"/></div>
                <div class="cl4-5"><input class="input1" type="text" name="activeLocationDetail" value="${community.activeLocationDetail}" /></div>
            </div>
        </div>
    </div>
        <div class="content2">
        <div class="box2 clearfix">
            <div class="mall fl">
                <div class="cl1">商场/购物中心名称</div>
                <div class="cl2">
                    <textarea class="textarea" name="mallName" id="mallName"  placeholder="每个名称用逗号隔开">${community.mallName }</textarea>
                </div>
            </div>
            <div class="hospital fl">
                <div class="cl1">医院/医疗机构名称</div>
                <div class="cl2">
                    <textarea class="textarea" name="treatmentDepartmentName" placeholder="每个名称用逗号隔开" id="treatmentDepartmentName">${community.treatmentDepartmentName }</textarea>
                </div>
            </div>
            <div class="school fl">
                <div class="cl1">周边学校/教育机构名称</div>
                <div class="cl2">
                    <textarea class="textarea" name="educationDepartmentName" placeholder="每个名称用逗号隔开" id="educationDepartmentName">${community.educationDepartmentName }</textarea>
                </div>
            </div>
            <div class="banck fl">
                <div class="cl1">银行营业厅/证券机构名称</div>
                <div class="cl2">
                    <textarea class="textarea" name="bankingDepartmentName" placeholder="每个名称用逗号隔开" id="bankingDepartmentName">${community.bankingDepartmentName }</textarea>
                </div>
            </div>
        </div>
    </div>
        <div class="comfirm">
            <div class="comfirm-value btnStyle1" onclick="validateForm()">保存</div>
        </div>
    </div>
	</form>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/community/js/communityedit.js"></script>
</body>
</html>