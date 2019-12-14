package club.leyvan.mybatis.sqlsession;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybaits.sqlsession
 * @version: 1.0
 */
public interface SqlSession {
    <T> T getMapper(Class<T> daoInterfaceClass);

    void close();
}
