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
	</head>
	<body>
	<div class="createBack">
		<div class="workorder-breadcrumb">
		    <span class="workorder-breadcrumb-icon"></span>
		    <span>收款管理</span>
		    <span class='ic_right'></span>
		    <span>审核</span>
		</div>
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >项目合同信息</span>
				</div>
			</div>
		</div>
		<div class="body-box" style="background:#fff;padding-bottom:16px;">
			<input id="contractCode" type="hidden" value="${contractVo.contractCode }"/>
			<input id="backMoneyPlanCode" type="hidden" value="${backMoneyPlanCode }"/>
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
				<div>
					<span>业务员：</span>
					<span>${contractVo.createrName}</span>	
				</div>
				<div>
					<span>审批时间：</span>
					<span><fmt:formatDate value="${contractVo.approveTime}" pattern='yyyy-MM-dd HH:mm:ss'/></span>	
				</div>
				<div>
					<span>合同收款状态：</span>
					<span>待审核</span>	
				</div>
			</div>	
		</div>	
				
		<div class="blackbackground">
			<div style="background-color:rgba(102, 102, 102, 1);width:100%;height:44px;float: left;margin-bottom: 20px" >
				<div style="color: #FFFFFF;border-style:solid; border-width:10px; border-color:rgba(102, 102, 102, 1)" >
					<span >设置收款计划</span>
				</div>
			</div>
		</div>
		
		<div class="body-box" id="template" style="background: #fff;">
			<c:forEach items="${list}" var="backMoneyPlanPeriods" varStatus="status">
				<div class="table-wrap">
					<div class="table-wrap-a">
						<span class="wrap-span">第${status.index+1 }期：</span>
					</div>	
				</div>
				<div class="table-wrap">
					<div>
						<span class="wrap-span">款项属性：</span>
						<span>
						<c:if test="${ backMoneyPlanPeriods.type==1}">
							进度款
						</c:if>
						<c:if test="${ backMoneyPlanPeriods.type==2}">
							结算款
						</c:if>
						</span>	
					</div>	
				</div>	
				<div class="table-wrap">
					<div>
						<span class="wrap-span">计划收款时间：</span>
						<span>
							<fmt:formatDate value="${ backMoneyPlanPeriods.planBackTime}" pattern='yyyy-MM-dd'/>
						</span>	
					</div>	
				</div>	
				<div class="table-wrap">
					<div>
						<span class="wrap-span">计划收款金额：</span>
						<span>
							${backMoneyPlanPeriods.planBackMoney }
						</span>	
					</div>	
				</div>	
			</c:forEach>
		</div>
		<div style="background: #fff;text-align: center;">
			<button style="display:inline-block;margin: 20px 40px;color: #51d2b6;border-radius:4px;border:1px solid #51d2b6;width: 120px;height: 40px;line-height: 40px;text-algin: center;" onclick="pass()">通过</button> 
			<button style="display:inline-block;margin: 20px 40px;color: #51d2b6;border-radius:4px;border:1px solid #51d2b6;width: 120px;height: 40px;line-height: 40px;text-algin: center;" onclick="notPass()">不通过</button> 
			<button style="display:inline-block;margin: 20px 40px;color: #51d2b6;border-radius:4px;border:1px solid #51d2b6;width: 120px;height: 40px;line-height: 40px;text-algin: center;"  onclick="cancel()">取消</button> 
		</div>
	</div>
	
	<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.min.js"></script>
	<script type="text/javascript"	src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript"	src="<%=basePath%>/common/plugin/common.js"></script>
	<script type="text/javascript"	src="<%=basePath%>/backMoneyPlan/js/checkBackMoneyPlan.js"></script>
	</body>
</html>