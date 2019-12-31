//package com.hori.enums;
//
//
//
//public class StaticValue {
//	
//	
//
//    public static int mpagSize = 10;
//
//    public static int spagSize = 5;
//    
//    public static int zdySize = 8;
// 
//	public static final String resp_fail = "fail";
//	
//	//未选择状态
//	public static String UN_CHECKED = "";
//	
//	public static String USER_STATUS = "1";
//	
//	
//	//管理员的权限等级号
//	public static final String GLY_LEVEL_NO = "000";
//    /**
//	 * ALLOW_IP:允许访问的IP
//	 * 
//	 */
//	public  static String ALLOW_IP ;
//	
//	/**
//	 * XTGL:系统管理的模块
//	 * 
//	 */
//	public  static String XTGL ;
//	
//	/**
//	 * JKDA:健康档案的模块
//	 * 
//	 */
//	public  static String JKDA ;
//	
//	/**
//	 * JTBC:家庭病床的模块
//	 * 
//	 */
//	public  static String JTBC ;
//	
//	/**
//	 * BRMK:病人模块
//	 * 
//	 */
//	public  static String BRMK ;
//	
//	/**
//	 * HZJBXXTB:同步患者基本信息
//	 */
//	public static String HZJBXXTB;
//	
//	/**
//	 * HZZDXXTB:同步患者诊断信息
//	 */
//	public static String HZZDXXTB;
//	
//	/**
//	 * HZJBXXTB:医护人员数据同步URL
//	 */
//	public static String YHRYSJTB;
//
//	public static final String RESULT_SUCCESS = "0";
//	
//	public static final String RESULT_FAIIL = "1";
//	
//	
//	public static final String YX_USER_STATUS="1";
//	
//
//	public static final String WX_USER_STATUS="0";
//	
//	//0表示系统数据
//	public static final String XT_SJLY = "0";  
//	
//	/**
//	 * 实时心电数据前缀
//	 */
//	public static final String REALTIME_CHART_PREFIX = "fd:";
//	/**
//	 * 心电参数
//	 */
//	public static final String ECG_PARA = "0";
//	/**
//	 * 心电图
//	 */
//	public static final String ECG_CHART = "1";
//	/**
//	 * RESP
//	 */
//	public static final String RESP_CHART = "2";
//	/**
//	 * 血氧
//	 */
//	public static final String SPO2 = "3";
//	/**
//	 * 无创血压
//	 */
//	public static final String NIBP = "4";
//
//	
//	public static final String ECG_I = "I";
//	
//	public static final String ECG_II = "II";
//	
//	public static final String ECG_III = "III";
//	
//	public static final String ECG_AVR = "avr";
//	public static final String ECG_AVL = "avl";
//	public static final String ECG_AVF = "avf";
//	public static final String ECG_V1 = "v1";
//	public static final String ECG_V2 = "v2";
//	public static final String ECG_V3 = "v3";
//	public static final String ECG_V4 = "v4";
//	public static final String ECG_V5 = "v5";
//	public static final String ECG_V6 = "v6";
//	
//	
//	/**
//	 * 网络类设备
//	 */
//	public static final String NET_SERIAL = "1";
//	/**
//	 * 短信类设备
//	 */
//	public static final String SMS_SERIAL = "2";
//	
//	
//	static String istologserver = null;
//	static {
//		try {
//			
//			ALLOW_IP  = InitSystemPara.systemParaMap.get(InitSystemPara.ALLOW_IP);			
//			XTGL  = InitSystemPara.systemParaMap.get(InitSystemPara.XTGL);	
//			JKDA  = InitSystemPara.systemParaMap.get(InitSystemPara.JKDA);	
//			JTBC  = InitSystemPara.systemParaMap.get(InitSystemPara.JTBC);	
//			BRMK  = InitSystemPara.systemParaMap.get(InitSystemPara.BRMK);	
//			HZJBXXTB = InitSystemPara.systemParaMap.get(InitSystemPara.HZJBXXTB);
//			HZZDXXTB = InitSystemPara.systemParaMap.get(InitSystemPara.HZZDXXTB);
//			YHRYSJTB =  InitSystemPara.systemParaMap.get(InitSystemPara.YHRYSJTB);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.print("获取全局变量失败;");
//		}
//	
//	}
//	
//	
//
//}
