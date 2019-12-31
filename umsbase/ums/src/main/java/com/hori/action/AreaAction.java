package com.hori.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hori.model.Attchment;
import com.hori.model.Community;
import com.hori.model.ProCityAreaTown;
import com.hori.pageModel.Json;
import com.hori.service.AttchmentService;
import com.hori.service.CommunityService;
import com.hori.service.ProCityAreaTownService;
import com.hori.util.HttpClientUtil;
import com.hori.util.PictureUtil;
import com.hori.utils.RandomUtil;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


/**
 * 用户ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "areaAction")
public class AreaAction extends BaseAction  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(AreaAction.class);
	
	@Autowired
	private ProCityAreaTownService proCityAreaTownService = null;
	
	@Autowired
	private CommunityService communityService = null;
	
	public void getListById(){		
		String id = this.getRequest().getParameter("id");//"0086";
		if(false == StringUtils.isNoneBlank(id)){
			id = "0086";
		}
		
		super.writeJson(proCityAreaTownService.findTreeAreaByParentId(id));
	}
	
	public void getAreaTreeById(){		
		String id = this.getRequest().getParameter("id");
		if(false == StringUtils.isNoneBlank(id)){
			id = "0086";
		}
		
		String level = this.getRequest().getParameter("level");
		
		System.out.println("level:" + level);
		if(true == "3".equals(level)){
			super.writeJson(communityService.getAreaTreeByCountry(id));
		}else{
			super.writeJson(proCityAreaTownService.findTreeAreaByParentId(id));
		}
	}
	
	public void getAreaTreeAll(){		
		String id = this.getRequest().getParameter("id");
		if(false == StringUtils.isNoneBlank(id)){
			id = "0086";
		}
		
		List<Map<String, Object>> list1 = proCityAreaTownService.findTreeAreaByParentId(id);
		List<Map<String, Object>> list2 = null;
		List<Map<String, Object>> list3 = null;
		List<Map<String, Object>> list4 = null;
		if(null != list1){
			for(Iterator<Map<String, Object>> iter1 = list1.iterator() ; iter1.hasNext() ;){
				Map<String, Object> map1 = iter1.next();
				list2 = proCityAreaTownService.findTreeAreaByParentId((String) map1.get("id"));
				if(null == list2){
					continue;
				}
				map1.put("children", list2);
				for(Iterator<Map<String, Object>> iter2 = list2.iterator() ; iter2.hasNext() ;){
					Map<String, Object> map2 = iter2.next();
					list3 = proCityAreaTownService.findTreeAreaByParentId((String) map2.get("id"));
					if(null == list3){
						continue;
					} 
					map2.put("children", list3);
					for(Iterator<Map<String, Object>> iter3 = list3.iterator() ; iter3.hasNext() ;){
						Map<String, Object> map3 = iter3.next();
						list4 = communityService.getAreaTreeByCountry((String) map3.get("id"));
						if(null == list4){
							continue;
						}
						map3.put("children", list4);
					}
				}
			}
			super.writeJson(list1);
		}else{
			super.writeJson(new ArrayList());
		}
		
	}
	
	
	public void getAreaCommunityTree(){		
		String id = this.getRequest().getParameter("id");
		if(false == StringUtils.isNoneBlank(id)){
			id = "0086";
		}
		
		List<Map<String, Object>> listPCAT = proCityAreaTownService.findTreeAreaAll();
		List<Community> listCommunity = communityService.findAll();
		
		
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		//先把地区按id为关键定放到一个hashmap中，方便后面查找
		Map<String, Map<String, Object>> mapPCAT = new HashMap<String, Map<String, Object>>();
		for(Iterator<Map<String, Object>> iter = listPCAT.iterator() ; iter.hasNext() ;){
			Map<String, Object> pcat = iter.next();
			String code = (String) pcat.get("id");
			String parentId = (String) pcat.get("parentId");
			mapPCAT.put(code, pcat);
			//顺便把省级的数据放到结果集里面去
			if(true == id.equals(parentId)){
				listResult.add(pcat);
			}
		}
		
		//生成省份树型级
		for(Iterator<Map<String, Object>> iter = listPCAT.iterator() ; iter.hasNext() ;){
			Map<String, Object> pcat = iter.next();
			String parentId = (String) pcat.get("parentId");
			
			if(false == mapPCAT.containsKey(parentId)){
				continue;
			}
			
			Map<String, Object> mapParent = mapPCAT.get(parentId);
			ArrayList<Map<String, Object>> children = (ArrayList<Map<String, Object>>) mapParent.get("children");
			if(null == children){
				children = new ArrayList<Map<String, Object>>();
				mapParent.put("children", children);
			}
			children.add(pcat);			
		}
		
		
		//把小区信息放到镇/区的子信息中去
		for(Iterator<Community> iter = listCommunity.iterator() ; iter.hasNext() ;){
			Community community = iter.next();
			if(false == mapPCAT.containsKey(community.getCountry())){
				continue;
			}
			
			//小区信息
			Map<String, Object> mapCommunity = new HashMap<String, Object>();
			mapCommunity.put("id", community.getId());
			mapCommunity.put("text", community.getCommunityName());
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("organizationSeq", community.getOrganizationSeq());
			mapCommunity.put("attributes", attributes);
			
			//镇/区信息
			Map<String, Object> mapCountry = (Map<String, Object>) mapPCAT.get(community.getCountry());
			ArrayList<Map<String, Object>> children = null;
			if(false == mapCountry.containsKey("children")){
				children = new ArrayList<Map<String, Object>>();
				mapCountry.put("children", children);
			}else{
				children = (ArrayList<Map<String, Object>>) mapCountry.get("children");
			}
			children.add(mapCommunity);
		}
		
		listResult.sort(new Comparator<Map<String, Object>>(){
			@Override
			public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
				// TODO Auto-generated method stub
				String id0 = (String) arg0.get("id");
				String id1 = (String) arg1.get("id");
				return id0.compareTo(id1);
			}
		});
		
		super.writeJson(listResult);
	}
}










