package com.hori.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityVo {
	private String id;

	private String province;
	private String provinceName;
	private String city;
	private String cityName;	
	private String country;
	private String countryName;
	private String town;
	private String townName;
	private String tatol;
	private String organizationSeq;
	List<Map<String,String>> townList=new ArrayList<Map<String,String>>();
	
	
	public List<Map<String, String>> getTownList() {
		return townList;
	}
	public void setTownList(List<Map<String, String>> townList) {
		this.townList = townList;
	}
	public String getOrganizationSeq() {
		return organizationSeq;
	}
	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getTatol() {
		return tatol;
	}
	public void setTatol(String tatol) {
		this.tatol = tatol;
	}
	
	@Override
	public String toString() {
		return "CommunityVo [id=" + id + ", province=" + province + ", provinceName=" + provinceName + ", city=" + city
				+ ", cityName=" + cityName + ", country=" + country + ", countryName=" + countryName + ", town=" + town
				+ ", townName=" + townName + ", tatol=" + tatol + ", organizationSeq=" + organizationSeq + ", townList="
				+ townList + "]";
	}
	
	
	

}
