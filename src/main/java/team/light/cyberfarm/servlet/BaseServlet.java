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

        //todo delete this
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许跨域的请求方法GET, POST, HEAD 等
        response.setHeader("Access-Control-Allow-Methods", "*");
        //重新预检验跨域的缓存时间 (s)
        response.setHeader("Access-Control-Max-Age", "4200");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            String substring = requestURL.substring(requestURL.lastIndexOf("/") + 1);
            Method method = clazz.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
             response.getWriter().println("404");
        }


    }


}