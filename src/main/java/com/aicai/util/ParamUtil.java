package com.aicai.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.aicai.convert.TypeConverter;

public class ParamUtil {
    public static final void dealInParam(Map<String, Class<?>> inParamNames,
            HttpServletRequest req, Class<?> actionClass, Object action) {
        Iterator<Entry<String, Class<?>>> itIn = inParamNames.entrySet()
                .iterator();
        while (itIn.hasNext()) {
            Map.Entry<String, Class<?>> entry = itIn.next();
            String value = req.getParameter(entry.getKey());
            Object converted = TypeConverter.convert(value, entry.getValue());
            Field f;
            try {
                f = actionClass.getDeclaredField(entry.getKey());
                f.setAccessible(true);
                f.set(action, converted);
                f.setAccessible(false);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static final void dealOutParam(Map<String, String> outParamNames,
            HttpServletRequest req, Class<?> actionClass, Object action) {
        Iterator<Entry<String, String>> itOut = outParamNames.entrySet()
                .iterator();
        while (itOut.hasNext()) {
            Map.Entry<String, String> entry = itOut.next();
            Field f;
            try {
                f = actionClass.getDeclaredField(entry.getKey());
                // TODO check if the filed is primitive type; if not then deal
                // it ;
                // f.getDeclaringClass().isPrimitive();
                f.setAccessible(true);
                if (f.get(action) != null)
                    req.setAttribute(entry.getKey(), f.get(action));
                f.setAccessible(false);
                // System.out.println("req参数的"
                // + "变量类型是"
                // + req.getAttribute(entry.getKey()).getClass()
                // .getSimpleName() + entry.getKey() + "变量值是"
                // + req.getAttribute(entry.getKey()).toString());
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
