package com.hori.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.adms.util.ExportexcelWithTerminal;
import com.hori.db.support.DataGridPage;
import com.hori.model.Terminal;
import com.hori.pageModel.TerminalPage;
import com.hori.pageModel.TreeNode;
import com.hori.service.ProCityAreaTownService;
import com.hori.service.TerminalService;
import com.hori.util.enums.OpenfireResultType;
import com.hori.vo.ProvinceVo;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

@Action(value = "terminalAction", results = { 
		@Result(name = "parameterstrategy", location = "/terminal/parameterstrategy.jsp"),
		@Result(name = "parameterstrategyAddPage", location = "/terminal/addstrategy.jsp"), 
		@Result(name = "index", location = "/index.jsp",type="redirect"), 
		@Result(name ="terminalList",location = "/terminal/groupmanagement.jsp"),
		@Result(name ="terminal_preview",location = "/system/terminal_preview.jsp")
		})
public class TerminalAction extends BaseAction implements ModelDriven<TerminalPage>{
   
    private String pageSize = "";
	
	private String pageNo = "";
	/**
	 * 预览终端的类型 1：表示对讲同步的终端，2：广告系统添加的预览终端
	 */
	private Integer terminalType = 2;
	private TerminalPage terminalPage = new TerminalPage();
	
	private List<ProvinceVo> provinceVo;
	
	@Override
	public TerminalPage getModel() {
		return this.terminalPage;
	}
     
	@Autowired
	private TerminalService terminalService;
	
	@Autowired
	private ProCityAreaTownService proCityAreaTownService;
	
	/**
	 * 到终端机参数策略列表页面
	 * @return
	 */
	public String goParameterstrategyList(){
		
		return "parameterstrategy";
	}
	
	
	/**
	 * 到终端机参数策略新增页面
	 * @return
	 */
	public String goParameterstrategyAdd(){
		
		return "parameterstrategyAddPage";	
	}
	
	public String goTerminal(){
		return "terminalList";
	}
	
	
	/**
	 * 终端机列表
	 */
	public void terminalList(){
	try {
		HttpServletRequest request = this.getRequest();
		int _pageNo =1;
		int _pageSize = 10;
		if( true == NumberUtils.isNumber(pageNo) ){
			_pageNo = Integer.valueOf(pageNo);
		}
		
		if( true == NumberUtils.isNumber(pageSize) ){
			_pageSize = Integer.valueOf(pageSize);
		}
		terminalPage.setPageNumber(_pageNo);
		terminalPage.setPageSize(_pageSize);
		DataGridPage list  = new DataGridPage();
		if(StringUtils.isNotBlank(terminalPage.getOrganizationSeq())){
			request.getSession().setAttribute("terminalOrg", terminalPage.getOrganizationSeq());
			request.getSession().setAttribute("fuzzyKey", terminalPage.getFuzzyKey());
			list = this.terminalService.datagrid(terminalPage);
		}
//		DataGridPage list = this.terminalService.datagrid(terminalPage);
		super.writeJson(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	
	/**
	 * 导出终端数据到excel表
	 */
	public void exportToExcel(){
		//为导出数据做准备
//       String terminalOrg=(String) this.getRequest().getSession().getAttribute("terminalOrg");
//       String fuzzyKey=(String)this.getRequest().getSession().getAttribute("fuzzyKey");
//       terminalPage.setOrganizationSeq(terminalOrg);
//       terminalPage.setFuzzyKey(fuzzyKey);
	   List<TerminalPage> result = this.terminalService.getByInfo(terminalPage);
	   Map<String, String> pcatownMap = this.proCityAreaTownService.getAll();
	   ExportexcelWithTerminal.generateclsjExcelFor2003("终端信息表.xls", result, this.getResponse(),pcatownMap);
	}
	
	
	
	public void updateTerminalName(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String data = this.getRequest().getParameter("jsonData");
			data=data.replace("“", "\"");
			JSONArray jsonArray = JSONArray.fromObject(data);  //包含终端id跟终端名称的JSON对象数组
			this.terminalService.updateByJson(jsonArray);
			map.put("result", "success");
			map.put("success", "编辑名称成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			map.put("error", "系统遇到一点麻烦，请稍后重试！");
		}
		writeJson(map);
	}
	
	/**
	 * 小区树形结构初始化
	 */
	public void initTress(){
		List<TreeNode> rootNode = this.terminalService.getProvinsTerminal();
		List<TreeNode> cityNode = this.terminalService.getCityTerminal();
		List<TreeNode> countryNode = this.terminalService.getFinalNode();
		List<TreeNode> communityNode = this.terminalService.getCommunityNodeTerminal();
		if(communityNode!=null&&communityNode.size()>0){
			for(TreeNode treeNode:communityNode){
				String text = "<span class='tree-title' onclick='search("+treeNode.getId()+")'>"+treeNode.getText()+"</span>";
				treeNode.setText(text);
			}
		}
		List<TreeNode> treeNodes = fillTree(rootNode,cityNode,countryNode,communityNode);
		writeJson(treeNodes);
	}
	
	/**
	 * 查找小区在树的位置
	 * @return
	 */
	public  void searchTree() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<TreeNode> communityNode = this.terminalService.getCommunityNodeTerminal();
			String searchKey = this.getRequest().getParameter("communityName");   //设置哪个小区的节点默认展开
			String opendId = "未搜索到小区!";
			if(StringUtils.isNotBlank(searchKey)){
				for(TreeNode treeNode:communityNode){
					if(treeNode.getText().indexOf(searchKey)!=-1){
						if(opendId.equals("未搜索到小区!")){
							opendId = treeNode.getId();
						}else{ 
							opendId =opendId+","+treeNode.getId();
						}
					}
				}
				map.put("result", "success");
				map.put("opendId", opendId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			map.put("error", "搜索失败，请稍后重试！");
		}
		writeJson(map);
	}
	
	
	
			
	
	/**
	 * 初始化行政区域的终端数量树
	 * @param rootNodes
	 * @param cityNodes
	 * @param countryNodes
	 * @param communityNodes
	 * @return
	 */
    public List<TreeNode> fillTree(List<TreeNode> rootNodes,List<TreeNode> cityNodes,List<TreeNode> countryNodes,List<TreeNode> communityNodes){  
        List<TreeNode> list=new ArrayList<TreeNode>(); //定义一个树形结构实体  
        for(TreeNode childNode:countryNodes){
        	for(TreeNode communityNode:communityNodes){
        		if(childNode.getId().equals(communityNode.getParentId())){
        			childNode.addChild(communityNode);
        		}
        	}
        }
        
        for(TreeNode cityNode:cityNodes){
        	for(TreeNode treeNode:countryNodes){
        		if(cityNode.getId().equals(treeNode.getParentId())){
        			cityNode.addChild(treeNode);
        		}
        	}
        }
        for(TreeNode rootNode:rootNodes){
        	for(TreeNode treeNode:cityNodes){
        		if(rootNode.getId().equals(treeNode.getParentId())){
        			rootNode.addChild(treeNode);
        		}
        	}
        }
        list.addAll(rootNodes);
        return list; //返回树形结构list集合  
    }  
    
    /**
     * 跳转到预览终端的页面
     * @return
     */
    public String goTerminalPreview(){
		return "terminal_preview";
    }
      
    /**
	 * 终端机列表
	 */
	public void terminalPreviewList(){
	try {
		
		if( true == NumberUtils.isNumber(pageNo) ){
			terminalPage.setPageNumber(Integer.valueOf(pageNo));
		}
		if( true == NumberUtils.isNumber(pageSize) ){
			terminalPage.setPageSize(Integer.valueOf(pageSize));
		}
		terminalPage.setTerminalType(terminalType);
		DataGridPage list  = new DataGridPage();
		list = this.terminalService.datagrid(terminalPage);
//		DataGridPage list = this.terminalService.datagrid(terminalPage);
		super.writeJson(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	/**
	 * 保存预览设备
	 */
    public void saveTerminal(){
    	String terminalSerial = this.getRequest().getParameter("terminalSerial");  
    	String terminalName = this.getRequest().getParameter("terminalName");   
    	Terminal t = terminalService.getBySerial(terminalSerial);
    	Map<String, String> map = new HashMap<String, String>();
    	if(null!=t){
    		map.put("result", "false");
			map.put("error", "该终端设备ID已经存在");
			writeJson(map);
			return;
    	}
    	Terminal terminal = new Terminal();
    	terminal.setTerminalSerial(terminalSerial);
    	terminal.setRegisterAccount(terminalSerial);
    	terminal.setTerminalName(terminalName);
    	terminal.setTerminalAliasName(terminalName);
    	terminal.setTerminalType(terminalType);
    	terminal.setCreateTime(System.currentTimeMillis());
    	
    	try {
    		int result = terminalService.saveTerminal(terminal);
    		if(OpenfireResultType.RESULT_SUCCESS.getValue()==result){
    			map.put("result", "success");
    		}
            if(OpenfireResultType.RESULT_ERROR.getValue()==result){
    			map.put("result", "false");
    			map.put("error", "该账号在openfire已经存在");
    		}
            if(OpenfireResultType.RESULT_FAIL.getValue()==result){
            	map.put("result", "false");
            	map.put("error", "添加预览终端设备失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			map.put("error", "网络出现问题请联系管理员");
		}
    	writeJson(map);
    }
	
    
    public void delTerminalView(){
    	String terminalSerial = this.getRequest().getParameter("terminalSerial");  
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			int r = terminalService.delTerminalView(terminalSerial);
			map.put("result", "success");
			if(OpenfireResultType.RESULT_FAIL.getValue()==r){
				map.put("result", "false");
            	map.put("error", "删除预览终端设备失败");
			}
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "false");
			map.put("error", "网络出现问题请联系管理员");
		}
    	writeJson(map);
    }
    public void terminalPreviewCheckbox(){
    	List<TerminalPage> list = terminalService.getTerminalPagesByType(String.valueOf(terminalType));
    	writeJson(list);
    }
    
    public void pushMsgToTerminal(){
    	String terminalSerials = this.getRequest().getParameter("terminalSerialArr");  
    	String materialIds = this.getRequest().getParameter("attchmentIdArr"); 
    	terminalService.pushMsgToTerminal(materialIds,terminalSerials);
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


	public List<ProvinceVo> getProvinceVo() {
		return provinceVo;
	}


	public void setProvinceVo(List<ProvinceVo> provinceVo) {
		this.provinceVo = provinceVo;
	}
	
	
}
