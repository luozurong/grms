/*
 * FieldExchangeLogMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-22 Created
 */
package com.hori.grms.dao;

import com.hori.grms.model.FieldExchangeLog;
import com.hori.grms.model.FieldExchangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FieldExchangeLogMapper {
    int countByExample(FieldExchangeLogExample example);

    int deleteByExample(FieldExchangeLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(FieldExchangeLog record);

    int insertSelective(FieldExchangeLog record);

    List<FieldExchangeLog> selectByExample(FieldExchangeLogExample example);

    FieldExchangeLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FieldExchangeLog record, @Param("example") FieldExchangeLogExample example);

    int updateByExample(@Param("record") FieldExchangeLog record, @Param("example") FieldExchangeLogExample example);

    int updateByPrimaryKeySelective(FieldExchangeLog record);

    int updateByPrimaryKey(FieldExchangeLog record);
}