package com.aicai.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public String       name         = "wk";
    public int          primitiveAge = 1;
    public Integer      wrapperAge   = 2;
    public List<String> listType     = new ArrayList<String>();

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchFieldException, SecurityException {
        Test2 test = new Test2();
        Class a = Class.forName("com.aicai.core.Test2");
        Field primitiveAge = a.getField("primitiveAge");
        Field wrapperAge = a.getField("wrapperAge");
        Field listType = a.getField("listType");
        System.out.println(primitiveAge.getClass());
        System.out.println(primitiveAge.getDeclaringClass());
        System.out.println(primitiveAge.getGenericType());
        System.out.println(primitiveAge.getType());
        System.out.println("----------");
        System.out.println(wrapperAge.getClass());
        System.out.println(wrapperAge.getDeclaringClass());
        System.out.println(wrapperAge.getGenericType());
        System.out.println(wrapperAge.getType());
        System.out.println("----------");
        System.out.println(listType.getClass());
        System.out.println(listType.getDeclaringClass());
        System.out.println(listType.getGenericType());
        System.out.println(listType.getType());
        System.out.println("----------");
        System.out.println(primitiveAge.getType() == int.class);
        System.out.println(primitiveAge.getType() == Integer.class);
        System.out.println(primitiveAge.getType() == Integer.TYPE);
        System.out.println("----------");
        System.out.println(wrapperAge.getType() == int.class);
        System.out.println(wrapperAge.getType() == Integer.class);
        System.out.println(wrapperAge.getType() == Integer.TYPE);
        System.out.println("----------------");
        System.out.println(int.class == Integer.TYPE);
    }
}
