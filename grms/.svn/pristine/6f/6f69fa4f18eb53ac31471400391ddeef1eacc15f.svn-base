<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>新建资源</title>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/default.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/project/css/addProjectProdect.css" />
<style>
	#select_Area td input[type="checkbox"]{
		/* opacity: 1 !important; */
	}
	.datagrid-body{
	  overflow: auto !important;
	}
</style>
</head>
<body>
    <input  id="businessType" type="text" hidden="true" value="${businessType}"></input>
    <input  id="operate" type="text" hidden="true" value="${operate}"></input>
    
    <div class="topTitle clearfix">
		    <div class="go-back fl" style="margin-left:24px;margin-right:0" onclick="getBackProject()">返回</div>		
			<div class="path1 fl">项目管理</div>
			<div class="path2 fl">新建项目</div>
			<div class="path2 fl">${operate==0?"新建资源":"编辑资源" }</div>
	</div>
    
	<div class="addProject" style="margin-top:0">
		 <div class="workorder-tabs">
		 <c:if test="${operate==0 }">		 
	        <div class="workorder-tabs1" data-value="0101011">
	            <span>社区运营</span>
	        </div>
	        <div class="workorder-tabs2" data-value="0101012">
	            <span>媒管</span>
	        </div>
	        <div  class="workorder-tabs3" data-value="0101013">
	            <span>用户运营</span>
	        </div>
	        <div  class="workorder-tabs4" data-value="0101014">
	            <span>电商运营</span>
	        </div>
	      </c:if >
	      <c:if test="${operate==1 }">
	         <c:choose>
				     <c:when test="${businessType ==1}">
					   <div  class="workorder-tabs1" data-value="0101011" class="workorder-select">
		                   <span>社区运营</span>
		               </div>
				    </c:when>
				    <c:when test="${businessType ==2}">
						<div class="workorder-tabs2" data-value="0101012">
		                   <span>媒管</span>
		                </div>
				    </c:when>
				    <c:when test="${businessType ==3}">
					     <div  class="workorder-tabs3" data-value="0101013">
		            		<span>用户运营</span>
		        		 </div>
				    </c:when>
				    <c:otherwise>
					   		<div  class="workorder-tabs4"  data-value="0101014">
		            		<span>电商运营</span>
		        		   </div>
			        </c:otherwise>
			 </c:choose>

	      </c:if>
	    </div>	
	</div>
	<div class="addProject-pop">
	<!-- 	<div class="addProject-pop-active"> -->
	    <!-- 社区运营 -------------社区运营--------------------社区运营----------------------------------------------------------->
		<div>
			<div class="pop-none">
			    <input id="project1Id" type="hidden" value="${project1.id }"></input>
				<div class="pop-none-top pop-none-left">			
					<span>设置默认时间段:</span>
					<input style="width:160px;height:32px;" id="startTime1"  type= "text" class= "easyui-datetimebox" value="<fmt:formatDate type="both" 
            value="${project1.beginTime}" />" /> --至-- 
        			<input style="width:160px;height:32px;" id="endTime1"  type= "text" class= "easyui-datetimebox" value="<fmt:formatDate type="both" 
            value="${project1.endTime}" />" />
				</div>
				<div  class="pop-none-top pop-none-right">
					<span class="pop-none-btn" onclick="selectArea()">选择小区</span>
					<span id="project1AreaSh">${empty project1.areaName ?"未选小区":"已选小区:" } ${project1.areaName}</span>
					<!-- 保存小区：组织机构编号 -->
					<input id="project1AreaO" type="hidden"  value="${project1.organizationSeq }">
					<!-- 保存小区：名称 -->
					<input id="project1AreaName" type="hidden"  value="${project1.areaName }">
				</div>
			</div>
			<div class="pop-none-top pop-none-choose">
				<span>场次名称：</span>
				<input id="field_name" maxlength="20" class="pop-chang" type="text" value="${project1.fieldName}">
				<label id="isDistributionField" class='checkbox-icon ${project1.isDistributionField==1?"checkbox-bg":""}'>
					<input type="checkbox"  readonly name="basic">
					<span>是否服从场地分配</span>
				</label>
				<label id="isDistributionTime" class='checkbox-icon ${project1.isDistributionTime==1?"checkbox-bg":"" }'>
					<input type="checkbox"  readonly name="basic">
					<span>是否服从时间调配</span>
				</label>
			</div>
			<table>
				<thead>
					<tr>
						<th>类型</th>
						<th>产品清单</th>
						<th>产品规格</th>
						<th>购买数量</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" rowspan="22">类型</td>
						<td rowspan="2">标准场地<span class="color-light">（3米*3米，必须加基础引流）</span></td>
						<td>场次</td>
						<td>1</td>
					</tr>
					<!--标准场地（3米*3米，必须加基础引流） -->
					<tr class="project_1_1">
						<td><label class='checkbox-icon checkbox-bg hascheckbox'><input type="checkbox" value="1011" /><span>帐篷数(必选)</span></label></td>
						<td><input id="p1011"  class="input-num" type="text" value='${p1011 eq  null?1:p1011.buyNum }'></td>
					</tr>
					<tr>
						<td rowspan="16" align="center">游戏/内容:</td>
						<td>物料</td>
						<td>默认与帐篷数相同</td>
					</tr>
					<tr>
						<td colspan="2">驻留/引流（单选）</td>
					</tr>
					<!--                      -->
					<tr name="dd">
						<td><label class="radio-icon ${p1022 eq  null?"":"radio-bg"}"><input type="radio" name="dd" value="1021">集印章</label></td>
						<td rowspan="7"><input id="ddNum"
						<c:choose>
						    <c:when test="${!empty p1022}">
								value="${p1022.buyNum }"
						    </c:when>
						     <c:when test="${!empty p1023}">
								value="${p1023.buyNum }"
						    </c:when>
						     <c:when test="${!empty p1024}">
								value="${p1024.buyNum }"
						    </c:when>
						    <c:when test="${!empty p1025}">
								value="${p1025.buyNum }"
						    </c:when>
						     <c:when test="${!empty p1026}">
								value="${p1026.buyNum }"
						    </c:when>
						     <c:when test="${!empty p1027}">
							  	value="${p1027.buyNum }"
						    </c:when>
						</c:choose>

						 class="input-num" type="text"></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1022 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1022">蒙眼敲锣</label></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1023 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1023" >掌上明珠</label></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1024 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1024" >我心飞翔</label></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1025 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1025">海底捞月</label></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1026 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1026">套圈</label></td>
					</tr>
					<tr name="dd">
						<td><label class="radio-icon ${p1027 eq  null?"":"radio-bg" }"><input type="radio" name="dd" value="1027">抢板凳</label></td>
					</tr>
					<!--         游戏/内容：              -->
					<tr>
						<td colspan="2">追加游戏（多选）</td>	
					</tr>
					<tr>
						<td class="project1"><label class='checkbox-icon ${p1031 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1031"/><span>集印章</span></label></td>
						<td><input  class="input-num" type="text"  value="${p1031.buyNum }"></td>
					</tr>
					<tr>
						<td class="project1"><label class='checkbox-icon ${p1032 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1032"/><span>蒙眼敲锣</span></label></td>
						<td><input  class="input-num" type="text" value="${p1032.buyNum }"></td>
					</tr>
					<tr>			
						<td class="project1"><label class='checkbox-icon ${p1033 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1033"/><span>掌上明珠</span></label></td>
						<td><input  class="input-num" type="text" value="${p1033.buyNum }"></td>
					</tr>
					<tr>			
						<td class="project1"><label class='checkbox-icon ${p1034 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1034"/><span>我心飞翔</span></label></td>
						<td><input class="input-num" type="text" value="${p1034.buyNum }"></td>		
					</tr>
					<tr>					
						<td class="project1"><label class='checkbox-icon ${p1035 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1035"/><span>海底捞月</span></label></td>
						<td><input class="input-num" type="text" value="${p1035.buyNum }"></td>					
					</tr>
					<tr>					
						<td class="project1"><label class='checkbox-icon ${p1036 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1036"/><span>套圈</span></label></td>
						<td><input class="input-num" type="text" value="${p1036.buyNum }"></td>				
					</tr>
					<!--      专场策划                 -->
					<tr>
					    <td align="center" rowspan="2">专场策划</td>
						<td class="project1"><label class='checkbox-icon ${p1037 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="ds" value="1037"/><span>抢板凳</span></label></td>
						<td><input class="input-num" type="text" value="${p1037.buyNum }"></td>					
					</tr>
					<tr>									
						<td class="project1">
							<label class='checkbox-icon ${p1041 eq  null?"":"checkbox-bg" }'>
								<input type="checkbox"  readonly name="ds" value="1041"/>
								<span>定制（引流/驻留/品宣/体验</span>
							</label>
						</td>
						<td><input class="input-num" type="text" value="${p1041.buyNum }"></td>
					</tr>
					<!-- 基础引流 -->
					<tr>	
						<td align="center" rowspan="2">基础引流<span class="color-light"></span></td>
						<td class="project1">
							<label class='checkbox-icon ${p1051 eq  null?"":"checkbox-bg" }'>
								<input type="checkbox"  readonly name="ds" value="1051"/>
								<span>APP引流</span>
							</label>
						</td>
						<td><input class="input-num"  type="text" value="${p1051.buyNum }" /></td>
					</tr>
					<tr>							
						<td class="project1">
							<label class='checkbox-icon ${p1052 eq  null?"":"checkbox-bg" }'>
								<input type="checkbox"  readonly name="ds" value="1052"/>
								<span>LCD引流</span>
							</label>
						</td>
						<td><input class="input-num" type="text" value="${p1052.buyNum }"></td>
					</tr>
				</tbody>
			</table>	
			<div class="pop-footer">
				<span onclick="getBackProject()">返回</span>
				<span onclick="saveProject1()">保存</span>
			</div>
		</div>
		
		<!-- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 ---- 煤管 -->
		<div>
			<div class="pop-none">
				<div class="pop-none-top pop-none-left">
					<span>设置默认时间段:</span>
					<input style="width:115px;height:32px;" id="startTime2"  type= "text" class= "easyui-datebox"> --至-- 
        			<input style="width:115px;height:32px;" id="endTime2"  type= "text" class= "easyui-datebox">
				</div>
				<div  class="pop-none-top pop-none-right">
					<span class="pop-none-btn"  onclick="selectArea()">设置默认小区</span>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>类型</th>
						<th>产品清单</th>
						<th>产品清单</th>
						<th  style="width: 250px;">时间</th>
						<th>购买数量</th>
						<th  style="width: 250px;">已选小区</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td  align="center" rowspan="7">LCD门禁屏</td>
						<td  align="center" rowspan="4">屏体播放：</td>
						<td class="project2"><label class='checkbox-icon ${p2011 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>5s</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2011">
							<input class="oreqS" type="hidden" value="${p2011.areaIds}"><!--用于保存小区组织机构编号-->	
							
							<input style="width:115px;"  value="<fmt:formatDate type="date" value="${p2011.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"  value="<fmt:formatDate type="date" value="${p2011.endTime}"/>"   type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2011.areaNames}</td>						
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						<td class="project2"><label class='checkbox-icon ${p2012 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>10s</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2012">
							<input class="oreqS" type="hidden" value="${p2012.areaIds}"><!--用于保存小区组织机构编号-->	
							<input style="width:115px;"    value="<fmt:formatDate type="date" value="${p2012.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"    value="<fmt:formatDate type="date" value="${p2012.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2012.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					
					</tr>
					<tr>
						<td class="project2"><label class='checkbox-icon ${p2013 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>15s</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2013">
							<input class="oreqS" type="hidden" value="${p2013.areaIds}"><!--用于保存小区组织机构编号-->	
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2013.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2013.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2013.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
				
					</tr>
					<tr>
						<td class="f180 project2">
							<label class='checkbox-icon ${p2014 eq  null?"":"checkbox-bg" }'>
								<input type="checkbox"  readonly name="basic"><span>其他</span>
								<input class="input-num  input-inline-num" maxlength="5" type="text" value="${p2014.otherNum1}">
							</label>
						</td>
						<td>
							<input class="meanId" type="hidden" value="2014">
							<input class="oreqS" type="hidden" value="${p2014.areaIds}"><!--用于保存小区组织机构编号-->	
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2014.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2014.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2014.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
				
					</tr>
					<tr>
						<td align="center">语音广告：</td>
						<td class="project2"><label class='checkbox-icon ${p2021 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>三选一轮播</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2021">
							<input class="oreqS" type="hidden" value="${p2021.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2021.beginTime}"/>"  type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2021.endTime}"/>"  type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2021.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						<td align="center">屏体互动（扫码）</td>
						<td class="project2"><label class='checkbox-icon ${p2031 eq  null?"":"checkbox-bg" }'><input type="checkbox" readonly name="basic"><span>屏体互动（扫码）</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2031">
							<input class="oreqS" type="hidden" value="${p2031.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2031.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2031.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2031.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						<td align="center">开门弹窗</td>
						<td class="project2"><label class='checkbox-icon ${p2041 eq  null?"":"checkbox-bg" }'><input type="checkbox" readonly name="basic"><span>四选一循环</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2041">
							<input class="oreqS" type="hidden" value="${p2041.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2041.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2041.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2041.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						<td align="center" rowspan="29">APP+微信</td>
						<td  align="center" rowspan="17">banner位：</td>
						<td class="project2"><label class='checkbox-icon ${p2051 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>开门页-第1帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2051">
							<input class="oreqS" type="hidden" value="${p2051.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2051.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2051.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2051.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2052 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>开门页-第2帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2052">
							<input class="oreqS" type="hidden" value="${p2052.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2052.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2052.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2052.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2053 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>开门页-第3帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2053">
							<input class="oreqS" type="hidden" value="${p2053.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2053.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2053.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2053.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2054 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>开门页-第4帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2054">
							<input class="oreqS" type="hidden" value="${p2054.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2054.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2054.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2054.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2061 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>资讯页-第1帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2061">
							<input class="oreqS" type="hidden" value="${p2061.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2061.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2061.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2061.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2062 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>资讯页-第2帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2062">
							<input class="oreqS" type="hidden" value="${p2062.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2062.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2062.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2062.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2063 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>资讯页-第3帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2063">
							<input class="oreqS" type="hidden" value="${p2063.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2063.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2063.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2063.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2064 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>资讯页-第4帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2064">
							<input class="oreqS" type="hidden" value="${p2064.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2064.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2064.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2064.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2101 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>生活页顶部-第1帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2101">
							<input class="oreqS" type="hidden" value="${p2101.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2101.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2101.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2101.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2102 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>生活页顶部-第2帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2102">
							<input class="oreqS" type="hidden" value="${p2102.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2102.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2102.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2102.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2103 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>生活页顶部-第3帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2103">
							<input class="oreqS" type="hidden" value="${p2103.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2103.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2103.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2103.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2104 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>生活页顶部-第4帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2104">
							<input class="oreqS" type="hidden" value="${p2104.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2104.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2104.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2104.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
			
						<td class="project2"><label class='checkbox-icon ${p2201 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>生活页中部-独占</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2201">
							<input class="oreqS" type="hidden" value="${p2201.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2201.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2201.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2201.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2071 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商城页-第1帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2071">
							<input class="oreqS" type="hidden" value="${p2071.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2071.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2071.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2071.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td class="project2"><label class='checkbox-icon ${p2072 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商城页-第2帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2072">
							<input class="oreqS" type="hidden" value="${p2072.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2072.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p2072.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2072.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
			
						<td class="project2"><label class='checkbox-icon ${p2073 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商城页-第3帧</span></label></td>
						<td> 
							<input class="meanId" type="hidden" value="2073">
							<input class="oreqS" type="hidden" value="${p2073.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2073.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2073.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2073.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td class="project2"><label class='checkbox-icon ${p2074 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商城页-第4帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2074">
							<input class="oreqS" type="hidden" value="${p2074.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2074.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2074.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2074.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						<td rowspan="12" class="f100">资讯feed流：（可选1－12帧）</td>
						<td class="project2"><label class='checkbox-icon ${p2081 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>1帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2081">
							<input class="oreqS" type="hidden" value="${p2081.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2081.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2081.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2081.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td class="project2"><label class='checkbox-icon ${p2082 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>2帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2082">
							<input class="oreqS" type="hidden" value="${p2082.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2082.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2082.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2082.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2083 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>3帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2083">
							<input class="oreqS" type="hidden" value="${p2083.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2083.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2083.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2083.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2084 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>4帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2084">
							<input class="oreqS" type="hidden" value="${p2084.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2084.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2084.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2084.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2085 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>5帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2085">
							<input class="oreqS" type="hidden" value="${p2085.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p2085.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2085.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2085.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td class="project2"><label class='checkbox-icon ${p2086 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>6帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2086">
							<input class="oreqS" type="hidden" value="${p2086.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2086.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2086.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2086.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2087 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>7帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2087">
							<input class="oreqS" type="hidden" value="${p2087.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"  value="<fmt:formatDate type="date" value="${p2087.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"  value="<fmt:formatDate type="date" value="${p2087.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2087.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2088 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>8帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2088">
							<input class="oreqS" type="hidden" value="${p2088.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p2088.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p2088.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2088.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project2"><label class='checkbox-icon ${p2089 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>9帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2089">
							<input class="oreqS" type="hidden" value="${p2089.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2089.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2089.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2089.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2090 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>10帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2090">
							<input class="oreqS" type="hidden" value="${p2090.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2090.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2090.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2090.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2091 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>11帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2091">
							<input class="oreqS" type="hidden" value="${p2091.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2091.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p2091.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2091.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project2"><label class='checkbox-icon ${p2092 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>12帧</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="2092">
							<input class="oreqS" type="hidden" value="${p2092.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2092.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p2092.endTime}"/>" type= "text" class= "easyui-datebox endTime">
						</td>
						<td align="center">1</td>
						<td align="center" class="area_names">${p2092.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
				</tbody>
			</table>
			<div class="pop-footer">
				<span onclick="getBackProject()">返回</span>
				<span onclick="saveProduct(2)">保存</span>
			</div>
		</div>
		
		<!-- 用户运营 ---- 用户运营 ---- 用户运营 ---- 用户运营 ---- 用户运营 ---- 用户运营 ---- 用户运营 ---- 用户运营 -->
		<div>
			<div class="pop-none">
				<div class="pop-none-top pop-none-left">
					<span>设置默认时间段:</span>
					<input style="width:115px;height:32px;" id="startTime3"  type= "text" class= "easyui-datebox"> --至-- 
        			<input style="width:115px;height:32px;" id="endTime3"  type= "text" class= "easyui-datebox">
				</div>
				<div  class="pop-none-top pop-none-right">
					<span class="pop-none-btn" onclick="selectArea()">设置默认小区</span>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>类型</th>
						<th>产品清单</th>
						<th>产品清单</th>
						<th  style="width: 250px;">时间</th>
						<th>购买数量</th>
						<th  style="width: 250px;">已选小区</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" rowspan="16">APP+微信</td>
						<td align="center">APP软文</td>
						<td class="project3"><label class='checkbox-icon ${p3011 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>APP软文</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3011">
							<input class="oreqS" type="hidden" value="${p3011.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3011.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3011.endTime}"/>" type= "text" class= "easyui-datebox endTime">        					
        				</td>
						<td align="center"><input value="${p3011.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3011.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td align="center" rowspan="6">APP活动</td>
						<td class="project3"><label class='checkbox-icon ${p3021 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>福利派发</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3021">
							<input class="oreqS" type="hidden" value="${p3021.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"   value="<fmt:formatDate type="date" value="${p3021.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"   value="<fmt:formatDate type="date" value="${p3021.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3021.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3021.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						
						<td class="project3"><label class='checkbox-icon ${p3022 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>砸金蛋</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3022">
							<input class="oreqS" type="hidden" value="${p3022.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3022.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3022.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3022.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3022.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
					
						<td class="project3"><label class='checkbox-icon ${p3023 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>九宫格</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3023">
							<input class="oreqS" type="hidden" value="${p3023.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3023.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3023.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        					      					     		
        				</td>
						<td align="center"><input value="${p3023.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3023.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					
					<tr>
						
						
						<td class="project3"><label class='checkbox-icon ${p3024 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>小游戏</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3024">
							<input class="oreqS" type="hidden" value="${p3024.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3024.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"        value="<fmt:formatDate type="date" value="${p3024.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3024.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3024.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>  
					</tr>
					<tr>
					
						
						<td class="project3"><label class='checkbox-icon ${p3025 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>竞猜活动</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3025">
							<input class="oreqS" type="hidden" value="${p3025.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3025.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3025.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3025.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3025.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						
						<td class="project3"><label class='checkbox-icon ${p3026 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>投票活动</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3026">
							<input class="oreqS" type="hidden" value="${p3026.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3026.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3026.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3026.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3026.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td align="center" rowspan="8">屏体播放：</td>
						<td class="project3"><label class='checkbox-icon ${p3031 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>头条</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3031">
							<input class="oreqS" type="hidden" value="${p3031.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3031.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3031.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3031.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3031.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
						
						<td class="project3"><label class='checkbox-icon ${p3032 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副1</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3032">
							<input class="oreqS" type="hidden" value="${p3032.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3032.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"    value="<fmt:formatDate type="date" value="${p3032.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3032.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3032.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project3"><label class='checkbox-icon ${p3033 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副2</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3033">
							<input class="oreqS" type="hidden" value="${p3033.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3033.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3033.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3033.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3033.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project3"><label class='checkbox-icon ${p3034 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副3</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3034">
							<input class="oreqS" type="hidden" value="${p3034.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3034.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3034.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3034.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3034.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
			
						<td class="project3"><label class='checkbox-icon ${p3035 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副4</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3035">
							<input class="oreqS" type="hidden" value="${p3035.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3035.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p3035.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3035.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3035.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project3"><label class='checkbox-icon ${p3036 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副5</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3036">
							<input class="oreqS" type="hidden" value="${p3036.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3036.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3036.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3036.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3036.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project3"><label class='checkbox-icon ${p3037 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副6</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3037">
							<input class="oreqS" type="hidden" value="${p3037.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3037.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3037.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3037.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3037.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td class="project3"><label class='checkbox-icon ${p3038 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>副7</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3038">
							<input class="oreqS" type="hidden" value="${p3038.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p3038.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3038.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3038.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3038.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
				
						<td align="center">微信公众号消息推送</td>
						<td class="project3"><label class='checkbox-icon ${p3041 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>消息推送</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="3041">
							<input class="oreqS" type="hidden" value="${p3041.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p3041.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"    value="<fmt:formatDate type="date" value="${p3041.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p3041.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p3041.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
				</tbody>
			</table>
			<div class="pop-footer">
				<span onclick="getBackProject()">返回</span>
				<span onclick="saveProduct(3)">保存</span>
			</div>
		</div>
		
		<!-- 电商运营 ---- 电商运营 ---- 电商运营 ---- 电商运营 ---- 电商运营 ---- 电商运营 ---- 电商运营 ---- 电商运营 -->
		<div>
			<div class="pop-none">
				<div class="pop-none-top pop-none-left">
					<span>设置默认时间段:</span>
					<input style="width:115px;height:32px;" id="startTime4"  type= "text" class= "easyui-datebox"> --至-- 
        			<input style="width:115px;height:32px;" id="endTime4"  type= "text" class= "easyui-datebox">
				</div>
				<div  class="pop-none-top pop-none-right">
					<span class="pop-none-btn" onclick="selectArea()">设置默认小区</span>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>类型</th>
						<th>产品清单</th>
						<th>产品清单</th>
						<th style="width: 250px;">时间</th>
						<th>购买数量</th>
						<th style="width: 250px;">已选小区</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" rowspan="6">APP+微信</td>
						<td align="center">电商平台服务</td>
						<td class="project4"><label class='checkbox-icon ${p4011 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商城基础服务（上架、咨询、结算）（7%）</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4011">
							<input class="oreqS" type="hidden" value="${p4011.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4011.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4011.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4011.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p4011.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td align="center" rowspan="2">合伙人销售服务</td>
						<td class="project4"><label class='checkbox-icon ${p4021 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>合伙人销售（16%-20%）</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4021">
							<input class="oreqS" type="hidden" value="${p4021.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p4021.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4021.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4021.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p4021.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td class="project4"><label class='checkbox-icon ${p4022 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>合伙人渠道素材制作</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4022">
							<input class="oreqS" type="hidden" value="${p4022.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4022.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p4022.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4022.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p4022.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						<td align="center" rowspan="3">APP软文</td>
						<td class="project4"><label class='checkbox-icon ${p4031 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>商品包装设计</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4031">
							<input class="oreqS" type="hidden" value="${p4031.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4031.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"       value="<fmt:formatDate type="date" value="${p4031.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4031.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p4031.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						
						<td class="project4"><label class='checkbox-icon ${p4032 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>秒杀</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4032">
							<input class="oreqS" type="hidden" value="${p4032.areaIds}"><!--用于保存小区组织机构编号-->
							<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4032.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"      value="<fmt:formatDate type="date" value="${p4032.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4032.buyNum}" class="input-num" type="text"></td>
						<td align="center" class="area_names">${p4032.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
					<tr>
					
						
						<td class="project4"><label class='checkbox-icon ${p4033 eq  null?"":"checkbox-bg" }'><input type="checkbox"  readonly name="basic"><span>拼团</span></label></td>
						<td>
							<input class="meanId" type="hidden" value="4033">
							<input class="oreqS" type="hidden" value="${p4033.areaIds}"><!--用于保存小区组织机构编号-->						
							<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p4033.beginTime}"/>" type= "text" class= "easyui-datebox startTime">
        					<input style="width:115px;"     value="<fmt:formatDate type="date" value="${p4033.endTime}"/>" type= "text" class= "easyui-datebox endTime">
        				</td>
						<td align="center"><input value="${p4033.buyNum}" class="input-num"  type="text"></td>
						<td align="center" class="area_names">${p4033.areaNames}</td>
						<td><div class="tb-color">选择小区</div></td>
					</tr>
				</tbody>
			</table>
			<div class="pop-footer">
				<span onclick="getBackProject()">返回</span>
				<span onclick="saveProduct(4)">保存</span>
			</div>
		</div>
	</div>	
	
	<!-- 查询小区 -->
	<!-- 添加客户的弹窗,2,3,4-->
	<div id="select_Area" style="display: none">
	    <div class="area-input">
			<span>小区名称：</span>
			<input  type="text" id="communityName" maxlength="20"/>
			
	 		<span>区域:</span>
            <input id="province"  type="text" />
            <input id="city"  type="text" value="请选择"/>
            <input id="country"  type="text" value="请选择"/> 
                             
		    <span class="search-btn" onclick="detailSearch()">搜索</span>
		</div>
		<div>
			<div id="infoDg"></div>
		</div>
		<div class="infop" style="position: relative;">
			<div id="infoPp" style=""></div>
		</div>
	</div>
	
	<!-- 省、市、区保存值的隐藏域 -->
	 <input  id="provinceCode" type="text" hidden="true" ></input>
	 <input  id="cityCode" type="text" hidden="true" ></input>
	 <input  id="countryCode" type="text" hidden="true" ></input>
	 
	 <script type="text/javascript">
	    //用户保存3,4 的剩余次数
	    var addNum=${addNum};
	 </script>
	 
	 <!--遮罩-->
	<div class="shadeBox">
		<div class="tipsInfo">
			<p>正在处理中......</p>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=basePath%>/common/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/plugin/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/project/js/addProjectProdect.js"></script>
</body>
</html>