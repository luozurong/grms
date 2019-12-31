package com.hori.ums.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.hori.service.AreaManagementService;
import com.hori.service.UserService;
import com.hori.ums.webservice.IUserInfoService;
import com.hori.vo.AuthorizeAreaVo;
import com.hori.vo.UserInfoDto;

@WebService(endpointInterface="com.hori.ums.webservice.IUserInfoService")
public class IUserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private AreaManagementService areaManagementService;
	@Autowired
	private UserService userService;
	
	@Override
	public List<AuthorizeAreaVo> getAuthorizeAreaByAccount(String account) {
		List<AuthorizeAreaVo> list=	areaManagementService.getProCityAreaTownByUserAccount(account);
		return list;
	}

	@Override
	public UserInfoDto getUserInfoByAccount(String account) {
		// TODO Auto-generated method stub
		return userService.getUserInfoByAccount(account);
	}

}
