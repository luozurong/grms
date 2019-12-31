/*
 * ProjectProductMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.ProductMenu;
import com.hori.grms.model.ProjectProduct;
import com.hori.grms.model.ProjectProductExample;
import com.hori.grms.vo.ProjectProductVo;
import com.hori.grms.vo.project.ProjectMenuVo;

public interface ProjectProductMapper {
    int countByExample(ProjectProductExample example);

    int deleteByExample(ProjectProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectProduct record);

    int insertSelective(ProjectProduct record);

    List<ProjectProduct> selectByExample(ProjectProductExample example);

    ProjectProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectProduct record, @Param("example") ProjectProductExample example);

    int updateByExample(@Param("record") ProjectProduct record, @Param("example") ProjectProductExample example);

    int updateByPrimaryKeySelective(ProjectProduct record);

    int updateByPrimaryKey(ProjectProduct record);
    /**
     * 通过项目编码查询资料清单VO
     * @param projectCode
     * @return
     */
	List<ProjectMenuVo> findVoByProjectCode(String projectCode);

	/**
	 * 通过项目编号 查看 id列表
	 * @param productCode
	 * @return
	 */
	List<String> findByProjectCode(@Param("productCode") String productCode);

	/**
	 * 通过项目编号删除
	 * @param productCode
	 */
	void deleteByProjectCode(@Param("productCode") String productCode);

	/**
	 * 查询所有
	 * @return
	 */
	List<ProductMenu> findAllProductMenus();
	
	/**
	 * 根据项目编号查找，按开始时间【倒序】
	 * @param projectCode
	 * @return
	 */
	List<ProjectProductVo> selectByProjectCode(@Param("projectCode")String projectCode);
}