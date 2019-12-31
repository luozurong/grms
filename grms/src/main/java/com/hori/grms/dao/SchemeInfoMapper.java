package com.hori.grms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hori.grms.model.SchemeInfo;

public interface SchemeInfoMapper {
	
	/**
	 * 分页获取提案列表
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<SchemeInfo> getSchemeInfos(@Param("keyword")String keyword, 
									@Param("pageIndex")Integer pageIndex,
									@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取提案列表总数量
	 * @param keyword
	 * @return
	 */
	int getSchemeInfoCount(@Param("keyword")String keyword);
	
	/**
	 * 根据提案名称获取提案id
	 * @param name
	 * @return
	 */
	String getSchemeInfoByName(@Param("name")String name);
	
	/**
	 * 根据id获取提案
	 * @param id
	 * @return
	 */
	SchemeInfo getSchemeInfoById(@Param("id")String id);
	
	/**
	 * 保存提案
	 */
	int save(SchemeInfo schemeInfo);
	
	/**
	 * 删除提案
	 * @param id
	 * @return
	 */
	int delete(String id);
	
}
