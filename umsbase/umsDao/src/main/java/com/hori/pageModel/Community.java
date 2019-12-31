package com.hori.pageModel;

import java.io.Serializable;
import java.util.Date;

import com.hori.dao.queryBean.BaseQueryBean;

public class Community extends BaseQueryBean implements Serializable {

	private static final long serialVersionUID = 7574794670058464603L;


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
     * 入住户数
     */
	private String familyCount;
	/**
     * 入住人数
     */
	private String peopleCount;
	/**
     * 媒体终端数量
     */
	private String terminalCount;
	/**
     * 门禁卡数量
     */
	private String grandCardCount;
	/**
     * 能否做活动
     */
	private String enablePromotionActive;
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
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
    private int  peopleKey; 
    private int  terminalKey;
	
	private Integer communityType;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
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


	public String getActiveLocationDetail() {
		return activeLocationDetail;
	}

	public void setActiveLocationDetail(String activeLocationDetail) {
		this.activeLocationDetail = activeLocationDetail;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getTreatmentDepartmentName() {
		return treatmentDepartmentName;
	}

	public void setTreatmentDepartmentName(String treatmentDepartmentName) {
		this.treatmentDepartmentName = treatmentDepartmentName;
	}

	public String getEducationDepartmentName() {
		return educationDepartmentName;
	}

	public void setEducationDepartmentName(String educationDepartmentName) {
		this.educationDepartmentName = educationDepartmentName;
	}

	public String getBankingDepartmentName() {
		return bankingDepartmentName;
	}

	public void setBankingDepartmentName(String bankingDepartmentName) {
		this.bankingDepartmentName = bankingDepartmentName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPeopleKey() {
		return peopleKey;
	}

	public void setPeopleKey(int peopleKey) {
		this.peopleKey = peopleKey;
	}

	public int getTerminalKey() {
		return terminalKey;
	}

	public void setTerminalKey(int terminalKey) {
		this.terminalKey = terminalKey;
	}


	public String getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(String familyCount) {
		this.familyCount = familyCount;
	}

	public String getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(String peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getTerminalCount() {
		return terminalCount;
	}

	public void setTerminalCount(String terminalCount) {
		this.terminalCount = terminalCount;
	}

	public String getGrandCardCount() {
		return grandCardCount;
	}

	public void setGrandCardCount(String grandCardCount) {
		this.grandCardCount = grandCardCount;
	}

	public String getEnablePromotionActive() {
		return enablePromotionActive;
	}

	public void setEnablePromotionActive(String enablePromotionActive) {
		this.enablePromotionActive = enablePromotionActive;
	}

	public Integer getCommunityType() {
		return communityType;
	}

	public void setCommunityType(Integer communityType) {
		this.communityType = communityType;
	}


}
