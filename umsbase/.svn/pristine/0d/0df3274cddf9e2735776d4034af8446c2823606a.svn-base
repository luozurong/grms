package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.AreaDao;
import com.hori.model.Area;
import com.hori.service.AreaService;

/**
 * 区域管理service实现类
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAll() {
		return areaDao.getAll("code", true);
	}
	@Override
	public Area findByCode(Integer code) {
		return areaDao.findUniqueBy("code", code);
	}

}
