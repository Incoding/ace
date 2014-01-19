package com.aicai.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultUtil {

    public static void dealResult(Object returnValue, HttpServletRequest req,
            HttpServletResponse resp) {
        // now this method only can deal jsp
        // TODO
        if (returnValue instanceof String) {
            try {
                req.getRequestDispatcher((String) returnValue).forward(req,
                        resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
