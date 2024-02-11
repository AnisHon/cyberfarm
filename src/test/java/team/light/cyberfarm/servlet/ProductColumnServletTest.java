package team.light.cyberfarm.servlet;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.ProductColumn;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductColumnServletTest {

    @Test
    public void jsonToStringTest() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("categoryCount", 1);
        ProductColumn productColumn = new ProductColumn();
        productColumn.setId(1);
        productColumn.setName("A");
        productColumn.setCoverUrl("1.jpj");
        productColumn.setPrice(123);
        map.put("product", new ArrayList<ProductColumn>(){{add(productColumn);}});

        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);
    }

}
