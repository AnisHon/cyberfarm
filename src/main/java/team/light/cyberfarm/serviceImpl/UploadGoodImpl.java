package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.mapping.GoodsMapping;
import team.light.cyberfarm.service.UploadGood;
import team.light.cyberfarm.tool.SqlSessionGetter;

public class UploadGoodImpl implements UploadGood {

    private final SqlSession session = SqlSessionGetter.getSession();

    @Override
    public void upload(Good good) {
        GoodsMapping mapper = session.getMapper(GoodsMapping.class);
        mapper.insertGood(good);
        session.commit();
    }
}
