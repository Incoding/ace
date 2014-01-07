package com.aicai.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.aicai.annotation.In;
import com.aicai.annotation.Out;
import com.aicai.annotation.Url;
import com.aicai.exception.AicaiMvcException;
import com.aicai.reflection.ClassUtil;

public class Test {
    public static ConcurrentHashMap<String, Object> actionMaping = new ConcurrentHashMap<String, Object>();

    public static void init() {

        Set<Class<?>> a = ClassUtil.getClasses("com.aicai.mvc");
        registerAction(a);
        // 过来的请求
        String uri = "/aicai/name/method";
        String[] uriArray = uri.split("/");
        String actionName = uriArray[uriArray.length - 2];
        String actionmethod = uriArray[uriArray.length - 1];
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
            String controllerName = controllerClass.getSimpleName();
            Method[] methods = controllerClass.getMethods();
            Map<String, String> inparamNames = new HashMap<String, String>();
            Map<String, String> outParamNames = new HashMap<String, String>();
            int paramNumber = 0;
            Field[] fileds = controllerClass.getFields();
            for (Field filed : fileds) {
                // in param ,
                if (filed.isAnnotationPresent(In.class)) {
                    inparamNames.put(filed.getName(), filed.getName());
                }
                if (filed.isAnnotationPresent(Out.class)) {
                    outParamNames.put(filed.getName(), filed.getName());
                }
            }
            for (Method method : methods) {
                String methodName = method.getName();
                if (method.isAnnotationPresent(Url.class)) {
                    // 有action注解的时候
                    ActionWrapper action = new ActionWrapper(controllerClass,
                            method, inparamNames, outParamNames);
                    // TODO to be more perfermance
                    actionMaping.put(controllerName + methodName, action);
                } else if (methodName.equals("index")) {
                    // 默认index
                    ActionWrapper action = new ActionWrapper(controllerClass,
                            method, inparamNames, outParamNames);
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
