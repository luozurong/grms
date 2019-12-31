/*
 * ProjectMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import com.hori.grms.model.Project;
import com.hori.grms.model.ProjectExample;
import com.hori.grms.queryBean.ProjectQueryBean;
import com.hori.grms.vo.ProjectDetailsVo;
import com.hori.grms.vo.project.ProjectVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    /**
     * 查询项目列表
     * @param projectQueryBean
     * @return
     */
	List<Project> list(ProjectQueryBean projectQueryBean);
	/**
	 * 通过Code查询项目详情Vo
	 * @param projectId
	 * @return
	 */
	ProjectDetailsVo findProjectDetailsVoByProjectCode(String projectCode);
	/**
	 * 撤回项目
	 * @param productCode 项目编号
	 */
	void revoke(String productCode);
	
	/**
	 * 删除项目
	 * @param productCode
	 */
	void delet(String productCode);
	/**
	 * 查询项目列表总数
	 * @param projectQueryBean
	 * @return
	 */
	int total(ProjectQueryBean projectQueryBean);


	/**
	 * 通过小区
	 * @param productCode
	 * @return 
	 */
	ProjectVo findProjectByProjectCode(@Param("projectCode")String projectCode);

	/**
	 * 通过项目编号查找项目
	 * @param productCode
	 * @return
	 */
	Project selectByProductCode(String productCode);
	
	/**
	 * 终止项目
	 * @param productCode
	 */
	void stopProject(String productCode);
	/**
	 * 通过合同编号查找项目
	 * @param productCode
	 * @return
	 */
	Project getByContractCode(String contractCode);
	
	
	/**
	 * 获取指定客户下所有项目的执行状态
	 * @param custId
	 */
	List<Map<String,Object>> projectStatus(@Param("custId") String custId);

	
}