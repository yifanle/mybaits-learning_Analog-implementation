package club.leyvan.test;

import club.leyvan.dao.IUserDao;
import club.leyvan.domain.User;
import club.leyvan.mybatis.io.Resources;
import club.leyvan.mybatis.sqlsession.SqlSession;
import club.leyvan.mybatis.sqlsession.SqlSessionFactory;
import club.leyvan.mybatis.sqlsession.SqlSessionFactoryBuilder;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.test
 * @version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        try{
            //加载配置文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //构造者模式创建工厂建造者
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            //工厂模式创建session
            SqlSession session = factory.openSession();
            //通过动态代理创建代理对象
            IUserDao userDao = session.getMapper(IUserDao.class);
            List<User> list = userDao.findAll();
            for (User user: list) {
                System.out.println(user);
            }
            session.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
