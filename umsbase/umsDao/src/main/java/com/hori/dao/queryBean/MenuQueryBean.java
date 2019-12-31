package com.hori.dao.queryBean;

import java.io.Serializable;

public class MenuQueryBean extends BaseQueryBean implements Serializable {
	private String menuname;
	private String systemName;
	private String menuStatus;
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	

}
