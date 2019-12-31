package com.hori.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.adms.util.ExportexcelWithCommunity;
import com.hori.dao.queryBean.CommunityQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.ProCityAreaTown;
import com.hori.pageModel.Community;
import com.hori.service.CommunityService;
import com.hori.service.ProCityAreaTownService;
import com.opensymphony.xwork2.ModelDriven;


@Action(value = "communityAction",
	   results = {
			     @Result(name ="list",location = "/community/community.jsp"),
			     @Result(name="edit",location = "/community/communityedit.jsp"),
			     @Result(name="view",location = "/community/communityview.jsp")
			     })
public class CommunityAction extends BaseAction implements ModelDriven<Community> {
   
	private static final long serialVersionUID = 1751886949852202084L;
	
	private Community community;
	
	private String pageSize = "";
	
	private String pageNo = "";
	
	private  CommunityQueryBean queryBean;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private ProCityAreaTownService proCityAreaTownService; 
	
	private List<ProCityAreaTown> provinces;
	private List<ProCityAreaTown> citys;
	private List<ProCityAreaTown> areas;
	private List<ProCityAreaTown> towns;
	
	
	public CommunityQueryBean getQueryBean() {
		return queryBean;
	}


	public void setQueryBean(CommunityQueryBean queryBean) {
		this.queryBean = queryBean;
	}


	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getPageNo() {
		return pageNo;
	}


	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}



	public List<ProCityAreaTown> getProvinces() {
		return provinces;
	}


	public void setProvinces(List<ProCityAreaTown> provinces) {
		this.provinces = provinces;
	}


	public List<ProCityAreaTown> getCitys() {
		return citys;
	}


	public void setCitys(List<ProCityAreaTown> citys) {
		this.citys = citys;
	}


	public List<ProCityAreaTown> getAreas() {
		return areas;
	}


	public void setAreas(List<ProCityAreaTown> areas) {
		this.areas = areas;
	}


	public List<ProCityAreaTown> getTowns() {
		return towns;
	}


	public void setTowns(List<ProCityAreaTown> towns) {
		this.towns = towns;
	}


	@Override
	public Community getModel() {
		if(community==null) community= new Community();
		return this.community;
	}
    
	
	public String goCommunityList(){
		return "list";
	}
	
	/**
	 * 初始化省份信息
	 */
	public void initProvince(){
		provinces = proCityAreaTownService.getNoParentId();
		ProCityAreaTown proCityAreaTown =new ProCityAreaTown();
		proCityAreaTown.setId(0);
		proCityAreaTown.setCode("");
		proCityAreaTown.setName("请选择");
		proCityAreaTown.setParentId("");
		provinces.add(0, proCityAreaTown);
		super.writeJson(provinces);
	}
	
	
	/**
	 * 根据父select传入的id查询其下的值
	 * @return
	 */
	public void getChildSelectData(){
		HttpServletRequest request = getRequest();
		String code = request.getParameter("code");
		getResponse().setContentType("text/xml; charset=utf-8");
		citys =proCityAreaTownService.findProCityAreaTownByParentId(code);
		ProCityAreaTown proCityAreaTown =new ProCityAreaTown();
		proCityAreaTown.setId(0);
		proCityAreaTown.setCode("");
		proCityAreaTown.setName("请选择");
		proCityAreaTown.setParentId("");
		citys.add(0, proCityAreaTown);
		writeJson(citys);
	}
	
	/**
	 * 小区列表
	 * @return
	 */
	public void list(){
		int _pageNo = 1;
		int _pageSize = 7;
		if( true == NumberUtils.isNumber(pageNo) ){
			_pageNo = Integer.valueOf(pageNo);
		}
		
		if( true == NumberUtils.isNumber(pageSize) ){
			_pageSize = Integer.valueOf(pageSize);
		}
		community.setPageNumber(_pageNo);
		community.setPageSize(_pageSize);
		community.setCommunityType(1);
		try {
			DataGridPage listAll = this.communityService.datagrid(community);
			//为导出数据做准备
			this.getRequest().getSession().setAttribute("province", community.getProvince());
			this.getRequest().getSession().setAttribute("city", community.getCity());
			this.getRequest().getSession().setAttribute("country", community.getCountry());
			this.getRequest().getSession().setAttribute("peopleKey", community.getPeopleKey());
			this.getRequest().getSession().setAttribute("familyCount", community.getFamilyCount());
			this.getRequest().getSession().setAttribute("terminalKey", community.getTerminalKey());
			this.getRequest().getSession().setAttribute("terminalCount", community.getTerminalCount());
			this.getRequest().getSession().setAttribute("communityName", community.getCommunityName());
			super.writeJson(listAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 编辑小区信息
	 * @return
	 */
	public String communityEdit(){
		com.hori.model.Community community2 = new com.hori.model.Community();
		String id=community.getId();
		community2 =this.communityService.getById(id);
		if(community2!=null){
			ProCityAreaTown   proCityAreaTown=this.proCityAreaTownService.getByCode(community2.getCity());
			if(proCityAreaTown.getName().equals("市辖区")){
				this.getRequest().setAttribute("city", this.proCityAreaTownService.getByCode(proCityAreaTown.getParentId()).getName());
			}else{
				this.getRequest().setAttribute("city", proCityAreaTown.getName());
			}
			
			ProCityAreaTown area = this.proCityAreaTownService.getByCode(community2.getCountry());
			this.getRequest().setAttribute("area", area.getName());
		}
		this.getRequest().setAttribute("community", community2);
		return "edit";
	}
	
	/**
	 * 查看小区信息
	 * @return
	 */
	public String communityView(){
		com.hori.model.Community community2 = new com.hori.model.Community();
		String id=community.getId();
		community2 =this.communityService.getById(id);
		if(community2!=null){
			ProCityAreaTown   proCityAreaTown=this.proCityAreaTownService.getByCode(community2.getCity());
			if(proCityAreaTown.getName().equals("市辖区")){
				this.getRequest().setAttribute("city", this.proCityAreaTownService.getByCode(proCityAreaTown.getParentId()).getName());

			}else{
				this.getRequest().setAttribute("city", proCityAreaTown.getName());
			}
			
			ProCityAreaTown area = this.proCityAreaTownService.getByCode(community2.getCountry());
			this.getRequest().setAttribute("area", area.getName());
		}
		this.getRequest().setAttribute("community", community2);
		return "view";
	}
	
	/**
	 * 保存小区信息
	 */
	public void communitySave(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			com.hori.model.Community community2 = new com.hori.model.Community();
			community2 =this.communityService.getById(community.getId());
			community2.setEnablePromotionActive(Integer.valueOf(community.getEnablePromotionActive().split(",")[0]));
			community2.setBankingDepartmentName(community.getBankingDepartmentName());
			community2.setMallName(community.getMallName());
			community2.setEducationDepartmentName(community.getEducationDepartmentName());
			community2.setTreatmentDepartmentName(community.getTreatmentDepartmentName());
			community2.setActiveLocationDetail(community.getActiveLocationDetail());
			community2.setFamilyCount(Integer.valueOf(community.getFamilyCount()));
			community2.setPeopleCount(Integer.valueOf(community.getPeopleCount()));
			this.communityService.updateCommunity(community2);
			map.put("result", "success");
			map.put("success", "编辑信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			map.put("error", "系统遇到一点麻烦，请稍后重试！");
		}
		writeJson(map);
	}
    
	/**
	 * 删除小区信息
	 */
	public void communityDelete() {
	Map<String, String> map = new HashMap<String, String>();
	try {
		this.communityService.removeById(community.getId());
		String id=community.getId();
		map.put("result", "success");
		map.put("success", "删除信息成功！");
	} catch (Exception e) {
		e.printStackTrace();
		map.put("result", "false");
		map.put("error", "系统遇到一点麻烦，请稍后重试！");
	}
	writeJson(map);
}
     
	/**
	 * 导出小区数据到excel表
	 */
	public void exportToExcel(){
		//为导出数据做准备
       String province=(String) this.getRequest().getSession().getAttribute("province");
       String city=(String)this.getRequest().getSession().getAttribute("city");
       String country=(String)this.getRequest().getSession().getAttribute("country");
       Integer peopleKey=(Integer) this.getRequest().getSession().getAttribute("peopleKey");
       String familyCount=(String)this.getRequest().getSession().getAttribute("familyCount");
       Integer terminalKey=(Integer)this.getRequest().getSession().getAttribute("terminalKey");
       String terminalCount=(String)this.getRequest().getSession().getAttribute("terminalCount");
       String communityName=(String)this.getRequest().getSession().getAttribute("communityName");
		CommunityQueryBean queryBean = new CommunityQueryBean();
		queryBean.setProvince(province);
		queryBean.setCity(city);
		queryBean.setCountry(country);
		queryBean.setPeopleKey(peopleKey);
		queryBean.setFamilyCount(familyCount);
		queryBean.setTerminalKey(terminalKey);
		queryBean.setTerminalCount(terminalCount);
		queryBean.setCommunityName(communityName);
		queryBean.setCommunityType(1);
		List<com.hori.model.Community> communities = this.communityService.getByQueryBean(queryBean);
//		List<Area> countrys = this.areaService.getAll();
//		Map<Integer, String> countryMap = new HashMap<Integer,String>();
//		if(countrys!=null&&countrys.size()>0){
//			for(Area area:countrys){
//				countryMap.put(area.getCode(), area.getName());
//			}
//		}
//		
//		List<City> citys = this.cityService.getAll();
//		Map<Integer, String> cityMap = new HashMap<Integer,String>();
//		if(countrys!=null&&countrys.size()>0){
//			for(City city2:citys){
//				cityMap.put(city2.getCode(), city2.getName());
//			}
//		}
		ExportexcelWithCommunity.generateclsjExcelFor2003("小区信息.xls", communities,
				this.getResponse());
	}
	
	public Community getCommunity() {
		return community;
	}


	public void setCommunity(Community community) {
		this.community = community;
	}
	
	
}
