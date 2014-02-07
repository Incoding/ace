package com.aicai.interceptor;

import com.aicai.core.ActionInvocation;

@SuppressWarnings("serial")
public class TestInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("before invoke ==>1");
        String result = invocation.invoke();
        System.out.println("after  invoke==>1");
        return result;
    }

}
