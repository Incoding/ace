package com.aicai.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Stu {
    private int     id;
    private Integer id2;
    private String  stuName;

    public Stu() {
    }

    public Stu(int id, String stuName) {
        this.id = id;
        this.stuName = stuName;
    }

    public void setAll(int id, String stuName) {
        this.id = id;
        this.stuName = stuName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String toString() {
        return id + stuName;
    }

    //
    public static void main(String[] args) throws ClassNotFoundException {

        Class cla = Class.forName("com.aicai.core.Stu");
        // 解析属性信息
        Field[] f = cla.getDeclaredFields();
        for (Field field : f) {
            System.out.println("属性=" + field.toString());
            System.out.println("数据类型＝" + (field.getType() == Integer.class));
            // System.out.println("是否是原生类型"
            // + field.getDeclaringClass().isPrimitive());
            // System.out.println("数据类型222＝" + field.getGenericType());
            System.out.println("属性名＝" + field.getName());
            // int mod = field.getModifiers();
            // System.out.println("属性修饰符＝" + Modifier.toString(mod));
            System.out.println("--------------------");
        }
        // 解析方法信息
        Method[] methodlist = cla.getDeclaredMethods();
        // for (Method method : methodlist) {
        // System.out.println("---------------");
        // System.out.println("方法＝" + method.toString());
        // System.out.println("方法名＝" + method.getName());
        // int mod = method.getModifiers();
        // System.out.println("方法修饰符＝" + Modifier.toString(mod));
        // System.out.println("方法参数列表");
        // Class pts[] = method.getParameterTypes();
        // for (int i = 0; i < pts.length; i++) {
        // Class class1 = pts[i];
        // if (i != 0) {
        // System.out.println(class1);
        // }
        // System.out.println("返回类型" + method.getReturnType());
        // }
        // }
    }
}