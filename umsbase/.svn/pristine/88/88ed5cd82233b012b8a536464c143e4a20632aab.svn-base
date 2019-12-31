package com.hori.ums.webservice.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.dao.AreaAuthorizeDao;
import com.hori.service.UserService;
import com.hori.ums.webservice.IUserValidateService;
import com.hori.ums.webservice.bean.UserDto;

public class IUserValidateServiceImpl implements IUserValidateService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaAuthorizeDao areaAuthorizeDao;
	
	@Override
	public UserDto isExistUserByMobile(String mobile) {
		return userService.getUserDetailInfoByMobile(mobile);
	}

	@Override
	public boolean isAuthorizeArea(String mobile, String organizationSeq, String country) {
		
		if(StringUtils.isEmpty(country)){
			return false;
		}
		
		UserDto userDetail = userService.getUserDetailInfoByMobile(mobile);
		
		if(userDetail==null){
			return false;
		}
		
		List<String> authorizeAreas = areaAuthorizeDao.getAuthorizeAreasByUserId(userDetail.getUserId());
		
		if(authorizeAreas==null||authorizeAreas.size()==0){
			return false;
		}
		
		for (String authorizeArea : authorizeAreas) {
			if(country.equals(authorizeArea)){
				return true;
			}
		}
		
		return false;
	}

}
