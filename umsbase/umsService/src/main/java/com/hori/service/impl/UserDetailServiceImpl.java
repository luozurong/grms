package com.hori.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.OrganizationDao;
import com.hori.dao.UserDao;
import com.hori.dao.UserDetailDao;
import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.dao.queryBean.UserDetailQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Menu;
import com.hori.model.Role;
import com.hori.model.UserDetail;
import com.hori.pageModel.Json;
import com.hori.service.UserDetailService;
@Service("userDetailService")
public class UserDetailServiceImpl extends BaseServiceImpl implements UserDetailService {
	@Autowired
	UserDetailDao userDetailDao;
	@Autowired
	OrganizationDao organizationDao;
	@Autowired
	UserDao userDao;
	
	public DataGridPage findUserDetailByParam(UserDetailQueryBean queryBean,String userType,String departId,String systemId,byte dataArea){
		DataGridPage dataGridPage=new DataGridPage();
		String departIds="";
		if(userType.equals("0")||dataArea==2){
		  
	  	List<Map<String, String>> listMap=organizationDao.getDepartmentById(systemId);
		for(int i=0;i<listMap.size();i++){
		    if(i==0){
		    	departIds="'"+listMap.get(i).get("departId")+"'";
		    }else{
		    	departIds=departIds+",'"+listMap.get(i).get("departId")+"'";
		    }
	  if(!departIds.equals("")){
	      dataGridPage=userDetailDao.findUserDetailPage(queryBean,departIds,userType);
          }    
		}
		}else{
			List<Map<String, String>> listMap=new ArrayList<Map<String, String>>();
			if(dataArea==0){
				listMap=organizationDao.getDepartmentNext2(departId);
			}else{
				listMap=organizationDao.getDepartmentNext(departId);
			}
			for(int i=0;i<listMap.size();i++){
			    if(i==0){
			    	departIds="'"+listMap.get(i).get("departId")+"'";
			    }else{
			    	departIds=departIds+",'"+listMap.get(i).get("departId")+"'";
			    }
		  if(!departIds.equals("")){
		      dataGridPage=userDetailDao.findUserDetailPage(queryBean,departIds,userType);
              }    
			}
		}
		return dataGridPage;
	}
	
	//保存或者修改用户资料
	public int saveUserDetail(UserDetail m){
		int status=0;
		if(StringUtils.isNotBlank(m.getUserDetailId())){
			if(!userDetailDao.isExistMobile(m.getMobile(),m.getUserDetailId())){
			m.setModifiedTime(new Date());
			userDao.updateMobileById(m.getUserDetailId(), m.getMobile());
			userDetailDao.update(m);
			}else{
				status=1;
			}
		}else{
	
			if(!userDetailDao.isExistMobile(m.getMobile(),"")){
				m.setCreateTime(new Date());
				m.setModifiedTime(new Date());
				userDetailDao.save(m);
				}else{
					status=1;
				}
		}
		return status;
	}
	//删除用户资料
	public Map<String,Object> deleteUserDetail(String[] ids){
		Map<String,Object>  map=new HashMap<String,Object>();
		map.put("status", 0);
		for(int i=0;i<ids.length;i++){
			//检查有无关联帐号，如果有则不能删除
			boolean b=userDao.isExistUserDetail( ids[i]) ;
			if(b){
				map.put("status", 1);
				map.put("ids",ids[i]);
				return map;
			}
		}
		userDetailDao.deleteByIds(ids);
		
		return map;
	}
	//个人设置查询用户资料
	public List<Map<String, Object>> findUserDetailOne(String userAccount,String systemId){
		List<Map<String, Object>> mapList=userDetailDao.findUserDetailOne(systemId,userAccount);
		
		return mapList;
		
	}

	/**
	 * 修改用户个人资料
	 * @return
	 */
	public void updateUserDetail(String userDetailId,String nickName,String email,String name){
		userDetailDao.updateUserDetail(userDetailId, nickName, email, name);
	}
	/**
	 * 修改用户头像
	 * @return
	 */
	public void updateUserDetailPhoto(String userAccount,String address){
		userDetailDao.updateUserDetailPhoto(userAccount, address);
	}
}
