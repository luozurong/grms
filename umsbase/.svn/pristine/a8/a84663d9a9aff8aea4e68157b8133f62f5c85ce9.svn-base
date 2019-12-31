package com.hori.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 服务区域
 * 
 */
@Entity(name = "AddressData")
@Table(name = "address_data")
public class AddressData implements Serializable {
	private String id;
	/**
	 * 用这个关联相关的服务
	 */
	private String uuId;
	/**
	 * 最后一个编码
	 */
	private String code;
    /**
     * 省的编码
     */
	private String provinceCode;
	/**
	 * 省的名称
	 */
	private String provinceName;
	/**
	 * 市的编码
	 */
	private String cityCode;
	/**
	 * 市的名字
	 */
	private String cityName;
	/**
	 * 区的编码与县同级
	 */
	private String areaCode;
	/**
	 * 区的名称
	 */
	private String areaName;
	/**
	 * 镇的编码 与街道同级
	 */
	private String townCode;
	/**
	 * 镇的名称
	 */
	private String townName;

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
	@Column(name = "uuId", insertable = true, updatable = true, length = 32)
	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	@Basic(optional = true)
	@Column(name = "code", insertable = true, updatable = true, length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Basic(optional = true)
	@Column(name = "province_code", insertable = true, updatable = true, length = 32)
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Basic(optional = true)
	@Column(name = "province_name", insertable = true, updatable = true, length = 32)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Basic(optional = true)
	@Column(name = "city_code", insertable = true, updatable = true, length = 32)
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Basic(optional = true)
	@Column(name = "city_name", insertable = true, updatable = true, length = 32)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Basic(optional = true)
	@Column(name = "area_code", insertable = true, updatable = true, length = 32)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Basic(optional = true)
	@Column(name = "area_name", insertable = true, updatable = true, length = 32)
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Basic(optional = true)
	@Column(name = "town_code", insertable = true, updatable = true, length = 32)
	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	@Basic(optional = true)
	@Column(name = "town_name", insertable = true, updatable = true, length = 64)
	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	@Override
	public String toString() {
		return "AddressData [id=" + id + ", uuId=" + uuId + ", code=" + code
				+ ", provinceCode=" + provinceCode + ", provinceName="
				+ provinceName + ", cityCode=" + cityCode + ", cityName="
				+ cityName + ", areaCode=" + areaCode + ", areaName="
				+ areaName + ", townCode=" + townCode + ", townName="
				+ townName + "]";
	}

}
