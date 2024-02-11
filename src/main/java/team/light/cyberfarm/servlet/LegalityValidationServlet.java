package team.light.cyberfarm.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import team.light.cyberfarm.service.CheckUserService;
import team.light.cyberfarm.serviceImpl.CheckUserServiceImpl;

import java.io.IOException;

@WebServlet("/legal/*")
public class LegalityValidationServlet extends BaseServlet {

    private final CheckUserService checkUser = new CheckUserServiceImpl();

    public void checkTel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tel = request.getParameter("tel");
        String returnMessage = "Success";
        if (checkUser.isTelExists(tel)) {

            returnMessage = "Fail";
        }
        response.getWriter().print(returnMessage);

    }

}
