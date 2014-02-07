package com.aicai.interceptor;

import com.aicai.core.ActionInvocation;

@SuppressWarnings("serial")
public class Test2Interceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("before invoke ==>2");
        String result = invocation.invoke();
        System.out.println("after  invoke==>2");
        return result;
    }

}
