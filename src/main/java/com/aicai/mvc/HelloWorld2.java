package com.aicai.mvc;

import com.aicai.annotation.Action;
import com.aicai.annotation.Ajax;

@Action
public class HelloWorld2 {
    public String name;
    public String age;
    public String email;

    @Ajax
    public String login() {
        if (name == "wk") {
            System.out.println("wk");
        } else {
            System.out.println("login error");
        }
        return "OK";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
