package team.light.cyberfarm.tool;

import jakarta.servlet.http.HttpSession;

public class UserUtil {

    public static void saveUserLogin(HttpSession session, Integer id, String username, String tel) {
        if (id != null) {
            session.setAttribute("userId", id);
        }

        if (username != null && !username.isEmpty() && !username.isBlank()) {
            session.setAttribute("userName", username);
        }

        if (tel != null && !tel.isEmpty() && !tel.isBlank()) {
            session.setAttribute("userTel", tel);
        }



    }

    public static void logoutUser(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userTel");
    }

    public static boolean isLogin(HttpSession session) {
        return session.getAttribute("userId") != null ||
                session.getAttribute("userName")!= null ||
                session.getAttribute("userTel")!= null;
    }

}
