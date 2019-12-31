package com.hori.dao.queryBean;

/**
 * 用户查询queryBean
 * 
 * @author daihf
 *
 */
public class AclRoleQueryBean extends BaseQueryBean {
	
	
	//创建者的帐号
	private String belongAccount;


	private String roleName;
	

	
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBelongAccount() {
		return belongAccount;
	}

	public void setBelongAccount(String belongAccount) {
		this.belongAccount = belongAccount;
	}
	
	
}
