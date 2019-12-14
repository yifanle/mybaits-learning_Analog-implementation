package club.leyvan.mybatis.sqlsession.defaults;

import club.leyvan.mybatis.cfg.Configuration;
import club.leyvan.mybatis.sqlsession.SqlSession;
import club.leyvan.mybatis.sqlsession.SqlSessionFactory;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybaits.sqlsession.defaults
 * @version: 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration config;

    public DefaultSqlSessionFactory(Configuration config){
        this.config = config;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(config);
    }
}
