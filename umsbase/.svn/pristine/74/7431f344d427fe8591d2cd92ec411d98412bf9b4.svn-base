package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.service.XmppAdvertisementService;
import com.hori.utils.Md5;
import com.hori.utils.ServletUtil;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name="ModifyPwdByMobileServlet",value="/servlet/modifyPwdByMobile")
public class ModifyPwdByMobileServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(ModifyPwdByMobileServlet.class);
	
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	
	
    
	private static final long serialVersionUID = -2781468355582755656L;
	private RedisCacheService redisCacheService;
	private UserService userService;
	private XmppAdvertisementService xmppAdvertisementService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		userService=(UserService) ctx.getBean("userService");
		xmppAdvertisementService=(XmppAdvertisementService) ctx.getBean("xmppAdvertisementService");

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//处理登录请求
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			modifyPwd(request,  response);
	}
	
    protected void modifyPwd(HttpServletRequest request, HttpServletResponse response ) throws IOException{
    	
    	String result="0";
    	String reason="";
    	String reqStr = ServletUtil.praseRequst(request);
    	if(StringUtils.isBlank(reqStr)){
    		
    		result="001";
			reason ="消息格式错误！";
			returnResult(response,result,reason);
			return;
    	}
		JSONObject object=JSON.parseObject(reqStr);
		JSONObject body=object.getJSONObject("body");
		String userAccount = body.getString("userAccount");
		String password = body.getString("password");
		String repassword = body.getString("repassword");
		String mobile_check_code = body.getString("checkCode");
		
		if(StringUtils.isBlank(mobile_check_code)){
			result="005";
			reason ="验证码不能为空！";
			returnResult(response,result,reason);

			return ;
		}
		
		if(StringUtils.isBlank(userAccount)){
			result="005";
			reason ="手机号码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		
		if(StringUtils.isBlank(password)){			
			result="005";
			reason ="密码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		if(repassword==null||"".equals(repassword)){
			result="005";
			reason ="确认密码不能为空！";
			returnResult(response,result,reason);
			return ;
		}
		
		if(!password.equals(repassword)){
			result="005";
			reason ="密码与确认密码不相同！";
			returnResult(response,result,reason);
			return ;
		}
		if(password.length()<6 || password.length()>20){
			result="005";
			reason ="密码长度错误，请输入6-20位长度的密码！";
			returnResult(response,result,reason);
			return ;
		}
		User user  = userService.getUserByAccount(userAccount);
		if(null==user){
			result="005";
			reason ="该用户不存在！";
			returnResult(response,result,reason);
			return ;
		}
		String cacheCodeKey = user.getMobile()+"|"+CODEBYMOBILEKEY;
		String randomNum = redisCacheService.get(cacheCodeKey);
		randomNum = StringUtils.isBlank(randomNum)?"":randomNum;
	
		if(randomNum.equals(mobile_check_code)){
			//重置验证码，以防复用
			//修改密码为新密码
			Md5 md5 = new Md5();
			String passwd = md5.getMD5Str(password);
			user.setPassword(passwd);
			redisCacheService.del(cacheCodeKey);
			userService.update(user);
			returnResult(response,result,reason);
			try {
				int i=xmppAdvertisementService.updateUser(user, password, "");
				if(i!=0){
					throw new DocumentException();
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="006";
				reason ="同步openfire错误！";
				returnResult(response,result,reason);	
			}
		}else{
			result="005";
			reason ="验证码错误！";
			returnResult(response,result,reason);		}
	}
	
    
	
	private  static void returnResult(HttpServletResponse response ,String result,String reason) throws IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("reason", reason);
		String json = JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss");
		ServletUtil.sendResponse(response, json);
	}

}
