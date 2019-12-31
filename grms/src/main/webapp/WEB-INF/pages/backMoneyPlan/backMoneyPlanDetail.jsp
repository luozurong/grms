<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.hori.vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html style="height: 100%;">
	<head>
	<meta charset="UTF-8">
	<title>查看项目</title>
	<meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/backMoneyPlan/css/createBackMoneyPlan.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/backMoneyPlan/plugin/timezhou/css/style.css">
	<style type="text/css">
		.planList_Box .datagrid-row-checked{
    	  background-color:#fff!important;
    	}
    	.window{
    		top: 50% !important;
    	}
    	.dialog-button a{
    		background: #fff;
    	}
	</style>
	</head>
	<body>
	<div class="createBack">
		<div class="workorder-breadcrumb">
		    <span class="workorder-breadcrumb-icon"></span>
		    <span>收款管理</span>
		    <span class='ic_right'></span>
		    <span>收款计划</span>
		</div>
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >项目合同信息</span>
				</div>
			</div>
		</div>
		<div class="body-box" style="background:#fff;padding-bottom:16px;">
			<input id="roleType" type="hidden" value="${roleType}"/>
			<input id="contractCode" type="hidden" value="${contractVo.contractCode }"/>
			<input id="backMoneyPlanCode" type="hidden" value="${backMoneyPlanCode}"/>
			<input id="end" type="hidden" value="${end}"/>
			<div class="table-wrap table-wrap-div">
				<div>
					<span>项目ID：</span>
					<span>${contractVo.projectCode}</span>	
				</div>	
				<div>
					<span>合同ID：</span>
					<span>${contractVo.contractCode}</span>	
				</div>
				<div>
					<span>合同名称：</span>
					<span>${contractVo.contractName}</span>	
				</div>
				<div>
					<span>公司名称：</span>
					<span>${contractVo.name}</span>	
				</div>
				<div class="table-width">
					<span>合同总金额：</span>
					<span>${money}</span>	
				</div>
				<div >
					<span>业务员：</span>
					<span>${contractVo.createrName}</span>	
				</div>
				<div>
					<span>审批时间：</span>
					<span><fmt:formatDate value="${contractVo.approveTime}" pattern='yyyy-MM-dd HH:mm:ss'/></span>	
				</div>
				<div>
					<span>合同收款状态：</span>
					<span>${planStatus}</span>	
				</div>
			</div>	
		</div>	
				
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >系统信息</span>
				</div>
			</div>
		</div>
		
		<div class="body-box" id="template" style="background: #fff;">
			<div class="table-wrap">
				<div>
					<span class="wrap-span">创建人：</span>
					<span>${createrName }</span>	
				</div>	
			</div>	
			<div class="table-wrap" style="padding-bottom: 20px;">
				<div>
					<span class="wrap-span">创建时间：</span>
					<span><fmt:formatDate value="${createTime}" pattern='yyyy-MM-dd HH:mm:ss'/></span>
				</div>	
			</div>	
		</div>
		
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >收款登记</span>
				</div>
			</div>
		</div>
		<div class="planList_Box" style="margin: 20px;width:100%;">
			<div id="planList"  style='width:97%'></div>
		</div>
		
		
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >全部到账说明</span>
				</div>
			</div>
		</div>
		<div>
			<span class="wrap-span" style="display:block;width: 100%;padding: 0 20px; text-align:left; box-sizing:border-box;">${allbackRemark}</span>
		</div>
		
		
	</div>
	
	<!--登记收款-->
	<div id="roleWin" style="display: none">
	    <div class="roleWin-wrap" id='roleWin1'>
            <div class="allotWorkOrder-input" style="padding: 20px 0; margin: 0 40px;">
            	<div style="line-height: 50px;">
            		<span id="realeReduce" style="display: none"><span style="color: red;">*</span>实际扣款：</span>
            		<span id="realeBack" style="display: none"><span style="color: red;">*</span>实际收款：</span>
            		 <input type="text" id="realBackMoney" style="padding-left: 10px;width:170px;height: 32px;border: 1px solid #ccc;">
            	</div>
            	<div  style="line-height: 50px;" id="realbacktime">
            		<span><span style="color: red;">*</span>到账时间：</span>
            		<input style="height: 32px;width: 180px;"  id="realBackTime" class="easyui-datebox" value="">
            	</div>
            	<div  style="line-height: 50px;margin-top: 10px;">
            		<span style="vertical-align: top;"><span style="color: red;vertical-align: top;">*</span>审批意见：</span>
            		<textarea id="realBackRemark" style="border-radius: 4px;width:250px;height: 70px;padding: 10px;border: 1px solid #ccc;"></textarea>
            	</div>
	    	</div>
	    </div>
	</div>
	
	
	<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
	<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
	<script type="text/javascript"	src="<%=basePath%>/backMoneyPlan/js/backMoneyPlanDetail.js"></script>
	</body>
</html>