package com.aicai.mvc;

import javax.servlet.http.HttpServletRequest;

import com.aicai.annotation.Action;
import com.aicai.annotation.In;
import com.aicai.annotation.Out;
import com.aicai.annotation.RT;
import com.aicai.annotation.Result;
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

    /**
     * 响应jsp
     * 
     * @return
     */
    public String index() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name=" + name);
        System.out.println("hello world -->age=" + age);
        return "/helloworld.jsp";
    }

    /**
     * 响应ajax
     * 
     * @return
     */
    @Url
    @Result(type = RT.AJAX)
    public String renderAjax() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name=" + name);
        System.out.println("hello world -->age=" + age);
        return "this is ajax response from server:name=" + name + ",age=" + age;
    }

    /**
     * 响应请求action (重定向需要自己加上 context的前缀)
     * 
     * @return
     */
    @Url
    @Result(type = RT.REDIRECT)
    public String login() {
        System.out.println("login redirect method");
        return "/HelloWorld/renderAjax";
    }

    /**
     * 响应请求action
     * 
     * @return
     */
    @Url
    @Result(type = RT.ACTION)
    public String loginForward() {
        System.out.println("login forward method");
        return "/HelloWorld/renderAjax";
    }

    /**
     * action中获取请求参数
     * 
     * @return
     */
    @Url
    @Result(type = RT.ACTION)
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
