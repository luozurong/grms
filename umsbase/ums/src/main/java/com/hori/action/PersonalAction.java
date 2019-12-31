package com.hori.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hori.model.AuthorizeArea;
import com.hori.model.UserDetail;
import com.hori.pageModel.Json;
import com.hori.service.UserDetailService;
import com.hori.service.UserService;
import com.trisun.message.sms.SmsSender;

/**
 * 个人设置
 * 
 * @author 
 * 
 */

@Action(value = "personalAction", 
  results = { @Result(name = "personal", location = "/system/setPersonal.jsp")})
public class PersonalAction extends BaseAction{
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private UserService userService;
	@Autowired
	private SmsSender smsSender;

	/**
	 * 调转到个人设置
	 * @return
	 */
	public String goPersonal(){
		//测试写死运维系统
		return "personal";
	}
	/**
	 * 初始化数据
	 * @return
	 */
	public void initUserDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();

		String systemId = (String)request.getSession().getAttribute("selected_platform");
		String userAccount = (String)request.getSession().getAttribute("userAccount");
		List<Map<String, Object>>  mapList=userDetailService.findUserDetailOne(userAccount, systemId);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		j.setObj(mapList);
		writeJson(j);
	}
	
	/**
	 * 修改个人设置
	 * @return
	 */
	public void alterUserDetail(){
	    String nickName=getRequest().getParameter("nickName");
		String name = getRequest().getParameter("name");
		String email = getRequest().getParameter("email");
        String userDetailId= getRequest().getParameter("userDetailId");
        userDetailService.updateUserDetail(userDetailId, nickName, email, name);
        Json j = new Json();
		j.setSuccess(true);
		j.setMsg("sucess");
		writeJson(j);

	}
	/**
	 * 发送手机验证码
	 * @return
	 */
	public void sendMessage(){
	    String userAccount=getRequest().getParameter("userAccount");
	    Map<String,String> map=userService.sendMessge(userAccount);
	    String msg="";
	    Json j = new Json();
	    if(map.get("success").equals("0")){
			smsSender.sendSms(map.get("mobile"), map.get("content"));
			msg="成功";
		  	j.setSuccess(true);

	    }else if(map.get("success").equals("1")){
	    	msg="请求过于频繁，请稍后再试";
		  	j.setSuccess(false);

	    }
	   j.setMsg(msg);
	   writeJson(j);
	}
	/**
	 * 修改手机号码
	 * @return
	 */
	
	public void alterMobile(){
	    String userAccount=getRequest().getParameter("userAccount");
	    String code=getRequest().getParameter("code");
	    String mobileNew=getRequest().getParameter("mobileNew");
	    Map<String,String> map=userService.alterMobile(userAccount, code, mobileNew);
	    String msg="";
	    Json j = new Json();
	    if(map.get("success").equals("0")){
	 			msg="成功";
	 		  	j.setSuccess(true);

	   }else if(map.get("success").equals("1")){
	 	    	msg="验证码错误";
	 		  	j.setSuccess(false);
	   }else if(map.get("success").equals("2")){
		   		msg="该手机号已经存在";
		   		j.setSuccess(false);
	   }
	    j.setMsg(msg);
		writeJson(j);
         
	}
	/**
	 * 修改密码
	 * @return
	 */
	public void alterPassword(){
	    String userAccount=getRequest().getParameter("userAccount");
	    String password=getRequest().getParameter("password");
	    String passwordNew=getRequest().getParameter("passwordNew");
	    String passwordRe=getRequest().getParameter("passwordRe");
    	Json j = new Json();
    	String msg="";
        if(passwordRe.equals(passwordNew)){
        	Map<String,String> map=userService.alterPassword(userAccount, passwordNew, password);
        	if(map.get("success").equals("0")){
        			msg="成功";
        			j.setSuccess(true);

        	}else if(map.get("success").equals("1")){
        			msg="旧密码错误";
        			j.setSuccess(false);
	 	    	}
        }else{
        	msg="新密码两次输入不一致";
			j.setSuccess(false);
        }
	    j.setMsg(msg);
		writeJson(j);
         
	}

}
