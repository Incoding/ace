package com.aicai.mvc;

import com.aicai.annotation.Action;
import com.aicai.annotation.Url;

@Action
public class HelloWorld {

    public String index() {
        System.out.println("hello world ,hello aicai mvc");
        return null;
    }

    @Url
    public String login() {
        return null;
    }
}
