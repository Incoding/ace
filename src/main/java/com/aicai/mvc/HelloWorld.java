package com.aicai.mvc;

import com.aicai.annotation.Action;
import com.aicai.annotation.In;
import com.aicai.annotation.Out;
import com.aicai.annotation.Url;

@Action
public class HelloWorld {
    @In
    @Out
    private String name;

    @In
    @Out
    private String age;

    public String index() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name" + name);
        System.out.println("hello world -->age" + age);
        return "/helloworld.jsp";
    }

    @Url
    public String login() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
