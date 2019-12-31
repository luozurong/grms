package com.hori.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hori.dao.AreaAuthorizeDao;
import com.hori.service.AuthorizeAreaService;

@Service("authorizeAreaService")
public class AuthorizeAreaServiceImpl extends BaseServiceImpl   implements AuthorizeAreaService{
	
	@Resource
	private AreaAuthorizeDao  authorizeAreaDao;
	/**
	 *  获取某个责任区域内的所有运维人员账号
	 * @param provinceId 省
	 * @param cityId 市
	 * @param countryId 区域
	 * @return 
	 */
	public List<String> getUserIdsByAuthorizeArea(String provinceId,String cityId,String countryId,List<String> countrys){
		return authorizeAreaDao.getUserIdsByAuthorizeArea(provinceId, cityId, countryId,countrys);
	}
	
}
