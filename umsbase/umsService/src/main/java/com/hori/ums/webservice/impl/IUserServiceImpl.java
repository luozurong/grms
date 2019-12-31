package com.hori.ums.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.dao.queryBean.UserQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.pageModel.DataGrid;
import com.hori.service.AuthorizeAreaService;
import com.hori.service.UserService;
import com.hori.ums.webservice.IUserService;
import com.hori.ums.webservice.bean.UserDto;
import com.hori.ums.webservice.bean.UserWsQueryBean;
import com.hori.utils.FuzzyQueryUtils;

@WebService
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorizeAreaService authorizeAreaService;
	
	/**
	 * 获取门禁卡发卡时用到的用户datagrid
	 * 
	 * @param queryBean
	 * @return
	 */
	public DataGridPage queryUsers(UserWsQueryBean queryBean){
		
		UserQueryBean userQueryBean=new UserQueryBean();
		userQueryBean.setPageNumber(queryBean.getPageNumber());
		userQueryBean.setPageSize(queryBean.getPageSize());
		userQueryBean.setRole(queryBean.getRole());
		userQueryBean.setDepartment(queryBean.getDepartment());
		userQueryBean.setUserAccount(queryBean.getUserAccount());
		userQueryBean.setUserName(queryBean.getUserName());
		userQueryBean.setMobile(queryBean.getMobile());
		
		String province = queryBean.getProvince();
		String city = queryBean.getCity();
		String country = queryBean.getCountry();
		
		
		if(queryBean.getCountrys()==null||queryBean.getCountrys().size()==0){
			DataGridPage dg=new DataGridPage();
			dg.setTotal(0);
			dg.setPage(0);
			dg.setRows(new ArrayList<Object>());
			return dg;
		}
		
		List<String> userIds = authorizeAreaService.getUserIdsByAuthorizeArea(province, city, country,queryBean.getCountrys());
		
		if(userIds==null||userIds.size()==0){
			DataGridPage dg=new DataGridPage();
			dg.setTotal(0);
			dg.setPage(0);
			dg.setRows(new ArrayList<Object>());
			return dg;
		}
		userQueryBean.setUserIds(userIds);
		
		return userService.queryUsers(userQueryBean);
	}

	@Override
	public List<UserDto> getUsersByIds(String... userIds) {
		return userService.getUsersByIds(userIds);
	}

	@Override
	public List<String> getUserIds(UserWsQueryBean queryBean) {
		
		UserQueryBean userQueryBean=new UserQueryBean();

		userQueryBean.setRole(queryBean.getRole());
		userQueryBean.setDepartment(queryBean.getDepartment());
		userQueryBean.setUserName(queryBean.getUserName());
		userQueryBean.setMobile(queryBean.getMobile());
		userQueryBean.setUserAccount(queryBean.getUserAccount());
		
		String province = queryBean.getProvince();
		String city = queryBean.getCity();
		String country = queryBean.getCountry();
		
		if(queryBean.getCountrys()==null||queryBean.getCountrys().size()==0){
			return new ArrayList<String>();
		}
		
		List<String> userIds = authorizeAreaService.getUserIdsByAuthorizeArea(province, city, country,queryBean.getCountrys());
		
		if(userIds==null||userIds.size()==0){
			return new ArrayList<String>();
		}
		
		userQueryBean.setUserIds(userIds);
		
		
		if(FuzzyQueryUtils.isCondition(queryBean.getRole())||FuzzyQueryUtils.isCondition(queryBean.getDepartment())||FuzzyQueryUtils.isCondition(queryBean.getUserName())
				||FuzzyQueryUtils.isCondition(queryBean.getMobile())||FuzzyQueryUtils.isCondition(queryBean.getUserAccount())){
			return userService.getUserIds(userQueryBean);
		}else{
			if(userQueryBean.getUserIds()!=null){
				return userQueryBean.getUserIds();
			}else{
				return new ArrayList<String>();
			}
		}
		
	}

	@Override
	public UserDto getUserById(String userId) {
		return userService.getUserDetailInfoById(userId);
	}
	
	@Override
	public UserDto getUserByMobile(String mobile) {
		return userService.getUserDetailInfoByMobile(mobile);
	}
	
}
