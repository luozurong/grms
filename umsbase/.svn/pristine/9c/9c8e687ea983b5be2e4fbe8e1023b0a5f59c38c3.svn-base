package com.hori.model;

import java.io.Serializable;
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

/**
 * 终端信息
 *
 */
@Entity(name = "Terminal")
@Table(name = "terminal")
public class Terminal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223802633881228818L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 终端名称
	 */
	private String terminalName;
	/**
	 * 终端编码
	 */
	private String code;
	/**
	 * 小区机构编号
	 */
	private String organizationSeq;// organization_seq
	/**
	 * 小区号
	 */
	private String communitySerial;
	/**
	 * 分组状态，针对门口机和门禁控制器是否归属某个门（0 ：未分组 1：已分组）
	 */
	private String groupState;
	/**
	 * 位置名称（X区X栋X单元X楼栋...）
	 */
	private String locationName;
	/**
	 * 住户编号
	 */
	private String householdSerial;

	/**
	 * 创建日期
	 */
	private Long createTime;
	/**
	 * 更新日期
	 */
	private Long updateTime;
	
	/**
	 * 终端序列号
	 */
	private String terminalSerial;
	
	/**
	 * 注册号码（终端注册uums获得的8位随机用户账号）
	 */
	private String registerAccount;
    
	/**
	 * 视频门禁专用号码
	 */
	private String serial;
	
	/**
	 * 终端别名
	 */
	private String terminalAliasName;
	/**
	 * 终端类型1：表示对讲同步的终端，2：广告系统添加的预览终端
	 */
	private Integer terminalType ;
	
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
	@Column(name = "name", insertable = true, updatable = true)
	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	@Basic(optional = true)
	@Column(name = "code", insertable = true, updatable = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	@Column(name = "community_serial", insertable = true, updatable = true)
	public String getCommunitySerial() {
		return communitySerial;
	}

	public void setCommunitySerial(String communitySerial) {
		this.communitySerial = communitySerial;
	}

	@Basic(optional = true)
	@Column(name = "group_state", insertable = true, updatable = true)
	public String getGroupState() {
		return groupState;
	}

	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}

	@Basic(optional = true)
	@Column(name = "location_name", insertable = true, updatable = true)
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Basic(optional = true)
	@Column(name = "household_serial", insertable = true, updatable = true)
	public String getHouseholdSerial() {
		return householdSerial;
	}

	public void setHouseholdSerial(String householdSerial) {
		this.householdSerial = householdSerial;
	}
     
	@Basic(optional = true)
	@Column(name = "creator_time", insertable = true, updatable = true)
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Basic(optional = true)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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
	@Column(name = "register_account", insertable = true, updatable = true)
	public String getRegisterAccount() {
		return registerAccount;
	}

	public void setRegisterAccount(String registerAccount) {
		this.registerAccount = registerAccount;
	}
	
	@Basic(optional = true)
	@Column(name = "serial", insertable = true, updatable = true)
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
    
	@Basic(optional = true)
	@Column(name = "terminal_alias_name", insertable = true, updatable = true)
	public String getTerminalAliasName() {
		return terminalAliasName;
	}

	public void setTerminalAliasName(String terminalAliasName) {
		this.terminalAliasName = terminalAliasName;
	}

	
	@Basic(optional = true)
	@Column(name = "terminal_type", insertable = true, updatable = true)
	public Integer getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(Integer terminalType) {
		this.terminalType = terminalType;
	}

	@Override
	public String toString() {
		return "Terminal [id=" + id + ", terminalName=" + terminalName + ", code=" + code + ", organizationSeq="
				+ organizationSeq + ", communitySerial=" + communitySerial + ", groupState=" + groupState
				+ ", locationName=" + locationName + ", householdSerial=" + householdSerial + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", terminalSerial=" + terminalSerial
				+ ", registerAccount=" + registerAccount + ", serial=" + serial + ", terminalAliasName="
				+ terminalAliasName + "]";
	}
	
}
