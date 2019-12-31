package com.hori.service;

import java.util.List;
import java.util.Map;

import com.hori.dao.queryBean.UserDetailQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.UserDetail;

public interface UserDetailService {
	
	public DataGridPage findUserDetailByParam(UserDetailQueryBean queryBean,String userType,String departId,String systemId,byte dataArea);
	
	public int saveUserDetail(UserDetail m);
	
	public Map<String,Object>  deleteUserDetail(String[] ids);
	//个人设置查询用户资料
	public List<Map<String, Object>> findUserDetailOne(String userAccount,String systemId);
	//修改用户资料
	public void updateUserDetail(String userDetailId,String nickName,String email,String name);
	/**
	 * 修改用户头像
	 * @return
	 */
	public void updateUserDetailPhoto(String userAccount,String address);
}
