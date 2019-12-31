package com.hori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hori.db.HibernateBaseDao;
import com.hori.model.Area;
import com.hori.model.AuthorizeArea;

@Repository
public class AreaAdminDao  extends HibernateBaseDao<Area>{
	
	public List<Area> getAreaBysystemId(String systemId) {
		String hql = "FROM Area where systemId=? order by areaId ";
		return this.find(hql,systemId);
	}
	public List<Area> getAreaByAreaId(String systemId,String areaId) {
		String hql = "FROM Area where systemId=? and  areaId in ("+areaId+")order by areaId ";
		return this.find(hql,systemId);
	}

}
