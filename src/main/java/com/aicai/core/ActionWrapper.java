package com.aicai.core;

import java.lang.reflect.Method;

public class ActionWrapper {
    Class<?> controllerClass;
    Method   method;

    public ActionWrapper(Class<?> controllerClass2, Method method) {
        super();
        this.controllerClass = controllerClass2;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
