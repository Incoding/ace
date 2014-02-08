package com.aicai.core;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.aicai.reflection.ClassUtil;

public class Test {
    public static ConcurrentHashMap<String, ActionWrapper> actionMaping = new ConcurrentHashMap<String, ActionWrapper>();

    public static void init() {
        Set<Class<?>> actionClasses = ClassUtil.getClasses("com.aicai.mvc");
        ClassUtil.registerAction(actionClasses, actionMaping);
    }

    public static void main(String[] args) {
        init();
    }

    public static void registerAction(Set<Class<?>> a) {
        // for (Iterator<Class<?>> iterator = a.iterator(); iterator.hasNext();)
        // {
        // Class<?> controllerClass = iterator.next();
        // String controllerName = controllerClass.getSimpleName();
        // Method[] methods = controllerClass.getMethods();
        // Map<String, String> inparamNames = new HashMap<String, String>();
        // Map<String, String> outParamNames = new HashMap<String, String>();
        // int paramNumber = 0;
        // Field[] fileds = controllerClass.getFields();
        // for (Field filed : fileds) {
        // // in param ,
        // if (filed.isAnnotationPresent(In.class)) {
        // inparamNames.put(filed.getName(), filed.getName());
        // }
        // if (filed.isAnnotationPresent(Out.class)) {
        // outParamNames.put(filed.getName(), filed.getName());
        // }
        // }
        // for (Method method : methods) {
        // String methodName = method.getName();
        // if (method.isAnnotationPresent(Url.class)) {
        // // 有action注解的时候
        // ActionWrapper action = new ActionWrapper(controllerClass,
        // method, inparamNames, outParamNames);
        // // TODO to be more perfermance
        // actionMaping.put(controllerName + methodName, action);
        // } else if (methodName.equals("index")) {
        // // 默认index
        // ActionWrapper action = new ActionWrapper(controllerClass,
        // method, inparamNames, outParamNames);
        // actionMaping.put(controllerName + methodName, action);
        // }
        // // else {
        // // // 不是默认index,则根据 namespace,name,method进行映射
        // // }
        // }
        //
        // }
    }

    public ActionResult executor(ActionMapping ap) {
        System.out.println("add from home");
        
        return null;
    }
}
