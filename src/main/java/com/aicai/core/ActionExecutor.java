package com.aicai.core;

import java.lang.reflect.Method;
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
        String actionName = uriArray[uri.length() - 2];
        String actionmethod = uriArray[uri.length() - 1];
        // String method = uri.substring(uri.lastIndexOf("/"));
        ActionWrapper aw = actionMaping.get(actionName + actionmethod);
        if (null != aw) {
            actionExecutor(actionName, aw.getControllerClass(), actionmethod,
                    aw.getMethod());
        } else {
            throw new AicaiMvcException("action can not find .");
        }
        // ActionMapping am = new ActionMapping("HelloWorld", "com.aicai.mvc",
        // "helloworld", null);
    }

    private void actionExecutor(String actionName, Class<?> actionClass,
            String actionmethod, Method method) {
        Class<Object> c[] = null;
        AicaiAction ac = null;
        Object[] ob = null;
        try {
            // TODO string concat performance
            @SuppressWarnings("unchecked")
            // Class<AicaiAction> actionClass = (Class<AicaiAction>) Class
            // .forName(actionName + "." + actionmethod);
            // method = actionClass.getDeclaredMethod(actionmethod, c);
            Object action = actionClass.newInstance();
            if (action instanceof AicaiAction) {
                ac = (AicaiAction) action;
            }
            method.invoke(action, ob);
        } catch (Exception e) {
            if (!(e instanceof RuntimeException))
                throw new AicaiMvcException("action can not find");
        }

    }

}
