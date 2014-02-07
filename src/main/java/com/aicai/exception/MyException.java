package com.aicai.exception;

import java.util.ArrayList;
import java.util.List;

public class MyException extends Exception {
    // 容纳所有异常
    private List<Throwable> causes = new ArrayList<Throwable>();

    // 构造函数，传递一个异常列表
    public MyException(List<? extends Throwable> _causes) {
        causes.addAll(_causes);
    }

    // 读取所有的异常
    public List<Throwable> getException() {
        return causes;
    }

    public static void main(String[] args) {
        try {
            doStuff();
        } catch (MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
        }
    }

    public static void doStuff() throws MyException {
        List<Throwable> list = new ArrayList<Throwable>();
        // 第一个逻辑片段
        try {
            // Do something
            System.out.println("1");
            throw new RuntimeException("1的异常");
        } catch (Exception e) {
            list.add(e);
        }

        // 第二个逻辑片段
        try {
            // Do something
            System.out.println("2");
            throw new NullPointerException("ss");
        } catch (Exception e) {
            list.add(e);
        }

        // 检查是否有必要抛出异常
        if (list.size() > 0) {
            throw new MyException(list);
        }
    }
}