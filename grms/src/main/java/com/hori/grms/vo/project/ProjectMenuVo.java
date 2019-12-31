/**
 * 
 */
package com.hori.grms.vo.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * @ClassName: Project1 
 * @Description: 项目清单封装
 * @author zhuqiang
 * @date 2018年8月10日 下午8:10:34 
 */
public class ProjectMenuVo implements Serializable{
	private static final long serialVersionUID = -770159470355962699L;
	//id
	private String id;	
    //场次名称
	private String fieldName;
	//业务类型
	private String businessType;
	//业务名称
	private String businessName;
	//小区名称
	private String areaName;
	//小区机构编码
	private String organizationSeq;
	//产品清单信息
	private List<ProductMenuVo> projectMenus;
	//是否服从场地分配(1服从分配 0不服从分配)
	private Integer isDistributionField;
	//是否服从时间调配(1服从分配 0不服从分配)
	private Integer isDistributionTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Date beginTime;  //执行开始时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Date endTime; //执行结束时间
	
	
	
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

	public Integer getIsDistributionField() {
		return isDistributionField;
	}

	public void setIsDistributionField(Integer isDistributionField) {
		this.isDistributionField = isDistributionField;
	}

	/**
	 * @return the isDistributionTime
	 */
	public Integer getIsDistributionTime() {
		return isDistributionTime;
	}

	/**
	 * @param isDistributionTime the isDistributionTime to set
	 */
	public void setIsDistributionTime(Integer isDistributionTime) {
		this.isDistributionTime = isDistributionTime;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
	 * @return the projectMenus
	 */
	public List<ProductMenuVo> getProjectMenus() {
		return projectMenus;
	}
	/**
	 * @param projectMenus the projectMenus to set
	 */
	public void setProjectMenus(List<ProductMenuVo> projectMenus) {
		this.projectMenus = projectMenus;
	}
	/**
	 * @param id
	 * @param fieldName
	 * @param businessType
	 * @param businessName
	 * @param projectMenus
	 */
	public ProjectMenuVo(String id, String fieldName, String businessType, String businessName,
			List<ProductMenuVo> projectMenus) {
		super();
		this.id = id;
		this.fieldName = fieldName;
		this.businessType = businessType;
		this.businessName = businessName;
		this.projectMenus = projectMenus;
	}
	/**
	 * 
	 */
	public ProjectMenuVo() {
		super();		
	}

	/**
	 * @param id
	 * @param fieldName
	 * @param businessType
	 * @param businessName
	 * @param areaName
	 * @param organizationSeq
	 * @param projectMenus
	 * @param isDistributionField
	 * @param isDistributionTime
	 * @param beginTime
	 * @param endTime
	 */
	public ProjectMenuVo(String id, String fieldName, String businessType, String businessName, String areaName,
			String organizationSeq, List<ProductMenuVo> projectMenus, Integer isDistributionField,
			Integer isDistributionTime, Date beginTime, Date endTime) {
		super();
		this.id = id;
		this.fieldName = fieldName;
		this.businessType = businessType;
		this.businessName = businessName;
		this.areaName = areaName;
		this.organizationSeq = organizationSeq;
		this.projectMenus = projectMenus;
		this.isDistributionField = isDistributionField;
		this.isDistributionTime = isDistributionTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	
	
	
	
	
}
