package com.hori.filter;
import java.io.IOException;  
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;
  
/** 
 * <code>{@link CharLimitFilter}</code> 
 * 
 * 拦截防止sql注入 
 * 
 * @author Administrator 
 */  
public class XssFilter implements Filter {  
  
  
    /* (non-Javadoc) 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,  
            ServletException {  
          
            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(  
            (HttpServletRequest) request);  
            filterChain.doFilter(xssRequest, response);  
          
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}  
  
}  


/** 
 * <code>{@link XssHttpServletRequestWrapper}</code> 
 * 
 * TODO : document me 
 * 
 * @author Administrator 
 */  
 class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
    HttpServletRequest orgRequest = null;  
  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
        orgRequest = request;  
    }  
  
    /** 
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
    */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
  
    /** 
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
    * getHeaderNames 也可能需要覆盖 
    */  
    @Override  
    public String getHeader(String name) {  
  
        String value = super.getHeader(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
    /**
     * 因为使用了strust2.  所以要重新此方法
     */
    @Override
    public Map getParameterMap() {
    	   super.getContextPath();  
           Map<String,String[]> map = super.getParameterMap();  
           if(!map.isEmpty()){  
               Set<String> keySet = map.keySet();  
               Iterator<String> keyIt = keySet.iterator();  
               while(keyIt.hasNext()){  
                   String key = keyIt.next();  
//                 String value = map.get(key)[0];  
//                 map.get(key)[0] = this.replaceParam(value);  
                   //这边实现对整个数组的判断。  
                   String[] values=map.get(key); 
                   if(null!=values){
                	   for(int i=0;i<values.length;i++){  
                           map.get(key)[i]=xssEncode(values[i]);  
                       }  
                   }
               }  
           }  
           return map;  
    }
    
    
    /** 
    * 将容易引起xss漏洞的半角字符直接替换成全角字符 
    * 
    * @param s 
    * @return 
    */  
    private static String xssEncode(String s) {  
        if (s == null || "".equals(s)) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');//全角大于号  
                break;  
            case '<':  
                sb.append('＜');//全角小于号  
                break;  
            case '\'':  
                sb.append('‘');//全角单引号  
                break;  
            case '\"':  
                sb.append('“');//全角双引号  
                break;  
            case '&':  
                sb.append('＆');//全角  
                break;  
            case '\\':  
                sb.append('＼');//全角斜线  
                break;  
//            case '#':  
//                sb.append('＃');//全角井号  
//                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
  
    /** 
    * 获取最原始的request 
    * 
    * @return 
    */  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
    * 获取最原始的request的静态方法 
    * 
    * @return 
    */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }  
  
}