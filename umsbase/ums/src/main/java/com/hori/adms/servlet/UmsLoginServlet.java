package com.hori.adms.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hori.adms.servlet.message.ServletMessageReq;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.utils.ServletUtil;
import com.hori.utils.StaticValue;
import com.hori.vo.LoginForSystemVo;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;


/**
 * 权限申请接口
 * @author hhb
 * @date 2017年6月20日 上午9:40:22
 */
@WebServlet(name="UmsLoginServlet",value="/servlet/umsLoginServlet")
public class UmsLoginServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(UmsLoginServlet.class);
	
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService=(UserService) ctx.getBean("userService");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					// 客户端请求JSON串
					String reqStr = ServletUtil.praseRequst(request);
					LoginForSystemVo loginForSystemVo=new LoginForSystemVo();

					if(!StringUtils.isNotBlank(reqStr)){
						loginForSystemVo.setResult("4");
						loginForSystemVo.setReason("传入参数错误");
					}else{
						JSONObject object=JSON.parseObject(reqStr);
						JSONObject body=object.getJSONObject("body");
						//
						String userAccount = body.getString("userAccount");
						String password = body.getString("password");
						String systemId = body.getString("systemId");
						String resourceApp=body.getString("resourceApp");
						//app操作系统
						String OS=body.getString("OS");
						//app型号
						String mobileModels=body.getString("mobileModels");
						//ip
						String ip=body.getString("ip");
						if(StringUtils.isNotBlank(OS)&&StringUtils.isNotBlank(mobileModels)&&StringUtils.isNotBlank(ip)&&StringUtils.isNotBlank(userAccount)&&StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(systemId)&&StringUtils.isNotBlank(resourceApp)){
							loginForSystemVo=userService.loginForSystemByUms(userAccount, password, systemId,resourceApp);
							if(loginForSystemVo.getResult().equals("0")){
/*								iOperationRecordService.login(userAccount, ip, true, mobileModels, OS);
*/							}else{
/*								iOperationRecordService.login(userAccount, ip, false, mobileModels, OS);
*/							}
						}else if(StringUtils.isNotBlank(userAccount)&&StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(systemId)&&StringUtils.isNotBlank(resourceApp)){
							loginForSystemVo=userService.loginForSystemByUms(userAccount, password, systemId,resourceApp);
						}else{
							loginForSystemVo.setResult("4");
							loginForSystemVo.setReason("传入参数错误");
						 }
						}
					//转化为json字符串
					String json = JSON.toJSONString(loginForSystemVo,SerializerFeature.WriteMapNullValue);
					ServletUtil.sendResponse(response, json);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
