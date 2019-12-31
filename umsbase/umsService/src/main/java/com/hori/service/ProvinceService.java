package com.hori.service;

import java.util.List;

import com.hori.model.Province;
/**
 * 省管理
 * @author admin
 */
public interface ProvinceService {
	List<Province> getAll();
	Province findByCode(Integer code);
}
