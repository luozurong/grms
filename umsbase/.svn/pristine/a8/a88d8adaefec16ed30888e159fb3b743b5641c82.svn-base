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

@Entity
@Table(name="customer")
public class TCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 56386396159997345L;

	private String id;
	
	private String name;
	/**
	 * 客户类型  1 代理商  2  广告商
	 */
	private Integer type;
	
	
	private String proxy;
	/**
	 * 行业
	 */
	private String industry;
	
	private Integer level;
	/**
	 * 省份地区id
	 */
	private Integer province;
	
	private Integer city;
	/**
	 * 镇区id
	 */
	private Integer district;
	
	private String address;
	
	private String contact;
	
	private String phone;
	
	/**
	 * 职位
	 */
	private String position;
	
	private String mail;
	
	//private String pictures;
	
	/**
	 * 附件
	 */
	//private String attchments;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建人权限权重
	 */
	private String creatorPrivilege;
	
	
	private Date createTime;
	/**
	 * 是否合作过  0否  1是
	 */
	private String iscooperation; 
	/**
	 * 跟进人名称
	 */
	private String followName; 
	/**
	 * 跟进人账号
	 */
	private String followAccout;
	
	//private String attchmentSize;
	
	//private String picturesName; //图片名称
	//private String attchmentsName; //附件名称
	
	/*@Column(name="picturesName",length=300)
	public String getPicturesName() {
		return picturesName;
	}
	public void setPicturesName(String picturesName) {
		this.picturesName = picturesName;
	}
	@Column(name="attchmentsName",length=300)
	public String getAttchmentsName() {
		return attchmentsName;
	}
	public void setAttchmentsName(String attchmentsName) {
		this.attchmentsName = attchmentsName;
	}*/
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="id", nullable=false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	@Column(name="proxy")
	public String getProxy() {
		return proxy;
	}
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}
	
	
	@Column(name="industry")
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	

	@Column(name="level")
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	

	@Column(name="province")
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	
	

	@Column(name="city")
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	
	
	
	@Column(name="district")
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	
	
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Column(name="contact",nullable = false)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Column(name="phone",nullable = false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name="mail")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
/*	@Column(name="pictures")
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	@Column(name="attchments")
	public String getAttchments() {
		return attchments;
	}
	public void setAttchments(String attchments) {
		this.attchments = attchments;
	}*/
	
	@Column(name="creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	@Column(name="creator_privilege")
	public String getCreatorPrivilege() {
		return creatorPrivilege;
	}
	public void setCreatorPrivilege(String creatorPrivilege) {
		this.creatorPrivilege = creatorPrivilege;
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
	
/*	@Override
	public String toString() {
		return "TCustomer [id=" + id + ", name=" + name + ", type=" + type + ", proxy=" + proxy + ", industry="
				+ industry + ", level=" + level + ", province=" + province + ", city=" + city + ", district=" + district
				+ ", address=" + address + ", contact=" + contact + ", phone=" + phone + ", position=" + position
				+ ", mail=" + mail + ", pictures=" + pictures + ", attchments=" + attchments + ", creator=" + creator
				+ ", creatorPrivilege=" + creatorPrivilege + ", createTime=" + createTime + ", iscooperation="
				+ iscooperation + ", followName=" + followName + ", followAccout=" + followAccout + ", attchmentSize="
				+ attchmentSize + ", picturesName=" + picturesName + ", attchmentsName=" + attchmentsName + "]";
	}*/
	@Column(name="is_cooperation")
	public String getIscooperation() {
		return iscooperation;
	}
	public void setIscooperation(String iscooperation) {
		this.iscooperation = iscooperation;
	}
	
	
	@Column(name="follow_name")
	public String getFollowName() {
		return followName;
	}
	public void setFollowName(String followName) {
		this.followName = followName;
	}
	@Column(name="follow_accout")
	public String getFollowAccout() {
		return followAccout;
	}
	public void setFollowAccout(String followAccout) {
		this.followAccout = followAccout;
	}
	@Override
	public String toString() {
		return "TCustomer [id=" + id + ", name=" + name + ", type=" + type + ", proxy=" + proxy + ", industry="
				+ industry + ", level=" + level + ", province=" + province + ", city=" + city + ", district=" + district
				+ ", address=" + address + ", contact=" + contact + ", phone=" + phone + ", position=" + position
				+ ", mail=" + mail + ", creator=" + creator + ", creatorPrivilege=" + creatorPrivilege + ", createTime="
				+ createTime + ", iscooperation=" + iscooperation + ", followName=" + followName + ", followAccout="
				+ followAccout + "]";
	}
	
/*	@Column(name="attchment_size")
 	public String getAttchmentSize() {
 		return attchmentSize;
 	}
 	public void setAttchmentSize(String attchmentSize) {
 		this.attchmentSize = attchmentSize;
 	}*/
}
