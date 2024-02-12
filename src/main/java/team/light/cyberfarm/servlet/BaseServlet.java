package team.light.cyberfarm.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();

        response.setCharacterEncoding("UTF-8");
        Class<? extends BaseServlet> clazz = this.getClass();

        try {
            String substring = requestURL.substring(requestURL.lastIndexOf("/") + 1);
            Method method = clazz.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
             response.getWriter().print("404");
        }


    }


}