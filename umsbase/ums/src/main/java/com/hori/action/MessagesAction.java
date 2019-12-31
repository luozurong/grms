package com.hori.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hori.db.support.DataGridPage;
import com.hori.pageModel.MessagesPage;
import com.hori.service.MessagesService;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "messagesAction",
results = {
		     @Result(name ="list",location = "/system/messages.jsp")
		     })
public class MessagesAction extends BaseAction  implements ModelDriven<MessagesPage> {
    
	private MessagesPage messagesPage =  new MessagesPage();
	
	@Autowired
	private MessagesService messagesService;
	
	private String pageSize = "";
	
	private String pageNo = "";
	
	@Override
	public MessagesPage getModel() {
		return messagesPage;
	}
     
	
	public String goList(){
		return "list";
	}
	
	/**
	 * 消息列表页面
	 */
	public void list(){
		try {
			//用户权限等级
			String userLevelNo = (String) this.getRequest().getSession().getAttribute("userLevelNo"); 
			int _pageNo =1;
			int _pageSize = 10;
			if( true == NumberUtils.isNumber(pageNo) ){
				_pageNo = Integer.valueOf(pageNo);
			}
			
			if( true == NumberUtils.isNumber(pageSize) ){
				_pageSize = Integer.valueOf(pageSize);
			}
			messagesPage.setPageNumber(_pageNo);
			messagesPage.setPageSize(_pageSize);
			messagesPage.setViewLevelNo(userLevelNo);
			messagesPage.setSortType(" ORDER BY status ASC, create_time DESC");
			DataGridPage list = this.messagesService.datagrid(messagesPage);
			if(list.getTotal()<=0){
				super.writeJson(new DataGridPage());
			}else{
				super.writeJson(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	/**
	 * 修改消息的状态
	 */
	public void changeStatus(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String ids = this.getRequest().getParameter("id");
			if(StringUtils.isNotBlank(ids)){
				this.messagesService.changeStatusById(ids);
				map.put("result", "success");
				map.put("success", "状态已成功更改！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "error");
			map.put("error", "系统繁忙，请稍后重试！");
		}
		writeJson(map);
	}
	
	/**
	 * 统计消息的数量
	 */
	public void totalCount(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			//用户权限等级
			String userLevelNo = (String) this.getRequest().getSession().getAttribute("userLevelNo"); 
			map.put("result", "success");
			map.put("success", this.messagesService.totalCountByAccount(userLevelNo).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "error");
		}
		writeJson(map);
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
	
}
