<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@  page pageEncoding="UTF-8" import="java.util.*"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	
	//去空格
	function trimHZ(str) 
	{ 
		return  str.replace(/\s/g,"");
	} 

	

</script>
</head>
<body>
<div id="scrollContent">
	<div class="box2" panelWidth="600" panelTitle="开发者信息管理" showStatus="false" roller="true">
	    <!-- 基本开发者信息 -->
	    <s:form  id="editOrSaveDeveloper" action="editOrSaveDeveloper" theme="simple" name="editOrSaveDeveloper">
	    	<input type="hidden" name="developer.id" value="<s:property value="developer.id"/>"/>
	    	
		<table class="tableStyle" transMode="true" footer="normal">
			<tr>
				<td ><span class="star">*</span>开发者登录账号：</td>
				<td><input type="text" id="devAccount" name="developer.devAccount"  value="${developer.devAccount}" class="validate[required,length[1,30]]" <c:if test="${opttype!='add'}">readonly="readonly"</c:if> /></td>
				<td ><span class="star">*</span>开发者真实姓名：</td>
				<td><input type="text" id="devName" name="developer.devName"  value="${developer.devName}" class="validate[required,custom[chinese],length[1,30]]" /></td>
			</tr>
			<tr>
				<td ><span class="star">*</span>开发者类型：</td>
				<td>
				<select id="devType" name="developer.devType">
				  <option value ="1" <c:if test="${developer.devType=='1'}">selected="selected"</c:if> >个人开发者</option>
				  <option value ="2" <c:if test="${developer.devType=='2'}">selected="selected"</c:if>>企业开发者</option>
				</select>
				</td>
				<td ><span class="star">*</span>联系邮箱：</td>
				<td><input type="text" id="devEmail" name="developer.devEmail"  value="${developer.devEmail}" class="validate[custom[email]]" /></td>
			</tr>
			<tr>
				<td ><span class="star">*</span>联系电话：</td>
				<td><input type="text" id="devPhone" name="developer.devPhone"  value="${developer.devPhone}" class="validate[required,custom[mobilephone]]" /></td>
				<td ></span>开发者地址：</td>
				<td><input type="text" id="devAddr" name="developer.devAddr"  value="${developer.devAddr}"/></td>
			</tr>
			<tr>
				<td ></span>网站地址：</td>
				<td><input type="text" id="home_page" name="developer.home_page"  value="${developer.home_page}"/></td>
				<td ></span>公司名称：</td>
				<td><input type="text" id="companyName" name="developer.companyName"  value="${developer.companyName}"/></td>
			</tr>
			<tr>
				<td ></span>公司详细地址：</td>
				<td><input type="text" id="companyAddr" name="developer.companyAddr"  value="${developer.companyAddr}"/></td>
				<td ></span>企业邮箱：</td>
				<td><input type="text" id="companyEmail" name="developer.companyEmail"  value="${developer.companyEmail}"/></td>
			</tr>

			<tr>
				<td ></span>企业电话：</td>
				<td><input type="text" id="companyPhone" name="developer.companyPhone"  value="${developer.companyPhone}"/></td>
				<td ></span>公司联系人姓名：</td>
				<td><input type="text" id="contact" name="developer.contact"  value="${developer.companyEmail}"/></td>
			</tr>
			<tr>
				<td ></span>公司联系人电话：</td>
				<td><input type="text" id="contactphone" name="developer.contactphone"  value="${developer.contactphone}"/></td>
				<td ></span>等级：</td>
				<td><input type="text" id="grade" name="developer.grade"  value="${developer.grade}"/></td>
			</tr>
			<%-- 当进行查看操作时才能看到审核人审核时间等信息，修改操作时不显示审核人审核时间更新时间等信息 --%>
			<c:if test="${opttype=='view'}">
				<tr>
					<td ></span>审核状态：</td>
					<td><input type="text" id="status" name="developerStatus"  readonly="readonly"
					value="<c:choose>
						<c:when test="${developer.status=='0'}">待审核</c:when>
						<c:when test="${developer.status=='1'}">审核通过</c:when>
						<c:when test="${developer.status=='2'}">审核不通过</c:when>
					</c:choose>"/>
					<input type="hidden" id="status" name="developer.status" value="${developer.status}"/>
					</td>
					<td ></span>审核人ID：</td>
					<td><input type="text" id="checkerId" name="developer.checkerId"  readonly="readonly" value="${developer.checkerId}"/></td>
				</tr>
				<tr>
					<td ></span>审核人用户名：</td>
					<td><input type="text" id="checkerName" name="developer.checkerName"  readonly="readonly" value="${developer.checkerName}"/></td>
					<td ></span>审核时间：</td>
					<td><input type="text" id="checkTime" name="developer.checkTime"  readonly="readonly" value="<s:date name="developer.checkTime" format="yyyy-MM-dd" nice="false"/>"/></td>
				</tr>
				<tr>
					<td ></span>创建时间：</td>
					<td><input type="text" id="createTime" name="developer.createTime"  readonly="readonly" value="<s:date name="developer.createTime" format="yyyy-MM-dd" nice="false"/>"/></td>
					<td ></span>更新时间：</td>
					<td><input type="text" id="updateTime" name="developer.updateTime"  readonly="readonly" value="<s:date name="developer.updateTime" format="yyyy-MM-dd" nice="false"/>"/></td>
				</tr>
			</c:if>
		</table>
		
		<div class="padding_top10">
			<table class="tableStyle" transMode="true">
				<tr>
					<td colspan="4">
						<c:if test="${opttype!='view'}">
						<input type="button" onclick="return commit('editOrSaveDeveloper','saveDeveloper.html','developers.html');" value=" 提 交 "/>
						</c:if>
						<input type="button" onclick="javascript:void(0);history.back();" value=" 返 回 "/>
					</td>
				</tr>
			</table>
		</div> 
		</s:form>
	</div>
</div>				
</body>
</html>