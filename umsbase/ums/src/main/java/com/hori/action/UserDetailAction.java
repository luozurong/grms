package com.hori.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hori.dao.queryBean.UserDetailQueryBean;
import com.hori.db.support.DataGridPage;
import com.hori.model.UserDetail;
import com.hori.pageModel.Json;
import com.hori.service.UserDetailService;
import com.hori.vo.UserDetailLoginVo;

/**
 * 人员管理
 * 
 * @author hhb
 * 
 */

@Action(value = "userDetailAction", 
  results = { @Result(name = "userDetail", location = "/peopleManagement/employeesInformation.jsp"),
  
      })
public class UserDetailAction  extends BaseAction {
	
	@Autowired
	UserDetailService userDetailService;
	HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * 跳转人员管理页面
	 * @return
	 */
	public String goUserDetailList(){
		return "userDetail";
	}
	
	/**
	 * 根据参数分页查询
	 * @return
	 */
	public void getUserDetailByDepartId(){
		String userDetailQueryBean=getRequest().getParameter("userDetailQueryBean");
		String newStr = userDetailQueryBean.replaceAll("“","\"");
		String userType=(String)request.getSession().getAttribute("userType");
		String systemId = (String)request.getSession().getAttribute("selected_platform");
		//数据域（0表示个人数据，1表示部门数据，2表示全局数据）
		byte dataArea=(byte)request.getSession().getAttribute("dataArea");
		UserDetailQueryBean data=JSON.parseObject(newStr, UserDetailQueryBean.class);  
		//从session里面获取登录机构
		UserDetailLoginVo userDetailVo =(UserDetailLoginVo) request.getSession().getAttribute("userDetailVo");
		DataGridPage dataGridPage=userDetailService.findUserDetailByParam(data,userType,userDetailVo.getDepartId(),systemId,dataArea);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(dataGridPage);
		writeJson(j);

	}
	
	/**
	 * 保存或修改用户资料信息
	 * @return
	 */
	public void saveUserDetail(){
		String userDetialvo=getRequest().getParameter("userDetial");
		String newStr = userDetialvo.replaceAll("“","\"");
		UserDetail userDetail=JSON.parseObject(newStr, UserDetail.class);  
		Json j = new Json();
		String userType=(String)request.getSession().getAttribute("userType");
		j.setSuccess(true);
		//从session里面获取登录机构
		UserDetailLoginVo userDetailVo =(UserDetailLoginVo) request.getSession().getAttribute("userDetailVo");
		if(!userType.equals("0")&&userDetail.getDepartId().equals(userDetailVo.getDepartId())){
				j.setSuccess(false);
				j.setMsg("非管理员不能创建同级人员");
		}
		if(j.isSuccess()){
			
		
			int status=userDetailService.saveUserDetail(userDetail);
			if(status==0){
				j.setSuccess(true);
				j.setMsg("sucess");
			}else{
				j.setSuccess(false);
				j.setMsg("手机号重复，请重新填写");
			}
		}
       
		writeJson(j);
		String operationModule = "人员管理-员工信息管理";
		String operationType = "新增或者修改";
	}
	
	/**
	 * 删除用户资料
	 * @return
	 */
	public void deleteUserDetail(){
		String ids=getRequest().getParameter("ids"); 
		String[] idsArray= ids.split("'");
		Map map=userDetailService.deleteUserDetail(idsArray);
		Json j = new Json();
		if(map.get("status").equals(0)){
			j.setSuccess(true);
			j.setMsg("sucess");
		}else {
			j.setSuccess(false);
			j.setObj(map.get("ids"));
			j.setMsg("该员工已有关联帐号，请先删除帐号");
		}
		writeJson(j);	
		String operationModule = "人员管理-员工信息管理";
		String operationType = "删除";
	}
	
	

}
