package com.hori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="StrategyBag")
@Table(name="strategy_bag")
public class StrategyBag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5288967452138267972L;

	private String id;
	
	private String name;
	/**
	 * 策略包类型 0为 默认包 1为策略包
	 */
	private Integer type;
	
	private Date createTime;
	
	private String creatorId;
	
	
	//private Set<Strategy> strategyList=new HashSet<Strategy>();
		
	private String parsList;
	private String addressList;
	
	


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

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="creator_id")
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	

	
/*	@OneToMany(fetch=FetchType.EAGER)                                          //指定一对多关系
	//@OneToMany                                          //指定一对多关系
	@Cascade(value={CascadeType.SAVE_UPDATE})         //设定级联关系
	@JoinColumn(name="strategy_bag_id")
	public Set<Strategy> getStrategyList() {
		return strategyList;
	}

	public void setStrategyList(Set<Strategy> strategyList) {
		this.strategyList = strategyList;
	}
	*/
	
	


	@Transient
	public String getAddressList() {
		return addressList;
	}

	@Override
	public String toString() {
		return "StrategyBag [id=" + id + ", name=" + name + ", type=" + type + ", createTime=" + createTime
				+ ", creatorId=" + creatorId + ", parsList=" + parsList + ", addressList=" + addressList + "]";
	}

	public void setAddressList(String addressList) {
		this.addressList = addressList;
	}

	@Transient
	public String getParsList() {
		return parsList;
	}

	public void setParsList(String parsList) {
		this.parsList = parsList;
	}
}
