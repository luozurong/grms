package com.jlit.vdcs.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sf.json.JSONObject;

import com.hori.db.support.DataGridPage;
import com.hori.model.AuthorizeArea;
import com.hori.model.User;
import com.hori.ums.webservice.bean.UserDto;

/**
 * IGetUserByAuthorizeAreaService
 * @author Huang.De.Qiang
 * @date 2017年6月23日 上午11:11:20
 */
@WebService
public interface IWorkOrderFromUmsService {
	
	/**
	 * 根据责任区域分页获取user
	 * @param userName
	 * @param areaId
	 * @param pageNo
	 * @param pageSize
	 * @param userType
	 * @return
	 */
	public DataGridPage getUserByAuthorizeAreaForPage(String userName,String areaId,String pageNo,String pageSize,String userType);
	/**
	 * 根据责任区域id数组获取user
	 * @param userName
	 * @param areaId
	 * @param pageNo
	 * @param pageSize
	 * @param userType
	 * @return
	 */
	public DataGridPage getUserByAuthorizeAreasForPage(String userName,String[] areaId,String pageNo,String pageSize,String userType);
	/**
	 * 根据帐号获取用户信息
	 * @param userAccount
	 * @return
	 */
	public Map<String, String> findUserDetailByUserAccount(String userAccount);
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	public Map<String, String> findUserDetailByUserId(String id);
	/**
	 * 根据id获取用户责任区域
	 * @param id
	 * @return
	 */
	public List<AuthorizeArea> findAuthorizeAreaByUserId(String id);
	
	public UserDto getUserById(String userId);
	/**
	 * 根据责任区域获取user
	 * @param areaId
	 * @param userType
	 * @return
	 */
	public List<UserDto> getUserByAuthorizeArea(String areaId,String userType);
	
	/**
	 * 通过区域集合，获取对应的用户集合
	 * @param areaId
	 * @param userType
	 * @return
	 */
	public List<UserDto> getUsersByAuthorizeAreas(List<String> areaIds, String userType);
	/**
	 * 通过SQL获取所有的用户
	 * @return
	 */
	public List<String> getAllUsersBySend();
	
	/**
	 * 通过SQL获取所有的用户，用户类型
	 * @return
	 */
	public List<UserDto> getAllUsersTypeBySend();
}
