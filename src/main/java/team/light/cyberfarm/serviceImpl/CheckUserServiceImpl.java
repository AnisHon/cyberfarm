package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.mapping.UserMapping;
import team.light.cyberfarm.service.CheckUserService;
import team.light.cyberfarm.tool.SqlSessionGetter;

public class CheckUserServiceImpl implements CheckUserService {

    @Override
    public int checkUser(String tel, String password) {
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        int i = mapper.checkUser(tel, password);
        session.close();
        return i;
    }

    @Override
    public boolean isTelExists(String tel) {
        if (tel == null || tel.isEmpty()) {
            return false;
        }
        SqlSession session = SqlSessionGetter.getSession();
        UserMapping mapper = session.getMapper(UserMapping.class);
        int i = mapper.selectTel(tel);
        session.close();
        return i >= 1;
    }
}
