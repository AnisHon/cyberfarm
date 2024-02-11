package team.light.cyberfarm.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import team.light.cyberfarm.service.CheckUserService;
import team.light.cyberfarm.service.UserService;
import team.light.cyberfarm.serviceImpl.CheckUserServiceImpl;
import team.light.cyberfarm.serviceImpl.UserServiceImpl;
import team.light.cyberfarm.tool.UserUtil;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private final CheckUserService checkUser =new CheckUserServiceImpl();
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tel = request.getParameter("tel");
        String password = request.getParameter("password");

        if (tel == null || password == null || checkUser.checkUser(tel, password) == 0) {
            response.sendRedirect("./login.html?error_login=on");
            return;
        }

        Integer userId = userService.getUserId(tel);
        String username = userService.getUsername(tel);

        HttpSession session = request.getSession();
        UserUtil.saveUserLogin(session,userId, username, tel);
        response.sendRedirect("./my_farm.html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}