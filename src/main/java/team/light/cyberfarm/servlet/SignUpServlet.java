package team.light.cyberfarm.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.service.AddUser;
import team.light.cyberfarm.service.CheckUserService;
import team.light.cyberfarm.serviceImpl.AddUserImpl;
import team.light.cyberfarm.serviceImpl.CheckUserServiceImpl;
import team.light.cyberfarm.tool.JavaBeanUtil;
import team.light.cyberfarm.tool.UserUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(value = "/signUp")
public class SignUpServlet extends HttpServlet {
    private final AddUser addUser = new AddUserImpl();
    private final CheckUserService checkUser= new CheckUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = JavaBeanUtil.mappingObject(User.class, request, "setLevel");
            if (user.getPassword() == null||
                    user.getGender() == null  ||
                    user.getPassword().length() < 8 ||
                    checkUser.isTelExists(user.getTel())) {
                response.sendRedirect("./registration.html");
                return;
            }
            addUser.addNormalUser(user);
            UserUtil.saveUserLogin(request.getSession(), user.getId(), user.getUsername(), user.getTel());
            response.sendRedirect("./my_farm.html");
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            response.sendRedirect("./registration.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}