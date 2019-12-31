package com.hori.grms.interceptor;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.hori.adms.util.GlobalPropertiesValue;

import net.sf.json.JSONObject;
/**
 * 后台系统会话拦截器
 * @author hhb
 * @time 2018-8-02 上午10:42:33
 * @file BackendSessionInterceptor.java
 */
public class BackendSessionInterceptor implements HandlerInterceptor  {
	private final static Logger logger=LoggerFactory.getLogger(BackendSessionInterceptor.class);
	private String ssoLoginUri;
	private String callbackUri;

	/**
	 * 跳过拦截的url
	 */
	private final static List<String> SkipUrlList=new ArrayList<String>();
	static {
		SkipUrlList.add("/admin");//登录界面
		SkipUrlList.add("/admin/ajax/loginAction");//登录请求
		SkipUrlList.add("/admin/ajax/loginOut");//注销请求
		SkipUrlList.add("/admin/logout");//登出中转界面
	
	}
	/** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 ,再执行被拦截的Controller 然后进入拦截器链, 从最后一个拦截器往回执行所有的postHandle() 
     * 接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
//    @SuppressWarnings("rawtypes")
	@Override  
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {  
        String url=request.getRequestURL().toString();  
//        String contextPath=request.getContextPath();//contextPath
        logger.info("拦截器拦截url:"+url);
        boolean isSessionValid=true;//session是否有效
        boolean isSkip=false;//是否需要跳过拦截
        for(String su:SkipUrlList){
        	if(url.indexOf(su)!=-1){
        		isSkip=true;
        		 logger.info("跳过拦截:"+url);
        		break;
        	}
        }
        if(!isSkip){
        	String userAccount = (String)request.getSession().getAttribute("userAccount");
    		//从session中获取用户账号
/*    		String userType=(String) request.getSession().getAttribute("userType");
*/        	if(StringUtils.isNotBlank(userAccount)){
        		isSessionValid=true;
        	}else{
        		isSessionValid=false;
        	}
        }
        //判断是否是ajax请求
//        boolean isAjaxReq=isAjaxRequest(request);
        if(!isSessionValid){
        	response.sendRedirect(ssoLoginUri+"?redirectUri="+URLEncoder.encode(callbackUri, "utf-8"));
        	return false;//不在执行下去 
        }
        return true;
        
    }  
//    private boolean isAjaxRequest(HttpServletRequest request) { 
//	    String header = request.getHeader("X-Requested-With"); 
//	    if (header != null && "XMLHttpRequest".equals(header)) 
//	        return true; 
//	    else 
//	        return false; 
//	}
  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
        
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    
    }

	public String getSsoLoginUri() {
		return ssoLoginUri;
	}

	public void setSsoLoginUri(String ssoLoginUri) {
		this.ssoLoginUri = ssoLoginUri;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
	}  
    
    
}