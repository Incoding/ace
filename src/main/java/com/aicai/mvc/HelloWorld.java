package com.aicai.mvc;

import com.aicai.annotation.Action;
import com.aicai.annotation.In;
import com.aicai.annotation.Url;

@Action
public class HelloWorld {
    @In
    private String name;
    @In
    private String age;

    public String index() {
        System.out.println("hello world ,hello aicai mvc");
        System.out.println("hello world -->name" + name);
        System.out.println("hello world -->age" + age);
        return null;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
