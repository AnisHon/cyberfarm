package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.mapping.UserMapping;
import team.light.cyberfarm.service.UserService;
import team.light.cyberfarm.tool.SqlSessionGetter;

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
}
