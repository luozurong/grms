package com.hori.adms.servlet.message;

import org.apache.log4j.Logger;

import com.hori.utils.Md5;


/**
 * servlet处理公共方法
 *
 */
public class ServletMessageCommon {
	static Logger log=Logger.getLogger(ServletMessageCommon.class);
	/**
	 * 密码
	 */
	private static String PASSWD ="EA3vWPmfxhWUit2s";
	/**
	 * 验证客户端传过来的token
	 * @param token
	 * @return
	 */
	public static boolean checkToken(String token,String timeStamp,String passwd){
		Md5 md5 = new Md5();
		String  reallyToken= passwd+timeStamp;
		reallyToken = md5.getMD5Str(reallyToken);
		if(token.equals(reallyToken)){
		  return  true;	
		}
		return false;
	}
	public static void main(String[] args) {
		checkToken("FDDDDC548100034DEF5BAE2C6A771D43","20170119110504","EA3vWPmfxhWUit2s"); //FDDDDC548100034DEF5BAE2C6A771D43
	}
}
