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

@Entity(name = "TerminalAdvertisementLogs")
@Table(name = "terminal_advertisement_logs")
public class TerminalAdvertisementLogs implements java.io.Serializable {

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
	 * 创建日期
	 */
	private Date createTime;
	
	/**
	 * 小区名称
	 */
	private String communityName;
	/**
	 * 广告清单
	 */
	private String playContents;
	/**
	 * 广告播放日期
	 */
	private String playTime;
	/**
	 * 清单类型
	 */
	private String inventoryType;
	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic(optional = true)
	@Column(name = "terminal_serial", insertable = true, updatable = true)
	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}

	@Basic(optional = true)
	@Column(name = "organization_seq", insertable = true, updatable = true)
	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	@Basic(optional = true)
	@Column(name = "advertisement_id", insertable = true, updatable = true)
	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	@Basic(optional = true)
	@Column(name = "community_name", insertable = true, updatable = true)
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
    
	@Basic(optional = true)
	@Column(name = "play_contents", insertable = true, updatable = true)
	public String getPlayContents() {
		return playContents;
	}

	public void setPlayContents(String playContents) {
		this.playContents = playContents;
	}
    
	@Basic(optional = true)
	@Column(name = "play_time", insertable = true, updatable = true)
	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
    
	@Basic(optional = true)
	@Column(name = "inventory_type", insertable = true, updatable = true)
	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

}
