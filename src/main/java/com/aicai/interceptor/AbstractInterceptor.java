package com.aicai.interceptor;

import com.aicai.core.ActionInvocation;

@SuppressWarnings("serial")
public abstract class AbstractInterceptor implements Interceptor {

    public void init() {
    }

    public abstract String intercept(ActionInvocation invocation) throws Exception;

    public void destroy() {
    }
}