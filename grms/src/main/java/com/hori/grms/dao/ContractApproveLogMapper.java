package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.ContractApproveLog;

public interface ContractApproveLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContractApproveLog record);

    int insertSelective(ContractApproveLog record);

    ContractApproveLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContractApproveLog record);

    int updateByPrimaryKey(ContractApproveLog record);
    
    List<ContractApproveLog> selectByContractCode(@Param("contractCode")String contractCode);
    
    int deleteByContractCode(@Param("contractCode") String contractCode,@Param("status") String status);
    
    //查找出最新日志然后删除
    ContractApproveLog selectApproveLogNew(@Param("contractCode") String contractCode,@Param("status") String status);
}