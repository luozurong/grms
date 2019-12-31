package com.hori.grms.service;

import com.hori.grms.model.Test;

public interface TestService {
	  int deleteByPrimaryKey(Integer id);

	    int insert(Test record);

	    int insertSelective(Test record);

	    Test selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Test record);

	    int updateByPrimaryKey(Test record);
}
