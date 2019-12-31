package com.hori.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

import com.danga.MemCached.Logger;
import com.hori.dao.ProCityAreaTownDao;
import com.hori.dao.queryBean.ProCityAreaTownForTreeBean;
import com.hori.model.ProCityAreaTown;
import com.hori.service.ProCityAreaTownService;
/**
 * 区域管理
 * @author liaowl
 *
 */
@Service("proCityAreaTownService")
public class ProCityAreaTownServiceImpl implements ProCityAreaTownService {
	@Autowired
	private ProCityAreaTownDao proCityAreaTownDao;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String generatTreeNode(List<ProCityAreaTown> list) {
		// TODO Auto-generated method stub
		//openSelectTree();
		
		List<Map> nodeList=new ArrayList<Map>();
		if(null!=list){
			Map m=null;
			for(ProCityAreaTown d:list){
				m=new HashMap();
				//m.put("id", d.getId());
				m.put("pId", d.getParentId());
				m.put("name", d.getName());
				m.put("id", d.getCode());
				m.put("idd", d.getId());
				//chkDisabled:true 不能选择
				if(proCityAreaTownDao.findIsParentId(d.getCode())>0){//是否为父类，图片为关
					m.put("isParent", true);
				}else{
					m.put("isParent", false);	
				}
				nodeList.add(m);
			}
		}
		return JSONArray.fromObject(nodeList).toString();
	}

	@Override
	public List<ProCityAreaTown> findProCityAreaTownByName(String name) {
		// TODO Auto-generated method stub
		return proCityAreaTownDao.findProCityAreaTownByName(name);
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		proCityAreaTownDao.removeById(id);
	}
	
	@Override
	public void save(ProCityAreaTown proCityAreaTown) {
		// TODO Auto-generated method stub
		proCityAreaTownDao.save(proCityAreaTown);
		
	}

	@Override
	public void update(ProCityAreaTown proCityAreaTown) {
		// TODO Auto-generated method stub
		proCityAreaTownDao.update(proCityAreaTown);
	}
	public List<ProCityAreaTown> findProCityAreaTownByParentId(String parentId){
		return proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
		
	}
	
	public List<ProCityAreaTown> getAreaParentListByName(String name) throws Exception{
		//,,440106,440100,440000,0086
		String codes = this.proCityAreaTownDao.getAreaParentCodeListByName(name);
		//通过codes的集合得到相关的集合
		List list = this.proCityAreaTownDao.getAreaListByCodes(codes);
		list = converToZtreeNodes(list);
		
		return list;
		
	}
	
	 public List<ProCityAreaTown> getAreaParentCodeListByCodes(String code){
		 
		String codes = this.proCityAreaTownDao.getAreaParentCodeListByCodes(code);
		//通过codes的集合得到相关的集合
		List list = this.proCityAreaTownDao.getAreaListByCodes(codes);
		list = converToZtreeNodes(list);
		
		return list;
		
	}
	
	public List getAreaListByCodes(String codes){
		List list = this.proCityAreaTownDao.getAreaListByCodes(codes);
        
		list = converToZtreeNodes(list);
		
		return list;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List converToZtreeNodes(List list) {
		List<Map> nodeList=new ArrayList<Map>();
		if(null!=list){
			Map m=null;
			for(int i=0;i<list.size();i++){
				Object[] d = (Object[])list.get(i);
				m=new HashMap();
				//数组元素顺序select a.id as did,a.code as id ,a.name as name ,a.parentId as parentId
				m.put("idd", d[0]);
				m.put("id", d[1]);
				m.put("name", d[2]);
				m.put("pId", d[3]);
				m.put("open", true);
				//如果是父类就打开
				if(proCityAreaTownDao.findIsParentId(d[1].toString())>0){
					m.put("isParent", true);
				}else{
					m.put("isParent", false);	
				}
				nodeList.add(m);
			}
		}
		return nodeList;
	}
	
	public String getCodeByParentId(String parentId){
		return proCityAreaTownDao.getCodeByParentId(parentId);
	}
	
	
	public List<ProCityAreaTown> findProCityAreaTownByCode(String code){
		return proCityAreaTownDao.findProCityAreaTownByCode(code);
		
	}
	
	public ProCityAreaTown findById(int id){
		return proCityAreaTownDao.getById(id);
		
	}
	
	public int findIsParentId(String code){
		return proCityAreaTownDao.findIsParentId(code);
	}
	
	public List<ProCityAreaTown> openSelectTree(List<ProCityAreaTown> proCityAreaTowns){
		/*List<ProCityAreaTown> proCityAreaTowns = new ArrayList<ProCityAreaTown>();
		ProCityAreaTown pcat = new ProCityAreaTown();
		pcat.setId(28323);
		pcat.setCode("440111");
		pcat.setName("白云区");
		pcat.setParentId("440100");
		proCityAreaTowns.add(pcat);
		
		pcat = new ProCityAreaTown();
		pcat.setId(28309);
		pcat.setCode("440106001");
		pcat.setName("五山街道");
		pcat.setParentId("440106");
		proCityAreaTowns.add(pcat);
		
		pcat = new ProCityAreaTown();
		pcat.setId(31452);
		pcat.setCode("451323");
		pcat.setName("武宣县");
		pcat.setParentId("451300");
		proCityAreaTowns.add(pcat);
		
		pcat = new ProCityAreaTown();
		pcat.setId(17359);
		pcat.setCode("360000");
		pcat.setName("江西省");
		pcat.setParentId("0086");
		proCityAreaTowns.add(pcat);*/
		
		/*pcat = new ProCityAreaTown();
		pcat.setId(47498);
		pcat.setCode("0086");
		pcat.setName("中华人民共和国");
		pcat.setParentId("0000");
		proCityAreaTowns.add(pcat);*/
		
		List retList = new ArrayList();
		for(ProCityAreaTown p:proCityAreaTowns){
			int codeLenght = p.getCode().length();
			List eachList = null;
			switch(codeLenght){//以code的长度为判断
				case 4:
					eachList = getParentListByFour(p.getParentId());
					break;
				case 6:
					eachList = getParentListBySix(p.getParentId());
					break;
				case 9:
					eachList = getParentListByNine(p.getParentId());
					break;
			
			}
			retList.removeAll(eachList);
			retList.addAll(eachList);
		}
		
		return retList;
		
	}
	
	/**当转递code为6位时*/
	private List getParentListBySix(String parentId) {
		
		if(parentId.length()==4){//code为省级parentId为国级4位时
			
			List list = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
			List list1 = getParentListByFour(parentId);
			/*List list1 =  this.proCityAreaTownDao.findProCityAreaTownByCode(parentId);
			list.add(list1);*/
			list.addAll(list1);
			return list;
		}else{//code为市parentId为省级6位时
			String lastTwoStr = parentId.substring(2,6);
			if("0000".equals(lastTwoStr)){
				String parentId2 = parentId.substring(0, 2)+"0000";
				//450000
				List list2 = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId2);
				//450000
				List list3 =  this.proCityAreaTownDao.findProCityAreaTownByCode(parentId2);
				
				//------转化成model
				Object obj=(Object)list3.get(0);
				String parentId3 ="";
				if(obj instanceof ProCityAreaTown){
					parentId3 = ((ProCityAreaTown) obj).getParentId();
				  }
			
				
				List list4 = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId3);
				
				List list5 = getParentListByFour(parentId3);
				
				list2.addAll(list4);
				list2.addAll(list5);
				return list2;
			}else{//code为区parentId为市级6位时
				//450400
				List list1 = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
				String parentId2 = parentId.substring(0, 2)+"0000";
				//查找出parentId为450000的数据
				List list2 = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId2);
				
				//查找出code为450000的数据 省级数据
				List list3 =  this.proCityAreaTownDao.findProCityAreaTownByCode(parentId2);
				
				//------转化成model
				Object obj=(Object)list3.get(0);
				String parentId3 ="";
				if(obj instanceof ProCityAreaTown){
					parentId3 = ((ProCityAreaTown) obj).getParentId();
				  }
				
				//得到全部的省级0086
				List list4 = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId3);
				
				/*Object ob=(Object)list4.get(0);
				String parentId4 ="";
				if(ob instanceof ProCityAreaTown){
					parentId4 = ((ProCityAreaTown) ob).getParentId();
				  }*/
				List list5 = getParentListByFour(parentId3);
				
				list1.addAll(list2);
				
				list1.addAll(list4);
				list1.addAll(list5);
				return list1;
			}
		}
	}
	
	/**当转递code为9位时*/
	private List getParentListByNine(String parentId) {//code为镇parentId为区级6位时
		//450481 通过parentId找出同级的
		List list = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
		//450400
		String parentId2 = parentId.substring(0, 4)+"00";
		List list2 = getParentListBySix(parentId2);
		list.addAll(list2);
		
		return list; 
	}
	
	/**当转递code为4位时*/
	private List getParentListByFour(String parentId){
		
		   if("0000".equals(parentId)){
			   
			   List list = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
			   return list;
		   }else{
			//List list = this.proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
			List list =  this.proCityAreaTownDao.findProCityAreaTownByCode(parentId);
			//list.addAll(list1);
			return list;
		   }
			
	}

	
	public String openTreeNode(ProCityAreaTownForTreeBean proCityAreaTownForTreebean){
		String codes = "";
		List<ProCityAreaTown> list = null;
		String loginCode="";
		if(proCityAreaTownForTreebean!=null){
			codes = proCityAreaTownForTreebean.getCodes();
			list = proCityAreaTownForTreebean.getList();
			loginCode = proCityAreaTownForTreebean.getLoglonCodes();
		}
		String[] codeStr = codes.split(",");
		
		List<Map> nodeList=new ArrayList<Map>();
		if(null!=list){
			Map m=null;
			for(ProCityAreaTown d:list){
				m=new HashMap();
				//m.put("id", d.getId());
				m.put("pId", d.getParentId());
				m.put("name", d.getName());
				m.put("id", d.getCode());
				m.put("idd", d.getId());
				m.put("open", true);
				if(proCityAreaTownDao.findIsParentId(d.getCode())>0){//是否为父类，图片为关
					m.put("isParent", true);
				}else{
					m.put("isParent", false);
				}
				
				if(null!=loginCode&&!loginCode.equals("")){
				    m.put("chkDisabled", true);
				}
				for(int i=0;i<codeStr.length;i++){
					if(d.getCode().equals(codeStr[i])){
						
						m.put("checked",true);
						//m.put("chkDisabled", false);
					}
				}
				
				nodeList.add(m);
			}
		}
		return JSONArray.fromObject(nodeList).toString();
	}

	@Override
	public ProCityAreaTown getByCode(String code) {
		if(StringUtils.isBlank(code)){
			return null;
		}
		List<ProCityAreaTown> list=this.proCityAreaTownDao.findProCityAreaTownByCode(code);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public String getNameByCode(String code) {
		ProCityAreaTown po=getByCode(code);
		if(null!=po){
			return po.getName();
		}
		return null;
	}

	@Override
	public List<ProCityAreaTown> getNoParentId() {
		return proCityAreaTownDao.getNoParentId();
	}

	/*public AddressDataService getAddressDataService() {
		return addressDataService;
	}

	public void setAddressDataService(AddressDataService addressDataService) {
		this.addressDataService = addressDataService;
	}*/

	public ProCityAreaTown getByName(String name){
		return proCityAreaTownDao.getByName(name);
	}

	@Override
	public List<Map<String, Object>> findTreeAreaByParentId(String parentId) {
		// TODO Auto-generated method stub
		return proCityAreaTownDao.findTreeAreaByParentId(parentId);
	}

	@Override
	public List<ProCityAreaTown> findAll() {
		// TODO Auto-generated method stub
		return proCityAreaTownDao.getAll();
	}

	@Override
	public List<Map<String, Object>> findTreeAreaAll() {
		// TODO Auto-generated method stub
		return proCityAreaTownDao.findTreeAreaAll();
	}

	@Override
	public Map<String, String> getAll() {
		return this.proCityAreaTownDao.getAllMap();
	}
}
