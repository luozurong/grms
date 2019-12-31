package com.hori.grms.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hori.grms.page.PageParameter;
import com.hori.grms.util.DateUtils;
import com.hori.grms.util.StringUtils;

/**
 * 基础控制器 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 */
public class BaseController {
	protected static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();  
	protected static ThreadLocal<HttpServletResponse> responseLocal  = new ThreadLocal<HttpServletResponse>();  
	protected static ThreadLocal<HttpSession> sessionLocal  = new ThreadLocal<HttpSession>(); 
	/*protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  */
    //放置在方法的形参上：表示引用Model中的数据 这个后台没啥并发先做多线程控制吧
   // 放置在方法上面：表示请求该类的每个Action前都会首先执行它，也可以将一些准备数据的操作放置在该方法里面 目测有线程安全问题但是后台可以忽略不计,实在不行就直接使用方法参数
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        /*this.request= request;  
        this.response = response;  
        this.session = request.getSession();  */
    	  requestLocal.set(request);
          responseLocal.set(response);
          sessionLocal.set(request.getSession());
    } 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
		
		// Timestamp 类型转换
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Date date = DateUtils.parseDate(text);
				setValue(date==null?null:new Timestamp(date.getTime()));
			}
		});
	}
	
	/**
	 * 获取page对象
	 * @param request
	 * @return page对象
	 */
	public  PageParameter getPage(HttpServletRequest request){
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		/*String orderBy="asc";	//排序字段
		String sort="id";	//排序顺序
		*/	
		String orderBy=null;	//排序字段
		String sort=null;
	    if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		if(StringUtils.isNotEmpty(request.getParameter("sort")))
			sort=request.getParameter("sort").toString();
		if(StringUtils.isNotEmpty(request.getParameter("order")))
			orderBy=request.getParameter("order").toString();
		return new PageParameter(pageNo, pageSize, orderBy, sort);
	}
	
	/**
	 * 获取easyui分页数据
	 * @param page
	 * @return map对象
	 */
	public <T> Map<String, Object> getEasyUIData(PageParameter page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", page.getResult());
		map.put("total", page.getTotalCount());
		return map;
	}

	  public static HttpServletRequest getRequest() {  
	        return (HttpServletRequest) requestLocal.get();  
	    }  
	  
	    public static void setRequest(HttpServletRequest request) {  
	        requestLocal.set(request);  
	    }  
	  
	    public static HttpServletResponse getResponse() {  
	        return (HttpServletResponse) responseLocal.get();  
	    }  
	  
	    public static void setResponse(HttpServletResponse response) {  
	        responseLocal.set(response);  
	    }  
	  
	    public static HttpSession getSession() {  
	        return (HttpSession) ((HttpServletRequest) requestLocal.get()).getSession();  
	    }  
}
