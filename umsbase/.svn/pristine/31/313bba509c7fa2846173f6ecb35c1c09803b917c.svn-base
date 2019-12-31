package com.jlit.vdcs.webservice;

import java.util.List;

import javax.jws.WebService;

import com.hori.ums.webservice.bean.UserDto;



@WebService
public interface IUserByAuthorizeAreaService {
	
	/**
	 * 根据责任区域获取user
	 * @param areaId
	 * @param userType
	 * @return
	 */
	public List<String> getUserByAuthorizeArea(String provinceId,String cityId,String countryId);
	
	public List<UserDto> getUser(String provinceId,String cityId,String countryId);
	public UserDto getUserByAccount(String account);
}
