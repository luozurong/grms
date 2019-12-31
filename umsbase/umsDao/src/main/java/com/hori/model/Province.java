/***********************************************************************
 * Module:  DeptManage.java
 * Author:  daihf
 * Purpose: Defines the Class DeptManage
 ***********************************************************************/

package com.hori.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 省
 * 
 * @pdOid 009f5c9b-914c-44ff-b82a-94c41b9d1abb */
@Entity(name="Province")
@Table(name="province")
public class Province implements java.io.Serializable {
   /** 主键
    * 
    * @pdOid 4cdf9132-ed58-4ddc-a02e-7bb33aceb88e */
   public java.lang.Integer id;
   /** 省名称
    * 
    * @pdOid 1d107605-655a-4eb6-b7d9-edd50a9e6908 */
   public java.lang.String name;
   /** 编号
    * 
    * @pdOid 5131c56e-fb66-452a-ab43-aa5321038f40 */
   public java.lang.Integer code;

	public Province() {
      // TODO Add your own initialization code here.
	}
   
   /**
    * Get value of id
    *
    * @return id 
    */
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false, insertable=true, updatable=true, length=11)
   public java.lang.Integer getId()
   {
      return id;
   }
   
   /**
    * Set value of id
    *
    * @param newId 
    */
   public void setId(java.lang.Integer newId)
   {
      this.id = newId;
   }
   
   /**
    * Get value of name
    *
    * @return name 
    */
   @Basic(optional=true)
   @Column(name="province", insertable=true, updatable=true, length=20)
   public java.lang.String getName()
   {
      return name;
   }
   
   /**
    * Set value of name
    *
    * @param newName 
    */
   public void setName(java.lang.String newName)
   {
      this.name = newName;
   }
   
   /**
    * Get value of code
    *
    * @return code 
    */
   @Basic(optional=true)
   @Column(name="provinceID", insertable=true, updatable=true, length=11)
   public java.lang.Integer getCode()
   {
      return code;
   }
   
	/**
    * Set value of code
    *
    * @param newCode 
    */
    public void setCode(java.lang.Integer newCode)
    {
      this.code = newCode;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Province other = (Province) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

    

}