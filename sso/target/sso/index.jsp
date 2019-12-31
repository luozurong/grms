<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.oltu.oauth2.client.request.*"%>
<%
	//认证授权回调地址
	String redirect_url="http://127.0.0.1:8088/uaas/callback.jsp";
	//oauth地址
	String authorize_url="http://localhost:8088/uaas/oauth2/authorize";
	//应用client_id
	String client_id="9527";
	OAuthClientRequest oAuthClientRequest = OAuthClientRequest
			.authorizationLocation(authorize_url)
			.setClientId(client_id)
			.setRedirectURI(redirect_url)
			.setResponseType("code")
			.setState("1")
			.setScope("basic")
			.setParameter("display", "popup")
			.setParameter("forcelogin", "1")
			.buildQueryMessage();
	System.out.println("url:"+oAuthClientRequest.getLocationUri());
	response.sendRedirect(oAuthClientRequest.getLocationUri());
%>

