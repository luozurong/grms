package com.hori.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.AreaAdminDao;
import com.hori.dao.AreaAuthorizeDao;
import com.hori.dao.ProCityAreaTownDao;
import com.hori.dao.ProvinceDao;
import com.hori.dao.UserDao;
import com.hori.dao.queryBean.AreaQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.Area;
import com.hori.model.AuthorizeArea;
import com.hori.model.ProCityAreaTown;
import com.hori.model.Province;
import com.hori.model.User;
import com.hori.service.AreaManagementService;
import com.hori.vo.AuthorizeAreaVo;
import com.hori.vo.EasyUiTreeVo;
import com.jlit.vdcs.webservice.IAuthorizeAreaService;
@Service("areaManagementServiceImpl")
public class AreaManagementServiceImpl extends BaseServiceImpl implements AreaManagementService{
	@Autowired
	private ProCityAreaTownDao proCityAreaTownDao;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private AreaAuthorizeDao areaAuthorizeDao;
	@Autowired
	private UserDao userDao;
	@Resource
	private IAuthorizeAreaService iAuthorizeAreaService;
	@Autowired
	private AreaAdminDao areaAdminDao;

	/**
	 * 更改用户类型
	 * 
	 * @return
	 */
	public void changeUserType(String userId,String userType){
		userDao.updateUserType(userId, userType);
	}
	/**
	 * 人员责任范围分页查询
	 * 
	 * @return
	 */
	public DataGridPage findUserByParam(AreaQueryBean queryBean,String userType,String userAccount,String departId){
		DataGridPage dataGridPage=new DataGridPage();
		String userAccountIds="";

		if(userType.equals("0")){
			dataGridPage=areaAuthorizeDao.findUserPage(queryBean,userType,userAccountIds);

		}else{
			//查找出当前帐号的下级帐号
			List<Map<String, String>>  userAccountList=userDao.getUserAccountNext(departId);
			for(int i=0;i<userAccountList.size();i++){
				    if(i==0){
				    	userAccountIds="'"+userAccountList.get(i).get("userAccount")+"'";
				    }else{
						userAccountIds=userAccountIds+",'"+userAccountList.get(i).get("userAccount")+"'";
				    }
			}
			if(!userAccountIds.equals("")){
				dataGridPage=areaAuthorizeDao.findUserPage(queryBean,userType,userAccountIds);
			}

		}
		return dataGridPage;
	}
	
	/**
	 * 处理省份
	 * 
	 * @return
	 */
	public List<EasyUiTreeVo> dealProvince() {
		
		List<Province> areaList=provinceDao.getProvinceAll();
	    List<EasyUiTreeVo> menuList = new ArrayList<EasyUiTreeVo>();
		  // 查看结果
	  
	    for(int i=0;i<areaList.size();i++){
	    	EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    	easyUiTreeVo.setId(String.valueOf(areaList.get(i).getCode()));
	    	easyUiTreeVo.setText(areaList.get(i).getName());
	    	easyUiTreeVo.setState("closed");
	    	menuList.add(easyUiTreeVo);
	    }
	    
		return menuList;
	}
	/**
	 * 处理市区
	 * 
	 * @return
	 */
	public List<EasyUiTreeVo> dealArea(String parentId,String layer) {
		
		List<ProCityAreaTown> areaList=proCityAreaTownDao.findProCityAreaTownByParentId(parentId);
		
	    List<EasyUiTreeVo> menuList = new ArrayList<EasyUiTreeVo>();
		  // 查看结果
	    for(int i=0;i<areaList.size();i++){
	    	EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    	easyUiTreeVo.setId(String.valueOf(areaList.get(i).getCode()));
	    	easyUiTreeVo.setText(areaList.get(i).getName());
	    	easyUiTreeVo.setParentId(parentId);
	    	if(layer.equals("2")){
		    	easyUiTreeVo.setState("open");

	    	}else {
		    	easyUiTreeVo.setState("closed");

	    	}
	    	menuList.add(easyUiTreeVo);
	    }
	   
		return menuList;
	}
	/**
	 * 加载右边框信息(非管理员)
	 * 
	 * @return
	 */
	public  Map<String,Object> dealAuthorArea(String userAccount,String systemId,String userId,String status) {
		User user=userDao.getUserByAccount(userAccount);
		Map<String,Object> map=new HashMap<String,Object>();
		//登录用户的权限
		List<AuthorizeArea> authorizeAreaList=areaAuthorizeDao.getAreaAuthorizeById(user.getUserId(), systemId);
		List<AuthorizeArea> authorizeAreaListMy=new ArrayList<AuthorizeArea>();
		if(!status.equals("add")){
			//当前记录修改的初始化
			authorizeAreaListMy=areaAuthorizeDao.getAreaAuthorizeById(userId, systemId);
			map.put("authorizeArea", authorizeAreaListMy);
		}
	    List<EasyUiTreeVo> menuPro = new ArrayList<EasyUiTreeVo>();
	    List<EasyUiTreeVo> menuCity = new ArrayList<EasyUiTreeVo>();
	    List<EasyUiTreeVo> menuArea = new ArrayList<EasyUiTreeVo>();

	
	    for(int i=0;i<authorizeAreaList.size();i++){
	    	if(checkRe(menuPro,authorizeAreaList.get(i).getProvinceId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getProvinceId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getProvinceName());
	    	    List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
	    	    easyUiTreeVo.setState("closed");
	    	    easyUiTreeVo.setChildren(children);
	    		menuPro.add(easyUiTreeVo);
	    	}
	     	if(checkRe(menuCity,authorizeAreaList.get(i).getCityId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getCityId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getCityName());
	    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getProvinceId());
	    		 List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
		    	 easyUiTreeVo.setState("closed");
		    	 easyUiTreeVo.setChildren(children);
	    		menuCity.add(easyUiTreeVo);
	    	}
	     	if(checkRe(menuArea,authorizeAreaList.get(i).getAreaId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getAreaId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getAreaName());
	    	    easyUiTreeVo.setState("open");
	    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getCityId());
	    		menuArea.add(easyUiTreeVo);
	    	}
	    }
	    //初始化当前记录打勾
	    if(!status.equals("add")){
			//当前记录修改的初始化
			for(int i=0;i<authorizeAreaListMy.size();i++){
				for(int j=0;j<menuArea.size();j++){
					if(authorizeAreaListMy.get(i).getAreaId().equals(menuArea.get(j).getId())){
						menuArea.get(j).setChecked(true);
						break;
					}
				}
			} 
		}
	    //组装成tree
	    for(int i=0;i<menuArea.size();i++){
	    	for(int j=0;j<menuCity.size();j++){
	    		if(menuArea.get(i).getParentId().equals(menuCity.get(j).getId())){
	    			menuCity.get(j).getChildren().add(menuArea.get(i));
	    			break;
	    		}
	    	}
	    }
	    //组装成tree
	    for(int i=0;i<menuCity.size();i++){
	    	for(int j=0;j<menuPro.size();j++){
	    		if(menuCity.get(i).getParentId().equals(menuPro.get(j).getId())){
	    			menuPro.get(j).getChildren().add(menuCity.get(i));
	    			break;
	    		}
	    	}
	    }
	    
		map.put("menuPro", menuPro);

		return map;
	}

	/**
	 * 加载右边框信息(管理员)
	 * 
	 * @return
	 */
	public  Map<String,Object> dealAuthorAreaAdmin(String userAccount,String systemId,String userId,String status) {
		User user=userDao.getUserByAccount(userAccount);
		Map<String,Object> map=new HashMap<String,Object>();
		//登录用户的权限
		List<Area> authorizeAreaList=areaAdminDao.getAreaBysystemId(systemId);
		List<AuthorizeArea> authorizeAreaListMy=new ArrayList<AuthorizeArea>();
		if(!status.equals("add")){
			//当前记录修改的初始化
			authorizeAreaListMy=areaAuthorizeDao.getAreaAuthorizeById(userId, systemId);
			map.put("authorizeArea", authorizeAreaListMy);
		}else{
			map.put("authorizeArea", authorizeAreaListMy);
		}
	    List<EasyUiTreeVo> menuPro = new ArrayList<EasyUiTreeVo>();
	    List<EasyUiTreeVo> menuCity = new ArrayList<EasyUiTreeVo>();
	    List<EasyUiTreeVo> menuArea = new ArrayList<EasyUiTreeVo>();

	
	    for(int i=0;i<authorizeAreaList.size();i++){
	    	if(checkRe(menuPro,authorizeAreaList.get(i).getProvinceId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getProvinceId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getProvinceName());
	    	    List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
	    	    easyUiTreeVo.setState("closed");
	    	    easyUiTreeVo.setChildren(children);
	    		menuPro.add(easyUiTreeVo);
	    	}
	     	if(checkRe(menuCity,authorizeAreaList.get(i).getCityId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getCityId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getCityName());
	    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getProvinceId());
	    		 List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
		    	 easyUiTreeVo.setState("closed");
		    	 easyUiTreeVo.setChildren(children);
	    		menuCity.add(easyUiTreeVo);
	    	}
	     	if(checkRe(menuArea,authorizeAreaList.get(i).getAreaId())){
	    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
	    		easyUiTreeVo.setId(authorizeAreaList.get(i).getAreaId());
	    		easyUiTreeVo.setText(authorizeAreaList.get(i).getAreaName());
	    	    easyUiTreeVo.setState("open");
	    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getCityId());
	    		menuArea.add(easyUiTreeVo);
	    	}
	    }
	    //初始化当前记录打勾
	    if(!status.equals("add")){
			//当前记录修改的初始化
			for(int i=0;i<authorizeAreaListMy.size();i++){
				for(int j=0;j<menuArea.size();j++){
					if(authorizeAreaListMy.get(i).getAreaId().equals(menuArea.get(j).getId())){
						menuArea.get(j).setChecked(true);
						break;
					}
				}
			} 
		}
	    //组装成tree
	    for(int i=0;i<menuArea.size();i++){
	    	for(int j=0;j<menuCity.size();j++){
	    		if(menuArea.get(i).getParentId().equals(menuCity.get(j).getId())){
	    			menuCity.get(j).getChildren().add(menuArea.get(i));
	    			break;
	    		}
	    	}
	    }
	    //组装成tree
	    for(int i=0;i<menuCity.size();i++){
	    	for(int j=0;j<menuPro.size();j++){
	    		if(menuCity.get(i).getParentId().equals(menuPro.get(j).getId())){
	    			menuPro.get(j).getChildren().add(menuCity.get(i));
	    			break;
	    		}
	    	}
	    }
	    
		map.put("menuPro", menuPro);

		return map;
	}
	/**
	 * 检查是否存在重复
	 * 
	 * @return
	 */
	public static boolean checkRe( List<EasyUiTreeVo> easyUiList,String code){
		for(int i=0;i<easyUiList.size();i++){
			if(easyUiList.get(i).getId().equals(code)){
				return false;
			}
		}
		return true;
	}
	/**
	 * 加载省市的的区
	 * 
	 * @return
	 */
	public List<AuthorizeArea> initUserAreaByCity(String provinceId,String provinceName,String cityId,String cityName,String layer){
		List<AuthorizeArea> authorizeAreaList=new ArrayList<AuthorizeArea>();
		if(layer.equals("1")){
			//找到省下的所有市
			List<ProCityAreaTown> areaListCity=proCityAreaTownDao.findProCityAreaTownByParentId(provinceId);
			for(int i=0;i<areaListCity.size();i++){
				//找到所有区
				List<ProCityAreaTown> areaList=proCityAreaTownDao.findProCityAreaTownByParentId(areaListCity.get(i).getCode());
				for(int j=0;j<areaList.size();j++){
					AuthorizeArea authorizeArea=new AuthorizeArea();
					authorizeArea.setProvinceId(provinceId);
					authorizeArea.setProvinceName(provinceName);
					authorizeArea.setCityId(areaListCity.get(i).getCode());
					authorizeArea.setCityName(areaListCity.get(i).getName());
					authorizeArea.setAreaId(areaList.get(j).getCode());
					authorizeArea.setAreaName(areaList.get(j).getName());
					authorizeAreaList.add(authorizeArea);
				}

			}
		}else if(layer.equals("2")){
			//找到所有区
			List<ProCityAreaTown> areaList=proCityAreaTownDao.findProCityAreaTownByParentId(cityId);
			for(int j=0;j<areaList.size();j++){
				AuthorizeArea authorizeArea=new AuthorizeArea();
				authorizeArea.setProvinceId(provinceId);
				authorizeArea.setProvinceName(provinceName);
				authorizeArea.setCityId(cityId);
				authorizeArea.setCityName(cityName);
				authorizeArea.setAreaId(areaList.get(j).getCode());
				authorizeArea.setAreaName(areaList.get(j).getName());
				authorizeAreaList.add(authorizeArea);
			}
		}
		
		return authorizeAreaList;
	}
	/**
	 * 增加责任范围
	 * 
	 * @return
	 */
	public void addUserAutoroizeArea(String ids,String areaId,String systemId){
		String[] idsArray= ids.split("#");
		//比较
		String[] areaIdArray=areaId.split("#");
		String areaids="";
		for(int i=0;i<areaIdArray.length;i++){
			if(i==0){
				areaids="'"+areaIdArray[i]+"'";
			}else{
				areaids=areaids+",'"+areaIdArray[i]+"'";
			}
		}
		List<Area> dataList=areaAdminDao.getAreaByAreaId(systemId, areaids);
		for(int i=0;i<idsArray.length;i++){
			List<Area> addList=new ArrayList<Area>();
			List<AuthorizeArea> delList=new ArrayList<AuthorizeArea>();
		
			List<AuthorizeArea> authorizeAreaList=areaAuthorizeDao.getAreaAuthorizeById(idsArray[i], systemId);
            for(int k=0;k<dataList.size();k++){
            	int temp=0;
            	for(int l=0;l<authorizeAreaList.size();l++){
            		if(dataList.get(k).getAreaId().equals(authorizeAreaList.get(l).getAreaId())){
            			temp=1;
            		}
            	}
            	if(temp==0){
    				addList.add(dataList.get(k));
            	}
            }
            for(int k=0;k<authorizeAreaList.size();k++){
            	int temp=0;
            	for(int l=0;l<dataList.size();l++){
            		if(dataList.get(l).getAreaId().equals(authorizeAreaList.get(k).getAreaId())){
            			temp=1;
            		}
            	}
            	if(temp==0){
    				delList.add(authorizeAreaList.get(k));
            	}
            }
            
            List<String> addAreaId=new ArrayList<String>();
            for(int j=0;j<addList.size();j++){
            	AuthorizeArea p1 =new AuthorizeArea();
            	
            	p1.setAreaId(addList.get(j).getAreaId());
            	p1.setAreaName(addList.get(j).getAreaName());
            	p1.setCityId(addList.get(j).getCityId());
            	p1.setCityName(addList.get(j).getCityName());
            	p1.setProvinceId(addList.get(j).getProvinceId());
            	p1.setProvinceName(addList.get(j).getProvinceName());
            	p1.setUserId(idsArray[i]);
            	p1.setSystemId(systemId);
            	p1.setCreateTime(new Date());
            	p1.setModifiedTime(new Date());
    			areaAuthorizeDao.save(p1);
    			addAreaId.add(addList.get(j).getAreaId());
    		}
            List<String> delAreaId=new ArrayList<String>();
            String delId="";
        	for(int l=0;l<delList.size();l++){
        		if(l==0){
        			delId="'"+delList.get(l).getAreaId()+"'";
    			}else{
    				delId=delId+",'"+delList.get(l).getAreaId()+"'";
    			}
        		delAreaId.add(delList.get(l).getAreaId());
    		}
        	if(StringUtils.isNotBlank(delId)){
        		areaAuthorizeDao.deletAreaAuthorizeById(idsArray[i], systemId,delId);
        	}


        	dealAuthorArea(idsArray[i],delAreaId,addAreaId);
		}
      		
	}
	/**
	 * 把责任区域范围修改同步到uoms
	 * 
	 * @return
	 */
	private void dealAuthorArea(String userId,List<String> delAreaId, List<String> addAreaId){
		iAuthorizeAreaService.updateUserAuthorizeAreas(userId, addAreaId, delAreaId);
	}
	
	//加载所有当前用户区域
	public List<AuthorizeArea> initUserAreaById(String userId,String systemId){
		List<AuthorizeArea> authorizeAreaList=areaAuthorizeDao.getAreaAuthorizeById(userId, systemId);
		return authorizeAreaList;
	}
	//左边窗口初始化加载
	public Map<String,Object>  initUserAreaByIdLeft(String userId,String systemId){
		 List<AuthorizeArea> authorizeAreaList=areaAuthorizeDao.getAreaAuthorizeById(userId, systemId);
	     List<EasyUiTreeVo> menuPro = new ArrayList<EasyUiTreeVo>();
		 List<EasyUiTreeVo> menuCity = new ArrayList<EasyUiTreeVo>();
		 List<EasyUiTreeVo> menuArea = new ArrayList<EasyUiTreeVo>();
		 Map<String,Object> map=new HashMap<String,Object>();
		map.put("authorizeArea", authorizeAreaList);

		    for(int i=0;i<authorizeAreaList.size();i++){
		    	if(checkRe(menuPro,authorizeAreaList.get(i).getProvinceId())){
		    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
		    		easyUiTreeVo.setId(authorizeAreaList.get(i).getProvinceId());
		    		easyUiTreeVo.setText(authorizeAreaList.get(i).getProvinceName());
		    	    List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
		    	    easyUiTreeVo.setChildren(children);
		    		menuPro.add(easyUiTreeVo);
		    	}
		     	if(checkRe(menuCity,authorizeAreaList.get(i).getCityId())){
		    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
		    		easyUiTreeVo.setId(authorizeAreaList.get(i).getCityId());
		    		easyUiTreeVo.setText(authorizeAreaList.get(i).getCityName());
		    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getProvinceId());
		    		 List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
			    	 easyUiTreeVo.setChildren(children);
			    	 menuCity.add(easyUiTreeVo);
		    	}
		    	if(checkRe(menuArea,authorizeAreaList.get(i).getAreaId())){
		    		EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
		    		easyUiTreeVo.setId(authorizeAreaList.get(i).getAreaId());
		    		easyUiTreeVo.setText(authorizeAreaList.get(i).getAreaName());
		    		easyUiTreeVo.setParentId(authorizeAreaList.get(i).getCityId());
		    		menuArea.add(easyUiTreeVo);
		    	}
		    }
		    //找出需要加载的市
		    for(int i=0;i<menuPro.size();i++){
		    	//找出每个省的所有市
				List<ProCityAreaTown> areaListCity=proCityAreaTownDao.findProCityAreaTownByParentId(menuPro.get(i).getId());
				for(int j=0;j<areaListCity.size();j++){
					EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
		    		easyUiTreeVo.setId(areaListCity.get(j).getCode());
		    		easyUiTreeVo.setText(areaListCity.get(j).getName());
		    		easyUiTreeVo.setParentId(menuPro.get(i).getId());
			    	easyUiTreeVo.setState("closed");
		    		List<EasyUiTreeVo> children = new ArrayList<EasyUiTreeVo>();
					easyUiTreeVo.setChildren(children);
				    for(int k=0;k<menuCity.size();k++){
				    	if(areaListCity.get(j).getCode().equals(menuCity.get(k).getId())){
							List<ProCityAreaTown> areaCity=proCityAreaTownDao.findProCityAreaTownByParentId(menuCity.get(k).getId());
							for(int l=0;l<areaCity.size();l++){
								EasyUiTreeVo easyUiAreaVo=new EasyUiTreeVo();
								easyUiAreaVo.setId(areaCity.get(l).getCode());
								easyUiAreaVo.setText(areaCity.get(l).getName());
								easyUiAreaVo.setParentId(menuCity.get(k).getId());
						    	easyUiTreeVo.setState("open");
                                
                                Iterator<EasyUiTreeVo> it = menuArea.iterator();
                                while(it.hasNext()){
                                	EasyUiTreeVo x = it.next();
                                	if(areaCity.get(l).getCode().equals(x.getId())){
                                		easyUiAreaVo.setChecked(true);
                                		it.remove();
                                		break;
                                	}
                                }
								easyUiTreeVo.getChildren().add(easyUiAreaVo); 
							}
				    	}
				    	
				    }
				   
		    		menuPro.get(i).getChildren().add(easyUiTreeVo);

				}
	
			
		    	
		    }
		    map.put("menuPro", menuPro);
		return map;
	}
/*	public void deleUserAreaById(String userId,String systemId){
		areaAuthorizeDao.deletAreaAuthorizeById(userId, systemId);
	}
	*/
	//转换成easyUi
	private List<EasyUiTreeVo> transEasyUiVo(List<ProCityAreaTown> areaList){
		List<EasyUiTreeVo> easyUiTreeVoList=new ArrayList<EasyUiTreeVo>();
		for(int i=0;i<areaList.size();i++){
			EasyUiTreeVo easyUiTreeVo=new EasyUiTreeVo();
			easyUiTreeVo.setId(areaList.get(i).getCode());
			easyUiTreeVo.setParentId(areaList.get(i).getParentId());
			easyUiTreeVo.setText(areaList.get(i).getName());
			easyUiTreeVoList.add(easyUiTreeVo);
		}
		return easyUiTreeVoList;
		
		
	}
	
	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private List<EasyUiTreeVo> getChild(String id, List<EasyUiTreeVo> rootMenu) {
	    // 子菜单
	    List<EasyUiTreeVo> childList = new ArrayList<>();
	    for (EasyUiTreeVo menu : rootMenu) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (StringUtils.isNotBlank(menu.getParentId())) {
	            if (menu.getParentId().equals(id)) {
	                childList.add(menu);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (EasyUiTreeVo menu : childList) {//
	            // 递归
	            menu.setChildren(getChild(menu.getId(), rootMenu));
	        
	    } // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}


	@Override
	public List<AuthorizeAreaVo> getProCityAreaTownByUserAccount(String account) {
		// TODO Auto-generated method stub
		return areaAuthorizeDao.getProCityAreaTownByUserAccount(account);
	}

}
