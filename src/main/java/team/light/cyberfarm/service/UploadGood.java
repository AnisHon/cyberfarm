package team.light.cyberfarm.service;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.Good;
import team.light.cyberfarm.tool.SqlSessionGetter;

public interface UploadGood {


    void upload(Good good);

}
