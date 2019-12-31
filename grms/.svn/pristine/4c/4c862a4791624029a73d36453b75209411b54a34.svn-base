package com.hori.grms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.Area;
import com.hori.grms.model.District;
import com.hori.grms.page.PageParameter;
import com.hori.grms.queryBean.AreaQueryBean;
import com.hori.grms.queryBean.EnclosureResultsQueryBean;
import com.hori.grms.vo.AreaVo;
import com.hori.grms.vo.DistrictVo;
import com.hori.grms.vo.EnclosureResultsVo;
import com.hori.grms.vo.MapDistrictVo;

public interface AreaService {
	
	/**
	 * 机构编码查询小区数据
	 * @param areaSeq
	 * @return
	 */
	@Deprecated
     Area getByOrgSeq(String areaSeq);   
     /**
 	 * 获取小区的总数
 	 * @return
 	 */
 	public Long getAreaTotal();
 	
 	/**
	 * 获取当前的已经完工和尚未完工
	 * @param bean
	 * @return
	 */
	List<EnclosureResultsVo> getEnclosureResultsByDay(EnclosureResultsQueryBean bean);
	
	/**
	 * 区域小区获取小区的总数
	 * @param code
	 * @return
	 */
	List<MapDistrictVo> getCountryAreaTotalByCode(AreaQueryBean bean);
	
	
	//下面为v1.2.01新增
	
	public String getCitysByAccount(String userAccount) throws Exception;
	
	/**
	 * 在所有小区中获取所有省份以及其对应的市，有账号限制
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public List<DistrictVo> getProvincesAndCitys(String userAccount) throws Exception;
	
	/**
	 * 在所有小区中根据用户权限获取所有相关的省份
	 * @param userAccount
	 * @return
	 */
	public List<District> getProvinces(String userAccount) throws Exception;
	
	/**
	 * 在所有小区中根据省份获取所对应的市
	 * @param province
	 * @return
	 */
	public List<District> getCitysByProvince(String userAccount, String province) throws Exception;
	
	/**
	 * 在所有小区中根据市获取所对应的区域或县
	 * @param city
	 * @return
	 */
	public List<District> getCountrysByCity(String city);
	
	/**
	 * 在所有行政区域中根据省、市、县、乡代号获取名称
	 * @param code
	 * @return
	 */
	public String getNameByCode(String code);
	
	/**
	 * 在所有小区中根据省、市、区获取小区信息，有账号限制
	 * @param province
	 * @param citys
	 * @param country
	 * @param keyword
	 * @return
	 */
	public List<AreaVo> getCummunitys(String userAccount, String province, 
										String citys, String country, String keyword) throws Exception;
	
	/**
	 * 根据小区机构编码获取小区信息
	 * @param seq
	 * @return
	 */
	public Area getCummunityBySeq(@Param("seq")String seq);
	
	/**
	 * 查询过滤小区数据
	 * @param areaQueryBean
	 * @return
	 */
	public PageParameter<Area> getAreaInfoPages(AreaQueryBean areaQueryBean);
	
	/**
	 * 在所有小区中通过区编码获取街道信息
	 * @param city
	 * @return
	 */
	List<District> getTownsByCountry(String country);
	
	/**
	 * 获取小区机构编号
	 * @param areaQueryBean
	 * @return
	 */
	List<Area> getAreaInfos(AreaQueryBean areaQueryBean);
	
}
