package com.aicai.core;

public class ActionContext {
    static ThreadLocal<RequestWraper> actionContext = new ThreadLocal<RequestWraper>();

    public static RequestWraper getActionContext() {
        return actionContext.get();
    }

    public static void setActionContext(RequestWraper reqWraper) {
        actionContext.set(reqWraper);
    }
}
