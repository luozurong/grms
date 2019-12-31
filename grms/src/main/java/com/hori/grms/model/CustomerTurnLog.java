package com.hori.grms.model;

import java.io.Serializable;
import java.util.Date;

public class CustomerTurnLog implements Serializable{
	 	private String id;

	    private String customerId;

	    private String accepterName;

	    private String accepterAccount;

	    private String formerName;

	    private String formerAccount;

	    private Date createTime;

	    private String createrName;

	    private String createrAccount;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(String customerId) {
	        this.customerId = customerId;
	    }

	    public String getAccepterName() {
	        return accepterName;
	    }

	    public void setAccepterName(String accepterName) {
	        this.accepterName = accepterName;
	    }

	    public String getAccepterAccount() {
	        return accepterAccount;
	    }

	    public void setAccepterAccount(String accepterAccount) {
	        this.accepterAccount = accepterAccount;
	    }

	    public String getFormerName() {
	        return formerName;
	    }

	    public void setFormerName(String formerName) {
	        this.formerName = formerName;
	    }

	    public String getFormerAccount() {
	        return formerAccount;
	    }

	    public void setFormerAccount(String formerAccount) {
	        this.formerAccount = formerAccount;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public String getCreaterName() {
	        return createrName;
	    }

	    public void setCreaterName(String createrName) {
	        this.createrName = createrName;
	    }

	    public String getCreaterAccount() {
	        return createrAccount;
	    }

	    public void setCreaterAccount(String createrAccount) {
	        this.createrAccount = createrAccount;
	    }
}
