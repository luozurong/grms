package com.hori.pageModel;

import java.io.Serializable;
import java.util.Date;

import com.hori.dao.queryBean.BaseQueryBean;

/**
 * 终端播放清单的page
 * 
 * @author lishen
 *
 */
public class TerminalAdPage extends BaseQueryBean implements Serializable {
	private String id;
	/**
	 * 终端序列号
	 */
	private String terminalSerial;
	/**
	 * 小区机构编号
	 */
	private String organizationSeq;
	/**
	 * 广告id
	 */
	private String advertisementId;
	/**
	 *广告名称 
	 */
	private String advertisementName;
	
	/**
	 * 创建日期
	 */
	private String createTime;

	/**
	 * 小区名称
	 */
	private String communityName;
	/**
	 * 广告清单
	 */
	private String playContents;
	
	private String startTime;
	
	private String endTime;
   
	private String materialName;
	
	private String searchVal;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getPlayContents() {
		return playContents;
	}

	public void setPlayContents(String playContents) {
		this.playContents = playContents;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}

	@Override
	public String toString() {
		return "TerminalAdPage [id=" + id + ", terminalSerial=" + terminalSerial + ", organizationSeq="
				+ organizationSeq + ", advertisementId=" + advertisementId + ", advertisementName=" + advertisementName
				+ ", createTime=" + createTime + ", communityName=" + communityName + ", playContents=" + playContents
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", materialName=" + materialName + "]";
	}
	
}
