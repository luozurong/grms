package com.hori.ums.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.hori.dao.AreaAuthorizeDao;
import com.hori.dao.UserDao;
import com.hori.model.AuthorizeArea;
import com.hori.model.User;
import com.hori.ums.webservice.IAuthorizeAreaService;
import com.hori.ums.webservice.bean.UserDto;

@WebService
public class IAuthorizeAreaServiceImpl implements IAuthorizeAreaService{

	@Resource
	private  AreaAuthorizeDao areaAuthorizeDao;
	@Resource
	private  UserDao userDao;
	
	@Override
	public List<String> getAuthorizeAreasByUserId(String userId){
		// TODO Auto-generated method stub
		return areaAuthorizeDao.getAuthorizeAreasByUserId(userId);
	}

	@Override
	public List<AuthorizeArea> getAuthorizeAreasByUserIdAndField(String userId,
			String field, String filedValue,String groupField) {
		return areaAuthorizeDao.getAuthorizeAreasByUserIdAndField(userId,field,filedValue,groupField);
	}

	@Override
	public AuthorizeArea getAuthorizeAreaById(String id) {
		return areaAuthorizeDao.get(id);
	}
	@Override
	public List<UserDto> getUserByDepartId(String areaId,String departId){
		List<UserDto>  userList=userDao.getUserByDepartId(areaId, departId);
		return userList;
		
	}
}
