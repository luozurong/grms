package com.jlit.uaas.web.queryBean;

public class DeveloperQueryBean extends BaseQueryBean {
	private String devAccount;
	private String devName;
	private Integer devType;
	private Integer  status;
	public String getDevAccount() {
		return devAccount;
	}
	public void setDevAccount(String devAccount) {
		this.devAccount = devAccount;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public Integer getDevType() {
		return devType;
	}
	public void setDevType(Integer devType) {
		this.devType = devType;
	}
	public Integer getStatus() {
		if(null==status || "".equals(status))
			return null;
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
