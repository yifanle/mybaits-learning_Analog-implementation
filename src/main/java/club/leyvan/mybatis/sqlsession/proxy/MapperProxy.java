package club.leyvan.mybatis.sqlsession.proxy;

import club.leyvan.mybatis.cfg.Mapper;
import club.leyvan.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/14
 * @Description: club.leyvan.mybatis.sqlsession.proxy
 * @version: 1.0
 */
public class MapperProxy implements InvocationHandler {

    private Map<String,Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String,Mapper> mappers,Connection connection){
        this.mappers = mappers;
        this.connection = connection;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取IUserDao接口调用的方法名
        String methodName = method.getName();
        //获得该方法声明在的类名
        String className = method.getDeclaringClass().getName();
        //拼成key
        String key = className+"."+methodName;
        //根据key从Mappers中找到mapper
        Mapper mapper = mappers.get(key);
        //判断是否存在
        if(mapper==null){
            throw new IllegalArgumentException("未找到方法"+key);
        }
        //交给Executor执行查询
        //Executor可以又update等方法，可根据从xml中读取的标签判断
        return new Executor().selectList(mapper,connection);
    }
}
