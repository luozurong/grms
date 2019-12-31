package com.hori.ums.webservice;

import java.util.List;

import javax.jws.WebService;

import com.hori.vo.AuthorizeAreaVo;
import com.hori.vo.UserInfoDto;

@WebService
public interface IUserInfoService {
	/**
	 * 根据登陆账号查找责任区域
	 * @param account
	 * @return
	 */
	public List<AuthorizeAreaVo> getAuthorizeAreaByAccount(String account);
	/**
	 * 根据账号查询用户信息
	 * @return
	 */
	public UserInfoDto getUserInfoByAccount(String account);
}
