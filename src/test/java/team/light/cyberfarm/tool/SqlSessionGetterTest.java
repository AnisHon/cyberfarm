package team.light.cyberfarm.tool;

import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.User;
import team.light.cyberfarm.mapping.UserMapping;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log()
public class SqlSessionGetterTest {

    @Test
    public void testGetter() throws IOException {


        System.out.println(File.separatorChar);
    }



}
