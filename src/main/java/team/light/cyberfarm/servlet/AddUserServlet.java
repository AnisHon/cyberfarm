package team.light.cyberfarm.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.service.AddUser;
import team.light.cyberfarm.serviceImpl.AddUserImpl;
import team.light.cyberfarm.tool.JavaBeanUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@WebServlet(value = "/mod/addUser")
public class AddUserServlet extends HttpServlet {
    private final AddUser addUser = new AddUserImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        int result = 0;
        try {
            User user = JavaBeanUtil.mappingObject(User.class, null, new HashMap<>(), request);
            result = addUser.addUser(user);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            writer.println("<p>" + e + "</p>");
        }
        if (result == 0) {
            writer.println("<h1>提交失败，如果上面有一对英文说明这里出现问题，请及时联系我，否则可能是你使用了重复的手机号码或者有表单项没有填<h1>");
        } else {
            writer.println("<h1>提交成功<h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}