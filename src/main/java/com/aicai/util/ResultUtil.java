package com.aicai.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aicai.annotation.Ajax;

public class ResultUtil {

    private static final String UTF_8 = "UTF-8";

    public static void dealResult(Method method, Object returnValue,
            HttpServletRequest req, HttpServletResponse resp) {
        // now this method only can deal jsp
        // TODO
        if (returnValue instanceof String) {
            String result = (String) returnValue;
            if (method.isAnnotationPresent(Ajax.class)) {
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

            // try {
            // resp.sendRedirect(result);
            // } catch (IOException e) {
            // throw new RuntimeException("render jsp failed");
            // }
            // return;
            // default the way of render is jsp;
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
