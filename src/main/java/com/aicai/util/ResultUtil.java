package com.aicai.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aicai.annotation.RT;
import com.aicai.annotation.Result;
import com.aicai.core.ActionExecutor;

public class ResultUtil {

    private static final String UTF_8 = "UTF-8";

    public static void dealResult(Method method, Object returnValue,
            HttpServletRequest req, HttpServletResponse resp, ActionExecutor ace) {
        if (returnValue instanceof String) {
            String result = (String) returnValue;
            // if
            // (method.getDeclaringClass().isAnnotationPresent(Results.class)) {
            // Result[] results = method.getAnnotation(Results.class).value();
            // }
            if (method.isAnnotationPresent(Result.class)
                    && method.getAnnotation(Result.class).type() == RT.AJAX) {
                PrintWriter writer = null;
                resp.setHeader("Pragma", "no-cache");
                resp.setHeader("Cache-Control", "no-cache");
                resp.setDateHeader("Expires", 0);
                try {
                    writer = resp.getWriter();
                    resp.setCharacterEncoding(UTF_8);
                    writer.write(result);
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException("render ajax failed");
                } finally {
                    if (writer != null)
                        writer.close();
                }
                return;
            }
            if (method.isAnnotationPresent(Result.class)
                    && method.getAnnotation(Result.class).type() == RT.ACTION) {
                ace.execute(req, resp, result);
                return;
            }
            if (method.isAnnotationPresent(Result.class)
                    && method.getAnnotation(Result.class).type() == RT.REDIRECT) {
                try {
                    resp.sendRedirect(result);
                } catch (IOException e) {
                    throw new RuntimeException("render jsp failed");
                }
                return;
            }

            // default the way of render is jsp;
            // if (method.isAnnotationPresent(Result.class)
            // && method.getAnnotation(Result.class).type() == RT.JSP) {
            // try {
            // req.getRequestDispatcher(result).forward(req, resp);
            // } catch (ServletException e) {
            // throw new RuntimeException("render jsp failed");
            // } catch (IOException e) {
            // throw new RuntimeException("render jsp failed");
            // }
            // return;
            // }
            try {
                req.getRequestDispatcher(result).forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException("render jsp failed");
            } catch (IOException e) {
                throw new RuntimeException("render jsp failed");
            }
            return;
        }

    }
}
