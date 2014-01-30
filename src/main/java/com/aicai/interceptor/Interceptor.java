package com.aicai.interceptor;

import java.io.Serializable;

import com.aicai.core.ActionInvocation;

/**
 * invocation hold a iterator with interceptors, recursion
 * 
 * @author wk
 * 
 */
public interface Interceptor extends Serializable {

    void init();

    String intercept(ActionInvocation invocation) throws Exception;

    void destroy();
}