package com.hori.grms.queryBean;

import java.util.Arrays;
import java.util.List;

public class AreaQueryBean extends BaseQueryBean {
	
	private String areaSearchType;
	
	private String province;
	
	private String city;
	
	private List<String> citys;
	
	private String country;
	
	private String town;
	
	private String AreaName;
	
	private String areaType;
	
	private String[] translateStatus;
	
	private String countType;
	
	private String countData;
	
	private String countIndex;
	
	private String appType;
	
	private String organizationSeq;
	
	private String beginIndexId;
	
	private String endIndexId;
	
	private String beginDate;
	
	private String endDate;
	
	private String areaCategory;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String[] getTranslateStatus() {
		return translateStatus;
	}

	public void setTranslateStatus(String[] translateStatus) {
		this.translateStatus = translateStatus;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getCountData() {
		return countData;
	}

	public void setCountData(String countData) {
		this.countData = countData;
	}

	public String getCountIndex() {
		return countIndex;
	}

	public void setCountIndex(String countIndex) {
		this.countIndex = countIndex;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	public String getBeginIndexId() {
		return beginIndexId;
	}

	public void setBeginIndexId(String beginIndexId) {
		this.beginIndexId = beginIndexId;
	}

	public String getEndIndexId() {
		return endIndexId;
	}

	public void setEndIndexId(String endIndexId) {
		this.endIndexId = endIndexId;
	}



	@Override
	public String toString() {
		return "AreaQueryBean [areaSearchType=" + areaSearchType + ", province=" + province + ", city=" + city
				+ ", citys=" + citys + ", country=" + country + ", town=" + town + ", AreaName=" + AreaName
				+ ", areaType=" + areaType + ", translateStatus=" + Arrays.toString(translateStatus) + ", countType="
				+ countType + ", countData=" + countData + ", countIndex=" + countIndex + ", appType=" + appType
				+ ", organizationSeq=" + organizationSeq + ", beginIndexId=" + beginIndexId + ", endIndexId="
				+ endIndexId + ", beginDate=" + beginDate + ", endDate=" + endDate + ", areaCategory=" + areaCategory
				+ "]";
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAreaSearchType() {
		return areaSearchType;
	}

	public void setAreaSearchType(String areaSearchType) {
		this.areaSearchType = areaSearchType;
	}

	public List<String> getCitys() {
		return citys;
	}

	public void setCitys(List<String> citys) {
		this.citys = citys;
	}

	public String getAreaCategory() {
		return areaCategory;
	}

	public void setAreaCategory(String areaCategory) {
		this.areaCategory = areaCategory;
	}
	
	
}
