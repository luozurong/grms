package com.hori.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域分类
 * @author liaowl
 *
 */
@Entity(name = "ProCityAreaTown")
@Table(name = "pro_city_area_town")
public class ProCityAreaTown implements java.io.Serializable {
	/**
	 * 主键id
	 */
	private java.lang.Integer id;
	/**
	 * 区域代码
	 */
	private java.lang.String code;
	
	/**
	 * 名称
	 */
	private java.lang.String name;
	
	/**
	 * 上级分类ID
	 */
	private java.lang.String parentId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, insertable=true, updatable=true, length=11)
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	@Basic(optional=true)
	@Column(name="code", insertable=true, updatable=true, length=32)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	 @Basic(optional=true)
	 @Column(name="name", insertable=true, updatable=true, length=100)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	 @Basic(optional=true)
	 @Column(name="parentId", insertable=true, updatable=true, length=32)
	public java.lang.String getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ProCityAreaTown [id=" + id + ", code=" + code + ", name=" + name + ", parentId=" + parentId + "]";
	}
	
	
	
	
}
