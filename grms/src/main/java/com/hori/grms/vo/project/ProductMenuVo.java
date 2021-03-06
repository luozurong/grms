/**
 * 
 */
package com.hori.grms.vo.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hori.grms.model.Area;

/** 
 * @ClassName: ProjectMenu 
 * @Description: 产品清单类form表单类
 * @author zhuqiang
 * @date 2018年8月10日 下午8:26:02 
 */
public class ProductMenuVo implements Serializable{
	private static final long serialVersionUID = -2641861685721712165L;
		//产品清单id  product_mean 表的id
		private String id;
	    //类型 product_type
		private String productType;
		//产品清单  类型 product_menu
		private String productMenu;		
		//产品规格
		private String productSpec;
		//购买数量
		private Integer buyNum;
		//执行开始时间
		private Date beginTime;
		//执行结束时间
		private Date endTime;
		//选择小区id拼接字符串
		private String areaIds;
		//选择小区名称拼接字符串
		private String areaNames;
		private Integer otherNum1;  //记录煤管 其他otherNum1
		//产品明细id
		private String productMenuId;
		//限制次数，0不限制
		private Integer numLimit;
		//1按总数限制 2按月限制
		private Short numLimitType;
		
		/**
		 * @return the productMenuId
		 */
		public String getProductMenuId() {
			return productMenuId;
		}
		/**
		 * @param productMenuId the productMenuId to set
		 */
		public void setProductMenuId(String productMenuId) {
			this.productMenuId = productMenuId;
		}
		/**
		 * @return the otherNum1
		 */
		public Integer getOtherNum1() {
			return otherNum1;
		}
		/**
		 * @param otherNum1 the otherNum1 to set
		 */
		public void setOtherNum1(Integer otherNum1) {
			this.otherNum1 = otherNum1;
		}
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the productType
		 */
		public String getProductType() {
			return productType;
		}
		/**
		 * @param productType the productType to set
		 */
		public void setProductType(String productType) {
			this.productType = productType;
		}
		/**
		 * @return the productMenu
		 */
		public String getProductMenu() {
			return productMenu;
		}
		/**
		 * @param productMenu the productMenu to set
		 */
		public void setProductMenu(String productMenu) {
			this.productMenu = productMenu;
		}
		/**
		 * @return the productSpec
		 */
		public String getProductSpec() {
			return productSpec;
		}
		/**
		 * @param productSpec the productSpec to set
		 */
		public void setProductSpec(String productSpec) {
			this.productSpec = productSpec;
		}
		/**
		 * @return the buyNum
		 */
		public Integer getBuyNum() {
			return buyNum;
		}
		/**
		 * @param buyNum the buyNum to set
		 */
		public void setBuyNum(Integer buyNum) {
			this.buyNum = buyNum;
		}
		/**
		 * @return the beginTime
		 */
		public Date getBeginTime() {
			return beginTime;
		}
		/**
		 * @param beginTime the beginTime to set
		 */
		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}
		/**
		 * @return the endTime
		 */
		public Date getEndTime() {
			return endTime;
		}
		/**
		 * @param endTime the endTime to set
		 */
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		/**
		 * @return the areaIds
		 */
		public String getAreaIds() {
			return areaIds;
		}
		/**
		 * @param areaIds the areaIds to set
		 */
		public void setAreaIds(String areaIds) {
			this.areaIds = areaIds;
		}
		/**
		 * @return the areaNames
		 */
		public String getAreaNames() {
			return areaNames;
		}
		/**
		 * @param areaNames the areaNames to set
		 */
		public void setAreaNames(String areaNames) {
			this.areaNames = areaNames;
		}
		
		public Integer getNumLimit() {
			return numLimit;
		}
		public void setNumLimit(Integer numLimit) {
			this.numLimit = numLimit;
		}
		public Short getNumLimitType() {
			return numLimitType;
		}
		public void setNumLimitType(Short numLimitType) {
			this.numLimitType = numLimitType;
		}
		/**
		 * 
		 */
		public ProductMenuVo() {
			super();			
		}
		/**
		 * @param id
		 * @param productType
		 * @param productMenu
		 * @param productSpec
		 * @param buyNum
		 * @param beginTime
		 * @param endTime
		 * @param areaIds
		 * @param areaNames
		 * @param otherNum1
		 * @param productMenuId
		 */
		public ProductMenuVo(String id, String productType, String productMenu, String productSpec, Integer buyNum,
				Date beginTime, Date endTime, String areaIds, String areaNames, Integer otherNum1,
				String productMenuId,Integer numLimit,Short numLimitType) {
			super();
			this.id = id;
			this.productType = productType;
			this.productMenu = productMenu;
			this.productSpec = productSpec;
			this.buyNum = buyNum;
			this.beginTime = beginTime;
			this.endTime = endTime;
			this.areaIds = areaIds;
			this.areaNames = areaNames;
			this.otherNum1 = otherNum1;
			this.productMenuId = productMenuId;
			this.numLimit = numLimit;
			this.numLimitType = numLimitType;
		}		
}
