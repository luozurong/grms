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
import com.jlit.vdcs.webservice.IWorkOrderFromUmsService;

/**
 * 责任区域获取user
 * @author Huang.De.Qiang
 * @date 2017年6月23日 上午11:10:52
 */
@WebService
public class IWorkOrderFromUmsServiceImpl implements IWorkOrderFromUmsService {
	
	@Autowired
	private AreaAuthorizeDao areaAuthorizeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 根据区id获取user
	 */
	public DataGridPage getUserByAuthorizeAreaForPage(String userName,String areaId,String pageNo,String pageSize,String userType) {
		
		DataGridPage dataGridPage = areaAuthorizeDao.getUserByAuthorizeAreaForPage(userName,areaId,pageNo,pageSize,userType);
		List<Map<String,String>> rows = dataGridPage.getRows();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (Map<String, String> map : rows) {
			UserDto userDto = new UserDto();
			userDto.setUserId(map.get("user_id"));
			userDto.setName(map.get("user_name"));
			userDto.setMobile(map.get("user_mobile"));
			userDto.setUserAccount(map.get("user_account"));
			userDtos.add(userDto);
		}
		dataGridPage.setRows(userDtos);
		return dataGridPage;
	}
	/**
	 * 根据区id数组获取user
	 * @param areaId
	 * @param pageNo
	 * @param pageSize
	 * @param userType
	 * @return
	 */
	public DataGridPage getUserByAuthorizeAreasForPage(String userName,String[] areaId,String pageNo,String pageSize,String userType) {
		
		DataGridPage dataGridPage = areaAuthorizeDao.getUserByAuthorizeAreasForPage(userName,areaId,pageNo,pageSize,userType);
		List<Map<String,String>> rows = dataGridPage.getRows();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (Map<String, String> map : rows) {
			UserDto userDto = new UserDto();
			userDto.setUserId(map.get("user_id"));
			userDto.setName(map.get("user_name"));
			userDto.setMobile(map.get("user_mobile"));
			userDto.setUserAccount(map.get("user_account"));
			userDtos.add(userDto);
		}
		dataGridPage.setRows(userDtos);
		return dataGridPage;
	}
	
	/**
	 * 根据帐号获取用户信息
	 * @param userAccount
	 * @return
	 */
	public Map<String, String> findUserDetailByUserAccount(String userAccount) {
		List<Map<String,String>> list = userDao.findUserDetail("1", userAccount);
		if (list!=null&&list.size()>0) {
			Map<String, String> map = list.get(0);
			return map;
		}else {
			return null;
		}
	}
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	public Map<String, String> findUserDetailByUserId(String id) {
		Map<String, String> map = userDao.findUserDetailById("1", id);
		return map;
	}
	/**
	 * 根据id获取用户责任区域
	 * @param id
	 * @return
	 */
	public List<AuthorizeArea> findAuthorizeAreaByUserId(String id) {
		List<AuthorizeArea> areaAuthorize = areaAuthorizeDao.getAuthorizeAreaById(id, "1");
		return areaAuthorize;
	}
	
	public UserDto getUserById(String userId) {
		return userService.getUserDetailInfoById(userId);
	}
	
	/**
	 * 根据责任区域获取user
	 * @param userName
	 * @param areaId
	 * @param pageNo
	 * @param pageSize
	 * @param userType
	 * @return
	 */
	public List<UserDto> getUserByAuthorizeArea(String areaId,String userType){
		List<Map<String, String>> maps = areaAuthorizeDao.getUserByAuthorizeArea(areaId,userType);
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (Map<String, String> map : maps) {
			UserDto userDto = new UserDto();
			userDto.setUserId(map.get("user_id"));
			userDto.setName(map.get("user_name"));
			userDto.setMobile(map.get("user_mobile"));
			userDto.setUserAccount(map.get("user_account"));
			userDtos.add(userDto);
		}
		return userDtos;
	}
	
	/**
	 * 通过区域集合，获取对应的用户集合
	 * @param areaId
	 * @param userType
	 * @return
	 */
	public List<UserDto> getUsersByAuthorizeAreas(List<String> areaIds, String userType) {
		return this.areaAuthorizeDao.getUsersByAuthorizeAreas(areaIds, userType);
	}
	
	/**
	 * 通过SQL获取所有的用户
	 * @return
	 */
	public List<String> getAllUsersBySend(){
		return this.userDao.getAllUsersBySend();
	}
	
	/**
	 * 通过SQL获取所有的用户，用户类型
	 * @return
	 */
	public List<UserDto> getAllUsersTypeBySend(){
		return this.userDao.getAllUsersTypeBySend();
	}
}
