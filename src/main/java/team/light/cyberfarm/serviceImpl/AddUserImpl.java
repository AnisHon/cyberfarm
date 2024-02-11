package team.light.cyberfarm.serviceImpl;

import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.mapping.UserMapping;
import team.light.cyberfarm.service.AddUser;
import team.light.cyberfarm.tool.SqlSessionGetter;

@Log
public class AddUserImpl implements AddUser {

    private final SqlSession session = SqlSessionGetter.getSession();
    @Override
    public int addUser(User user) {
        int result = 0;
        try {
            UserMapping mapper = session.getMapper(UserMapping.class);
            result = mapper.insertUser(user);
            session.commit();
        } catch (Exception e) {
            log.warning(e.toString());
        }
        return result;
    }
}
