package com.hori.service;

import java.util.List;

import com.hori.model.Area;

/**
 * 区域管理
 */
public interface AreaService extends BaseServiceI{
	List<Area> getAll();
	Area findByCode(Integer code);
}
