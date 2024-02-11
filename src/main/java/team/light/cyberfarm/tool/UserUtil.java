package team.light.cyberfarm.tool;

import jakarta.servlet.http.HttpSession;

public class UserUtil {

    public static void saveUserLogin(HttpSession session, Integer id, String username, String tel) {
        session.setAttribute("userId", id);
        session.setAttribute("userName", username);
        session.setAttribute("userTel", tel);
    }
}
