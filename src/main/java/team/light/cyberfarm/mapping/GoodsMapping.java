package team.light.cyberfarm.mapping;

import org.apache.ibatis.annotations.*;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.entity.ProductColumn;

import java.util.List;

public interface GoodsMapping {

    @Insert("insert into goods(detail_text, producer, price, category, comment, name, picture01, picture02) " +
            "value(#{detailText}, #{producer}, #{price}, #{category}, #{comment}, #{name}, #{picture01}, #{picture02})")
    void insertGood(Good good);

    List<ProductColumn> selectLimitedProductColumn(@Param("category") int category, @Param("beg") int beg, @Param("limit") int limit);


    int getCategoryNum(int category);

    @Select("select * from goods where id = #{id}")
    Good selectGoodById(int id);

}
