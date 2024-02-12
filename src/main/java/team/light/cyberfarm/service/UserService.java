package team.light.cyberfarm.service;

import jakarta.servlet.http.HttpSession;
import team.light.cyberfarm.entity.User;

public interface UserService {

    String getUsername(String tel);
    Integer getUserId(String tel);

    User getUser(int id);

    void logout(HttpSession session);

    boolean changeUserInfo(User user, Integer id);

}
