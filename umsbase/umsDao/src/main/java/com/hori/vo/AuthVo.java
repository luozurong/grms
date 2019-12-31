package com.hori.vo;

import java.math.BigDecimal;
import java.util.List;

public class AuthVo implements java.io.Serializable {

	private String id;
	private String state = "open";// 是否展开(open,closed)
	private String cpid;
	private String cpname;
	/**权限类型 01:菜单 02:按钮*/
	private String ctype;
	

	
	private String cname;
	private String curl;
	private BigDecimal cseq;
	private String cdesc;
	private String ciconCls;
	
	private  List<AuthVo>  authVoList;
	
	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	public List<AuthVo> getAuthVoList() {
		return authVoList;
	}

	public void setAuthList(List<AuthVo> authVoList) {
		
		this.authVoList = authVoList;
	}






	

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public BigDecimal getCseq() {
		return cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public String getCiconCls() {
		return ciconCls;
	}

	public void setCiconCls(String ciconCls) {
		this.ciconCls = ciconCls;
	}

}