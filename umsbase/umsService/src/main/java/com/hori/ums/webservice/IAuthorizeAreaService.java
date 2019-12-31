package com.hori.ums.webservice;

import java.util.List;

import javax.jws.WebService;

import com.hori.model.AuthorizeArea;
import com.hori.model.User;
import com.hori.ums.webservice.bean.UserDto;


/**
 * 运维人员责任区域webservice
 * @author sucs
 *
 */
@WebService
public interface IAuthorizeAreaService {

	/**
	 * 获取用户的责任区域列表
	 * @param userId 用户id
	 * @return 返回区(县)id集合
	 */
	public List<String> getAuthorizeAreasByUserId(String userId);
	
	/**
	 * 根据用户id和查询的字段值获取责任区域
	 * @param userId 用户id
	 * @param field 省市区父级字段
	 * @param filedValue 字段值
	 * @param groupFiled 分组字段
	 * @return
	 */
	public List<AuthorizeArea> getAuthorizeAreasByUserIdAndField(String userId,String field,String filedValue,String groupField);
	
	/**
	 * 根据表id获取责任区域
	 * @param id
	 * @return
	 */
	public AuthorizeArea getAuthorizeAreaById(String id);
	
	/**
	 * 根据责任区域,和机构获取下级管理人员账号
	 * @param id
	 * @return
	 */
	public List<UserDto> getUserByDepartId(String area,String departId);
}
