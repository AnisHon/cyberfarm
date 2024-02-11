package team.light.cyberfarm.mapping;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.entity.ProductColumn;
import team.light.cyberfarm.tool.SqlSessionGetter;

import java.util.List;

public class GoodsMappingTest {
    @Test
    public void getCategoryNumTest() {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        int categoryNum = mapper.getCategoryNum(0);
        System.out.println(categoryNum);
        session.close();
    }

    @Test
    public void selectSinglePageTest() {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        List<ProductColumn> productColumns = mapper.selectLimitedProductColumn(0, 0, 3);
        System.out.println(productColumns);
    }


    @Test
    public void selectGoodByIdTest() {
        SqlSession session = SqlSessionGetter.getSession();
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        Good good = mapper.selectGoodById(16);
        System.out.println(good);
        session.close();
    }
}
