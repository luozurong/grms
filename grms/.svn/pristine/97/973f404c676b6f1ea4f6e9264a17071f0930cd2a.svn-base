package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.Contract;
import com.hori.grms.model.ContractTemplate;
import com.hori.grms.queryBean.ContractQueryBean;
import com.hori.grms.queryBean.ContractTemplateQueryBean;
import com.hori.grms.vo.ContractVo;

public interface ContractTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContractTemplate record);

    int insertSelective(ContractTemplate record);

    ContractTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContractTemplate record);

    int updateByPrimaryKey(ContractTemplate record);
    
    List<ContractTemplate> selectByQueryBean(@Param("queryBean") ContractTemplateQueryBean queryBean);
    
}