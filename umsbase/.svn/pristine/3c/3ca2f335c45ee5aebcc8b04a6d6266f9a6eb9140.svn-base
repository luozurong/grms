package com.hori.service;

import java.util.List;
import java.util.Map;

import com.hori.dao.queryBean.CommunityQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Community;
import com.hori.pageModel.DataGrid;

/**
 * 小区的service
 */
public interface CommunityService extends BaseServiceI {

	/*
	 * 查找全部
	 */
	public List<Community> findAll();
	
	/**
	 * 批量更新小区信息（增加/修改）
	 * 
	 * @param communities
	 */
	public void updateCommunitys(List<Community> communities);

	/**
	 * 从对讲获取小区信息 通过日期获取需要同步更新的小区信息
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<Community> getUpdateCommunityByTime(CommunityQueryBean queryBean);

	/**
	 * 从对讲获取小区信息 通过日期获取需要同步增加的小区信息
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<Community> getAddCommunityByTime(CommunityQueryBean queryBean);

	public List<Community> getByQueryBean(CommunityQueryBean queryBean);

	public void saveAll(List<Community> addCommunities);

	public DataGridPage findPageAll(int pageNo, int pageSize);

	public DataGridPage findPage(Community community, int pageNo, int pageSize);

	public DataGridPage datagrid(com.hori.pageModel.Community community);
	
	public Community getById(String id);
	public List<Community> getByLocation(String province, String city, String country);
	
	public List<Map<String,Object>> getAreaTreeByCountry(String country);


	public void updateCommunity(Community community);

	public void removeById(String id);

	public Community getByOrganizationSeq(String organizationSeq);

	public List<Community> getByOrganizationSeqs(List<String> orgList);

	public Community getBySerial(String terminalSerial);

	public void bachSave(List<Community> addCommunities);



}
