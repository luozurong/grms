/*
 * ProjectProductMenuMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.ProjectProductMenu;
import com.hori.grms.model.ProjectProductMenuExample;
import com.hori.grms.vo.project.ProductMenuVo;

public interface ProjectProductMenuMapper {
    int countByExample(ProjectProductMenuExample example);

    int deleteByExample(ProjectProductMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectProductMenu record);

    int insertSelective(ProjectProductMenu record);

    List<ProjectProductMenu> selectByExample(ProjectProductMenuExample example);

    ProjectProductMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectProductMenu record, @Param("example") ProjectProductMenuExample example);

    int updateByExample(@Param("record") ProjectProductMenu record, @Param("example") ProjectProductMenuExample example);

    int updateByPrimaryKeySelective(ProjectProductMenu record);

    int updateByPrimaryKey(ProjectProductMenu record);
    /**
     * 通过项目清单Id查询项目产品明细Vo
     * @param id
     * @return
     */
	List<ProductMenuVo> findVoByProductMenuId(String id);
	/**
	 * 释放项目相关的资源
	 * @param productCode 项目编号
	 */
	void releaseLock(String productCode);
	/**
	 * 释放清单相关的项目产品id资源
	 * @param projectProductId 项目产品id
	 */
	void releaseResources(String projectProductId);
	/**
	 * 查找清单关联的小区数量
	 * @param projectProductId 项目清单主键id
	 * @return
	 */
	Long getAreaNumByPPId(String projectProductId);

	/**
	 * 查找 id列表 通过 project_product表的主键id集合
	 * @param project_products
	 * @return
	 */
	List<String> findByProjectProductIds(@Param("project_products")List<String> project_products);

	/**
	 * 通过项目id查看id列表
	 * @param productCode
	 * @return
	 */
	List<String> findByProjectCode(@Param("productCode")String productCode);

	/**
	 * 通过项目编号删除
	 * @param productCode
	 */
	void deleteByProjectCode(@Param("productCode")String productCode);
	/**
	 * 锁定项目资源
	 * @param productCode
	 */
	void lockResources(String productCode);
	/**
	 * 获取符合条件的媒管资源
	 * @param productMenuId
	 * @param organizationSeq
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Integer getMGTotal(@Param("productMenuId")String productMenuId, @Param("organizationSeq")String organizationSeq, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
	/**
	 * 获取符合条件的用户或者电商资源
	 * @param productMenuId
	 * @param beginTime
	 * @return
	 */
	Integer getYHorDSTotal(@Param("productMenuId")String productMenuId, @Param("beginTime")Date beginTime);
	/**
	 * 查找清单关联的小区数量
	 * @param projectProductId 项目产品明细id
	 * @return
	 */
	long getAreaNumByPPMId(String projectProductMenuId);
}