package team.light.cyberfarm.service;


import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.entity.ProductColumn;


import java.util.List;
import java.util.Map;


public interface DisplayGoods {

    int selectCategoryCount(int category);

    List<ProductColumn> selectSinglePage(int category, int beg, int limit);

    Map<String, Object> selectToMap(int category, int beg, int limit);

    Good selectGood(int id);

}
