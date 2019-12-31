package com.jlit.uaas.dao.test;
import java.util.HashMap;
import java.util.Map;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.junit.Before;
import org.junit.Test;
public class AccessTokenTest {
	
	@Before
	public void init(){
		

	}
	@SuppressWarnings("unchecked")
	@Test
	public void accessTokenTest(){
		String url="http://127.0.0.1:8080/uaas/oauth2/access_token";
		Map parameters=new HashMap();
		parameters.put("grant_type", "authorization_code");
		parameters.put("code", "802021ac8d1f420da69c523c14c09fc8");
		parameters.put("client_id", "1011");
		parameters.put("client_secret", "123456");
		parameters.put("redirect_uri", "http://127.0.0.1:8080/uaas/oauthCallback");
		try {
			HttpUtil.post(url, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void userMeTest(){
		String url="http://127.0.0.1:8080/uaas/res/users/me";
		Map parameters=new HashMap();
		parameters.put("access_token", "c8d172f9e60fdab6030fc9167038d860");
		try {
			HttpUtil.post(url, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
