package com.hori.dao.queryBean;

import java.util.List;

import com.hori.model.ProCityAreaTown;


public class ProCityAreaTownForTreeBean {
	public String loglonCodes;
	public String codes;
	public List<ProCityAreaTown> list;
	public String getLoglonCodes() {
		return loglonCodes;
	}
	public void setLoglonCodes(String loglonCodes) {
		this.loglonCodes = loglonCodes;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public List<ProCityAreaTown> getList() {
		return list;
	}
	public void setList(List<ProCityAreaTown> list) {
		this.list = list;
	}
	

}
