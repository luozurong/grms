package com.hori.grms.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AreaVo {
	
	@JsonIgnore
	private String id;
	
	private String areaName;
	
	private String organizationSeq;
	
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
