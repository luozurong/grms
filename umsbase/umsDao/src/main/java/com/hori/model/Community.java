package com.hori.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Community")
@Table(name = "community")
public class Community implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2477345738861480197L;
	/**
     * 主键id
     */
	private String id;
    /**
     * 小区名称
     */
	private String communityName;
	/**
     * 详细地址描述
     */
	private String address;
	/**
     * 小区机构编号
     */
	private String organizationSeq;// organization_seq
	/**
     * 所属省份
     */
	private String province;
	/**
     * 所属城市
     */
	private String city;
	/**
     * 归属县/ 区
     */
	private String country;
	/**
     * 归属乡镇
     */
	private String town;
	/**
     * 小区类型，高级小区
     */
	private Integer type=-1;
	/**
     * 入住户数
     */
	private Integer familyCount=0;
	/**
     * 入住人数
     */
	private Integer peopleCount=0;
	/**
     * 媒体终端数量
     */
	private Integer terminalCount=0;
	/**
     * 门禁卡数量
     */
	private Integer grandCardCount=0;
	/**
     * 能否做活动
     */
	private Integer enablePromotionActive = 0;
	/**
     * 活动场地大小
     */
	private String activeLocationDetail;
	/**
     * 商场/购物中心名称
     */
	private String mallName;
	/**
     * 医院/医药连锁机构（名称）
     */
	private String treatmentDepartmentName;
	/**
     * 周边学校及教育机构（名称）
     */
	private String educationDepartmentName;
	/**
     * 银行营业厅/证券/银行终端（名称）
     */
	private String bankingDepartmentName;
	/**
	 * 小区号
	 */
	private String serial;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 更新日期
	 */
	private Date updateTime;
	/**
	 * 小区类型（1：正式小区，2：测试小区）
	 */
	private Integer communityType;
	
	 @Id
	 @GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	 @GeneratedValue(generator = "system-uuid")
	 @Column(name="ID", nullable=false, insertable=true, updatable=true, length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Basic(optional=true)
	@Column(name="name", insertable=true, updatable=true)
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	@Basic(optional=true)
	@Column(name="address", insertable=true, updatable=true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
	@Basic(optional=true)
	@Column(name="organization_seq", insertable=true, updatable=true)
	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}
    
	@Basic(optional=true)
	@Column(name="province", insertable=true, updatable=true)
	public String getProvince() {
		return province;
	}
     
	public void setProvince(String province) {
		this.province = province;
	}
    
	@Basic(optional=true)
	@Column(name="city", insertable=true, updatable=true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    
	@Basic(optional=true)
	@Column(name="country", insertable=true, updatable=true)
	public String getCountry() {
		return country;
	}
  
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Basic(optional=true)
	@Column(name="town", insertable=true, updatable=true)
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
 
	@Basic(optional=true)
	@Column(name="type", insertable=true, updatable=true)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
	@Basic(optional=true)
	@Column(name="family_count", insertable=true, updatable=true)
	public Integer getFamilyCount() {
		return familyCount;
	}
    
	   
	public void setFamilyCount(Integer familyCount) {
		this.familyCount = familyCount;
	}
    
	@Basic(optional=true)
	@Column(name="people_count", insertable=true, updatable=true)
	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
    
	@Basic(optional=true)
	@Column(name="terminal_count", insertable=true, updatable=true)
	public Integer getTerminalCount() {
		return terminalCount;
	}

	public void setTerminalCount(Integer terminalCount) {
		this.terminalCount = terminalCount;
	}
    
	@Basic(optional=true)
	@Column(name="grand_card_count", insertable=true, updatable=true)
	public Integer getGrandCardCount() {
		return grandCardCount;
	}

	public void setGrandCardCount(Integer grandCardCount) {
		this.grandCardCount = grandCardCount;
	}
	
	@Basic(optional=true)
	@Column(name="enable_promotion_active", insertable=true, updatable=true)
	public Integer getEnablePromotionActive() {
		return enablePromotionActive;
	}

	public void setEnablePromotionActive(Integer enablePromotionActive) {
		this.enablePromotionActive = enablePromotionActive;
	}
	
	@Basic(optional=true)
	@Column(name="active_location_detail", insertable=true, updatable=true)
	public String getActiveLocationDetail() {
		return activeLocationDetail;
	}

	public void setActiveLocationDetail(String activeLocationDetail) {
		this.activeLocationDetail = activeLocationDetail;
	}
	
	@Basic(optional=true)
	@Column(name="mall_name", insertable=true, updatable=true)
	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}
	
	@Basic(optional=true)
	@Column(name="treatment_department_name", insertable=true, updatable=true)
	public String getTreatmentDepartmentName() {
		return treatmentDepartmentName;
	}

	public void setTreatmentDepartmentName(String treatmentDepartmentName) {
		this.treatmentDepartmentName = treatmentDepartmentName;
	}
	
	@Basic(optional=true)
	@Column(name="education_department_name", insertable=true, updatable=true)
	public String getEducationDepartmentName() {
		return educationDepartmentName;
	}

	public void setEducationDepartmentName(String educationDepartmentName) {
		this.educationDepartmentName = educationDepartmentName;
	}
	
	@Basic(optional=true)
	@Column(name="banking_department_name", insertable=true, updatable=true)
	public String getBankingDepartmentName() {
		return bankingDepartmentName;
	}

	public void setBankingDepartmentName(String bankingDepartmentName) {
		this.bankingDepartmentName = bankingDepartmentName;
	}

	@Basic(optional=true)
	@Column(name="serial", insertable=true, updatable=true)
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
    
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", insertable=true, updatable=true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time", insertable=true, updatable=true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
	@Basic(optional=true)
	@Column(name="community_type", insertable=true, updatable=true)
	public Integer getCommunityType() {
		return communityType;
	}

	public void setCommunityType(Integer communityType) {
		this.communityType = communityType;
	}

	@Override
	public String toString() {
		return "Community [id=" + id + ", communityName=" + communityName + ", address=" + address
				+ ", organizationSeq=" + organizationSeq + ", province=" + province + ", city=" + city + ", country="
				+ country + ", town=" + town + ", type=" + type + ", familyCount=" + familyCount + ", peopleCount="
				+ peopleCount + ", terminalCount=" + terminalCount + ", grandCardCount=" + grandCardCount
				+ ", enablePromotionActive=" + enablePromotionActive + ", activeLocationDetail=" + activeLocationDetail
				+ ", mallName=" + mallName + ", treatmentDepartmentName=" + treatmentDepartmentName
				+ ", educationDepartmentName=" + educationDepartmentName + ", bankingDepartmentName="
				+ bankingDepartmentName + ", serial=" + serial + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", communityType=" + communityType + "]";
	}



	
}
