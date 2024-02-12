package team.light.cyberfarm.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.light.cyberfarm.entity.User;

import java.util.List;

public interface UserMapping {

    @Select("select * from user")
    List<User> selectAll();

    @Insert("insert into user(username, password, tel, gender, level) VALUES(#{username}, #{password}, #{tel}, #{gender}, #{level})")
    int insertUser(User user);

    @Select("select count(id) from user where tel = #{tel} and password = #{password}")
    int checkUser(@Param("tel") String tel, @Param("password") String password);

    @Select("select count(tel) from user where tel = #{tel}")
    int selectTel(String tel);

    @Select("select id from user where tel = #{tel}")
    Integer selectIdByTel(String tel);

    @Select("select username from user where tel = #{tel}")
    String selectUsernameByTel(String tel);

    @Select("select * from user where id = #{id}")
    User selectUserById(int id);



    Integer aaa();

}
