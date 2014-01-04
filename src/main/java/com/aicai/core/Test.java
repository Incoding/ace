package com.aicai.core;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.aicai.annotation.Url;
import com.aicai.exception.AicaiMvcException;
import com.aicai.reflection.ClassUtil;

public class Test {
    public static ConcurrentHashMap<String, Object> actionMaping = new ConcurrentHashMap<String, Object>();

    public static void init() {

        Set<Class<?>> a = ClassUtil.getClasses("com.aicai.mvc");
        registerAction(a);
        // 过来的请求
        ActionMapping am = new ActionMapping("HelloWorld", "com.aicai.mvc",
                "helloworld", null);
        // new ActionExecutor().executor(am);

    }

    public static void main(String[] args) {
        init();
    }

    public static void registerAction(Set<Class<?>> a) {
        for (Iterator<Class<?>> iterator = a.iterator(); iterator.hasNext();) {
            Class<?> controllerClass = iterator.next();
            String controllerName = controllerClass.getName();
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (method.isAnnotationPresent(Url.class)) {
                    // 有action注解的时候
                    ActionWrapper action = new ActionWrapper(controllerClass,
                            method);
                    // TODO to be more perfermance
                    actionMaping.put(controllerName + methodName, action);
                } else if (methodName.equals("index")) {
                    // 默认index
                    ActionWrapper action = new ActionWrapper(controllerClass,
                            method);
                    actionMaping.put(controllerName + methodName, action);
                }
                // else {
                // // 不是默认index,则根据 namespace,name,method进行映射
                // }
            }

        }
    }

    public ActionResult executor(ActionMapping ap) {
        Class<Object> c[] = null;
        Method method = null;
        AicaiAction ac = null;
        Object[] ob = null;
        try {
            // TODO string concat performance
            @SuppressWarnings("unchecked")
            Class<AicaiAction> actionClass = (Class<AicaiAction>) Class
                    .forName(ap.getNamespace() + "." + ap.getName());
            method = actionClass.getDeclaredMethod(ap.getMethod(), c);
            Object action = actionClass.newInstance();
            if (action instanceof AicaiAction) {
                ac = (AicaiAction) action;
            }
            method.invoke(action, ob);
        } catch (Exception e) {
            if (!(e instanceof RuntimeException))
                throw new AicaiMvcException("action can not find");
        }
        return null;
    }
}
