package com.hori.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.writers.HstsHeaderWriter;

import com.hori.service.SalesmanService;
/**
 * 返回业务员账户、姓名，含除重
 * @author hori
 *
 */
@Action(value="salesmanAction")
public class SalesmanAction extends BaseAction {

	private static final long serialVersionUID = 1751886949852202084L;
	
	private static final Log log = LogFactory.getLog(SalesmanAction.class);
	
	@Autowired
	private SalesmanService salesmanService;
	
	public void getSalesmanList(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletRequest request = getRequest();
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		List<Map<String, Object>> list;
		try {
			list = salesmanService.fetchByName(name,account);
			map.put("succ", true);
			map.put("data", list);
		} catch (Exception e) {
			map.put("succ", false);
			e.printStackTrace();
		}
		super.writeJson(map);
	}
	
}
