package team.light.cyberfarm;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "本页用于测试用途" + "</h1>");
        out.println("</body></html>");

//        UserServiceImpl userService = new UserServiceImpl();
//        User user = new User();
//        user.setTel("1230");
//        user.setUsername("NormalUser");
//        user.setPassword("Han123456");
//        user.setGender(10);
//        userService.changeUserInfo(user, 12);


    }

}