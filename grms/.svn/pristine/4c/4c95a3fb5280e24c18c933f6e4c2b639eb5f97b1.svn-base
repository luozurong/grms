package com.hori.grms.service;

import java.util.List;

import com.hori.grms.model.SchemeInfo;

public interface SchemeInfoService {
	
	/**
	 * 分页获取提案列表
	 * @param keyword
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public List<SchemeInfo> getSchemeInfos(String keyword, Integer pageNumber, Integer pageSize);
	
	/**
	 * 获取提案列表总数量
	 * @param keyword
	 * @return
	 */
	public int getSchemeInfoCount(String keyword);
	
	/**
	 * 根据id获取提案
	 * @param id
	 * @return
	 */
	public SchemeInfo getSchemeInfoById(String id);
	
	/**
	 * 判断提案名称是否存在
	 * @param name
	 * @return
	 */
	public boolean isExistName(String name);
	
	/**
	 * 保存提案
	 * @param schemeInfo
	 * @return
	 */
	public int save(SchemeInfo schemeInfo);
	
	/**
	 * 删除/批量删除提案
	 * @param ids
	 * @return
	 */
	public int delete(List<String> ids);
}
