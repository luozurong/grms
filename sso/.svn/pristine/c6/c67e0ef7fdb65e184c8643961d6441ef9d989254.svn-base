package com.jlit.uaas.enums;
/**
 * oauth错误码定义
 * @author laizs
 * @time 2014-5-5 下午1:27:41
 * @file OauthError.java
 */
public enum OauthErrorCode {
	
	INVALID_REQ_DEFAULT("invalid_request","请求参数有误","400000"),//请求参数默认的错误代码
	INVALID_CLIENT("invalid_client","client_id无效或审核不通过","400001"),//client_id无效错误
	EXPIRE_CODE("expired_code","authorizationCode已过期，authorizationCode的有效时间是10分钟","401001"),//authorizationCode过期
	UNAUTHORIZED_CLIENT("unauthorized_client","client_id未授权","401002"),//client_id未授权
	INVALID_CLIENT_SECRET("invalid_client_secret","client_secret参数验证失败","401003"),//client_id与client_serect验证失败
	REDIRECT_URI_MISMATCH("redirect_uri_mismatch","重定向地址不匹配","401004"),//重定向地址不匹配
	INVALID_TOKEN("invalid_token","无效的access_token","401005"),//无效的token
	EXPIRE_TOKEN("expired_token","过期的access_token","401006")//过期的token
	;
	
	private OauthErrorCode(String error,String error_description,  String error_code) {
		this.error = error;
		this.error_description=error_description;
		this.error_code = error_code;
	}

	/**
	 * 错误信息
	 */
    private String error;
    /**
     * 错误描述
     */
    private String error_description;
   /**
    * 错误码
    */
    private String error_code;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	
    
    

   
	
}
