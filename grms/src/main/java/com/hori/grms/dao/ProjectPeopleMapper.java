/*
 * ProjectPeopleMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import com.hori.grms.model.ProjectPeople;
import com.hori.grms.model.ProjectPeopleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPeopleMapper {
    int countByExample(ProjectPeopleExample example);

    int deleteByExample(ProjectPeopleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectPeople record);

    int insertSelective(ProjectPeople record);

    List<ProjectPeople> selectByExample(ProjectPeopleExample example);

    ProjectPeople selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectPeople record, @Param("example") ProjectPeopleExample example);

    int updateByExample(@Param("record") ProjectPeople record, @Param("example") ProjectPeopleExample example);

    int updateByPrimaryKeySelective(ProjectPeople record);

    int updateByPrimaryKey(ProjectPeople record);
    /**
     * 通过项目code获取项目人员表
     * @param projectId
     * @return
     */
	List<ProjectPeople> findByProjectCode(String projectCode);

	/**
	 * 通过项目code删除项目人员表
	 * @param productCode
	 */
	void deleteByProjectCode(@Param("productCode")String productCode);
}