<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.oltu.oauth2.client.request.*"%>
<%@ page import="org.apache.oltu.oauth2.client.response.*"%>
<%@ page import="org.apache.oltu.oauth2.common.message.types.GrantType" %>
<%@ page import="org.apache.oltu.oauth2.client.*" %>
<%@ page import="org.apache.oltu.oauth2.common.*" %>

<%
	//认证授权回调地址
	String redirect_url="http://127.0.0.1:8088/uaas/callback.jsp";
	//oauth地址
	String authorize_url="http://localhost:8088/uaas/oauth2/authorize";
	//oauth accesstoken地址
	String access_token_url="http://localhost:8088/uaas/oauth2/access_token";
	//获取用户信息的地址
	String me_url="http://localhost:8088/uaas/res/users/me";
	//应用client_id
	String client_id="9527";
	//应用client_secrect
	String client_secret="123456";

	//接受oauth服务器返回的code
	OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
	String code = oar.getCode();
	System.out.println("code:"+code);
	//使用code交换access_token
	
	OAuthClientRequest oAuthClientRequest = OAuthClientRequest
                .tokenLocation(access_token_url)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(client_id)
                .setClientSecret(client_secret)
                .setRedirectURI(redirect_url)
                .setCode(code)
                .buildQueryMessage();
	
	System.out.println("locationUri:"+oAuthClientRequest.getLocationUri());
	 //create OAuth client that uses custom http client under the hood
    OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    //Facebook is not fully compatible with OAuth 2.0 draft 10, access token response is
    //application/x-www-form-urlencoded, not json encoded so we use dedicated response class for that
    //Custom response classes are an easy way to deal with oauth providers that introduce modifications to
    //OAuth 2.0 specification
    OAuthJSONAccessTokenResponse oAuthResponse=null;
    try{
    	  oAuthResponse =oAuthClient.accessToken(oAuthClientRequest);
    }catch(Exception e){
    	e.printStackTrace();
    	//如果获取access_token失败，则重新授权....
    	response.sendRedirect("/uaas/");
    	return;
    }
    String accessToken = oAuthResponse.getAccessToken();
    String refreshToken = oAuthResponse.getRefreshToken();
    long expiresIn= oAuthResponse.getExpiresIn();
    System.out.println("accessToken_result,"+"accessToken:"+accessToken+",refreshToken:"+refreshToken+",expiresIn:"+expiresIn);
   
    
    //根据accessToken获取用户的信息
    OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(me_url)
        .setAccessToken(accessToken).buildQueryMessage();
    System.out.println("获取用户信息uri:"+bearerClientRequest.getLocationUri());
    bearerClientRequest.getLocationUri();
	OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
	System.out.println("获取用户信息结果："+"responseCode->"+resourceResponse.getResponseCode()+",body->"+resourceResponse.getBody());
	
	
	
	
%>


