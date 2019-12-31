package com.hori.grms.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hori.grms.cache.RedisCache;
import com.hori.grms.dao.AreaMapper;
import com.hori.grms.model.Area;
import com.hori.grms.model.District;
import com.hori.grms.page.PageParameter;
import com.hori.grms.queryBean.AreaQueryBean;
import com.hori.grms.queryBean.EnclosureResultsQueryBean;
import com.hori.grms.service.AreaService;
import com.hori.grms.vo.AreaVo;
import com.hori.grms.vo.DistrictVo;
import com.hori.grms.vo.EnclosureResultsVo;
import com.hori.grms.vo.MapDistrictVo;
@Service("areaService")
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaDao;
	
	@Autowired
	private RedisCache cache;
	
/*	@Autowired
	private IBigDataUserAreaService iBigDataUserAreaService;*/
	
	@Override
	public Area getByOrgSeq(String areaSeq) {
		// TODO Auto-generated method stub
		String cache_key=RedisCache.CAHCENAME+"|areaSeq|getByOrgSeq|"+areaSeq;
		Area area=cache.getCache(cache_key, Area.class);
		if(null==area){
			area=areaDao.getByOrgSeq(areaSeq);
			if(null!=area)
				cache.putCache(cache_key, area);
		}
		return area;
	}

	@Override
	public Long getAreaTotal() {
		return areaDao.getAreaTotal();
	}

	@Override
	public List<EnclosureResultsVo> getEnclosureResultsByDay(EnclosureResultsQueryBean bean) {
		return areaDao.getEnclosureResultsByDay(bean);
	}

	@Override
	public List<MapDistrictVo> getCountryAreaTotalByCode(AreaQueryBean bean) {
		String cache_key=RedisCache.CAHCENAME+"|AreaQueryBean|getCountryAreaTotalByCode|"+bean.toString();
		List<MapDistrictVo> districtVos =  cache.getListCache(cache_key,MapDistrictVo.class);
			districtVos=areaDao.getCountryAreaTotalByCode(bean);
			if(null!=districtVos&&districtVos.size()>0)
				cache.putListCache(cache_key, districtVos);
		return districtVos;
	}

	//以下为1.2.01新增
	
	@Override
	public String getCitysByAccount(String userAccount) throws Exception {
		//从chims根据用户账号获取省、市信息
		if(StringUtils.isBlank(userAccount)) {
			return null;
		}
/*		String json  iBigDataUserAreaService.getBigDataUserAreaData(userAccount);
 * 
*/	    String json=null;
		String citys = transformToCityString(json);
		return citys;
	}
	
	@Override
	public List<DistrictVo> getProvincesAndCitys(String userAccount) throws Exception {
//		userAccount = "bdadmin123";
		List<DistrictVo> districts = new ArrayList<>();
		if(StringUtils.isBlank(userAccount)) {
			return districts;
		}
		//先从chims获取，没有再从大数据area表获取
/*		String json = iBigDataUserAreaService.getBigDataUserAreaData(userAccount);
*/		String json=null;
		districts = transformToDistrict(json);

		if(districts != null && !districts.isEmpty()) {
			return districts;
		}
		if(districts == null) {
			districts = new ArrayList<>();
		}
		List<String> provinceCodes = areaDao.getProvinces();
		if(provinceCodes != null && !provinceCodes.isEmpty()) {
			DistrictVo dv = null;
			List<District> citys = null;
			District dprovince = null;
			for(String provinceCode : provinceCodes) {
				dv = new DistrictVo();
				dprovince = new District();
				dprovince.setCode(provinceCode);
				dprovince.setName(getNameByCode(provinceCode));
				List<String> dbCitys = areaDao.getCitysByProvince(provinceCode);
				if(dbCitys != null && !dbCitys.isEmpty()) {
					citys = new ArrayList<>();
					District district = null;
					for(String city : dbCitys) {
						district = new District();
						district.setCode(city);
						district.setName(getNameByCode(city));
						citys.add(district);
					}
				}
				dv.setProvince(dprovince);
				dv.setCitys(citys);
				districts.add(dv);
			}
		}
		return districts;
	}
	
	@Override
	public List<District> getProvinces(String userAccount) throws Exception {
//		userAccount = "bdadmin123";
		List<District> provinces = new ArrayList<>();
		if(StringUtils.isBlank(userAccount)) {
			return provinces;
		}
		//先从chims获取，没有再从大数据area表获取
/*		String json = iBigDataUserAreaService.getBigDataUserAreaData(userAccount);
*/		String json=null;
		provinces = transformToProvinces(json);
		if(provinces != null && !provinces.isEmpty()) {
			return provinces;
		}
		if(provinces == null) {
			provinces = new ArrayList<>();
		}
		List<String> provinceStrings = areaDao.getProvinces();
		if(provinceStrings != null && !provinceStrings.isEmpty()) {
			District district = null;
			for(String province : provinceStrings) {
				district = new District();
				district.setCode(province);
				district.setName(getNameByCode(province));
				provinces.add(district);
			}
		}
		return provinces;
	}

	@Override
	public List<District> getCitysByProvince(String userAccount, String province) throws Exception {
//		userAccount = "bdadmin123";
		List<District> citys = new ArrayList<>();
		if(StringUtils.isBlank(userAccount)) {
			return citys;
		}
		//先从chims获取，没有再从大数据area表获取
/*		String json = iBigDataUserAreaService.getBigDataUserAreaData(userAccount);
*/		String json=null;
		citys = transformToCitysByProvince(json, province);
		if(citys != null && !citys.isEmpty()) {
			return citys;
		}
		if(citys == null) {
			citys = new ArrayList<>();
		}
		List<String> cityStrings = areaDao.getCitysByProvince(province);
		if(cityStrings != null && !cityStrings.isEmpty()) {
			District district = null;
			for(String city : cityStrings) {
				district = new District();
				district.setCode(city);
				district.setName(getNameByCode(city));
				citys.add(district);
			}
		}
		return citys;
	}
	
	@Override
	public List<District> getCountrysByCity(String city) {
		List<District> districts = new ArrayList<>();
		List<String> countrys = areaDao.getCountrysByCity(city);
		if(countrys != null && !countrys.isEmpty()) {
			District district = null;
			for(String country : countrys) {
				district = new District();
				district.setCode(country);
				district.setName(getNameByCode(country));
				districts.add(district);
			}
		}
		return districts;
	}
	
	
	/**
	 * 在所有小区中通过区编码获取街道信息
	 * @param city
	 * @return
	 */
	@Override
	public List<District> getTownsByCountry(String country){
		List<District> districts = new ArrayList<>();
		List<String> towns = areaDao.getTownsByCountry(country);
		if(towns != null && !towns.isEmpty()) {
			District district = null;
			for(String town : towns) {
				district = new District();
				district.setCode(town);
				district.setName(getNameByCode(town));
				districts.add(district);
			}
		}
		return districts;
	}
	
	@Override
	public List<AreaVo> getCummunitys(String userAccount, String province, 
			String citys, String country, String keyword) throws Exception {
//		userAccount = "bdadmin123";
		if(StringUtils.isBlank(citys)) {
			citys = getCitysByAccount(userAccount);
		}
		List<String> cityList = null;
		if(StringUtils.isNotBlank(citys)) {
			cityList = Arrays.asList(citys.split(","));
		}
		if(StringUtils.isNotBlank(keyword)) {
			keyword = "%" + keyword + "%";
		}
		return areaDao.getCummunitys(province, cityList, country, keyword);
	}
	
	@Override
	public String getNameByCode(String code) {
		String key = "district|code|" + code;
		String name = cache.getCache(key, String.class);
		if(StringUtils.isNotBlank(name)) {
			return name;
		}
		name = areaDao.getNameByCode(code);
		if(StringUtils.isNotBlank(name)) {
			cache.putCache(key, name);
		}
		return name;
	}
	
	@Override
	public Area getCummunityBySeq(String seq) {
		String key = "cummunity|organizationSeq|" + seq;
		Area area = cache.getCache(key, Area.class);
		if(area == null) {
			area = areaDao.getCummunityBySeq(seq);
			if(area != null) {
				cache.putCache(key, area);
			}
		}else {
			if(StringUtils.isBlank(area.getAreaName())) {
				area = areaDao.getCummunityBySeq(seq);
				if(area != null) {
					cache.putCache(key, area);
				}
			}
		}
		return area;
	}
	
	/**
	 * json转换成DistrictVo，包含省市信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<DistrictVo> transformToDistrict(String json) throws Exception {
		List<DistrictVo> districts = null;
		if(StringUtils.isBlank(json)) {
			return districts;
		}
		districts = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, ?>> districtMapList = mapper.readValue(json, List.class);
		Map<String, String> provinceMap = new HashMap<>();
		if(districtMapList != null && !districtMapList.isEmpty()) {
			for(Map<String,?> map : districtMapList) {
				if(StringUtils.isNotBlank(String.valueOf(map.get("provinceCode")))) {
					provinceMap.put(String.valueOf(map.get("provinceCode")), String.valueOf(map.get("provinceName")));
				}
			}
		}
		DistrictVo dv = null;
		District dprovince = null;
		List<District> citys = null;
		for(Map.Entry<String, String> pmap : provinceMap.entrySet()) {
			dv = new DistrictVo();
			dprovince = new District();
			citys = new ArrayList<>();
			dprovince.setCode(pmap.getKey());
			dprovince.setName(pmap.getValue());
			dv.setProvince(dprovince);
			for(Map<String,?> map : districtMapList) {
				if(pmap.getKey().equals(String.valueOf(map.get("provinceCode")))) {
					District dcity = new District();
					dcity.setCode(String.valueOf(map.get("cityCode")));
					dcity.setName(String.valueOf(map.get("cityName")));
					citys.add(dcity);
				}
			}
			dv.setCitys(citys);
			districts.add(dv);
		}
		return districts;
	}
	
	/**
	 * json转换成省
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<District> transformToProvinces(String json) throws Exception {
		List<District> districts = null;
		if(StringUtils.isBlank(json)) {
			return districts;
		}
		districts = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, ?>> districtMapList = mapper.readValue(json, List.class);
		Map<String, String> provinceMap = new HashMap<>();
		if(districtMapList != null && !districtMapList.isEmpty()) {
			for(Map<String,?> map : districtMapList) {
				if(StringUtils.isNotBlank(String.valueOf(map.get("provinceCode")))) {
					provinceMap.put(String.valueOf(map.get("provinceCode")), String.valueOf(map.get("provinceName")));
				}
			}
		}
		District district = null;
		for(Map.Entry<String, String> pmap : provinceMap.entrySet()) {
			district = new District();
			district.setCode(pmap.getKey());
			district.setName(getNameByCode(pmap.getKey()));
			districts.add(district);
		}
		return districts;
	}
	
	/**
	 * json转成指定省份所对应的所有市
	 * @param json
	 * @param province
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<District> transformToCitysByProvince(String json, String province) throws Exception {
		List<District> districts = null;
		if(StringUtils.isBlank(json)) {
			return districts;
		}
		districts = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, ?>> districtMapList = mapper.readValue(json, List.class);
		if(StringUtils.isNotBlank(province)) {
			for(Map<String,?> map : districtMapList) {
				if(province.equals(String.valueOf(map.get("provinceCode")))) {
					District dcity = new District();
					dcity.setCode(String.valueOf(map.get("cityCode")));
					dcity.setName(String.valueOf(map.get("cityName")));
					districts.add(dcity);
				}
			}
		}
		return districts;
	}
	
	/**
	 * json转换成市，多个用逗号分隔
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String transformToCityString(String json) throws Exception {
		String cityString = null;
		StringBuffer citys = new StringBuffer();
		if(StringUtils.isBlank(json)) {
			return cityString;
		}
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, ?>> districtMapList = mapper.readValue(json, List.class);
		Map<String, String> provinceMap = new HashMap<>();
		if(districtMapList != null && !districtMapList.isEmpty()) {
			for(Map<String,?> map : districtMapList) {
				if(StringUtils.isNotBlank(String.valueOf(map.get("provinceCode")))) {
					provinceMap.put(String.valueOf(map.get("provinceCode")), String.valueOf(map.get("provinceName")));
				}
			}
		}
		for(Map.Entry<String, String> pmap : provinceMap.entrySet()) {
			for(Map<String,?> map : districtMapList) {
				if(pmap.getKey().equals(String.valueOf(map.get("provinceCode")))) {
					citys.append(map.get("cityCode"));
					citys.append(",");
				}
			}
		}
		if(StringUtils.isNotBlank(citys)) {
			cityString = citys.substring(0, citys.toString().length() - 1);
		}
		return cityString;
	}
	
	/**
	 * 查询过滤小区数据
	 * @param areaQueryBean
	 * @return
	 */
	public PageParameter<Area> getAreaInfoPages(AreaQueryBean areaQueryBean){
		PageParameter<Area> page = new PageParameter<Area>();
		int total = areaDao.getSearchAreaTotal(areaQueryBean);
		List<Area> areas = areaDao.getAreaInfoPages(areaQueryBean);
		page.setCurrentPage(areaQueryBean.getPageNumber());
		page.setPageSize(areaQueryBean.getPageSize());
		page.setResult(areas);
		page.setTotalCount(total);
		return page;
	}
	
	/**
	 * 获取小区机构编号
	 * @param areaQueryBean
	 * @return
	 */
	public List<Area> getAreaInfos(AreaQueryBean areaQueryBean){
		return areaDao.getAreaInfos(areaQueryBean);
	}
}
