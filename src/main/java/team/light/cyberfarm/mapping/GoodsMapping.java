package team.light.cyberfarm.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.entity.ProductColumn;

import java.util.List;

public interface GoodsMapping {

    @Insert("insert into goods(detail_text, producer, price, category, comment, name, picture01, picture02) " +
            "value(#{detailText}, #{producer}, #{price}, #{category}, #{comment}, #{name}, #{picture01}, #{picture02})")
    void insertGood(Good good);

    List<ProductColumn> selectLimitedProductColumn(@Param("category") int category, @Param("beg") int beg, @Param("limit") int limit);

    @Select("select count(goods.category) from goods where category = #{category}")
    int getCategoryNum(int category);

}
