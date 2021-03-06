package com.hori.grms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 执行清单Vo
 * @author wangsc
 *
 */
public class ProjectActionVo {
	    // 项目code
		private String projectCode;
		// 执行开始时间
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date beginTime;
		// 小区名称
		private String areaName;
		// 省编码
		private String province;
		// 省名称
		private String provinceName;
		// 城市编码
		private String city;
		// 城市名称
		private String cityName;
		// 区县编码
		private String country;
		// 区县名称
		private String countryName;
		// 小区详细地址
		private String areaAddress;
		// 业务类型：1社区运营 2 媒管 3用户运营 4电商运营
		private String businessType;
		// 场次
		private String fieldName;
		// 项目执行清单主键id
		private String projectActionId;
		// 执行清单编码
		private String actionCode;
		// 项目名称
		private String projectName;
		// 执行状态（1：待确认 2：策划中 3：待执行 3：执行中  4：已完成）
		private Integer actionStatus;
		// 异常状态：1：正常 0：异常
		private Integer exceptionStatus;
		
		public String getProjectCode() {
			return projectCode;
		}
		public void setProjectCode(String projectCode) {
			this.projectCode = projectCode;
		}
		public Date getBeginTime() {
			return beginTime;
		}
		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}
		public String getAreaName() {
			return areaName;
		}
		public void setAreaName(String areaName) {
			this.areaName = areaName;
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
		public String getAreaAddress() {
			return areaAddress;
		}
		public void setAreaAddress(String areaAddress) {
			this.areaAddress = areaAddress;
		}
		public String getBusinessType() {
			return businessType;
		}
		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getProjectActionId() {
			return projectActionId;
		}
		public void setProjectActionId(String projectActionId) {
			this.projectActionId = projectActionId;
		}
		public String getActionCode() {
			return actionCode;
		}
		public void setActionCode(String actionCode) {
			this.actionCode = actionCode;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public Integer getActionStatus() {
			return actionStatus;
		}
		public void setActionStatus(Integer actionStatus) {
			this.actionStatus = actionStatus;
		}
		public Integer getExceptionStatus() {
			return exceptionStatus;
		}
		public void setExceptionStatus(Integer exceptionStatus) {
			this.exceptionStatus = exceptionStatus;
		}
		@Override
		public String toString() {
			return "ProjectActionVo [projectCode=" + projectCode + ", beginTime=" + beginTime + ", areaName=" + areaName
					+ ", province=" + province + ", provinceName=" + provinceName + ", city=" + city + ", cityName="
					+ cityName + ", country=" + country + ", countryName=" + countryName + ", areaAddress="
					+ areaAddress + ", businessType=" + businessType + ", fieldName=" + fieldName + ", projectActionId="
					+ projectActionId + ", actionCode=" + actionCode + ", projectName=" + projectName
					+ ", actionStatus=" + actionStatus + ", exceptionStatus=" + exceptionStatus + "]";
		}
}
