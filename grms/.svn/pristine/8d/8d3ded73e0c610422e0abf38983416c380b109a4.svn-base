package com.hori.grms.dao;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.DepartmentBusinessArea;
import com.hori.grms.vo.DepartmentBusinessVo;


public interface DepartmentBusinessAreaMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepartmentBusinessArea record);

    int insertSelective(DepartmentBusinessArea record);

    DepartmentBusinessArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepartmentBusinessArea record);

    int updateByPrimaryKey(DepartmentBusinessArea record);
    
    void delDepartmentBusinessArea(@Param("businessTypeId")String businessTypeId);
    
    DepartmentBusinessVo getDepartmentAreaById(@Param("businessTypeId")String businessTypeId);

	DepartmentBusinessArea selectAreaByBusinessTypeId(@Param("businessTypeId")String businessTypeId);
}