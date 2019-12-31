package com.jlit.uaas.enums;

import org.apache.log4j.Logger;

/**
 * 系统发起oauth认证的使用的公有配置信息
 * 从数据库中获取oauth服务器的一下接口地址信息
 * 使用单例模式，双重检查锁定
 * @author laizs
 * @time 2014-11-13下午7:09:59
 * @file OauthSystemSetting.java
 *
 */
public class OauthSystemSetting {
	private final static Logger logger=Logger.getLogger(OauthSystemSetting.class);
	private static OauthSystemSetting instance;
	private static final String oauth_authorize_uri="oauth_authorize_uri";
	private static final String oauth_access_token_uri="oauth_access_token_uri";
	private static final String oauth_me_uri="oauth_me_uri";
	private static final String oauth_user_menus_uri="oauth_user_menus_uri";
	private static final String oauth_user_platforms_uri="oauth_user_platforms_uri";
	private static final String oauth_logout_uri="oauth_logout_uri";
	/**
	 * oauth2服务器授权认证请求地址
	 */
	private String authorizeUri;
	/**
	 * oauth2服务器换取access_token的地址
	 */
	private String accessTokenUri;
	/**
	 * oauth2查询用户基本信息的地址
	 */
	private String userMeUri;
	/**
	 * oauth2查询用户权限菜单信息的地址
	 */
	private String userMemusUri;
	/**
	 * oauth2查询用户有权限访问的系统的uri
	 */
	private String userPlatformsUri;
	/**
	 * 登录oauth2系统的uri,需要重定向或访问
	 */
	private String logoutUri;
	/**
	 * 私有构造函数
	 */
	private OauthSystemSetting(){
		logger.info("-----------"+OauthSystemSetting.class.getSimpleName()+"----------------初始化");
		System.out.println(InitSystemPara.systemParaMap);
		this.authorizeUri=InitSystemPara.systemParaMap.get(oauth_authorize_uri);
		this.accessTokenUri=InitSystemPara.systemParaMap.get(oauth_access_token_uri);
		this.userMeUri=InitSystemPara.systemParaMap.get(oauth_me_uri);
		this.userMemusUri=InitSystemPara.systemParaMap.get(oauth_user_menus_uri);
		this.userPlatformsUri=InitSystemPara.systemParaMap.get(oauth_user_platforms_uri);
		this.logoutUri=InitSystemPara.systemParaMap.get(oauth_logout_uri);
	}
	/**
	 * 单例对象的获取入口
	 * @return
	 */
	public static OauthSystemSetting getInstance(){
		if(instance==null){
			synchronized (OauthSystemSetting.class) {
				if(instance==null){
					instance=new OauthSystemSetting();
				}
			}
		}
		return instance;
	}
	//getter
	public String getAuthorizeUri() {
		return authorizeUri;
	}
	
	public String getAccessTokenUri() {
		return accessTokenUri;
	}
	
	public String getUserMeUri() {
		return userMeUri;
	}
	
	public String getUserMemusUri() {
		return userMemusUri;
	}
	
	public String getUserPlatformsUri() {
		return userPlatformsUri;
	}
	
	public String getLogoutUri() {
		return logoutUri;
	}
	public static void main(String[] args) {
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		System.out.println(OauthSystemSetting.getInstance().getAccessTokenUri());
		
	}
	
	
	
	
}
