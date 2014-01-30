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

import com.aicai.interceptor.Interceptor;
import com.aicai.interceptor.Test2Interceptor;
import com.aicai.interceptor.TestInterceptor;
import com.aicai.reflection.ClassUtil;
import com.aicai.util.ParamUtil;
import com.aicai.util.ResultUtil;

public class ActionExecutor {
    public static ConcurrentHashMap<String, ActionWrapper> actionMaping = new ConcurrentHashMap<String, ActionWrapper>();

    // public static Iterator<Interceptor> iterator = null;

    public void init() {
        Set<Class<?>> actionClasses = ClassUtil.getClasses("com.aicai.mvc");
        ClassUtil.registerAction(actionClasses, actionMaping);
        // List<Interceptor> interList = new ArrayList<Interceptor>();
        // interList.add(new TestInterceptor());
        // interList.add(new Test2Interceptor());
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 如何高效获得namespace,name,method
        String uri = req.getRequestURI();
        // TODO string split performance
        String[] uriArray = uri.split("/");
        String actionName = uriArray[uriArray.length - 2];
        String actionmethod = uriArray[uriArray.length - 1];
        // String method = uri.substring(uri.lastIndexOf("/"));
        ActionWrapper aw = actionMaping.get(actionName + actionmethod);
        Object action;
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
            ResultUtil.dealResult(aw.getMethod(), returnValue, req, resp);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param req
     * @param resp
     * @param actionName
     * @param actionmethod
     * @param aw
     * @param iterator
     */
    // private void invocation(HttpServletRequest req, HttpServletResponse resp,
    // String actionName, String actionmethod, ActionWrapper aw,
    // Iterator<Interceptor> iterator) {
    // while (iterator.hasNext()) {
    // Interceptor interceptor = iterator.next();
    // try {
    // interceptor.intercept(null);
    // } catch (Exception e) {
    // e.printStackTrace();
    // throw new RuntimeException("interceptor error ", e);
    // }
    // }
    // if (null != aw) {
    // actionExecutor(actionName, aw.getControllerClass(), actionmethod,
    // aw.getMethod(), aw.getInParamNames(),
    // aw.getOutParamNames(), req, resp);
    // } else {
    // throw new AicaiMvcException("path: action=" + actionName + ",name="
    // + actionmethod + "can not find corresponding action");
    // }
    // // ActionMapping am = new ActionMapping("HelloWorld",
    // // "com.aicai.mvc",
    // // "helloworld", null);
    // }

    static String actionExecutor(String actionName, Class<?> actionClass,
            String actionmethod, Method method,
            Map<String, Class<?>> inParamNames,
            Map<String, String> outParamNames, HttpServletRequest req,
            HttpServletResponse resp, Object action) {
        Class<Object> c[] = null;
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
