package com.hori.service;

import java.util.List;
import java.util.Map;

import com.hori.db.support.Page;
import com.hori.model.Attchment;

public interface AttchmentService  extends BaseServiceI {
	
	/*
	 * 查找全部
	 */
	public List<Attchment> findAll();
	
	/*
	 * 取个数
	 */
	public Long total();
	
	/*
	 * 按条件查询
	 */
	public Page find( Attchment attchment, int pageNo, int pageSize);
	
	public String save( Attchment attchment );
	
	public int update(Attchment attchment);

	public Map<String, String> getByAttchmentInfoIds(List<String> attchmentId);

	public Attchment findByattchmentId(String id);

	public void deleteById(String attchmentId);
}
