package com.hori.grms.queryBean;

import java.util.Date;
import java.util.List;

/**
 * 执行清单异常查询Bean
 * @author hehj
 * @datetime 2018年8月14日 上午10:31:33
 */
public class PAExceptionQueryBean extends BaseQueryBean {
	// 执行清单ID
	private String projectActionCode;
	// 处理状态，0未处理 1处理中 2已处理
	private Short status;
	//上报时间
	private Date createTime;
	//上报部门(业务类型)
	private String businessType;
	// 用户数据域，0为个人数据，1为部门数据，2为全局数据
	private byte dataArea;
	//项目编号
	private String productCode;
	/**
	 *  数据域对应权限的用户列表:
	 *  个人数据：用户可查看自己和下级的数据；
	 *	部门数据：用户可查看自己、下级和机构内同级别的数据；
	 *	全局数据：用户可查看系统全部数据
	 */
	private List<String> userList;
	/**
	 *  数据域对应权限的部门列表:
	 *  个人数据：用户可查看自己和下级部门的数据；
	 *	部门数据：用户可查看自己、下级和机构内同级别的数据；
	 *	全局数据：用户可查看系统全部数据
	 */
	private List<String> departList;
	
	public String getProjectActionCode() {
		return projectActionCode;
	}
	public void setProjectActionCode(String projectActionCode) {
		this.projectActionCode = projectActionCode;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public byte getDataArea() {
		return dataArea;
	}
	public void setDataArea(byte dataArea) {
		this.dataArea = dataArea;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public List<String> getDepartList() {
		return departList;
	}
	public void setDepartList(List<String> departList) {
		this.departList = departList;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@Override
	public String toString() {
		return "PAExceptionQueryBean [projectActionCode=" + projectActionCode + ", status=" + status + ", createTime="
				+ createTime + ", businessType=" + businessType + ", dataArea=" + dataArea + ", productCode="
				+ productCode + ", userList=" + userList + ", departList=" + departList + "]";
	}
	
}
