package com.jlit.uaas.util;

import java.util.HashMap;
import java.util.Map;

import com.hori.vo.AuthorLoginVo;
import com.hori.vo.LoginForSystemLoginVo;

import net.sf.json.JSONObject;

public class LoginUtil {

	@SuppressWarnings("rawtypes")
	public static LoginForSystemLoginVo Json2LoginForSystemVo(String jsonStr){
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("resourceMenu1", AuthorLoginVo.class);
		classMap.put("resourceMenu2", AuthorLoginVo.class);
		classMap.put("resourceButton", AuthorLoginVo.class);
		LoginForSystemLoginVo vo= (LoginForSystemLoginVo) JSONObject.toBean(jsonObject, LoginForSystemLoginVo.class,classMap);
		return vo;
	}
	
}
