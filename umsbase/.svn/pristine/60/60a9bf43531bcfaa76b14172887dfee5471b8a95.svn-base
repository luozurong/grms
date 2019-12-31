package com.jlit.vdcs.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.hori.dao.AreaAuthorizeDao;
import com.hori.dao.UserDao;
import com.hori.db.support.DataGridPage;
import com.hori.model.AuthorizeArea;
import com.hori.model.User;
import com.hori.service.UserService;
import com.hori.ums.webservice.bean.UserDto;
import com.jlit.vdcs.webservice.IUserByAuthorizeAreaService;
import com.jlit.vdcs.webservice.IWorkOrderFromUmsService;

/**
 * 根据省市区区域获取user
 */
@WebService
public class IUserByAuthorizeAreaServiceImpl implements IUserByAuthorizeAreaService {
	
	@Autowired
	private AreaAuthorizeDao areaAuthorizeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	
	@Override
	public List<String> getUserByAuthorizeArea(String provinceId,
			String cityId, String countryId) {
		
		List<String> accounts = new ArrayList();
		
		List<String> userIds = areaAuthorizeDao.getUserIdsByAuthorizeArea(provinceId, cityId, countryId);
		int size = userIds.size();  
		String[] ids = (String[])userIds.toArray(new String[size]);
		
		//System.out.println("size:"+size);
		if(size!=0){
			List<UserDto> userDto= userService.getUsersByIds(ids);
			for (UserDto user : userDto) {
				accounts.add(user.getUserAccount());
			}
			
			return accounts;
		}else{
			return null;
		}
		
	}
	
	public List<UserDto> getUser(String provinceId,
			String cityId, String countryId) {
		
		List<String> userIds = areaAuthorizeDao.getUserIdsByAuthorizeArea(provinceId, cityId, countryId);
		int size = userIds.size();  
		String[] ids = (String[])userIds.toArray(new String[size]);
		
		//System.out.println("size:"+size);
		if(size!=0){
			List<UserDto> userDto= userService.getUsersByIds(ids);
			
			return userDto;
		}else{
			return null;
		}
		
	}
	
	
	public UserDto getUserByAccount(String account){
		UserDto userDto = new UserDto();
		User user = userService.getUserByAccount(account);
		if(user!=null){
			userDto.setUserAccount(user.getUserAccount());
			userDto.setMobile(user.getMobile());
		}
		return userDto;
	}
}
