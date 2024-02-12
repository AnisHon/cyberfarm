package team.light.cyberfarm.serviceImpl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.mapping.GoodsMapping;
import team.light.cyberfarm.mapping.UserMapping;
import team.light.cyberfarm.service.UserService;
import team.light.cyberfarm.tool.SqlSessionGetter;
import team.light.cyberfarm.tool.UserUtil;



public class UserServiceImpl implements UserService {
    @Override
    public String getUsername(String tel) {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        String s = mapper.selectUsernameByTel(tel);
        session.close();
        return s;
    }

    @Override
    public Integer getUserId(String tel) {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        Integer id = mapper.selectIdByTel(tel);
        session.close();
        return id;
    }

    @Override
    public User getUser(int id) {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        User user = mapper.selectUserById(id);
        session.close();
        return user;
    }

    @Override
    public void logout(HttpSession session) {
        UserUtil.logoutUser(session);
    }

    @Override
    public boolean changeUserInfo(User newUser, Integer id) {
        User oldUser = getUser(id);
        if (newUser.getTel().equals(oldUser.getTel())) {
            newUser.setTel(null);
        }
        if (newUser.getGender().equals(oldUser.getGender())) {
            newUser.setGender(null);
        }
        if (newUser.getUsername().equals(oldUser.getUsername())) {
            newUser.setUsername(null);
        }
        if (newUser.getPassword().equals(oldUser.getPassword()) || newUser.getPassword().isEmpty()) {
            newUser.setPassword(null);
        }

        if (newUser.getPassword() == null && newUser.getTel() == null &&
                newUser.getUsername() == null && newUser.getGender() == null) {
            return false;
        }

        SqlSession session = SqlSessionGetter.getSession();

        try (session) {
            GoodsMapping mapper = session.getMapper(GoodsMapping.class);
            mapper.updateUserInfo(newUser, id);
            session.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
