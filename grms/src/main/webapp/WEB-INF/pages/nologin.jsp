<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page language="java" import="com.hori.grms.service.RedisCacheService" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
//spring容器
WebApplicationContext ctx = null;
ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
RedisCacheService redisCacheService=(RedisCacheService)ctx.getBean("redisCacheService");
if(null!=session){

    session.removeAttribute("userAccount");
}
%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
	<%-- ${msg}
	${fn:length(msg)} --%>
	  <script type="text/javascript">
		top.location.href="/grms";
	</script>  
	
	</body>
</html>