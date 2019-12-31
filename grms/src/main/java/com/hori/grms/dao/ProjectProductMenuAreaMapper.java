/*
 * ProjectProductMenuAreaMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import com.hori.grms.model.ProjectProductMenuArea;
import com.hori.grms.model.ProjectProductMenuAreaExample;
import com.hori.grms.queryBean.ProjectActionQueryBean;
import com.hori.grms.vo.ExportMGVo;
import com.hori.grms.vo.ProjectProductMenuAreaVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectProductMenuAreaMapper {
    int countByExample(ProjectProductMenuAreaExample example);

    int deleteByExample(ProjectProductMenuAreaExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectProductMenuArea record);

    int insertSelective(ProjectProductMenuArea record);

    List<ProjectProductMenuArea> selectByExample(ProjectProductMenuAreaExample example);

    ProjectProductMenuArea selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectProductMenuArea record, @Param("example") ProjectProductMenuAreaExample example);

    int updateByExample(@Param("record") ProjectProductMenuArea record, @Param("example") ProjectProductMenuAreaExample example);

    int updateByPrimaryKeySelective(ProjectProductMenuArea record);

    int updateByPrimaryKey(ProjectProductMenuArea record);

	/**
	 * 通过project_product_menu_id列表 删除 
	 * @param project_product_menuIds
	 */
	void deleteByProjectProductMenuIds(@Param("project_product_menuIds") List<String> project_product_menuIds);


    /**
     * 查找媒管部门需要导出的数据
     * @param queryBean
     * @return
     */
	List<ExportMGVo> listMGData(@Param("queryBean") ProjectActionQueryBean queryBean);

	/**
	 * 根据项目清单id查找关联的小区名称，适用于用户运营/媒管/电商<br/>
	 * 关联多个小区，则小区名称用,分隔。<br/>
	 * 如：天朗明居,骏景园,合理正通测试小区,...
	 * @param projectProductId
	 * @return
	 */
	String getAreaNamesByPPID(@Param("projectProductId") String projectProductId);
	/**
	 * 根据项目产品明细得到关联小区信息
	 * @param projectProductMenuId
	 * @return
	 */
	List<ProjectProductMenuAreaVo> getAreaByPPMId(String projectProductMenuId);
	
	/**
	 * 根据vo参数获得小区名称集合
	 * @param projectProductMenuAreaVo
	 * @return
	 */
	List<String> getAreaNamesByPPMAVo(ProjectProductMenuAreaVo projectProductMenuAreaVo);
	
	/**
	 * 根据项目产品明细id 获得所有小区集合
	 * @param projectProductMenuId
	 * @return
	 */
	List<String> getAreaNamesByPPMId(String projectProductMenuId);
	

}