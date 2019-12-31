package com.hori.vo;

import java.util.Date;

public class MenuButttonVo implements java.io.Serializable{
	private String systemName;
	private String systemId;
	private String menuId;
	private String menuCode;
	private String menuParentId;
	private String menuname;
	private String menuUrl;
	private String menuIcon;
	private Byte menuApp;
	private Byte menuStatus;
	private Byte menuOrder;
	private Date createTime;
	private Date modifiedTime;
	private String note;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public Byte getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(Byte menuStatus) {
		this.menuStatus = menuStatus;
	}
	public Byte getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Byte menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Byte getMenuApp() {
		return menuApp;
	}
	public void setMenuApp(Byte menuApp) {
		this.menuApp = menuApp;
	}
	

}
