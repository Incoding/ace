package com.aicai.core;

import java.lang.reflect.Method;

import com.aicai.exception.AicaiMvcException;

public class ActionExecutor {
    public static void main(String[] args) {
        ActionMapping am = new ActionMapping("AicaiAction", "com.aicai.core",
                "helloworld", null);
        new ActionExecutor().executor(am);
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
