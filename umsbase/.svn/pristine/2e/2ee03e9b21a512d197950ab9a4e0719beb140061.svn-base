package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.ProvinceDao;
import com.hori.model.Province;
import com.hori.service.ProvinceService;

/**
 * 省管理service实现
 * @author xuht
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;
	
	@Override
	public List<Province> getAll() {
		return provinceDao.getAll();
	}
	@Override
	public Province findByCode(Integer code) {
		return provinceDao.findUniqueBy("code", code);
	}

}
