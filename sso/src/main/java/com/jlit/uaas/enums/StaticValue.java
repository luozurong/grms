package com.jlit.uaas.enums;



public class StaticValue {

    public static int mpagSize = 10;

    public static int spagSize = 5;
    
    
    /**
	 * ALLOW_IP:允许访问的IP
	 * 
	 */
	public  static String ALLOW_IP ;

	/**
	 * CONSUMER_KEY:分配给对接平台的key
	 * 
	 */
	public  static String CONSUMER_KEY ;

	/**
	 * CONSUMER_SECRET:分配给对接平台的secret
	 * 
	 */
	public  static String CONSUMER_SECRET ;
	

	public static  String UUMS_UMPC_SERVLET_URL ;
	
	public static final String authorize_url ="/uums/authorize.html";
	
	static String istologserver = null;
	static {
		try {
			
			ALLOW_IP  = InitSystemPara.systemParaMap.get(InitSystemPara.ALLOW_IP);			
			CONSUMER_KEY  = InitSystemPara.systemParaMap.get(InitSystemPara.CONSUMER_KEY);			
			CONSUMER_SECRET  = InitSystemPara.systemParaMap.get(InitSystemPara.CONSUMER_SECRET);
			
			UUMS_UMPC_SERVLET_URL = InitSystemPara.systemParaMap.get(InitSystemPara.UUMS_UMPC_SERVLET_URL);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("获取全局变量失败;");
		}
	}
	

}
