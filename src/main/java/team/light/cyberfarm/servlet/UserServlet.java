package team.light.cyberfarm.servlet;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.service.UserService;
import team.light.cyberfarm.serviceImpl.UserServiceImpl;
import team.light.cyberfarm.tool.JavaBeanUtil;
import team.light.cyberfarm.tool.UserUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@Log
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private final UserService userService = new UserServiceImpl();
    public void selectUser(HttpServletRequest request, HttpServletResponse response) {
        Object id = request.getSession().getAttribute("userId");
        if (id == null) {
            return;
        }

        User user = userService.getUser((Integer) id);
        String userJson = JSON.toJSONString(user);

        response.setContentType("text/json;charset=UTF-8");
        try {
            response.getWriter().write(userJson);
        } catch (IOException e) {
            log.severe(e.toString());
        }

    }

    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        if (id == null) {
            return;
        }
        try {
            User user = JavaBeanUtil.mappingObject(User.class, request, "setLevel");
            boolean result = userService.changeUserInfo(user, id);
            if (result) {
                UserUtil.saveUserLogin(session, id, user.getUsername(), user.getTel());
            }

            response.sendRedirect("../my_farm.html");
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return;
        } catch (IOException e) {
            log.severe(e.toString());
        }

    }

    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null) {
            UserUtil.logoutUser(session);
            try {
                response.sendRedirect("../index.html");
            } catch (IOException e) {
                log.severe(e.toString());
            }
        }
    }

    public void selectUsername(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setContentType("text/json;charset=UTF-8");
        Integer id = (Integer) session.getAttribute("userId");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        if (id == null) {
            stringObjectHashMap.put("isLogin", false);
            try {
                response.getWriter().write(JSON.toJSONString(stringObjectHashMap));
            } catch (IOException e) {
               log.severe(e.toString());
            }
            return;
        }
        String username = (String) session.getAttribute("userName");
        try {


            stringObjectHashMap.put("username", username);
            stringObjectHashMap.put("isLogin", true);
            response.getWriter().write(JSON.toJSONString(stringObjectHashMap));
        } catch (IOException e) {
            log.severe(e.toString());
        }
    }

}
