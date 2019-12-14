package club.leyvan.mybatis.utils;

import club.leyvan.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/14
 * @Description: club.leyvan.mybatis.utils
 * @version: 1.0
 */
public class DataSourceUtil {

    private static Connection connection = null;

    public static Connection getConnection(Configuration config){

        try{
            Class.forName(config.getDriver());
            connection = DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
