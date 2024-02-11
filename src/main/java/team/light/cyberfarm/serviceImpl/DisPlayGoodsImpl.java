package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.ProductColumn;
import team.light.cyberfarm.mapping.GoodsMapping;
import team.light.cyberfarm.service.DisplayGoods;
import team.light.cyberfarm.tool.SqlSessionGetter;

import java.util.List;

public class DisPlayGoodsImpl implements DisplayGoods {
    private SqlSession session = SqlSessionGetter.getSession();
    @Override
    public int selectCategoryCount(int category) {
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        return mapper.getCategoryNum(category);
    }

    @Override
    public List<ProductColumn> selectSinglePage(int category, int beg, int limit) {
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        return mapper.selectLimitedProductColumn(category, beg, limit);
    }
}
