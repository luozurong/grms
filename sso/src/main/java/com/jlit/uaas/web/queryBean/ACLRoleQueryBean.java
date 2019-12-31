package com.jlit.uaas.web.queryBean;

/**
 * @affect 色信息快速查询使用的封装查询条件的实体queryBean  
 * @author damon
 * @date 2009-4-21 下午05:05:29
 */


public class ACLRoleQueryBean extends BaseQueryBean{
	
    /**
     * 权限名称
     */
    private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
	
}
