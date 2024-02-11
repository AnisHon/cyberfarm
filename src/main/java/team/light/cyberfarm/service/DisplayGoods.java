package team.light.cyberfarm.service;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.ProductColumn;
import team.light.cyberfarm.tool.SqlSessionGetter;

import java.util.List;

public interface DisplayGoods {

    int selectCategoryCount(int category);

    List<ProductColumn> selectSinglePage(int category, int beg, int limit);

}
