package club.leyvan.mybatis.utils;

import club.leyvan.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybatis.cfg
 * @version: 1.0
 */
public class Executor {

    /*public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.取出mapper中的数据
            String queryString = mapper.getQueryString(); //select * from user
            String resultType = mapper.getResultType(); //club.leyvan.domain.User
            Class domainClass = Class.forName(resultType);
            //2.获取PreparedStatement对象
            pstm = conn.prepareStatement(queryString);
            //3.执行SQL语句，获取结果集
            rs = pstm.executeQuery();
            //4.封装结果集
            List<E> list = new ArrayList<E>();//定义返回值
            while(rs.next()) {
                //实例化要封装的实体类对象
                E obj = (E)domainClass.newInstance();

                //取出结果集的元信息：ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出总列数
                int columnCount = rsmd.getColumnCount();
                //遍历总列数
                for (int i = 1; i <= columnCount; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//要求：实体类的属性和数据库表的列名保持一种
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    writeMethod.invoke(obj,columnValue);
                }
                //把赋好值的对象加入到集合中
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm,rs);
        }
    }*/

    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try{
            //从mapper中取得xml中得映射信息
            //1.查询语句
            String queryString = mapper.getQueryString();
            //2.查询后返回的实体类型
            String resultType = mapper.getResultType();
            //根据resultType（Stirng）获得Class对象用于后面得反射创建
            Class domainClass = Class.forName(resultType);
            //查询数据库
            psmt = conn.prepareStatement(queryString);
            rs = psmt.executeQuery();
            //封装结果用的结果集
            List<E> list = new ArrayList<E>();
            //封装读取得数据
            while(rs.next()){
                //实体化一个实例用于封装一条数据
                E domain = (E)domainClass.newInstance();
                //rs为数据库中的横向数据集,要获得纵向得每一个数据元得集
                ResultSetMetaData rsmd = rs.getMetaData();
                //获取每一条数据得列数
                int columnNum = rsmd.getColumnCount();
                //遍历每列数据得列名和值，列名用于给实体类赋值 列名序号从1开始
                for(int i=1;i<=columnNum;i++){
                    //获得列名
                    String columnName = rsmd.getColumnName(i);
                    //根据列名获得每列得值
                    Object columnValue = rs.getObject(columnName);
                    //PropertyDescriptor:java内省操作类 要求：columnName要和实体类得属性名相同
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
                    //通过内省操作获得字段得写方法
                    Method writeMethod = pd.getWriteMethod();
                    //调用写方法：第一个参数：哪个对象得方法 第二个参数：该对象写方法的参数
                    writeMethod.invoke(domain,columnValue);
                }
                //实体封装完成，放入list
                list.add(domain);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            release(psmt,rs);
        }
    }


    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
