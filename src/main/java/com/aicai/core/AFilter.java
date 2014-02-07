package com.aicai.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AFilter implements Filter {
    private ActionExecutor ace;

    public void init(FilterConfig filterConfig) throws ServletException {
        ace = new ActionExecutor();
        ace.init();
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // TODO prepare
        // TODO deal
        // ActionExecutor
        ace.execute(request, response, null);
        // TODO do some clean
        // chain.doFilter(request, response);
    }

    public void destroy() {

    }

}
