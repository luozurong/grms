package com.hori.grms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Deprecated
//@ControllerAdvice
public class SpringMVCExceptionHandler implements HandlerExceptionResolver{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*@ExceptionHandler({Exception.class})
	public ModelAndView resolveException(HttpServletRequest request,Exception ex){
		logger.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage());
		// 根据不同错误转向不同页面 后面再写
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		return mv;
	}*/

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage());
		// 根据不同错误转向不同页面 后面再写
		/*ModelAndView mv = new ModelAndView("redirect:"+request.getContextPath()+"/WEB-INF/pages/error.jsp");
		mv.addObject("exception", ex);
		return mv;*/
		return null;
	}
	
}