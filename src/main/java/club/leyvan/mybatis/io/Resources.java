package club.leyvan.mybatis.io;

import java.io.InputStream;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybaits.io
 * @version: 1.0
 */
public class Resources {
    /**
     * 加载文件
     * @param s
     * @return
     */
    public static InputStream getResourceAsStream(String s) {
        return Resources.class.getClassLoader().getResourceAsStream(s);
    }
}
