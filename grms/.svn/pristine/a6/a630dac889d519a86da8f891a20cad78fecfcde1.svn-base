package com.hori.grms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hori.grms.enums.ResultCode;
import com.hori.grms.model.District;
import com.hori.grms.service.AreaService;
import com.hori.grms.vo.AreaVo;
import com.hori.grms.vo.DistrictVo;

/**
 * 区域、小区相关
 * @author wangzhen
 *
 */
@Controller
@RequestMapping("/common")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 获取账号所对应权限的所有省份与城市
	 * @return
	 */
	@RequestMapping(value = "/districts", method = {RequestMethod.GET})
	@ResponseBody
	public Object getProvincesAndCitys() {
		Map<String,Object> result = new HashMap<>();
		try {
			String userAccount = (String) request.getSession().getAttribute("userAccount");
			List<DistrictVo> districts = areaService.getProvincesAndCitys(userAccount);
			result.put("data", districts);
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在所有小区中获取所有省份
	 * @return
	 */
	@RequestMapping(value = "/districts/provinces", method = {RequestMethod.GET})
	@ResponseBody
	public Object getProvinces() {
		Map<String,Object> result = new HashMap<>();
		try {
			String userAccount = (String) request.getSession().getAttribute("userAccount");
			List<District> provinces = areaService.getProvinces(userAccount);
			result.put("data", provinces);
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在所有小区中通过省份编码获取省份对应的市
	 * @param province
	 * @return
	 */
	@RequestMapping(value = "/districts/{province}/citys", method = {RequestMethod.GET})
	@ResponseBody
	public Object getCitysByProvince(@PathVariable String province) {
		Map<String,Object> result = new HashMap<>();
		try {
			String userAccount = (String) request.getSession().getAttribute("userAccount");
			List<District> citys = areaService.getCitysByProvince(userAccount, province);
			result.put("data", citys);
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在所有小区中通过市编码获取市对应的区或者县城
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/districts/{city}/countrys", method = {RequestMethod.GET})
	@ResponseBody
	public Object getCountrysByCity(@PathVariable String city) {
		Map<String,Object> result = new HashMap<>();
		try {
			List<District> countrys = areaService.getCountrysByCity(city);
			result.put("data", countrys);
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在所有小区中通过区编码获取街道信息
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/districts/{country}/towns", method = {RequestMethod.GET})
	@ResponseBody
	public Object getTownsByCountry(@PathVariable String country) {
		Map<String,Object> result = new HashMap<>();
		try {
			List<District> countrys = areaService.getTownsByCountry(country);
			result.put("data", countrys);
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在所有小区中通过省份编码、市编码(可以多个，以,分隔)、区编码、关键字获取小区
	 * @param province
	 * @param citys
	 * @param country
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "/communitys", method = {RequestMethod.GET})
	@ResponseBody
	public Object getCommunitys(String province, String citys, String country, String keyword) {
		Map<String,Object> result = new HashMap<>();
		try {
			String userAccount = (String) request.getSession().getAttribute("userAccount");
			List<AreaVo> cummunitys = areaService.getCummunitys(userAccount, province, citys, country, keyword);
			result.put("data", cummunitys);
			result.put("total", cummunitys.size());
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
}
