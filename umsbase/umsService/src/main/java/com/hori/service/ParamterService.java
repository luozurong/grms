package com.hori.service;

import java.util.List;

import com.hori.model.Paramter;

public interface ParamterService  extends BaseServiceI {
	
	public List<Paramter> findByType(String type);
}
