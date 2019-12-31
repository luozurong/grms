/**
 * 
 */
package com.hori.grms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hori.grms.pageModel.Json;
import com.hori.grms.service.ProductMenuService;
import com.hori.grms.vo.project.ProjectMenuVo;

/** 
 * @ClassName: ProductMenuContoller 
 * @Description: 清单列表
 * @author zhuqiang
 * @date 2018年8月20日 下午3:09:50 
 */
@Controller
@RequestMapping("/productMenu")
public class ProductMenuContoller  extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductMenuService productMenuService;
	
	@RequestMapping(value = "/findAddNumsByMeanIdAndBeginTime", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String findAddNumsByMeanIdAndBeginTime(
			@RequestParam(value = "meanId") String meanId,
			@RequestParam(value = "beginTime") Date beginTime
			){
		Json json = new Json();
		try {
			Integer num=productMenuService.findAddNumsByMeanIdAndBeginTime(meanId, beginTime);
			json.setObj(num);
			json.setSuccess(true);
			json.setMsg("成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("失败");
		} finally {
			return JSON.toJSONString(json);
		}
	}
}
