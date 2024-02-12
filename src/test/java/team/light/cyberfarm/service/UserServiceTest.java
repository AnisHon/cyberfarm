package team.light.cyberfarm.service;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.mapping.UserMapping;
import team.light.cyberfarm.serviceImpl.UserServiceImpl;
import team.light.cyberfarm.tool.SqlSessionGetter;

public class UserServiceTest {

    @Test
    public void updateUserInfoTest() {
        UserServiceImpl userService = new UserServiceImpl();
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);

        User clone = new User();


    }


}
