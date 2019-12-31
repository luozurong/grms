package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.ParamterDao;
import com.hori.model.Paramter;
import com.hori.service.ParamterService;

/**
 * 用户Service
 * 
 * @author 
 * 
 */
@Service("paramterService") 
public class ParamterServiceImpl extends BaseServiceImpl  implements ParamterService {

	@Autowired
	private ParamterDao paramterDao = null;
	
	@Override
	public List<Paramter> findByType(String type) {
		// TODO Auto-generated method stub
		return paramterDao.findByType(type);
	}
	
	
	
}
