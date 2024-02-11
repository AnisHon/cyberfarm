package team.light.cyberfarm.tool;

import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.User;

import java.util.HashMap;
import java.util.HashSet;

public class JavaBeanUtilTest {

    @Test
    public void mappingObjectTest() {

        HashSet<String> ignore = new HashSet<>();
        ignore.add("setGender");
    }

}
