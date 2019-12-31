package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.Area;
import com.hori.grms.queryBean.AreaQueryBean;
import com.hori.grms.queryBean.EnclosureResultsQueryBean;
import com.hori.grms.vo.AreaVo;
import com.hori.grms.vo.EnclosureResultsVo;
import com.hori.grms.vo.MapDistrictVo;

public interface AreaMapper{
	/**
	 * 机构编码查询小区数据
	 * @param areaSeq
	 * @return
	 */
	public Area getByOrgSeq(@Param(value = "organizationSeq")String areaSeq);
	
	/**
	 * 获取省对应的小区数
	 * @return
	 */
	public List<MapDistrictVo> getProvinceAreaTotal();
	/**
	 * 获取市对应的小区数
	 * @param code
	 * @return
	 */
	public List<MapDistrictVo> getCityAreaTotalByCode(@Param(value = "code")String code);
	
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
	public List<EnclosureResultsVo> getEnclosureResultsByDay(EnclosureResultsQueryBean bean);
	/**
	 * 区域小区获取小区的总数
	 * @param code
	 * @return
	 */
	public List<MapDistrictVo> getCountryAreaTotalByCode(AreaQueryBean bean);
	
	
	//下面为v1.2.01新增
	
	/**
	 * 在所有小区中获取所有相关的省份
	 * @return
	 */
	public List<String> getProvinces();
	
	/**
	 * 在所有小区中根据省份获取所在的城市
	 * @param province
	 * @return
	 */
	public List<String> getCitysByProvince(String province);
	
	/**
	 * 在所有小区中根据市获取区域或县
	 * @param city
	 * @return
	 */
	public List<String> getCountrysByCity(String city);
	
	/**
	 * 在所有行政区域中根据省、市、县、乡代号获取名称
	 * @param code
	 * @return
	 */
	public String getNameByCode(String code);
	
	/**
	 * 在所有小区中根据省、市、区获取小区信息
	 * @param province
	 * @param citys
	 * @param country
	 * @return
	 */
	public List<AreaVo> getCummunitys(@Param("province")String province, @Param("citys")List<String> citys, 
									@Param("country")String country, @Param("keyword")String keyword);
	
	/**
	 * 根据小区机构编码获取小区信息
	 * @param seq
	 * @return
	 */
	public Area getCummunityBySeq(@Param("seq")String seq);

	/**
	 * 根据查询条件查询小区数据
	 * @param areaQueryBean
	 * @return
	 */
	public List<Area> getAreaInfoPages(AreaQueryBean areaQueryBean);


	/**
	 * 根据条件查询总数
	 * @param areaQueryBean
	 * @return
	 */
	public int getSearchAreaTotal(AreaQueryBean areaQueryBean);

	/**
	 * 在所有小区中通过区编码获取街道信息
	 * @param city
	 * @return
	 */
	public List<String> getTownsByCountry(String country);

	/**
	 * 获取小区机构编号
	 * @param areaQueryBean
	 * @return
	 */
	public List<Area> getAreaInfos(AreaQueryBean areaQueryBean);

	/**
	 * 获取已转化小区机构序列号
	 * @return
	 */
	public List<String> getTranslatedOrgs();

	/**
	 * 获取自定义小区机构
	 * @param param
	 * @return
	 */
	public List<String> getRegionSearchOrgs(AreaQueryBean param);
}
