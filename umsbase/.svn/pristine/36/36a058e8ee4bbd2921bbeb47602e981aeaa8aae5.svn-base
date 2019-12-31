package com.hori.pageModel;

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

import com.hori.dao.queryBean.BaseQueryBean;


public class Customer extends BaseQueryBean implements Serializable{



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", type=" + type + ", proxy=" + proxy + ", industry="
				+ industry + ", level=" + level + ", province=" + province + ", city=" + city + ", district=" + district
				+ ", address=" + address + ", contact=" + contact + ", phone=" + phone + ", position=" + position
				+ ", mail=" + mail + ", pictures=" + pictures + ", attchments=" + attchments + ", creator=" + creator
				+ ", creatorPrivilege=" + creatorPrivilege + ", createTime=" + createTime + ", iscooperation="
				+ iscooperation + ", followName=" + followName + ", followAccout=" + followAccout + ", pictureSize="
				+ pictureSize + ", attchmentSize=" + attchmentSize + ", page=" + page + ", rows=" + rows + ", sort="
				+ sort + ", order=" + order + ", picturesName=" + picturesName + ", attchmentsName=" + attchmentsName
				+ ", forwardParams=" + forwardParams + ", followcount=" + followcount + "]";
	}
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
	
	private String pictures;
	
	/**
	 * 附件
	 */
	private String attchments;
	
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
	private String followAccout;;
	
	
	/**
	 * t图片大小
	 */
	private String pictureSize;
	
	public String getPictureSize() {
		return pictureSize;
	}
	public void setPictureSize(String pictureSize) {
		this.pictureSize = pictureSize;
	}
	/**
	 * 附件大小
	 */
	private String attchmentSize;
	
	public String getAttchmentSize() {
		return attchmentSize;
	}
	public void setAttchmentSize(String attchmentSize) {
		this.attchmentSize = attchmentSize;
	}
	

	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)

	private String picturesName; //图片名称
	private String attchmentsName; //附件名称
	
	private String forwardParams;
	
	public String getForwardParams() {
		return forwardParams;
	}
	public void setForwardParams(String forwardParams) {
		this.forwardParams = forwardParams;
	}
	
	public int getPage() {
		return page;
	}
	public String getPicturesName() {
		return picturesName;
	}
	public void setPicturesName(String picturesName) {
		this.picturesName = picturesName;
	}
	public String getAttchmentsName() {
		return attchmentsName;
	}
	public void setAttchmentsName(String attchmentsName) {
		this.attchmentsName = attchmentsName;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getProxy() {
		return proxy;
	}
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public String getAttchments() {
		return attchments;
	}
	public void setAttchments(String attchments) {
		this.attchments = attchments;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getCreatorPrivilege() {
		return creatorPrivilege;
	}
	public void setCreatorPrivilege(String creatorPrivilege) {
		this.creatorPrivilege = creatorPrivilege;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIscooperation() {
		return iscooperation;
	}
	public void setIscooperation(String iscooperation) {
		this.iscooperation = iscooperation;
	}
	public String getFollowName() {
		return followName;
	}
	public void setFollowName(String followName) {
		this.followName = followName;
	} 

	
	public String getFollowAccout() {
		return followAccout;
	}
	public void setFollowAccout(String followAccout) {
		this.followAccout = followAccout;
	}
	public int getFollowcount() {
		return followcount;
	}
	public void setFollowcount(int followcount) {
		this.followcount = followcount;
	}
	private int  followcount;
	

}
