/**
 * 
 */
package com.hori.grms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hori.grms.model.Customer;
import com.hori.grms.page.PageParameter;
import com.hori.grms.service.CustomerService;

/** 
 * @ClassName: CustomerController 
 * @Description: 客户
 * @author zhuqiang
 * @date 2018年8月14日 下午3:46:59 
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(ProjectActionController.class);
    
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 根据用户id 查看 业务员关联的客户
	 * @return
	 */
	@RequestMapping(value ="/getlistData",produces="text/html;charset=UTF-8;")
	@ResponseBody
	private  String customerListByUser(
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="pageSize",required=false) Integer pageSize
			){
		Map<String, Object> resultMap=new HashMap<>();
		try {
		Integer page_no=1;
		Integer page_size=5;
		
		if(pageNo!=null&&pageNo!=0) page_no=pageNo;
				
		//获取 session 的用户账号
		HttpSession session=getSession();
		String ownerAccount=(String) session.getAttribute("userAccount");	
		
		PageInfo<Customer> pageP=customerService.findCustomerListByUser(ownerAccount,page_no,page_size);
		
		
		resultMap.put("success", true);
		resultMap.put("rows", pageP.getList());
		resultMap.put("total", pageP.getTotal());
       } catch (Exception e) {
			e.printStackTrace();
			logger.info("错误原因："+e.getMessage());
			resultMap.put("success", false);
		
		}finally {
			return JSON.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd");
		}
	}
}
