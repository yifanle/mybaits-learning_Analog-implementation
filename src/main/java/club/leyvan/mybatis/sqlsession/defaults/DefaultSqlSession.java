package club.leyvan.mybatis.sqlsession.defaults;

import club.leyvan.mybatis.cfg.Configuration;
import club.leyvan.mybatis.sqlsession.SqlSession;
import club.leyvan.mybatis.sqlsession.proxy.MapperProxy;
import club.leyvan.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/14
 * @Description: club.leyvan.mybatis.sqlsession.defaults
 * @version: 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration config;
    private Connection connection;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
        connection = DataSourceUtil.getConnection(config);
    }

    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},new MapperProxy(config.getMappers(),connection));
    }

    public void close() {
        if(connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
