package team.light.cyberfarm.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import team.light.cyberfarm.entity.User;

import java.util.List;

public interface UserMapping {

    @Select("select * from user")
    List<User> selectAll();

    @Insert("insert into user(username, password, tel, gender, level) VALUES(#{username}, #{password}, #{tel}, #{gender}, #{level})")
    int insertUser(User user);

}
