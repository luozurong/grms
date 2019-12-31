package com.jlit.uaas.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 记录错误日志的过滤器，用于捕获并记录所有未考虑到的异常（主要是各种RuntimeException和页面异常）。
 * 
 * @author
 * 
 */
public class ThrowableLoggingFilter implements Filter {

	/**
	 * 日志
	 */
	private static final Log log = LogFactory
			.getLog(ThrowableLoggingFilter.class);

	/**
	 * 初始化过滤器。
	 * 
	 * @param filterConfig
	 *            过滤器配置
	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		if (log.isDebugEnabled()) {
			log.debug("Innitialize ThrowableLoggingFilter.");
		}
	}

	/**
	 * 过滤请求。
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 * @param servletResponse
	 *            Servlet响应
	 * @param filterChain
	 *            过滤器链
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (RuntimeException e) {
			if (log.isErrorEnabled()) {
				log.error("ThrowableLoggingFilter catches RuntimeException:", e);
			}
			throw e;
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("ThrowableLoggingFilter catches IOException:", e);
			}
			throw e;
		} catch (ServletException e) {
			if (log.isErrorEnabled()) {
				log.error("ThrowableLoggingFilter catches ServletException:", e);
			}
			throw e;
		} catch (Throwable t) {
			if (log.isErrorEnabled()) {
				log.error("ThrowableLoggingFilter catches Throwable:", t);
			}
			throw new RuntimeException(t);
		}
	}

	/**
	 * 销毁过滤器。
	 */
	public void destroy() {
	}

}
