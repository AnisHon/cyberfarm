package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.entity.ProductColumn;
import team.light.cyberfarm.mapping.GoodsMapping;
import team.light.cyberfarm.service.DisplayGoods;
import team.light.cyberfarm.tool.SqlSessionGetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisPlayGoodsImpl implements DisplayGoods {
    @Override
    public int selectCategoryCount(int category) {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        int categoryNum = mapper.getCategoryNum(category);
        session.close();
        return categoryNum;
    }

    @Override
    public List<ProductColumn> selectSinglePage(int category, int beg, int limit) {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        List<ProductColumn> productColumns = mapper.selectLimitedProductColumn(category, beg, limit);
        session.close();
        return productColumns;
    }


    @Override
    public Map<String, Object> selectToMap(int category, int beg, int limit) {
        int count = selectCategoryCount(category);
        List<ProductColumn> columns = selectSinglePage(category, beg, limit);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", count);
        resultMap.put("columns", columns);
        return resultMap;
    }

    @Override
    public Good selectGood(int id) {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        Good good = mapper.selectGoodById(id);
        session.close();
        return good;
    }
}
