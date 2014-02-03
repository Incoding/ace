package com.aicai.mvc;

import javax.servlet.http.HttpServletRequest;

import com.aicai.annotation.Action;
import com.aicai.annotation.ActionResult;
import com.aicai.annotation.Ajax;
import com.aicai.annotation.In;
import com.aicai.annotation.Out;
import com.aicai.annotation.Url;
import com.aicai.core.ActionContext;

@Action
public class HelloWorld {
    @In
    @Out
    private String  name;

    @In
    @Out
    private Integer age;

    public String index() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name=" + name);
        System.out.println("hello world -->age=" + age);
        return "/helloworld.jsp";
    }

    @Url
    @Ajax
    public String renderAjax() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name=" + name);
        System.out.println("hello world -->age=" + age);
        return "this is ajax response from server:name=" + name + ",age=" + age;
    }

    /**
     * redirect request
     * 
     * @return
     */
    @Url
    @ActionResult
    public String login() {
        System.out.println("login redirect method");
        return "/aicai-mvc/HelloWorld/renderAjax";
    }

    /**
     * forward request
     * 
     * @return
     */
    @Url
    @ActionResult
    public String loginForward() {
        System.out.println("login forward method");
        return "/HelloWorld/renderAjax";
    }

    @Url
    @ActionResult
    public String actioncontext() {
        HttpServletRequest req = ActionContext.getActionContext().getReq();
        System.out.println("actioncontext method,path is ==>"
                + req.getRequestURI());
        System.out.println("this thread's name is==>"
                + req.getParameter("name"));
        System.out.println("this thread's age is==>" + req.getParameter("age"));
        return "/HelloWorld/renderAjax";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
