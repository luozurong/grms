package com.hori.grms.vo;

import java.util.ArrayList;
import java.util.List;

import com.hori.grms.model.ProjectProductMenuArea;

/**
 * 关联小区数据封装VO
 * @author hori
 *
 */
public class ProjectProductMenuAreaVo {
    //项目产品明细id
    private String projectProductMenuId;
    //省编码
    private String province;
    //省名称
    private String provinceName;
    //市编码
    private String city;
    //市名称
    private String cityName;
    //区县编码
    private String country;
    //区县名称
    private String countryName;
    /**
     * 小区名称集合
     */
    private List<String> ppmas = new ArrayList<>();

	public String getProjectProductMenuId() {
		return projectProductMenuId;
	}
	public void setProjectProductMenuId(String projectProductMenuId) {
		this.projectProductMenuId = projectProductMenuId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public List<String> getPpmas() {
		return ppmas;
	}
	public void setPpmas(List<String> ppmas) {
		this.ppmas = ppmas;
	}
    
    
}
