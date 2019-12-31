package com.hori.ums.webservice;


import javax.jws.WebService;

import com.hori.ums.webservice.bean.UserDto;


/**
 * 运维人员数据校验webservice，主要由sip服务器调用，用于校验用户账号和责任区域
 * @author sucs
 *
 */
@WebService
public interface IUserValidateService {

	/**
	 * 根据手机号判断运维人员是否存在
	 * @param mobile 手机号码
	 * @return 如果不存在返回null，存在返回运维人员数据
	 */
	public UserDto isExistUserByMobile(String mobile);
	
	
	
	/**
	 * 判断某个小区是否在运维人员的责任区域内
	 * @param mobile 运维人员手机号码
	 * @param organizationSeq 小区机构编号
	 * @param country 归属县
	 * @return
	 */
	public boolean isAuthorizeArea(String mobile,String organizationSeq,String country);

}
