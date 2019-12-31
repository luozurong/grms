<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@page import="com.jlit.uums.webservice.bean.*"%>
<%@page import="java.util.*"%>
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
<script type="text/javascript" src="bin/js/framework.js"></script>
<link href="bin/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=contextPath%>/bin/"/>
<!--框架必需end-->
<script type="text/javascript">
// jQuery.jCookie('expandable','0c');//设置cookie,默认展开第一个一级菜单
	jQuery.jCookie('expandable','0c11');//设置cookie,随意设置一个菜单，免得自动展开第一个菜单
</script>
<script type="text/javascript" src="bin/js/nav/ddaccordion.js"></script>
<script type="text/javascript" src="bin/js/text/text-overflow.js"></script>
<style>
a {
	behavior:url(bin/js/method/focus.htc)
}
.categoryitems span{
	width:160px;
}
</style>
<script>
	//打开内页时出现进度条
	$(function(){
		//默认打开第一个菜单的链接
		/* var first_menu=$('#first_menu').val();
		if(first_menu){
			top.frmright.document.location.href=first_menu;//框架左边主页面打开链接
		} */
		$(".categoryitems a[target*=frmright]").click(function(){
			showProgressBar();
		});
	})
</script>
</head>
<body leftFrame="true">
<div id="scrollContent">
	
	<%
	try{
	List<UserMenu> rootMenus = (List<UserMenu> ) request.getSession().getAttribute("rootMenus");
	
	List<UserMenu> subMenus = (List<UserMenu> ) request.getSession().getAttribute("subMenus");
	//查找符合条件的菜单
	for (UserMenu rootMenu : rootMenus) {// 抽取出权限内的root菜单
				String s =  "<div class='arrowlistmenu'><div class='menuheader expandable'><span class='normal_icon5' ";
				if(null!=rootMenu.getMemo()&&!"".equals(rootMenu.getMemo().trim())){
					s+="style='background-image:url(\""+rootMenu.getMemo()+"\");' ";
				}
				s+="></span>"+rootMenu.getCnname()+"</div><ul class='categoryitems'>";
				//List<Menu> list = new ArrayList<Menu>();
				int index=0;
				for (UserMenu subMenu : subMenus) {
					if (rootMenu.getId().equals(subMenu.getParentid()))	{
						if(0==index){
							//第一可点击的菜单
							s+="<input type=\"hidden\" id=\"first_menu\" value=\""+subMenu.getMainurl()+"\" />";
							s+="<li class=\"arrowlistmenuCurrent\"><a class=\"arrowlistmenuCurrent\" target='frmright' href='"+subMenu.getMainurl()+"'><span class='text_slice'>"+subMenu.getCnname()+"</span></a></li>";								
						}else{
							s+="<li ><a target='frmright' href='"+subMenu.getMainurl()+"'><span class='text_slice'>"+subMenu.getCnname()+"</span></a></li>";								
						}
						index++;
					}
				}
				s+="</ul></div>";
				out.println(s);
	}
	}catch(Exception e){
		
	}
	
	
	%>
	
</div>			
</body>
</html>