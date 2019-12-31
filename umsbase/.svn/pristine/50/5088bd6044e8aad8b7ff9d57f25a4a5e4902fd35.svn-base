package com.hori.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hori.dao.queryBean.ProCityAreaTownForTreeBean;
import com.hori.model.ProCityAreaTown;
/**
 * 区域管理
 * @author liaowl
 *
 */

public interface ProCityAreaTownService {
	/**
	 * 查找全部
	 */
	public List<ProCityAreaTown> findAll();
	/**
	 * 得到分类树的json格式字符串
	 */
	public String generatTreeNode(List<ProCityAreaTown> list);
	/**
	 * 通过名字查找
	 */
	public List<ProCityAreaTown> findProCityAreaTownByName(String name);
	/**
	 * 通过父类的parentId查找到相关的ProCityAreaTown集合数据
	 * @param parentId
	 * @return
	 */
	
	public List<ProCityAreaTown> findProCityAreaTownByParentId(String parentId);
	
	/**
	 * 查出所有的树列表数据
	 * @return
	 */
	public List<Map<String,Object>> findTreeAreaAll();
	
	/**
	 * 通过code查找到ProCityAreaTown数据
	 * @param code
	 * @return
	 */
	public List<ProCityAreaTown> findProCityAreaTownByCode(String code);
	/**
	 * 判断code是不是父类
	 * @param code
	 * @return
	 */
	public int findIsParentId(String code);
	/**
	 * 通过id删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 添加数据
	 * @param proCityAreaTown
	 */
	
	public void save(ProCityAreaTown proCityAreaTown);
	/**
	 * 更新数据
	 * @param proCityAreaTown
	 */
	public void update(ProCityAreaTown proCityAreaTown);
	/**
	 * 通过id查找相关的ProCityAreaTown数据
	 * @param id
	 * @return
	 */
	public ProCityAreaTown findById(int id);
	/**
	 * 名字查找时候的数据显示
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<ProCityAreaTown> getAreaParentListByName(String parentId) throws Exception;
	/**
	 * 通过codes查找到相关的ProCityAreaTown集合数据
	 * @param codes
	 * @return
	 */
	public List getAreaListByCodes(String codes);
	/**
	 * 通过code查找到相关的ProCityAreaTown集合数据
	 * @param code
	 * @return
	 */
	public List<ProCityAreaTown> getAreaParentCodeListByCodes(String code);
	/**
	 * 通过parentId找到code
	 * @param parentId
	 * @return
	 */
	public String getCodeByParentId(String parentId);
	/**
	 * 打开的树
	 * @return
	 */
	public List<ProCityAreaTown> openSelectTree(List<ProCityAreaTown> proCityAreaTowns);
	/**
	 * 打开的树
	 * @param list
	 * @return
	 */
	public String openTreeNode(ProCityAreaTownForTreeBean proCityAreaTownForTreebean);
	/**
	 * 根据编号获取
	 * @param code
	 * @return
	 */
	ProCityAreaTown getByCode(String code);
	/**
	 * 根据编码获取名称
	 * @param code
	 * @return
	 */
	String getNameByCode(String code);
	
	/**
	 * 查询所有省份provence
	 * @return
	 */
	public List<ProCityAreaTown> getNoParentId();
	/**
	 * 通过名字查找省
	 * @param name
	 * @return
	 */
	public ProCityAreaTown getByName(String name);
	
	
	/**
	 * 通过父类parentId查出所有的树列表数据
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> findTreeAreaByParentId(String parentId);
	
	public Map<String, String> getAll();
}
