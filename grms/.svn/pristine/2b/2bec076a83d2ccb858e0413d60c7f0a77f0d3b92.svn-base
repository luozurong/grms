package com.hori.grms.util.fan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class JsonConfigSetter {
	/**
	 * Json过滤属性配置
	 * @param filterProperties 需要过滤的属性集合
	 * @return
	 */
	public static JsonConfig setJsonFilter(JsonConfig jsonConfig, final Set<String> filterProperties){
		if(filterProperties != null && filterProperties.size() != 0){
			PropertyFilter filter = new PropertyFilter(){
				public boolean apply(Object source, String name, Object value) {
					// 返回 true, 表示这个属性将被过滤掉
					return filterProperties.contains(name); 
				}
			};
			jsonConfig.setJsonPropertyFilter(filter);
		}
		return jsonConfig;
	}
	
	/**
	 * Json过滤属性配置
	 * @param containProperties 需要过滤的属性集合
	 * @return
	 */
	public static JsonConfig setJsonkeepProperties(JsonConfig jsonConfig, final Set<String> keepProperties){
		if(keepProperties != null && keepProperties.size() != 0){
			PropertyFilter filter = new PropertyFilter(){
				public boolean apply(Object source, String name, Object value) {
					// 返回 true, 表示这个属性将被过滤掉
					return !keepProperties.contains(name); 
				}
			};
			jsonConfig.setJsonPropertyFilter(filter);
		}
		return jsonConfig;
	}
	
	/**
	 * 
	 * Json日期格式配置配置,当然日期为Date类型时使用该方法配置
	 * @param jsonConfig
	 * @param dateProperties
	 * @param datePattern
	 * @return JsonConfig 
	 * @since  jhCommon1.0
	 */
	public static JsonConfig setJsonDateFormatByDateType(JsonConfig jsonConfig, final String[] dateProperties,
			final String datePattern) {
		return setConversionProcessor(jsonConfig, Date.class, new ConversionProcessor() {
			@Override
			public Object process(String key, Object value) {
				Set<String> dateSet = new HashSet<String>(); 
				for(int i=0; i<dateProperties.length; i++){
					dateSet.add(dateProperties[i]);
				}
				String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
				DateFormat dateFormat = null;
				if (dateSet.contains(key)) {
					try {
						dateFormat = new SimpleDateFormat(datePattern);
					} catch (Exception ex) {
						dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
					}
					try {
						return dateFormat.format(value);	
					} catch (Exception e) {
					}
					return null;
				}else{
					return value;
				}
			}
		});
	}
	
	/**
	 * 
	 * Json日期格式配置配置,当然日期为Date类型时使用该方法配置(转换字段与转换类型对应)
	 * @param jsonConfig
	 * @param dateProperties
	 * @param datePattern
	 * @return JsonConfig 
	 * @since  jhCommon1.0
	 */
	public static JsonConfig setJsonDateFormatByDateType(JsonConfig jsonConfig,final String[] dateProperties,
			final String[] datePattern) {
		return setConversionProcessor(jsonConfig, Date.class, new ConversionProcessor() {
			@Override
			public Object process(String key, Object value) {
				Set<String> dateSet = new HashSet<String>();
				Map<String,String> dateMap = new HashMap<String,String>();
				
				for(int i=0; i<dateProperties.length; i++){
					dateSet.add(dateProperties[i]);
					dateMap.put(dateProperties[i], datePattern[i]);//属性和转换 格式对应
				}
				String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
				DateFormat dateFormat = null;
				if (dateSet.contains(key)) {
					try {
						dateFormat = new SimpleDateFormat(dateMap.get(key));
					} catch (Exception ex) {
						dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
					}
					try {
						return dateFormat.format(value);	
					} catch (Exception e) {
					}
					return null;
				}else{
					return value;
				}
			}
		});
	}
	
	/**
	 * Json日期格式配置配置
	 * @param jsonConfig
	 * @param dateFormat 日期格式
	 * version 1.0
	 * @return
	 */
	public static JsonConfig setJsonDateFormat(JsonConfig jsonConfig, String dateFormat){
		jsonConfig.registerJsonValueProcessor(Date.class, (JsonValueProcessor) new DateJsonValueProcessor(dateFormat));
		return jsonConfig;
	}
	
	/**
	 * 设置Json数据格式化转换器
	 * @param jsonConfig
	 * @param type	转换类的类型
	 * @param conversionProcessor 转换器
	 * @return
	 */
	public static JsonConfig setConversionProcessor(JsonConfig jsonConfig, Class type, ConversionProcessor conversionProcessor){
		jsonConfig.registerJsonValueProcessor(type, conversionProcessor);
		return jsonConfig;
	}
}
