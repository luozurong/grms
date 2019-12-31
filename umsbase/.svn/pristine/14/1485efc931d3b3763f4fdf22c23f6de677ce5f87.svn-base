package com.hori.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "UserType")
@Table(name = "user_type")
public class UserType {

	private java.lang.Integer id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 描述
	 */
	private java.lang.String describe;
	/**
	 * 是否有效（0：有效 1：无效）
	 */
	private java.lang.Integer valid;
	/**
	 * 排序
	 */
	private java.lang.Integer cseq;
	@Id
	@Column(name = "id", insertable = true, updatable = true)
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	@Basic(optional = true)
	@Column(name = "describe", insertable = true, updatable = true)
	public java.lang.String getDescribe() {
		return describe;
	}

	public void setDescribe(java.lang.String describe) {
		this.describe = describe;
	}
	@Basic(optional = true)
	@Column(name = "valid", insertable = true, updatable = true)
	public java.lang.Integer getValid() {
		return valid;
	}

	public void setValid(java.lang.Integer valid) {
		this.valid = valid;
	}
	@Basic(optional = true)
	@Column(name = "cseq", insertable = true, updatable = true)
	public java.lang.Integer getCseq() {
		return cseq;
	}

	public void setCseq(java.lang.Integer cseq) {
		this.cseq = cseq;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id + ", name=" + name + ", describe=" + describe + ", valid=" + valid + ", cseq="
				+ cseq + "]";
	}

	
}
