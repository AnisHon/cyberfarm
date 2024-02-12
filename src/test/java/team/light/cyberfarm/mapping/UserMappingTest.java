package team.light.cyberfarm.mapping;

import com.alibaba.fastjson2.JSON;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.serviceImpl.UserServiceImpl;
import team.light.cyberfarm.tool.SqlSessionGetter;

public class UserMappingTest {

    @Test
    public void checkUserTest() {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        int i = mapper.checkUser("AnisHan", "Han1234560");
        System.out.println(i);
    }

    @Test
    public void isTelExistTest() {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        int i = mapper.selectTel("0");
        System.out.println(i);
        System.out.println(JSON.toJSONString(false));
    }

    @Test
    public void selectIdByTelTest() {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        System.out.println(mapper.selectIdByTel(1 + ""));
        System.out.println(mapper.selectIdByTel("114514"));
    }

    @Test
    public void selectUsernameByTelTest() {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        System.out.println(mapper.selectUsernameByTel("1"));
        System.out.println(mapper.selectUsernameByTel("114514"));
    }


    @Test
    public void updateUserInfoTest() {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        user.setTel("1230");
        user.setUsername("NormalUser");
        user.setPassword("Han123456");
        user.setGender(2);
        userService.changeUserInfo(user, 12);
    }

}
