package com.aicai.core;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aicai.exception.AicaiMvcException;
import com.aicai.interceptor.Interceptor;

public class ActionInvocation {
    HttpServletRequest    req;
    HttpServletResponse   resp;
    String                actionName;
    String                actionmethod;
    ActionWrapper         aw;
    Iterator<Interceptor> iterator;
    Object                action;
    private String        returnValue;

    public ActionInvocation(HttpServletRequest req, HttpServletResponse resp,
            String actionName, String actionmethod, ActionWrapper aw,
            Iterator<Interceptor> iterator, Object action) {
        super();
        this.req = req;
        this.resp = resp;
        this.actionName = actionName;
        this.actionmethod = actionmethod;
        this.aw = aw;
        this.iterator = iterator;
        this.action = action;
    }

    public String invoke() {
        if (iterator.hasNext()) {
            Interceptor interceptor = iterator.next();
            try {
                interceptor.intercept(this);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("interceptor error ", e);
            }
        } else {
            if (null != aw) {
                returnValue = ActionExecutor.actionExecutor(actionName,
                        aw.getControllerClass(), actionmethod, aw.getMethod(),
                        aw.getInParamNames(), aw.getOutParamNames(), req, resp,
                        action);
                return returnValue;
            } else {
                throw new AicaiMvcException("path: action=" + actionName
                        + ",name=" + actionmethod
                        + "can not find corresponding action");
            }
            // ActionMapping am = new ActionMapping("HelloWorld",
            // "com.aicai.mvc",
            // "helloworld", null);
        }
        return returnValue;

    }
}
