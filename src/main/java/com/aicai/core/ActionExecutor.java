package com.aicai.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aicai.exception.AicaiMvcException;
import com.aicai.reflection.ClassUtil;

public class ActionExecutor {
    public static ConcurrentHashMap<String, ActionWrapper> actionMaping = new ConcurrentHashMap<String, ActionWrapper>();

    public void init() {
        Set<Class<?>> actionClasses = ClassUtil.getClasses("com.aicai.mvc");
        ClassUtil.registerAction(actionClasses, actionMaping);
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
        if (null != aw) {
            actionExecutor(actionName, aw.getControllerClass(), actionmethod,
                    aw.getMethod(), aw.getInParamNames(),
                    aw.getOutParamNames(), req, resp);
        } else {
            throw new AicaiMvcException("path" + actionName + actionmethod
                    + "can not find corresponding action");
        }
        // ActionMapping am = new ActionMapping("HelloWorld", "com.aicai.mvc",
        // "helloworld", null);
    }

    private void actionExecutor(String actionName, Class<?> actionClass,
            String actionmethod, Method method,
            Map<String, String> inParamNames,
            Map<String, String> outParamNames, HttpServletRequest req,
            HttpServletResponse resp) {
        Class<Object> c[] = null;
        Object[] ob = null;
        try {
            // TODO string concat performance
            // Class<AicaiAction> actionClass = (Class<AicaiAction>) Class
            // .forName(actionName + "." + actionmethod);
            // method = actionClass.getDeclaredMethod(actionmethod, c);
            // TODO more reflect performance
            // Thread.currentThread().getContextClassLoader().loadClass(name)
            Object action = actionClass.getDeclaredConstructor().newInstance();
            Iterator<Entry<String, String>> itIn = inParamNames.entrySet()
                    .iterator();
            while (itIn.hasNext()) {
                Map.Entry<String, String> entry = itIn.next();
                String value = req.getParameter(entry.getKey());
                Field f = actionClass.getDeclaredField(entry.getKey());
                f.setAccessible(true);
                f.set(action, value);
                f.setAccessible(false);
            }
            Object returnValue = method.invoke(action, ob);
            Iterator<Entry<String, String>> itOut = outParamNames.entrySet()
                    .iterator();
            while (itOut.hasNext()) {
                Map.Entry<String, String> entry = itOut.next();
                Field f = actionClass.getDeclaredField(entry.getKey());
                // TODO check if the filed is primitive type; if not then deal
                // it ;
                // f.getDeclaringClass().isPrimitive();
                f.setAccessible(true);
                if (f.get(action) != null)
                    req.setAttribute(entry.getKey(), f.get(action));
                f.setAccessible(false);
                System.out.println("req参数" + entry.getKey() + "的变量值是"
                        + req.getAttribute(entry.getKey()).toString());
            }
            if (returnValue instanceof String) {
                req.getRequestDispatcher((String) returnValue).forward(req,
                        resp);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("action can not find");
        }

    }
}
