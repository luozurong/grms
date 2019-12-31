package com.hori.adms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.hori.model.User;
import com.hori.service.RedisCacheService;
import com.hori.service.UserService;
import com.trisun.message.sms.SmsSender;
@WebServlet(name="GetCodeByMobileServlet",value="/getCodeDyByMobile")
public class GetCodeByMobileServlet extends HttpServlet{
	/**
	 * 日志对象
	 */
	private final static Logger logger=Logger.getLogger(GetCodeByMobileServlet.class);
	/**
	 * 验证码生存时间
	 */
	private static final long CODEBYMOBILELIVETIMES = 60*5;
	/**
	 * 防刷生存时间
	 */
	private static final long CODEBYMOBILETIMELIVETIMES = 60*3;
	/**
	 * 验证多少次有效的防刷数次
	 */
	private static final long CODEBYMOBILETIMES = 5;
	/**
	 * 防刷key
	 */
	private static final String CODEBYMOBILETIMESKEY = "cache|getCodeByMobileTimes";
	/**
	 * 手机验证码的key
	 */
	private static final String CODEBYMOBILEKEY = "cache|getCodeByMobile";
	
	//操作类型
	private static final String DONE_TYPE = "doneType";
	
	private static final String MOBILE_CHECK_CODE = "mobile_check_code";
	
	//通过绑定手机获取骓码
    private static final String GET_CODE = "getcode";
    
    private static final String GET_CODE_CHECK = "ceche|GET_CODE_CHECK";
    
	private static final long serialVersionUID = -2781468355582755656L;
	private SmsSender smsSender;
	private RedisCacheService redisCacheService;
	private UserService userService;
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		smsSender=(SmsSender) ctx.getBean("smsSender");
		redisCacheService=(RedisCacheService) ctx.getBean("redisCacheService");
		userService=(UserService) ctx.getBean("userService");
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
		String errors = "";
		String doneType = (String)request.getParameter(DONE_TYPE);
		if(GET_CODE.equals(doneType)){
			errors = getCodeByMobile(request,  response);
		}
		if(MOBILE_CHECK_CODE.equals(doneType)){
			errors = checkAccountAndCode( request,  response);
		}
		
		PrintWriter out = response.getWriter();
		out.print(errors);
		out.flush();
		out.close();
	}
	
    protected String getCodeByMobile(HttpServletRequest request, HttpServletResponse response ){
		
		String errors = "";
		
		String mobile = request.getParameter("mobile");
		String isGetCode = request.getParameter("isGetCode");
		
		
		
		if(mobile ==null||"".equals(mobile)){
			errors = "手机号码不能为空！";
			return errors;
		}
		User user = userService.getUserByAccount(mobile);
		if(user==null){
			errors = "用户帐号不存在！";
			return errors;
		}
		String codeValue = redisCacheService.get(isGetCode.toUpperCase());
		
		

		String cacheCodeTimeKey = mobile+"|"+CODEBYMOBILETIMESKEY;
		
		String codeTimesStr =  redisCacheService.get(cacheCodeTimeKey);
		int codeTimes =StringUtils.isBlank(codeTimesStr)?0:Integer.parseInt(codeTimesStr);
		if(codeTimes>=CODEBYMOBILETIMES&&(!isGetCode.equals(codeValue)||StringUtils.isBlank(codeValue))){//弹出图形验证码
			errors = "validateCode";
			return errors;
		}
		String randomNum = Math.random()*9000+1000+"";
		randomNum = randomNum.substring(0, 4);
		redisCacheService.incr(cacheCodeTimeKey, CODEBYMOBILETIMELIVETIMES, TimeUnit.SECONDS);
		//重置验证码，以防复用
		//request.getSession().removeAttribute("randomNum");
		//request.getSession().setAttribute("randomNum", randomNum);
		
		
		String content = randomNum+"（重置密码验证码），5分钟内有效，超时请重新申请。";
		
		smsSender.sendSms(mobile, content);
		logger.info("----------randomNum-------:::"+randomNum);
		String cacheCodeKey = mobile+"|"+CODEBYMOBILEKEY;
		redisCacheService.del(cacheCodeKey);
		redisCacheService.set(cacheCodeKey, randomNum, CODEBYMOBILELIVETIMES);
		errors = "sendRandonNum";
		
		return errors;
	}
	
    
    protected String checkAccountAndCode(HttpServletRequest request, HttpServletResponse response ){
		
		String errors = "";
		
		String mobile = request.getParameter("mobile");
		String check_code = request.getParameter("check_code");
		
	
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
		User user = userService.getUserByAccount(mobile);
		if(user==null){
			errors = "用户帐号不存在";
		}else{
			errors = "checkAccountAndCode";
		}
		
		return errors;
	}
}
