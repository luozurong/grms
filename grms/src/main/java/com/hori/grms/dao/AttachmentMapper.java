/*
 * AttachmentMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2018-08-10 Created
 */
package com.hori.grms.dao;

import com.hori.grms.model.Attachment;
import com.hori.grms.model.AttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentMapper {
    int countByExample(AttachmentExample example);

    int deleteByExample(AttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    List<Attachment> selectByExample(AttachmentExample example);

    Attachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

	/**
	 * 根据关联附件id查看相关附件
	 * @param correlationId
	 * @param type 类型
	 * @return
	 */
	List<Attachment> findBycorrelationId(@Param("correlationId") String correlationId,@Param("type") Integer type);

	/**
	 * 根据关联附件id删除相关附件
	 * @param productCode
	 */
	void deleteByCorrelationId(@Param("correlationId")String correlationId);

	/**
	 * 根据项目编号获取所有清单附件
	 * @param projectCode
	 * @return
	 */
	List<Attachment> queryByProjectCode(String projectCode);
	/**
	 * 根据关联附件id结案相关附件
	 * @param correlationId
	 * @param type 类型
	 * @return
	 */
	List<Attachment> findBycorrelationIdForClose(@Param("correlationId")String correlationId, @Param("type")int type);

}