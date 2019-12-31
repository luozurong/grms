package com.hori.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.SalesmanServiceDao;
import com.hori.service.SalesmanService;
@Service("SalesmanService")
public class SalesmanServiceImpl implements SalesmanService {
	
	@Autowired
	private SalesmanServiceDao salesmanServiceDao;
	
	@Override
	public List<Map<String, Object>> fetchByName(String name, String account) {
		List<Map<String, Object>> salasmanInfo = salesmanServiceDao.fetchByName(name);
		if (salasmanInfo != null && salasmanInfo.size() > 0) {
			Iterator<Map<String, Object>> iterator = salasmanInfo.iterator();
			while(iterator.hasNext()){
				Map<String, Object> next = iterator.next();
				if (next.get("userAccount").toString().equals(account.trim())) {
					iterator.remove();
				}
			}
		}
		return salasmanInfo;
	}

}
