package com.hori.service;

import java.util.List;
import java.util.Map;

import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.AuthorizeArea;
import com.hori.vo.AuthorizeAreaVo;
import com.hori.vo.EasyUiTreeVo;

public interface AreaManagementService {
	public DataGridPage findUserByParam(AreaQueryBean queryBean,String userType,String userAccount,String departId);
	
	public List<EasyUiTreeVo> dealProvince();	
	public void addUserAutoroizeArea(String ids,String areaId,String systemId);
	
	public List<AuthorizeArea> initUserAreaById(String userId,String systemId);
	
/*	public void deleUserAreaById(String userId,String systemId);
*/	public List<EasyUiTreeVo> dealArea(String parentId,String layer);

	public List<AuthorizeAreaVo> getProCityAreaTownByUserAccount(String account);
	/**
	 * 处理非管理员责任范围
	 * 
	 * @return
	 */
	public  Map<String,Object>  dealAuthorArea(String userAccount,String systemId,String userId,String status) ;
	
	/**
	 * 加载省市的的区
	 * 
	 * @return
	 */
	public List<AuthorizeArea> initUserAreaByCity(String provinceId,String provinceName,String cityId,String cityName,String layer);
	/**
	 *加载数据
	 * 
	 * @return
	 */
	public Map<String,Object>  initUserAreaByIdLeft(String userId,String systemId);
	/**
	 * 更改用户类型
	 * 
	 * @return
	 */
	public void changeUserType(String userId,String userType);
	/**
	 * 加载右边框信息(管理员)
	 * 
	 * @return
	 */
	public  Map<String,Object> dealAuthorAreaAdmin(String userAccount,String systemId,String userId,String status);
}
