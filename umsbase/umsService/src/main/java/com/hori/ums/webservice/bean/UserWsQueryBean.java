package com.hori.ums.webservice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用户webservice查询queryBean
 * 
 * @author sucs
 *
 */
public class UserWsQueryBean extends BaseQueryBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户账号
	 */
	private String userAccount;

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 角色名
	 */
	private String role;
	
	/**
	 * 机构
	 */
	private String department;
	
	/**
     * 省
     */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县区
	 */
	private String country;

	/**
	 * 运维人员责任区域(县、区 号列表)
	 */
	private List<String> countrys;
	
	
	public String getUserAccount() {
		return userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public String getMobile() {
		return mobile;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRole() {
		return role;
	}
	public String getDepartment() {
		return department;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<String> getCountrys() {
		return countrys;
	}
	public void setCountrys(List<String> countrys) {
		this.countrys = countrys;
	}
	
}
