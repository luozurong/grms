package com.hori.grms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hori.grms.enums.BusinessType;
import com.hori.grms.httpclient.HttpResult;
import com.hori.grms.model.Test;
import com.hori.grms.service.AreaDataService;
import com.hori.grms.service.HttpClientApiService;
import com.hori.grms.service.ProjectActionService;
import com.hori.grms.service.TestService;
import com.hori.grms.vo.AreaDataReqVo;
import com.hori.grms.vo.AreaDataRspVo;

@Controller
@RequestMapping("test")
public class TestController {
	private final static Logger logger=Logger.getLogger(TestController.class);
	@Autowired
	private TestService testService;
	@Autowired
	private HttpClientApiService httpClientApiService;
	@Autowired
	private AreaDataService areaDataService;
	@Autowired
	private ProjectActionService projectActionService;
	
	@RequestMapping("/insert")
	public void insertCon(){
		Test vo=new Test();
		vo.setCreateTime(new Date());
		vo.setName("Test");
		testService.insert(vo);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/testHttpclient")
	@ResponseBody
	public String testHttpclient(){
		try {
			/*Map header=new HashMap();
			header.put("time_stamp", System.currentTimeMillis()+"");
			header.put("token", "");
			Map body=new HashMap();
			body.put("pageSize", 10);
			body.put("pageNum", 1);
			body.put("province", "");
			body.put("city", "");
			body.put("country", "");
			body.put("areaName", "");
			body.put("householdNumSign", "");
			body.put("householdNum", "");
			body.put("advertisingTerminalNumSign", "");
			body.put("advertisingTerminalNum", "");
			body.put("areaCategory", "");
			body.put("translateStatus", "");
			Map params=new HashMap();
			params.put("header", header);
			params.put("body", body);
			String req=JSON.toJSONString(params);*/
			//logger.info("请求："+req);
			//String url="http://118.190.139.218:8090/horiBigData/servlet/countAreaDataByGrms";
			//HttpResult rs=this.httpClientApiService.doPostJson(url, req);
			//logger.info("---:"+rs);
			AreaDataRspVo rsp=areaDataService.searchAreaDataList(new AreaDataReqVo());
			logger.info("列表响应："+JSON.toJSONString(rsp));
			Set<String> organizationSeqs=new HashSet<String>();
			organizationSeqs.add("4400100204");
			organizationSeqs.add("1200200168");
			Map<String,AreaDataRspVo.AreaData> ads=this.areaDataService.getAreaDataByOrganizationSeqs(organizationSeqs);
			logger.info("获取多个小区响应："+JSON.toJSONString(ads));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/testAction")
	@ResponseBody
	public String testAction(){
		try {
			projectActionService.createProjectActionsByProject("201808201643");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

}
