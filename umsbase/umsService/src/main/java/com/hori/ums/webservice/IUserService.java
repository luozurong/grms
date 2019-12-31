package com.hori.ums.webservice;

import java.util.List;

import javax.jws.WebService;

import com.hori.db.support.DataGridPage;
import com.hori.pageModel.DataGrid;
import com.hori.ums.webservice.bean.UserDto;
import com.hori.ums.webservice.bean.UserWsQueryBean;

/**
 * 运维人员用户数据webservice
 * @author sucs
 *
 */
@WebService
public interface IUserService {

	/**
	 * 获取门禁卡发卡时用到的用户DataGridPage
	 * 
	 * @param queryBean
	 * @return
	 */
	public DataGridPage queryUsers(UserWsQueryBean queryBean);
	
	
	/**
 	 * 根据用户id获取用户信息列表
 	 * @param userIds
 	 * @return
 	 */
	public List<UserDto> getUsersByIds(String... userIds);
	
	
	/**
 	 * 根据id获取用户包括角色，部门，责任区域等的详细信息
 	 * @param userId 用户id
 	 * @return
 	 */
	public UserDto getUserById(String userId);
	
	/**
 	 * 根据手机号获取用户包括角色，部门等的详细信息
 	 * @param mobile 手机号
 	 * @return
 	 */
	public UserDto getUserByMobile(String mobile);
	
	/**
	 * 获取用户id列表
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<String> getUserIds(UserWsQueryBean queryBean);
}
