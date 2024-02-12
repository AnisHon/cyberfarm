package team.light.cyberfarm.mapping;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.light.cyberfarm.entity.Cart;

import java.util.List;

public interface CartMapping {

    @Select("select c.id as id, g.name as name, c.count as count from cart c left join goods g on c.good_id = g.id where c.user_id = #{userId}")
    List<Cart> selectUserCartById(int userId);

    @Select("select count(cart.user_id) from cart where user_id = #{userId}")
    Integer selectUserCartCountById(int userId);

    @Insert("insert into cart(good_id, user_id, count) values(#{goodId}, #{userId}, #{count})")
    Integer insertUserCartById(@Param("goodId") Integer goodId, @Param("userId") Integer userId, @Param("count") Integer count);

    @Delete("delete from cart where id = #{id}")
    int deleteUserCartById(Integer id);

    @Delete("delete from cart where user_id = #{userId}")
    int emptyCart(Integer id);

}
