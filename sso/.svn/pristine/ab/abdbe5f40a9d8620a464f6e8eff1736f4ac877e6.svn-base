package com.jlit.uaas.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

public class ServletToBeanProxy extends GenericServlet {

    private String targetBean;

    private Servlet proxy;

    public void init() throws ServletException {
        this.targetBean = getInitParameter("targetBean");
        getServletBean();
        proxy.init(getServletConfig());
    }

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        proxy.service(req, res);

    }

    private void getServletBean() {

        WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        this.proxy = (Servlet) wac.getBean(targetBean);

    }

}