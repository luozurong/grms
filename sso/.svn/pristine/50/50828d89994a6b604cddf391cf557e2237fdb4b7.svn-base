<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>开发者信息列表</title>
<script type="text/javascript" src="<c:url value='/js/developers/developer.js'/>"></script>
<script type="text/javascript">
/**
*审核-弹出框
*/
function authInfo(id,account){
	var diag = new top.Dialog();
	diag.Title = "审核（"+account+"）开发者信息";
	diag.URL = "developerInfoCheckWin.html?id="+id;
	diag.Width = 650;
	diag.Height = 450;
	diag.OKEvent = function(){
		var commitResult = diag.innerFrame.contentWindow.commit();//审核提交结果  只有提交成功才返回true
		if(commitResult){
			top.frmright.document.location.href=top.frmright.document.location.href;//刷新页面框架左边菜单
			diag.close();
		}
	};
	diag.show();
	showProgressBar();
}
</script>
</head>
<body>
<div class="position">
	<div class="center">
	<div class="left">
	<div class="right">
		<span>当前位置：系统管理>> 开发者信息列表</span>
	</div>	
	</div>	
	</div>
</div>
<div id="scrollContent" >	
<s:form id="manageDeveloper" action="manageDeveloper" theme="simple" name="manageDeveloper">

	<div class="box2" roller="false">
	    
			<table>
				<tr>
					<td>用户名</td>
					<td><input name="developerQueryBean.devName" value="${developerQueryBean.devName}" type="text" style="width: 200px;"/></td>
					<td>帐号</td>
					<td><input name="developerQueryBean.devAccount" value="${developerQueryBean.devAccount}" type="text" style="width: 200px;"/></td>
					<%-- <td>用户类型</td>
					<td><s:select list="#request.types" listValue="name" listKey="id" headerKey="" headerValue="全部" name="userQueryBean.userType" theme="simple"/></td> --%>
					<td><button type="submit"  name="method:searchDeveloper"><span class="icon_find">查询</span></button></td>
				</tr>
			</table>
			
	</div>
	
	<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
	<div class="center">
	<div class="left">
	<div class="right">		
		<div class="padding_top5 padding_left10">
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="addObject('manageDeveloper','manageDeveloper!add.html')"><span class="icon_add" >添加</span></a>
		<div class="box_tool_line"></div>
		<!-- <a onclick="delChecked('delUserChecked.html','users.html')" href="javascript:;"><span class="icon_delete">删除</span></a>  -->

		</div>
	</div>
	</div>		 
	</div>	
	</div>
	<div class="clear"></div>

			<display:table name="developers" class="tableStyle" requestURI="" id="developer" 
				export="false" pagesize="10" cellpadding="0" cellspacing="0">
				
				<display:column property="devAccount" sortable="false" headerClass="sortable"
					 paramId="id"
					paramProperty="id"  title="开发者帐号"
					style="width: 10%;word-break:break-all;" />
					
				<display:column property="devName" sortable="false" headerClass="sortable"
					title="开发者名称"
					style="width: 10%;word-break:break-all;" />
				
				<display:column  sortable="false" headerClass="sortable"
					title="开发者类型"
					style="width: 10%;word-break:break-all;">
					<c:choose>
						<c:when test="${developer.devType=='1'}">
							<span>个人</span>
						</c:when>
						<c:when test="${developer.devType=='2'}">
							<span>企业</span>
						</c:when>
					</c:choose>	
				</display:column>
					
				<display:column property="devEmail" sortable="false" headerClass="sortable"
					title="联系邮箱"
					style="width: 10%;word-break:break-all;" />
					
				<display:column property="devPhone" sortable="false" headerClass="sortable"
					title="联系电话"
					style="width: 10%;word-break:break-all;" />
					
				<%-- <display:column property="companyName" sortable="false" headerClass="sortable"
					title="公司名称"
					style="width: 10%;word-break:break-all;" />
					
				<display:column property="contact" sortable="false" headerClass="sortable"
					title="公司联系人姓名"
					style="width: 10%;word-break:break-all;" />
					
				<display:column property="contactphone" sortable="false" headerClass="sortable"
					title="公司联系人电话"
					style="width: 10%;word-break:break-all;" /> --%>
					
				<display:column  sortable="false" headerClass="sortable"
					title="审核状态"
					style="width: 10%;word-break:break-all;">
					<c:choose>
						<c:when test="${developer.status=='0'}">
							<span style="color:orange;">待审核</span>
						</c:when>
						<c:when test="${developer.status=='1'}">
							<span style="color:green;">审核通过</span>
						</c:when>
						<c:when test="${developer.status=='2'}">
							<span style="color:red;">审核不通过</span>
						</c:when>
					</c:choose>
				</display:column>
				
				<display:column property="createTime" sortable="false" headerClass="sortable"
					 paramId="id" format="{0,date,yyyy-MM-dd}" 
					paramProperty="id" title="创建日期"
					style="width: 10%;word-break:break-all;" />
					
				<display:column sortable="false" headerClass="sortable" 
							title="操作"
							style="word-break:break-all;">
						<a href="viewDeveloper.html?id=${developer.id}">
							<span class="icon_view" >【查看】</span>
						</a>
						<a href="editDeveloper.html?id=${developer.id}">
							<span class="icon_edit" >【编辑】</span>
						</a>
						<a href="javascript:void(0);">
							<span class="icon_mark" onclick="authInfo('${developer.id}','${developer.devName}')">【开发者状态审核】</span>
						</a>

							<!--  <a href="javascript:void(0);" onclick="singleDelChecked('delUserChecked.html','${user.id}','users.html')">
								<span class="icon_delete" >【删除用户】</span>
							</a>
							-->
						
				</display:column>
				
			</display:table>
</s:form>
</div>
</body>
</html>