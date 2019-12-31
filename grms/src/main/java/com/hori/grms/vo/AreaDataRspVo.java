package com.hori.grms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 小区数据查询请求数据
 * @author laizs
 * @time 2018年8月15日下午7:37:09
 */
public class AreaDataRspVo implements Serializable{
	/**
	 * 请求处理结果
	  * 当结果为“0”时为正确
	 */
	private String result="0";
	/**
	 * 错误内容
	 */
	private String reason;
	/**
	 * 页码
	 */
	private int pageNum=1;
	/**
	 * 分页大小
	 */
	private int pageSize=10;
	/**
	 * 总页数
	 */
	private int pageCount=0;
	/**
	 * 总记录条数
	 */
	private int totalCount=0;
	/**
	 * 数据列表
	 */
	private List<AreaData> list=new ArrayList<AreaData>();
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<AreaData> getList() {
		return list;
	}

	public void setList(List<AreaData> list) {
		this.list = list;
	}
	/**
	 * 小区数据对象
	 * @author laizs
	 * @time 2018年8月15日下午7:47:04
	 */
	public static class AreaData{
		/**
		 * 省编码
		 */
		private String province="";
		/**
		 * 市编码
		 */
		private String city="";	
		/**
		 * 区编码
		 */
		private String country="";	
		/**
		 * 街道编码
		 */
		private String town;
		/**
		 * 省份名称
		 */
		private String provinceName;
		/**
		 * 城市名称
		 */
		private String cityName;
		/**
		 * 区名称
		 */
		private String countryName;
		/**
		 * 街道名称
		 */
		private String townName;
		/**
		 * 小区名称
		 */
		private String areaName;
		/**
		 * 小区机构编号
		 */
		private String organizationSeq;
		/**
		 * 小区类型，0：测试小区，1：云对讲项目  2：工程项目  3：样板演示小区  4：销售小区
		 */
		private String areaCategory;
		/**
		 * 转化状态: 0: 未转化 ， 1: 已完成转化  ,2 部分转化(试点转化)
		 */
		private String translateStatus;
		/**
		 * 实际户数
		 */
		private Long householdNum;
		/**
		 * 终端机数
		 */
		private Long advertisingTerminalNum;
		/**
		 * 小区地址
		 */
		private String areaAddress;
		/**
		 * 判断小区是否可用
		 */
		private Boolean result;
		

		
		public Boolean getResult() {
			return result;
		}
		public void setResult(Boolean result) {
			this.result = result;
		}
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
		public String getTown() {
			return town;
		}
		public void setTown(String town) {
			this.town = town;
		}
		public String getProvinceName() {
			return provinceName;
		}
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getTownName() {
			return townName;
		}
		public void setTownName(String townName) {
			this.townName = townName;
		}
		public String getAreaName() {
			return areaName;
		}
		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}
		public String getOrganizationSeq() {
			return organizationSeq;
		}
		public void setOrganizationSeq(String organizationSeq) {
			this.organizationSeq = organizationSeq;
		}
		public String getAreaCategory() {
			return areaCategory;
		}
		public void setAreaCategory(String areaCategory) {
			this.areaCategory = areaCategory;
		}
		public String getTranslateStatus() {
			return translateStatus;
		}
		public void setTranslateStatus(String translateStatus) {
			this.translateStatus = translateStatus;
		}
		public Long getHouseholdNum() {
			return householdNum;
		}
		public void setHouseholdNum(Long householdNum) {
			this.householdNum = householdNum;
		}
		public Long getAdvertisingTerminalNum() {
			return advertisingTerminalNum;
		}
		public void setAdvertisingTerminalNum(Long advertisingTerminalNum) {
			this.advertisingTerminalNum = advertisingTerminalNum;
		}
		public String getAreaAddress() {
			return areaAddress;
		}
		public void setAreaAddress(String areaAddress) {
			this.areaAddress = areaAddress;
		}
		
		
	}
}

 
