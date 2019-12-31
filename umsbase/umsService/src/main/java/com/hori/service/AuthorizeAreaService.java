package com.hori.service;

import java.util.List;

public interface AuthorizeAreaService extends BaseServiceI{
	
	/**
	 *  获取某个责任区域内的所有运维人员账号
	 * @param provinceId 省
	 * @param cityId 市
	 * @param countryId 县/区域
	 * @param countrys 运维人员的责任区域
	 */
	public List<String> getUserIdsByAuthorizeArea(String provinceId,String cityId,String countryId,List<String> countrys);
	
}
