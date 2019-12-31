<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%
	String host = request.getServerName();
	int port = request.getServerPort();
	String contextPath = request.getContextPath();
	request.setAttribute("host", host);
	request.setAttribute("port", port);
	request.setAttribute("contextPath", contextPath);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="http://${host}:${port}${contextPath}/" /><!-- 注意写好base -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="bin/js/jquery-1.4.js"></script>
<script src="bin/js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="bin/js/framework.js"></script>
<link href="bin/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=contextPath%>/bin/"/>
<!--框架必需end-->

<!--修正IE6不支持PNG图start-->
<script type="text/javascript" src="bin/js/method/pngFix/supersleight.js"></script>
<!--修正IE6不支持PNG图end-->

<!--截取文字start-->
<script type="text/javascript" src="bin/js/text/text-overflow.js"></script>
<!--截取文字end-->
<!-- 步进器js -->
<script type="text/javascript" src="bin/js/form/spinbox.js"></script>
<!-- 步进器js end -->
<!-- 表单验证js -->
<script src="bin/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="bin/js/form/validationEngine.js" type="text/javascript"></script>
<script type="text/javascript" src="js/customValidate.js"></script>
<!-- 表单验证js end -->
<script type="text/javascript" src="bin/js/form/loadmask.js"></script>
<script language="JavaScript" src="bin/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="bin/js/nav/switchable.js"></script>
<script type="text/javascript" src="bin/js/nav/switchable.effect.js"></script>
</head>
<body id="body">

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
		<tr>
			<td align="left" >
			审核状态：
			</td>
			<td >
			<input type="radio" name="status" checked="checked" value="1" onclick="changeStatus()"/>审核通过
			<input type="radio" name="status" value="2" onclick="changeStatus()"/>审核不通过
			</td>
		</tr>
		<tr style="display:none;" id="noCheckReason_tr">
			<td align="left" >
			审核失败原因：
			</td>
			<td >
			<select name="noCheckReason" id="noCheckReason" onchange="selectReseanValue()">
				<option value="样衰">样衰</option>
			</select>
			
			</td>
		</tr>
		<tr style="display:none;" id="noCheckReason_qt_tr">
			<td align="left" >
			手动填写原因：
			</td>
			<td >
			<textarea rows="" cols="" name="noCheckReason_qt" id="noCheckReason_qt"></textarea>
			</td>
		</tr>
	</table>



<script >
 $(function(){
	$("#trigger2").switchable("#slide2 > div > img", { effect: "scroll", vertical: true });
});

 
 	//状态改变触发
 	function changeStatus(){
 		var c=$('input[type="radio"][name="status"]:checked').val();
 		if(c=='2'){
 			$("#noCheckReason_tr").show();
 		}else{
 			$("#noCheckReason_tr").hide();
 			$("#noCheckReason_qt_tr").hide();
 		}
 	}
 	//触发选择失败原因下拉列表时调用
 	function selectReseanValue(){
 		var noCheckReason=$('#noCheckReason').val();
 		if('其他'==noCheckReason){
 			$("#noCheckReason_qt_tr").show();
 		}else{
 			$("#noCheckReason_qt_tr").hide();
 		}
 	}
 	
 	
 /**
	*信息提交
	*/
	function commit(){
	 	var result=true;
	 	var id='${developer.id}';
		var c=$('input[type="radio"][name="status"]:checked').val();
		var noCheckReasonValue='';//记录审核不通过的原因
		if(c=='1'){
			noCheckReasonValue='';
		}else{
			//判断是否选择了“其他”这个审核未通过
			var noCheckReason=$('#noCheckReason').val();
	 		if('其他'==noCheckReason){
	 			var noCheckReason_qt=$('#noCheckReason_qt').val();
	 			noCheckReason_qt=$.trim(noCheckReason_qt);
	 			if(''==noCheckReason_qt){
	 				alert('请手动填写具体的未审核通过原因！');
	 				return;
	 			}else{
	 				noCheckReasonValue=noCheckReason_qt;
	 			}
	 		}else{
	 			noCheckReasonValue=noCheckReason;
	 		}
		}
		
		
		
		$("#body").mask("表单正在提交...");
		$.ajax({
	         type: "POST",
	         async:false,//同步
	         dataType: "json", 
	         url:'checkDeveloperInfoReq.html',
	         data:{
	 			id:id,
				status:c,
				noCheckReason:noCheckReasonValue,
				r:new Date().getTime()
			},
	         success: function(msg) {
	        	 $("#body").unmask();
	        	 if("true"==msg.result){
	        	 }else{
	        		 alert(msg.error);
	        		 result=false;
	        	 }
	         },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 $("#body").unmask();
                alert(errorThrown);
                result=false
	         } 
	     });
		return result;
	}
</script>
</body>
</html>
