package com.aicai.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import com.aicai.interceptor.Interceptor;
import com.aicai.interceptor.Test2Interceptor;
import com.aicai.interceptor.TestInterceptor;
import com.aicai.reflection.ClassUtil;
import com.aicai.util.ParamUtil;
import com.aicai.util.ResultUtil;

public class ActionExecutor {
    public static ConcurrentHashMap<String, ActionWrapper> actionMaping = new ConcurrentHashMap<String, ActionWrapper>();

    public void init() {
        Set<Class<?>> actionClasses = ClassUtil.getClasses("com.aicai.mvc");
        ClassUtil.registerAction(actionClasses, actionMaping);
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp,
            String reqUri) {
        // TODO 如何高效获得namespace,name,method
        ActionContext.setActionContext(new RequestWraper(req, resp));
        String uri = null;
        if (reqUri != null) {
            uri = reqUri;
        } else {
            uri = req.getRequestURI();
        }
        // TODO string split performance
        String[] uriArray = StringUtil.split(uri, "/");
        String actionName = uriArray[uriArray.length - 2];
        String actionmethod = uriArray[uriArray.length - 1];
        ActionWrapper aw = actionMaping.get(actionName + actionmethod);
        Object action = null;
        try {
            action = aw.getControllerClass().getDeclaredConstructor()
                    .newInstance();
            List<Interceptor> interList = new ArrayList<Interceptor>();
            interList.add(new TestInterceptor());
            interList.add(new Test2Interceptor());
            Iterator<Interceptor> iterator = null;
            iterator = interList.iterator();
            String returnValue = new ActionInvocation(req, resp, actionName,
                    actionmethod, aw, iterator, action).invoke();
            ParamUtil.dealOutParam(aw.getOutParamNames(), req,
                    aw.getControllerClass(), action);
            ResultUtil.dealResult(aw.getMethod(), returnValue, req, resp, this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    static String actionExecutor(String actionName, Class<?> actionClass,
            String actionmethod, Method method,
            Map<String, Class<?>> inParamNames,
            Map<String, String> outParamNames, HttpServletRequest req,
            HttpServletResponse resp, Object action) {
        Object[] ob = null;
        try {
            /*
             * // TODO string concat performance Class<AicaiAction> actionClass
             * = (Class<AicaiAction>) Class .forName(actionName + "." +
             * actionmethod); method =
             * actionClass.getDeclaredMethod(actionmethod, c); // TODO more
             * reflect performance
             * Thread.currentThread().getContextClassLoader().loadClass(name)
             */
            ParamUtil.dealInParam(inParamNames, req, actionClass, action);
            Object returnValue = method.invoke(action, ob);
            return (String) returnValue;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("action can not find", e);
        }

    }
}
