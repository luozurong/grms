package com.hori.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * auth entity. @author Eclipse Persistence Tools
 */
@Entity(name = "Auth")
@Table(name = "auth")
public class Auth implements java.io.Serializable {

	// Fields

	private String id;
	private Auth auth;
	private String cdesc;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	/**权限类型 01:菜单 02:按钮*/
	private String ctype;
	private String ciconcls;
	//private Set<Troletauth> troletauths = new HashSet<Troletauth>(0);
	private Set<Auth> auths = new HashSet<Auth>(0);

	// Constructors

	/** default constructor */
	public Auth() {
	}


	// Property accessors
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public Auth getAuth() {
		return this.auth;
	}

	

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	@Column(name = "CDESC", length = 200)
	public String getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CSEQ", precision = 22, scale = 0)
	public BigDecimal getCseq() {
		return this.cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	@Column(name = "CURL", length = 200)
	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auth")
	public Set<Auth> getAuths() {
		return this.auths;
	}

	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Column(name = "CICONCLS", length = 20)
	public String getCiconcls() {
		return ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

}