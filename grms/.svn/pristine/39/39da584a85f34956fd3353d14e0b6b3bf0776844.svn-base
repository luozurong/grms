package com.hori.grms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.SchemeInfoMapper;
import com.hori.grms.model.SchemeInfo;
import com.hori.grms.service.SchemeInfoService;

/**
 * 結案管理
 * @author wangzhen
 *
 */
@Service
public class SchemeInfoServiceImpl implements SchemeInfoService {
	
	@Autowired
	private SchemeInfoMapper schemeInfoMapper;

	@Override
	public List<SchemeInfo> getSchemeInfos(String keyword, Integer pageNumber, Integer pageSize) {
		if(pageNumber == null) {
			pageNumber = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		if(StringUtils.isNotBlank(keyword)) {
			keyword = "%" + keyword + "%";
		}
		Integer pageIndex = (pageNumber - 1) * pageSize;
		return schemeInfoMapper.getSchemeInfos(keyword, pageIndex, pageSize);
	}

	@Override
	public int getSchemeInfoCount(String keyword) {
		if(StringUtils.isNotBlank(keyword)) {
			keyword = "%" + keyword + "%";
		}
		return schemeInfoMapper.getSchemeInfoCount(keyword);
	}
	
	@Override
	public SchemeInfo getSchemeInfoById(String id) {
		return schemeInfoMapper.getSchemeInfoById(id);
	}
	
	@Override
	public boolean isExistName(String name) {
		String id = schemeInfoMapper.getSchemeInfoByName(name);
		if(StringUtils.isNotBlank(id)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int save(SchemeInfo schemeInfo) {
		return schemeInfoMapper.save(schemeInfo);
	}

	@Override
	public int delete(List<String> ids) {
		int totalCount = 0;
		if(ids != null && !ids.isEmpty()) {
			for(String id : ids) {
				totalCount += schemeInfoMapper.delete(id);
			}
		}
		return totalCount;
	}

}
