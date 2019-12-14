package club.leyvan.mybatis.sqlsession;

import club.leyvan.mybatis.cfg.Configuration;
import club.leyvan.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import club.leyvan.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybaits.sqlsession
 * @version: 1.0
 */
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(InputStream in) {
        Configuration config = XMLConfigBuilder.loadConfiguration(in);
        return new DefaultSqlSessionFactory(config);
    }
}
