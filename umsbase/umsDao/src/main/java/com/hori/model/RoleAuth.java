package com.hori.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "RoleAuth")
@Table(name = "role_auth")
public class RoleAuth {
	private String id;
	private String roleId;
	private String authId;
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
	@Column(name = "role_id", insertable = true, updatable = true)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Basic(optional = true)
	@Column(name = "auth_id", insertable = true, updatable = true)
	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

}
