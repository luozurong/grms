<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="designFactory" value="合立正通" />
<c:set var="versionNum" value="v1.0" />
<c:set var="mpagSize">10</c:set>


<link rel="stylesheet" type="text/css" 
      href="<c:url value='/styles/displaytag.css'/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<c:url value='/bin/js/jquery-1.4.js'/>"></script>
<script src="<c:url value='/bin/js/form/validationEngine-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/bin/js/form/validationEngine.js'/> " type="text/javascript"></script>

<script type="text/javascript" src="<c:url value='/bin/js/framework.js'/>"></script>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<c:url value='/bin/'/>"/>
<link href="<c:url value='/bin/css/import_basic.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value='/bin/js/method/pngFix/supersleight.js'/>"></script>
<script src="<c:url value='/bin/js/jquery.form.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/bin/js/method/pngFix/supersleight.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bin/js/form/loadmask.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/global.js'/>"></script>

<%
	String host = request.getServerName();
	int port = request.getServerPort();
	String contextPath = request.getContextPath();
	request.setAttribute("host", host);
	request.setAttribute("port", port);
	request.setAttribute("contextPath", contextPath);
%>

