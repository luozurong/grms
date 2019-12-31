package com.hori.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.CommunityDao;
import com.hori.dao.queryBean.CommunityQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Community;
import com.hori.service.CommunityService;

@Service("communityService")
public class CommunityServiceImpl extends BaseServiceImpl implements CommunityService {
    @Autowired
	private CommunityDao communityDao;
	@Override
	public void updateCommunitys(List<Community> communities) {
		this.communityDao.bachUpdate(communities);
	}


	@Override
	public List<Community> getUpdateCommunityByTime(CommunityQueryBean queryBean) {
		return this.communityDao.getUpdateCommunityByTime(queryBean);
	}

	@Override
	public List<Community> getAddCommunityByTime(CommunityQueryBean queryBean) {
		return this.communityDao.getAddCommunityByTime(queryBean);
	}


	@Override
	public List<Community> getByQueryBean(CommunityQueryBean queryBean) {
		return communityDao.getByQueryBean(queryBean);
	}

	/*@Transactional(propagation = Propagation.SUPPORTS)*/
	@Override
	public void saveAll(List<Community> addCommunities) {
		 communityDao.saveAll(addCommunities);
	}


	@Override
	public DataGridPage datagrid(com.hori.pageModel.Community community) {
		DataGridPage j = new DataGridPage();
		j = this.find(community);
		return j;
	}


	private Long total(com.hori.pageModel.Community community) {
		return this.communityDao.total(community);
	}


	private DataGridPage find(com.hori.pageModel.Community community) {
		return this.communityDao.findAll(community);
	}


	@Override
	public DataGridPage findPageAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return communityDao.findPage(null , pageNo, pageSize);
	}


	@Override
	public DataGridPage findPage(Community community, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return communityDao.findPage(community, pageNo, pageSize);
	}



	@Override
	public List<Community> getByLocation(String province, String city, String country) {
		// TODO Auto-generated method stub
		return communityDao.getByLocation(province, city, country);
	}


	@Override
	public List<Map<String, Object>> getAreaTreeByCountry(String country) {
		// TODO Auto-generated method stub
		return communityDao.getAreaTreeByCountry(country);
	}



	@Override
	public Community getById(String id) {
		return this.communityDao.getById(id);
	}


	@Override
	public void updateCommunity(Community community) {
		this.communityDao.update(community);
	}


	@Override
	public void removeById(String id) {
		this.communityDao.removeById(id);
	}


	@Override
	public Community getByOrganizationSeq(String organizationSeq) {
		
		return communityDao.getByOrganizationSeq(organizationSeq);
	}

    /**
     * 通过小区结构编号集合获取一组小区数据
     */
	@Override
	public List<Community> getByOrganizationSeqs(List<String> orgList) {
		return communityDao.getByOrganizationSeqs(orgList);
	}


	@Override
	public List<Community> findAll() {
		// TODO Auto-generated method stub
		return communityDao.getAll();
	}


	@Override
	public Community getBySerial(String terminalSerial) {
		return this.communityDao.getBySerial(terminalSerial);
	}


	@Override
	public void bachSave(List<Community> addCommunities) {
		this.communityDao.bachSave(addCommunities);
	}




}
