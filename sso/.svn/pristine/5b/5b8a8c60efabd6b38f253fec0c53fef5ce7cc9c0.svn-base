package com.jlit.uaas.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
/**
 * SpringMVC中数据动态绑定，时间格式转换
 * @author lzs
 *
 */
public class MyBindingInitializer implements WebBindingInitializer {  
	  
    public void initBinder(WebDataBinder binder, WebRequest request) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //可以設定任意的日期格式  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class,   
            new CustomDateEditor(dateFormat, true));  
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));  
    }  
  
  
}  