package team.light.cyberfarm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import team.light.cyberfarm.tool.UserUtil;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {

    private static final List<String> ignorePath = List.of(
                    "css/", "bootstrap/", "fonts/", "icons/", "images/", "js/", "lib/",
                    "/about_us.html", "/index.html", "/registration.html", "/login.html", "/login", "/signUp",
                    "/404.html", "/user/selectUsername", "/legal/checkTel"
            );


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURL = req.getRequestURL().toString();

        if (UserUtil.isLogin(req.getSession())) {
            if (requestURL.contains("registration.html") || requestURL.contains("login.html")) {
                resp.sendRedirect(req.getContextPath() + "./my_farm.html");
            }
            chain.doFilter(request, response);
            return;
        } else {

            if (requestURL.endsWith("/")) {
                chain.doFilter(request, response);
                return;
            }
            for (String s : ignorePath) {
                if (requestURL.contains(s)) {
                    chain.doFilter(request, response);

                    return;
                }
            }
            resp.sendRedirect( "./login.html");
        }

        chain.doFilter(request, response);
    }
}
