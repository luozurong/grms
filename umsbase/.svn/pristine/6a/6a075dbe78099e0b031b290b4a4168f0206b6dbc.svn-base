package com.hori.adms.servlet;

import java.io.IOException;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.hori.utils.ServletUtil;
import com.trisun.message.sms.SmsSender;

@WebServlet(name="GetUmsCodeByMobileServlet",value="/servlet/getCodeByMobile")
public class GetUmsCodeByMobileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(UmsLoginServlet.class);

	/**
	 * 验证多少次有效的防刷数次
	 */
	private static final long CODEBYMOBILETIMES = 1000;
	/**
	 * 防刷key
	 */
	private static final String CODEBYMOBILETIMESKEY = "cache|getCodeByMobileTimes";
	/**
	 * 验证码生存时间
	 */
	private static final long CODEBYMOBILELIVETIMES = 60*5;
	/**
	 * 防刷生存时间
	 */
	private static final long CODEBYMOBILETIMELIVETIMES = 60*3;
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	private UserService userService;
	private SmsSender smsSender;
	private RedisCacheService redisCacheService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService=(UserService) ctx.getBean("userService");
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		smsSender=(SmsSender)ctx.getBean("smsSender");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					// 客户端请求JSON串
					String reqStr = ServletUtil.praseRequst(request);
					//请求结果
					int result=0;
					//说明
					String reason=null;
					if(!StringUtils.isNotBlank(reqStr)){
						result=1;
						reason="消息格式错误";
						returnResult(response,result,reason);
						return;
					}else{
						JSONObject object=JSON.parseObject(reqStr);
						JSONObject body=object.getJSONObject("body");
						
						String mobile = body.getString("mobile");
						String code=body.getString("code").toUpperCase();
						String doneType=body.getString("doneType").toUpperCase();
						//暂时不需要验证码
//						String codeValue = redisCacheService.get(isGetCode.toUpperCase());
						if(mobile ==null||"".equals(mobile)){
							result=2;
							reason="手机号码不能为空！";
							returnResult(response,result,reason);
							return;
						}

						if(code == null||"".equals(code)){
							result=2;
							reason="验证码不能为空！";
							returnResult(response,result,reason);
							return;
						}
						if(doneType == null||"".equals(doneType)){
							result=2;
							reason="操作状态不能为空！";
							returnResult(response,result,reason);
							return;
						}
						 if(doneType.equals("0")){
								String codeValue = redisCacheService.get(code.toUpperCase());						
								if(code.equals(codeValue)){
									result=0;
									reason="验证码正确 ！";
									returnResult(response,result,reason);
									return;
								}else{
									result=2;
									reason="验证码错误 ！";
									returnResult(response,result,reason);
									return;
								}
						   }
						
							User user = userService.getUserByAccount(mobile);

							if(doneType.equals("1")||doneType.equals("3")){

								if(user==null){
									result=3;
									reason="用户帐号不存在！";
									returnResult(response,result,reason);
									return;
								}
							}else if(doneType.equals("2")){
								if(user!=null){
									result=3;
									reason="该手机号已注册，请直接登录";
									returnResult(response,result,reason);
									return;
								}
							}
							//图片验证码再校验
							String codeValue = redisCacheService.get(code.toUpperCase());						
							if(code.equals(codeValue)){
								result=0;
								reason="验证码正确 ！";
								returnResult(response,result,reason);
								redisCacheService.del(codeValue);
							}else{
								result=2;
								reason="验证码错误 ！";
								returnResult(response,result,reason);
								return;
							}
						String cacheCodeTimeKey = mobile+"|"+CODEBYMOBILETIMESKEY;
						
						String codeTimesStr =  redisCacheService.get(cacheCodeTimeKey);
						int codeTimes =StringUtils.isBlank(codeTimesStr)?0:Integer.parseInt(codeTimesStr);
						if(codeTimes>=CODEBYMOBILETIMES){//
							result=4;
							reason="请稍后再操作！";
							returnResult(response,result,reason);
							return;
						}
						if(result==0){
							String randomNum = Math.random()*9000+1000+"";
							randomNum = randomNum.substring(0, 4);
							redisCacheService.incr(cacheCodeTimeKey, CODEBYMOBILETIMELIVETIMES, TimeUnit.SECONDS);
							//重置验证码，以防复用
					
						    String temp="";
						    if(doneType.equals("1")||doneType.equals("2")){
						    	temp="（修改手机号验证码），5分钟内有效，超时请重新申请。";
						    }else{
						    	temp="（重置密码验证码），5分钟内有效，超时请重新申请。";
						    }
							String content = randomNum+temp;
						
							smsSender.sendSms(mobile, content);
							logger.info("----------randomNum-------:::"+randomNum);
							String cacheCodeKey = mobile+"|"+CODEBYMOBILEKEY;
							redisCacheService.del(cacheCodeKey);
							redisCacheService.set(cacheCodeKey, randomNum, CODEBYMOBILELIVETIMES);
						}
						returnResult(response,result,reason);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	 protected String checkAccountAndCode(String mobile, String check_code ){
			
			String errors = "0";
		
			
		
			if(mobile ==null||"".equals(mobile)){
				errors = "手机号码不能为空！";
				return errors;
			}
			
			if(check_code == null||"".equals(check_code)){
				errors = "验证码不能为空！";
				return errors;
			}
			
			String checkCodeOfSession = redisCacheService.get(mobile+"|"+CODEBYMOBILEKEY) ;
			
			if(!check_code.equals(checkCodeOfSession)){
				errors = "验证码错误 ";
				return errors;
			}
			return errors;
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private  static void returnResult(HttpServletResponse response ,int result,String reason) throws IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("reason", reason);
		String json = JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss");
		ServletUtil.sendResponse(response, json);
	}

}
