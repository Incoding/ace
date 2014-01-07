package com.aicai.core;

import java.lang.reflect.Method;
import java.util.Map;

public class ActionWrapper {
    Class<?>            controllerClass;
    Method              method;
    Map<String, String> inParamNames;
    Map<String, String> outParamNames;

    public ActionWrapper(Class<?> controllerClass, Method method,
            Map<String, String> inParamNames, Map<String, String> outParamNames) {
        super();
        this.controllerClass = controllerClass;
        this.method = method;
        this.inParamNames = inParamNames;
        this.outParamNames = outParamNames;
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

    public Map<String, String> getInParamNames() {
        return inParamNames;
    }

    public void setInParamNames(Map<String, String> inParamNames) {
        this.inParamNames = inParamNames;
    }

    public Map<String, String> getOutParamNames() {
        return outParamNames;
    }

    public void setOutParamNames(Map<String, String> outParamNames) {
        this.outParamNames = outParamNames;
    }

}
