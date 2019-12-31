package com.hori.grms.util.fan;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public abstract class ConversionProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return value;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(key, value);
	}
	
	public abstract Object process(String key, Object value);

}
